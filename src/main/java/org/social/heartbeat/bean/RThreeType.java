package org.social.heartbeat.bean;

public class RThreeType {

	private int id;
	private String name;
	private String description;
	private RThreeCategory rThreeCategory;
	private int is_admin;
	private User userAuthor;
	private UserStatus userStatus;
	private String remarks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RThreeCategory getrThreeCategory() {
		return rThreeCategory;
	}
	public void setrThreeCategory(RThreeCategory rThreeCategory) {
		this.rThreeCategory = rThreeCategory;
	}
	public int getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}
	public User getUserAuthor() {
		return userAuthor;
	}
	public void setUserAuthor(User userAuthor) {
		this.userAuthor = userAuthor;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
