package com.gameSys.monitor.controller;

import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gameSys.monitor.pojo.OnlineMachineInfo;
import com.gameSys.monitor.request.BoxPingReq;
import com.gameSys.monitor.request.QueryBoxOnlineReq;
import com.gameSys.monitor.response.QueryAllBoxOnlineResp;
import com.gameSys.monitor.response.RespResult;
import com.gameSys.monitor.response.ResponseUtil;
import com.gameSys.monitor.service.MonitorService;
import com.google.common.base.Optional;

@Controller
@RequestMapping("/api/2/monitor")
public class MonitorController {
	private static Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Autowired
	private MonitorService monitorService;
	
	@RequestMapping(value="/boxPingServer", method = RequestMethod.POST)
	@ResponseBody
	public RespResult boxPingServer(@RequestBody BoxPingReq req) {
		if(req==null) return ResponseUtil.createLackParamResult("boxPingServer : connection success but redis unknown error.");
		
		OnlineMachineInfo onlineMachineInfo = new OnlineMachineInfo();
		onlineMachineInfo.setUid(req.getUid());
		onlineMachineInfo.setDeviceId(req.getDeviceId());
		onlineMachineInfo.setMachineCode(req.getMachineCode());
		onlineMachineInfo.setLastPingTime(req.getLastPingTime());
		
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
	public RespResult queryBoxOnline(@RequestBody QueryBoxOnlineReq req){
		if(req==null || req.getMachineCode()==null) return ResponseUtil.createLackParamResult("queryBoxOnline : lack param");
		Optional<OnlineMachineInfo> onlineMachineInfoOptional;
		try {
			onlineMachineInfoOptional = monitorService.get(req.getMachineCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtil.createUnknownResult("queryBoxOnline : redis unknown error.");
		}
        
		if (onlineMachineInfoOptional.isPresent()) {
            return ResponseUtil.createSuccessResponseResult("queryBoxOnline : the box online.");
        } else {
            return ResponseUtil.createNotFoundResult("queryBoxOnline : the box not Online.");
        }
	}
	
	@RequestMapping(value ="/queryAllBoxOnline", method = RequestMethod.POST)
	@ResponseBody
	public QueryAllBoxOnlineResp queryAllBoxOnline(){
		QueryAllBoxOnlineResp response = new QueryAllBoxOnlineResp();
		Optional<List<OnlineMachineInfo> > onlineMachineInfosSetOptional=null;
		try {
			onlineMachineInfosSetOptional = monitorService.getAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(ResponseUtil.UNKNOWN);
			response.setResultMsg("queryAllBoxOnline : redis unknown error.");
			return response;
		}
        
		if (onlineMachineInfosSetOptional.isPresent()) {
			response.setOnlineMachineInfosList(onlineMachineInfosSetOptional.get());
			response.setResultCode(ResponseUtil.SUCCESS);
			response.setResultMsg("queryAllBoxOnline : success.");
            
        } else {
			response.setResultCode(ResponseUtil.NOT_FOUND);
			response.setResultMsg("queryAllBoxOnline not_found.");
        }
		return response;
	}
	
}
