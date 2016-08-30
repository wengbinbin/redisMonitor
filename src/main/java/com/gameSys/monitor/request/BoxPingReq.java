package com.gameSys.monitor.request;

import java.io.Serializable;

public class BoxPingReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -788449501227551563L;
	private int uid;
	private String machineCode;
	private int deviceId;
	private String lastPingTime;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getLastPingTime() {
		return lastPingTime;
	}
	public void setLastPingTime(String lastPingTime) {
		this.lastPingTime = lastPingTime;
	}
}
