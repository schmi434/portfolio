package com.techelevator.campground.model.jdbc;

import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import java.time.LocalDate;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCReservationDAO implements ReservationDAO {

  private JdbcTemplate template;

  public JDBCReservationDAO(DataSource datasource) {
    template = new JdbcTemplate(datasource);
  }

  @Override
  public long createReservation(
      Site site, String reservationName, LocalDate arrive, LocalDate depart) {
    long nextReservationId = getNextReservationId();
    String sqlInsertNewReservation =
        "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date, create_date) VALUES(?, ?, ?, ?, ?, ?)";

    template.update(
        sqlInsertNewReservation,
        nextReservationId,
        site.getSite_id(),
        reservationName,
        arrive,
        depart,
        LocalDate.now());

    return nextReservationId;
  }

  private long getNextReservationId() {

    // line 68 - campground.sql
    SqlRowSet nextIdResult =
        template.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
    if (nextIdResult.next()) {
      return nextIdResult.getLong(1);
    } else {
      throw new RuntimeException("Error while getting an id for the new reservation");
    }
  }
}
