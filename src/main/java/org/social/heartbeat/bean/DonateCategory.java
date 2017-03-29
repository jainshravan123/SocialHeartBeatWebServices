package org.social.heartbeat.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DonateCategory
{

	private int id;
	private String category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
