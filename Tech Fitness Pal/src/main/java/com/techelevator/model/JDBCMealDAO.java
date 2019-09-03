package com.techelevator.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;




@Component
public class JDBCMealDAO implements mealDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JDBCMealDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return;
    }
	@Override
	public void saveMeal(String mealName, String foodEaten, int calories, Date date, String userName) {
	 
		
		
		jdbcTemplate.update("INSERT INTO meals(mealname, foodeaten, calories, date, username) VALUES (?, ?, ?, ?, ?)",
				mealName, foodEaten, calories, date, userName);
		
	}
	
	
	public ArrayList<String> showDailyCals(HttpSession session) {
		
		User x = (User) session.getAttribute("currentUser"); // This gets us the username for the session 
		String userX = x.getUserName();
		 
		SqlRowSet srs = jdbcTemplate.queryForRowSet("SELECT date, SUM(calories) as dailycals " + 
				"FROM meals WHERE date < " + "'" + LocalDate.now().toString() + "'" + " and username = " + "'" + userX + "'" +
				" GROUP BY username, date;");
		ArrayList<String> dailyCals = new ArrayList<String>(); 
		int rowCount = 0;
		 while (srs.next()) {
		      dailyCals.add(srs.getString("date") + " - " + srs.getString("dailycals"));
		      rowCount++;
		    }
		 return dailyCals;
		
		
		
	}

	
	
	
	
	 
	 
	}
