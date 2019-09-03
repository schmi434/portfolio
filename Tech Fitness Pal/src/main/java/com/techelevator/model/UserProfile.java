package com.techelevator.model;

import java.text.DateFormat;
import java.util.Date;

public class UserProfile {
	
	private String userName;
	
	private String displayName;
	 
	private double startingWeight;
	
	private double currentWeight;
	
	private double goalWeight;
	
	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private double height;
	
	private String avatarFileName;
	
	private double targetCalories;

	public String getUserName() {
		return userName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public double getStartingWeight() {
		return startingWeight;
	}

	public double getCurrentWeight() {
		return currentWeight;
	}

	public double getGoalWeight() {
		return goalWeight;
	}



	public double getHeight() {
		return height;
	}

	public String getAvatarFileName() {
		return avatarFileName;
	}

	public double getTargetCalories() {
		return targetCalories;
	}

	public void setUserName(Object object) {
		this.userName = (String) object;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setStartingWeight(double startingWeight) {
		this.startingWeight = startingWeight;
	}

	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}

	public void setGoalWeight(double goalWeight) {
		this.goalWeight = goalWeight;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}

	public void setTargetCalories(double targetCalories) {
		this.targetCalories = targetCalories;
	}
	
	

}
