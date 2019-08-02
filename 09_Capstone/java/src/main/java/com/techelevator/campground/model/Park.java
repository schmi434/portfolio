package com.techelevator.campground.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Park {

  private long park_id;
  private String name;
  private String location;
  private LocalDate establish_date;
  private int area;
  private int visitors;
  private String description;

  public long getPark_id() {
    return park_id;
  }

  public void setPark_id(long park_id) {
    this.park_id = park_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public LocalDate getEstablish_date() {
    return establish_date;
  }

  public void setEstablish_date(LocalDate establish_date) {
    this.establish_date = establish_date;
  }

  public int getArea() {
    return area;
  }

  public void setArea(int area) {
    this.area = area;
  }

  public int getVisitors() {
    return visitors;
  }

  public void setVisitors(int visitors) {
    this.visitors = visitors;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> parkInfo() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String formattedDate = establish_date.format(formatter);

    List<String> parkInfoToDisplay = new ArrayList<String>();
    parkInfoToDisplay.add(name + " National Park");
    String str = String.format("%-20s%s", "Location:", location);
    parkInfoToDisplay.add(str);
    str = String.format("%-20s%s", "Established:", formattedDate);
    parkInfoToDisplay.add(str);
    str = String.format("%-20s%,d sq km", "Area:", area);
    parkInfoToDisplay.add(str);
    str = String.format("%-20s%,d", "Annual Visitors:", visitors);
    parkInfoToDisplay.add(str);
    parkInfoToDisplay.add("");

    String[] splitDescription = description.split(" ");
    int counter = 0;
    str = "";
    for (int i = 0; i < splitDescription.length; i++) {
      str += splitDescription[i] + " ";
      counter++;
      if (counter == 15) {
        parkInfoToDisplay.add(str);
        str = "";
        counter = 0;
      }
    }
    if (str.length() > 0) {
      parkInfoToDisplay.add(str);
    }

    return parkInfoToDisplay;
  }

  @Override
  public String toString() {
    return name;
  }
}
