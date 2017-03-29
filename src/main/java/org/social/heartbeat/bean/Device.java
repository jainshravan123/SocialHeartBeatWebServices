package org.social.heartbeat.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Device 
{

	private String deviceType;
	private String deviceMacId;
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceMacId() {
		return deviceMacId;
	}
	public void setDeviceMacId(String deviceMacId) {
		this.deviceMacId = deviceMacId;
	}
	
	
	
	
}
