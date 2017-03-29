package org.social.heartbeat.bean;

import java.util.ArrayList;

public class UserAddress {

	private ArrayList<AddressComponent> userAddressComponent;
	private String formatted_address;

	public ArrayList<AddressComponent> getUserAddressComponent() {
		return userAddressComponent;
	}

	public void setUserAddressComponent(ArrayList<AddressComponent> userAddressComponent) {
		this.userAddressComponent = userAddressComponent;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	
	
}
