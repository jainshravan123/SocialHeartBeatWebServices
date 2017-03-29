package org.social.heartbeat.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CsrInitCategory 
{
	
	private int id;
	private String cat;
	private String cat_icon;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getCat_icon() {
		return cat_icon;
	}
	public void setCat_icon(String cat_icon) {
		this.cat_icon = cat_icon;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
