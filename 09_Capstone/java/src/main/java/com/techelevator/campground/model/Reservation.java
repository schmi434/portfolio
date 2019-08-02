package com.techelevator.campground.model;

import java.time.LocalDate;

public class Reservation {
  private long reservation_id;
  private long site_id;
  private String name;
  private LocalDate from_date;
  private LocalDate to_date;
  private LocalDate create_date;

  public long getReservationId() {
    return reservation_id;
  }

  public void setReservationId(long reservation_id) {
    this.reservation_id = reservation_id;
  }

  public long getSiteId() {
    return site_id;
  }

  public void setSiteId(long site_id) {
    this.site_id = site_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getFromDate() {
    return from_date;
  }

  public void setFromDate(LocalDate from_date) {
    this.from_date = from_date;
  }

  public LocalDate getToDate() {
    return to_date;
  }

  public void setToDate(LocalDate to_date) {
    this.to_date = to_date;
  }

  public LocalDate getCreateDate() {
    return create_date;
  }

  public void setCreateDate(LocalDate create_date) {
    this.create_date = create_date;
  }
}
