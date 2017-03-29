package org.social.heartbeat.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Module 
{

	private int id;
	private String module_name;
	private String module_icon;
	private int status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getModule_icon() {
		return module_icon;
	}
	public void setModule_icon(String module_icon) {
		this.module_icon = module_icon;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

	
	
}
