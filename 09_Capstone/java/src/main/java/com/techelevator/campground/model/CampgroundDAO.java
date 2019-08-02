package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {

  List<Object> getAllCampgrounds(Park park);
}
