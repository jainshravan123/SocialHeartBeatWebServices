package org.social.heartbeat.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CsrInitCatData 
{

	private int id;
	private CsrInitCategory csrInitCategory;
	private String cat_data_heading;
	private String cat_data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CsrInitCategory getCsrInitCategory() {
		return csrInitCategory;
	}
	public void setCsrInitCategory(CsrInitCategory csrInitCategory) {
		this.csrInitCategory = csrInitCategory;
	}
	public String getCat_data_heading() {
		return cat_data_heading;
	}
	public void setCat_data_heading(String cat_data_heading) {
		this.cat_data_heading = cat_data_heading;
	}
	public String getCat_data() {
		return cat_data;
	}
	public void setCat_data(String cat_data) {
		this.cat_data = cat_data;
	}
	
	
	
	
	
	
}
