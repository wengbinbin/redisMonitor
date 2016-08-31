package com.gameSys.monitor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gameSys.base.util.IPTool;
import com.gameSys.base.util.MD5Util;
import com.gameSys.base.util.ResponseUtil;
import com.gameSys.monitor.pojo.OnlineMachineInfo;
import com.gameSys.monitor.request.BoxPingReq;
import com.gameSys.monitor.request.QueryAllBoxOnlineReq;
import com.gameSys.monitor.request.QueryBoxOnlineReq;
import com.gameSys.monitor.response.QueryAllBoxOnlineResp;
import com.gameSys.monitor.response.QueryBoxOnlineResp;
import com.gameSys.monitor.response.RespResult;
import com.gameSys.monitor.service.MonitorService;
import com.google.common.base.Optional;

@Controller
@RequestMapping("/api/${verId}/monitor")
public class MonitorController {
	private static Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Autowired
	private MonitorService monitorService;
	
	@Value("${MD5.ATTACH}")
	private String MD5_ATTACH; 
	
	@RequestMapping(value="/boxPingServer", method = RequestMethod.POST)
	@ResponseBody
	public RespResult boxPingServer(@RequestBody BoxPingReq req,HttpServletRequest httpServletRequest) {
		if(req==null||req.getMachineCode()==null) return ResponseUtil.createLackParamResult("boxPingServer : connection success but redis lack machineCode.");
		if(req.getSign()==null) return ResponseUtil.createLackParamResult("sign为空");
		
		String ip = IPTool.getIpAddr(httpServletRequest);
		String origin = ip+req.getMachineCode().toString()+MD5_ATTACH;
		logger.info("boxPingServer:"+MD5Util.MD5Encode(origin, "utf-8"));
		if(!req.getSign().equals(MD5Util.MD5Encode(origin, "utf-8")))
			return ResponseUtil.createSignErrorResult();
		
		OnlineMachineInfo onlineMachineInfo = new OnlineMachineInfo();
		onlineMachineInfo.setUid(req.getUid());
		onlineMachineInfo.setDeviceId(req.getDeviceId());
		onlineMachineInfo.setMachineCode(req.getMachineCode());
		onlineMachineInfo.setLastPingTime(req.getLastPingTime());
		onlineMachineInfo.setVersion(req.getVersion());
		
		try {
			monitorService.add(onlineMachineInfo.getMachineCode(), onlineMachineInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtil.createSuccessResponseResult("boxPingServer : connection success but redis unknown error."); 
		}
		return ResponseUtil.createSuccessResponseResult("boxPingServer : connection success.");
		

	}
	
	@RequestMapping(value ="/queryBoxOnline", method = RequestMethod.POST)
	@ResponseBody
	public RespResult queryBoxOnline(@RequestBody QueryBoxOnlineReq req,HttpServletRequest httpServletRequest){
		if(req==null || req.getMachineCode()==null) return ResponseUtil.createLackParamResult("queryBoxOnline : lack param");
		if(req.getSign()==null) return ResponseUtil.createLackParamResult("sign为空");
		
		String ip = IPTool.getIpAddr(httpServletRequest);
		String origin = ip+req.getMachineCode().toString()+MD5_ATTACH;
		logger.info("queryBoxOnline:"+MD5Util.MD5Encode(origin, "utf-8"));
		if(!req.getSign().equals(MD5Util.MD5Encode(origin, "utf-8")))
			return ResponseUtil.createSignErrorResult();
		
		Optional<OnlineMachineInfo> onlineMachineInfoOptional;
		try {
			onlineMachineInfoOptional = monitorService.get(req.getMachineCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtil.createUnknownResult("queryBoxOnline : redis unknown error.");
		}
        
		if (onlineMachineInfoOptional.isPresent()) {
			QueryBoxOnlineResp resp = new QueryBoxOnlineResp();
			resp.setOnlineMachineInfo(onlineMachineInfoOptional.get());
			resp.setResultCode(ResponseUtil.SUCCESS);
			resp.setResultMsg("queryBoxOnline : the box online.");
            return resp;
        } else {
            return ResponseUtil.createNotFoundResult("queryBoxOnline : the box not Online.");
        }
	}
	
	@RequestMapping(value ="/queryAllBoxOnline", method = RequestMethod.POST)
	@ResponseBody
	public RespResult queryAllBoxOnline(@RequestBody QueryAllBoxOnlineReq req,HttpServletRequest httpServletRequest){
		
		if(req.getSign()==null) return ResponseUtil.createLackParamResult("sign为空");
		String ip = IPTool.getIpAddr(httpServletRequest);
		String origin = ip +MD5_ATTACH;
		logger.info("queryAllBoxOnline:"+MD5Util.MD5Encode(origin, "utf-8"));
		if(!req.getSign().equals(MD5Util.MD5Encode(origin, "utf-8"))){
			return ResponseUtil.createSignErrorResult();
		}
			
		Optional<List<OnlineMachineInfo> > onlineMachineInfosSetOptional=null;
		try {
			onlineMachineInfosSetOptional = monitorService.getAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtil.createUnknownResult("queryAllBoxOnline : redis unknown error.");
		}
        
		if (onlineMachineInfosSetOptional.isPresent()) {
			QueryAllBoxOnlineResp response = new QueryAllBoxOnlineResp();
			response.setOnlineMachineInfosList(onlineMachineInfosSetOptional.get());
			response.setResultCode(ResponseUtil.SUCCESS);
			response.setResultMsg("queryAllBoxOnline : success.");
			return response;
            
        } else {
			return ResponseUtil.createNotFoundResult("queryAllBoxOnline not_found.");
        }
		
	}
	
}
