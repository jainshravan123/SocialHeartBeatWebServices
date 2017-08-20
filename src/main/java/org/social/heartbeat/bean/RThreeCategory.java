package org.social.heartbeat.bean;

public class RThreeCategory {

	private int id;
	private String category_name;
	private User author;
	private String creation_date;
	private String updation_date;
	private RThreeUserLocation rtThreeUserLocation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getUpdation_date() {
		return updation_date;
	}
	public void setUpdation_date(String updation_date) {
		this.updation_date = updation_date;
	}
	public RThreeUserLocation getRtThreeUserLocation() {
		return rtThreeUserLocation;
	}
	public void setRtThreeUserLocation(RThreeUserLocation rtThreeUserLocation) {
		this.rtThreeUserLocation = rtThreeUserLocation;
	}
	

}
