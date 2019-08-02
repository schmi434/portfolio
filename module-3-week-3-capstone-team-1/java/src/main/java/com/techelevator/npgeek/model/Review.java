package com.techelevator.npgeek.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


public class Review {

	private int id;
	private String parkCode;
	/*
	 * @NotBlank(message="email is required")
	 * 
	 * @Email(message="Please enter a valid email")
	 */
	private String emailAddress;
	private String state;
	private String activityLevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) { //this needs validation
		if (emailAddress.contains("@") && (emailAddress.contains("."))) {
			this.emailAddress = emailAddress;
		}
		else {
			this.emailAddress = null;
		}
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
}
