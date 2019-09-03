/**
 * 
 */
package com.techelevator.model;


import java.text.DateFormat;
import java.time.LocalDate;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;




@Component
public class JdbcUserProfileDAO implements UserProfileDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcUserProfileDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return;
     
    }
	
	
	public void saveProfile(String username, String displayName, double startingWeight, double currentWeight,
			double goalWeight, int age, double height, String avatarFileName, HttpSession session) {
		
		User x = (User) session.getAttribute("currentUser"); // This gets us the username for the session 
		String userX = x.getUserName();
		
		
			long newId = jdbcTemplate.update(
	                "INSERT INTO user_profile(username, displayname, startingweight, goalweight, age, height) VALUES (?, ?, ?, ?, ?, ?)", 
	                userX, displayName, startingWeight, goalWeight, age, height);				
			UserProfile profile = new UserProfile();
			    
					
				profile.setDisplayName(displayName);
				profile.setStartingWeight(startingWeight);
				profile.setGoalWeight(goalWeight);
				profile.setAge(age);
				profile.setHeight(height);
		
	}
	
	

	public void updateProfile(double currentWeight, double goalWeight, double height, String avatarFileName) {
	 UserProfile filler = new UserProfile();
	 
	 
	}


	

	

	

	

	



	

}
