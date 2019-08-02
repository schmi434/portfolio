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

@Component
public class JdbcParkDao implements ParkDao {
	private JdbcTemplate template;

	@Autowired
	public JdbcParkDao(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}
	
//	private static final String SELECT_PARK_SQL = "SELECT park.parkCode, park.parkname, park.state, park.acreage, park.elevationinfeet, park.milesoftrail, park.numberofcampsites, park.climate, park.yearfounded, park.annualvisitorcount, park.inspirationalquote, park.parkdescription, park.entryfee, park.numberofanimalspecies FROM park";
	private static final String SELECT_PARK_SQL = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park";
	@Override
	    public Park getById(String code) {
	        SqlRowSet result = template.queryForRowSet(SELECT_PARK_SQL + " WHERE parkcode = ?", code);
	        if (result.next()) {
	            return mapRowToPark(result);
	        }
	        return null;
	    }

	@Override
	public List<Park> getAllParks() { //this might take too much sql stuff
		List<Park> allParks = new ArrayList<Park>();
		String parkSQL = "Select * from park;";
		SqlRowSet results = template.queryForRowSet(parkSQL);
		while (results.next()) {
			allParks.add(mapRowToPark(results));
		}
		return allParks;
	}

	@Override
	public Park getParkDetails(String code) {
		//List<Park> allParks = new ArrayList<Park>();
		Park park = new Park();
		String parkSQL = "Select * from park where parkcode = '?';";
		SqlRowSet results = template.queryForRowSet(parkSQL, code.toUpperCase());
		while (results.next()) {
			park = (mapRowToPark(results));
		}
		return park;
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park parkRow = new Park();
		parkRow.setParkCode(results.getString("parkcode"));
		parkRow.setParkName(results.getString("parkname"));
		parkRow.setState(results.getString("state"));
		parkRow.setAcreage(results.getInt("acreage"));
		parkRow.setElevationInFeet(results.getInt("elevationinfeet"));
		parkRow.setMilesOfTrail(results.getDouble("milesoftrail"));
		parkRow.setNumberOfCampsites(results.getInt("numberofcampsites"));
		parkRow.setClimate(results.getString("climate"));
		parkRow.setYearFounded(results.getInt("yearfounded"));
		parkRow.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		parkRow.setInspirationalQuote(results.getString("inspirationalquote"));
		parkRow.setQuoteSource(results.getString("inspirationalquotesource"));
		parkRow.setParkDescription(results.getString("parkdescription"));
		parkRow.setEntryFee(results.getInt("entryfee"));
		parkRow.setNumSpecies(results.getInt("numberofanimalspecies"));
		return parkRow;
		}

}
