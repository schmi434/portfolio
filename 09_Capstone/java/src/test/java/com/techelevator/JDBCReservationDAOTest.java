package com.techelevator;

import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import java.time.LocalDate;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCReservationDAOTest extends DAOIntegrationTest {

  private ReservationDAO reservationDAO;
  private DataSource dataSource = super.getDataSource();
  private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

  @Before
  public void setup() {
    reservationDAO = new JDBCReservationDAO(dataSource);
  }

  @Test
  public void verify_create_reservation() {

    Site site = new Site();
    site.setSite_id(1);

    reservationDAO.createReservation(
        site, "stephen-test", LocalDate.parse("2019-07-07"), LocalDate.parse("2019-07-14"));

    String sql = "SELECT * FROM reservation WHERE name = 'stephen-test'";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

    Assert.assertTrue(results.next());
  }
}
