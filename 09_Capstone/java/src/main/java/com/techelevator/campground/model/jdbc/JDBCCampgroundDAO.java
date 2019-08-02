package com.techelevator.campground.model.jdbc;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCampgroundDAO implements CampgroundDAO {
  private JdbcTemplate template;

  public JDBCCampgroundDAO(DataSource datasource) {
    template = new JdbcTemplate(datasource);
  }

  @Override
  public List<Object> getAllCampgrounds(Park park) {
    List<Object> campgrounds = new ArrayList<Object>();

    String sql = "SELECT * FROM campground WHERE park_id = ?";
    SqlRowSet results = template.queryForRowSet(sql, park.getPark_id());

    while (results.next()) {
      Campground campground = mapRowToCampground(results);
      campgrounds.add(campground);
    }

    return campgrounds;
  }

  private Campground mapRowToCampground(SqlRowSet results) {
    Campground campground = new Campground();
    campground.setCampground_id(results.getLong("campground_id"));
    campground.setPark_id(results.getLong("park_id"));
    campground.setName(results.getString("name"));
    campground.setOpen_from_mm(
      Integer.valueOf(results.getString("open_from_mm"))
    );
    campground.setOpen_to_mm(Integer.valueOf(results.getString("open_to_mm")));
    campground.setDaily_fee(results.getDouble("daily_fee"));

    return campground;
  }
}
