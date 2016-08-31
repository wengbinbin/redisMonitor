package com.gameSys.monitor.request;

import java.io.Serializable;

public class QueryBoxOnlineReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2667095386812237113L;
	private String machineCode;
	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	
}
