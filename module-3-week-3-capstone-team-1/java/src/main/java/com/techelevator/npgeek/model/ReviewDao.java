package com.techelevator.npgeek.model;

import java.util.List;

import com.techelevator.npgeek.model.jdbc.JdbcParkDao;

public interface ReviewDao {
	
	void saveReview(String parkCode, String emailAddress, String state, String activityLevel);
	
	List<Park> showFavPark(ParkDao parkDao);
	
	String showActiveLevel();
}
