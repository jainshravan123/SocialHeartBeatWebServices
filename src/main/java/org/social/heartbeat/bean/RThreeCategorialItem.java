package org.social.heartbeat.bean;

public class RThreeCategorialItem {

	private int id;
	private RThreeCategory rThreeCategory;
	private String heading;
	private String description;
	private int likes;
	private int most_useful;
	private String creation_date;
	private String updation_date;
	private String author;
	private String reference;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RThreeCategory getrThreeCategory() {
		return rThreeCategory;
	}
	public void setrThreeCategory(RThreeCategory rThreeCategory) {
		this.rThreeCategory = rThreeCategory;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getMost_useful() {
		return most_useful;
	}
	public void setMost_useful(int most_useful) {
		this.most_useful = most_useful;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
}
