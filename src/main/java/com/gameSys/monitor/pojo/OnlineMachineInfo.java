package com.gameSys.monitor.pojo;

public class OnlineMachineInfo {
	private int uid;
	private String machineCode;
	private int deviceId;
	private String lastPingTime;
	private String version;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
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
