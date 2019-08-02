package com.techelevator.npgeek.model.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Forecast;
import com.techelevator.npgeek.model.ForecastDao;

@Component
public class JdbcForecastDao implements ForecastDao {
	private JdbcTemplate template;

	@Autowired
	public JdbcForecastDao(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public Forecast[] getParkWeather(String code) {
		Forecast[] days  = new Forecast[5];
		String weatherSQL = "Select * from weather where parkcode = ?;";
		SqlRowSet results = template.queryForRowSet(weatherSQL, code.toUpperCase());
		//for (int i = 0; i < days.length; i++) {
		//	days[i] = (mapRowToForecast(results));
		//}
		int i = 0;
		while (results.next()) {
			days[i] = (mapRowToForecast(results));
			i++;
		}
		return days;
	}
	
		private Forecast mapRowToForecast(SqlRowSet results) {
			Forecast forecast = new Forecast();
			forecast.setParkCode(results.getString("parkcode"));
			forecast.setForecastDay(results.getInt("fivedayforecastvalue"));
			forecast.setLowTemp(results.getInt("low"));
			forecast.setHighTemp(results.getInt("high"));
			forecast.setForecast(results.getString("forecast"));
			return forecast;
		}

}
