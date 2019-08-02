package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {

   List<Object> getAvailableSites(Campground camp, LocalDate arrive, LocalDate depart);
}
