package com.gameSys.monitor.response;

import java.util.List;

import com.gameSys.monitor.pojo.OnlineMachineInfo;


public class QueryAllBoxOnlineResp extends RespResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5950648701143783960L;
	
	private List<OnlineMachineInfo> onlineMachineInfosList;

	public List<OnlineMachineInfo> getOnlineMachineInfosList() {
		return onlineMachineInfosList;
	}

	public void setOnlineMachineInfosList(List<OnlineMachineInfo> onlineMachineInfosList) {
		this.onlineMachineInfosList = onlineMachineInfosList;
	}
	
}
