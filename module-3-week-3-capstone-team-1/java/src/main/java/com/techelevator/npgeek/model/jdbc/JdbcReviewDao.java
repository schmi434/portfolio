package com.techelevator.npgeek.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDao;
import com.techelevator.npgeek.model.Review;
import com.techelevator.npgeek.model.ReviewDao;

@Component
public class JdbcReviewDao implements ReviewDao {
	private JdbcTemplate template;
	
	@Autowired
	public JdbcReviewDao(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public void saveReview(String parkCode, String emailAddress, String state, String activityLevel) {
		String sqlInsertReview = "Insert into survey_result(parkcode, emailaddress, state, activitylevel) values (?, ?, ?, ?)";
		template.update(sqlInsertReview, parkCode, emailAddress, state, activityLevel);
	}

	@Override
	public List<Park> showFavPark(ParkDao parkDao) {
		List<Park> favParks = new ArrayList<Park>();
		String favSQL = "Select parkcode, count (parkcode) from survey_result group by parkcode order by count (parkcode) desc, parkcode asc"; 
		SqlRowSet results = template.queryForRowSet(favSQL);
		while (results.next()) {
			Park park = new Park();
			park = parkDao.getById(results.getString("parkcode"));
			park.setSurveys(Integer.parseInt(results.getString("count")));
			favParks.add(park);
		}
		return favParks;
	}

	@Override
	public String showActiveLevel() {
		String favSQL = "Select * from survey_result group by activitylevel";
		SqlRowSet results = template.queryForRowSet(favSQL);
		return results.toString();
	}
	
}
