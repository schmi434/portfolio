package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCParkDAO implements ParkDAO {
  private JdbcTemplate template;

  public JDBCParkDAO(DataSource datasource) {
    template = new JdbcTemplate(datasource);
  }

  @Override
  public List<Object> getAllParks() {
    List<Object> parks = new ArrayList<Object>();

    String sql = "SELECT * FROM park ORDER BY name ASC";
    SqlRowSet results = template.queryForRowSet(sql);

    while (results.next()) {
      Park park = mapRowToPark(results);
      parks.add(park);
    }
    return parks;
  }

  private Park mapRowToPark(SqlRowSet results) {
    Park park = new Park();
    park.setPark_id(results.getLong("park_id"));
    park.setName(results.getString("name"));
    park.setLocation(results.getString("location"));
    park.setEstablish_date(results.getDate("establish_date").toLocalDate());
    park.setArea(results.getInt("area"));
    park.setVisitors(results.getInt("visitors"));
    park.setDescription(results.getString("description"));

    return park;
  }
}
