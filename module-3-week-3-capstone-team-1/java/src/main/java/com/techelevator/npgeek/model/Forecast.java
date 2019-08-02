package com.techelevator.npgeek.model;

public class Forecast {

	private String parkCode;
	private int forecastDay;
	private int lowTemp;
	private int highTemp;
	private String forecast;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getForecastDay() {
		return forecastDay;
	}

	public void setForecastDay(int forecastDay) {
		this.forecastDay = forecastDay;
	}

	public int getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}

	public int getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	//ok we're just doing this in jsp i think
	
	/*
	 * public void tempsToCelsius() { Double lowtemp = (lowTemp - 32.0) * (5.0 /
	 * 9.0); lowTemp = lowtemp.intValue(); Double hightemp = (highTemp - 32.0) *
	 * (5.0 / 9.0); highTemp = hightemp.intValue(); }
	 * 
	 * public void tempsToFarenheit() { lowTemp = (int) ((lowTemp * (9.0 / 5.0)) +
	 * 32.0); highTemp = (int) ((highTemp * (9.0 / 5.0) + 32.0)); }
	 */

	public String getImgUrl() { // so we trim leading/trailing whitespace
		String imgUrl = "";
		if (forecast.trim().length() == 0) // if the length is 0 then its either null or empty and we cant get a url
			return null;
		if (this.forecast.trim().contains(" ")) { // if its atleast 1 character and has a space then we have to find the
													// space plus the next character.
			for (int i = 0; i < forecast.length(); i++) { // we know there will be a character after the space because
															// of trim()
				if (forecast.charAt(i) == ' ') {
					// so we go through the forecast looking for the space
					imgUrl += Character.toUpperCase(forecast.charAt(i + 1)); // when we find it we ignore it, and add
																				// the next character to the string, but
																				// in lower case
					i++; // we then increment the counter by 1) to ignore the character we just added
				} else { // if the character isnt a space we just add it like normal.
					imgUrl += forecast.charAt(i);
				}
			}
			return "img/weather/" + imgUrl + ".png";
		} else {
			imgUrl = "img/weather/" + this.forecast.trim() + ".png"; // if there arent any spaces then we just return it
			return imgUrl;
		}
	}
}
