package com.techelevator.npgeek.model;

import java.util.List;



public interface ParkDao {

	List<Park> getAllParks();
	
	Park getParkDetails(String code);
	
	Park getById(String code);
	
}
