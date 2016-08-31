package com.gameSys.monitor.response;

import com.gameSys.monitor.pojo.OnlineMachineInfo;

public class QueryBoxOnlineResp extends RespResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3359194099261474100L;
	private OnlineMachineInfo onlineMachineInfo;

	public OnlineMachineInfo getOnlineMachineInfo() {
		return onlineMachineInfo;
	}

	public void setOnlineMachineInfo(OnlineMachineInfo onlineMachineInfo) {
		this.onlineMachineInfo = onlineMachineInfo;
	}
	
}
