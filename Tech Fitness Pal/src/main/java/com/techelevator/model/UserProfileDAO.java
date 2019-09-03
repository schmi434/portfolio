package com.techelevator.model;

import java.text.DateFormat;

import javax.servlet.http.HttpSession;

public interface UserProfileDAO {
	
	public void saveProfile(String displayName, String username, double startingWeight, double currentWeight, 
			double goalWeight,  int age, double height, String avatarFileName, HttpSession session);
	
	public void updateProfile(double currentWeight, double goalWeight, double height, 
			String avatarFileName);

	
}
