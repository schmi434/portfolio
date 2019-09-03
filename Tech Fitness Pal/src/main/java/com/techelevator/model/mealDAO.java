package com.techelevator.model;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

public interface mealDAO {
	
	public void saveMeal(String mealname, String foodeaten, int calories, Date date, String userName);
	
	public ArrayList<String> showDailyCals(HttpSession session);

}
