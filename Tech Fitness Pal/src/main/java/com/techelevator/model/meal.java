package com.techelevator.model;

import java.util.Date;

public class meal {
	
	private String mealLabel;
	private String foodeaten;
	private int calories;
	private Date date;
	
	public String getMealLabel() {
		return mealLabel;
	}

	public void setMealLabel(String mealLabel) {
		this.mealLabel = mealLabel;
	}

	public String getFoodeaten() {
		return foodeaten;
	}

	public int getCalories() {
		return calories;
	}

	public Date getDate() {
		return date;
	}

	public void setFoodeaten(String foodeaten) {
		this.foodeaten = foodeaten;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	
	
	

}
