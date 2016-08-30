package com.gameSys.monitor.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)  
public class RespResult implements Serializable {
	private static final long serialVersionUID = 3708840732377549289L;
	@JsonProperty("resultcode")
	private String resultCode;
	@JsonProperty("resultmsg")
	private String resultMsg;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
