package com.techelevator.campground.model;

import java.time.LocalDate;

public interface ReservationDAO {
  public long createReservation(
    Site site,
    String reservationName,
    LocalDate arrive,
    LocalDate depart
  );
}

