package com.techelevator;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import com.techelevator.campground.model.jdbc.JDBCSiteDAO;
import com.techelevator.campground.view.Menu;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class CampgroundCLI {
  private ParkDAO parkDAO;
  private Menu parkMenu;
  private CampgroundDAO campgroundDAO;
  private SiteDAO siteDAO;
  private ReservationDAO reservationDAO;

  public CampgroundCLI(DataSource datasource) {
    parkDAO = new JDBCParkDAO(datasource);
    parkMenu = new Menu(System.in, System.out);
    campgroundDAO = new JDBCCampgroundDAO(datasource);
    siteDAO = new JDBCSiteDAO(datasource);
    reservationDAO = new JDBCReservationDAO(datasource);
  }

  public static void main(String[] args) {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
    dataSource.setUsername("postgres");
    dataSource.setPassword("postgres1");

    CampgroundCLI application = new CampgroundCLI(dataSource);
    application.run();
  }

  public void run() {
    boolean keepRunning = true;

    while (keepRunning) {
      parkMenu.printOneLine("Select a Park for Further Details");
      List<Object> allParks = parkDAO.getAllParks();

      allParks.add("quit");

      Object parkChoice = parkMenu.getChoiceFromOptions(allParks);
      Park chosenPark = null;

      if (parkChoice.equals("quit")) {
        keepRunning = false;
      } else {
        chosenPark = (Park) parkChoice;
        parkMenu.printMultipleLines(chosenPark.parkInfo());
        parkInfoMenu(chosenPark);
      }
    }
  }

  private void parkInfoMenu(Park park) {
    while (true) {
      parkMenu.printOneLine("Select a Command");
      List<Object> menuOptions = new ArrayList<Object>();
      menuOptions.add("View Campgrounds");
      menuOptions.add("Search for Reservation");
      menuOptions.add("Return to Previous Screen");

      String menuChoice = (String) parkMenu.getChoiceFromOptions(menuOptions);

      if (menuChoice.equals("View Campgrounds")) {
        parkMenu.printOneLine(park.getName() + " National Park Campgrounds\n");

        showCampGrounds(park);

        parkMenu.printOneLine(" ");
        parkMenu.printOneLine("Select a Command");
        List<Object> subMenuOptions = new ArrayList<Object>();
        subMenuOptions.add("Search for Available Reservation");
        subMenuOptions.add("Return to Previous Screen");
        String subMenuChoice = (String) parkMenu.getChoiceFromOptions(
          subMenuOptions
        );
        if (subMenuChoice.equals("Search for Available Reservation")) {
          boolean reservationMade = makeReservation(park);
          if (reservationMade) {
            return;
          }
        }
      } else if (menuChoice.equals("Search for Reservation")) {
        boolean reservationMade = makeReservation(park);
        if (reservationMade) {
          return;
        }
      } else if (menuChoice.equals("Return to Previous Screen")) {
        return;
      }
    }
  }

  private boolean makeReservation(Park park) {
    while (true) {
      parkMenu.printOneLine("Search for Campground Reservation\n");
      List<Object> campgrounds = showCampGrounds(park);
      List<Long> allowableChoices = new ArrayList<Long>();

      for (Object camp : campgrounds) {
        long id = ((Campground) camp).getCampground_id();
        allowableChoices.add(id);
      }
      campgrounds.add("cancel");
      allowableChoices.add((long) 0);

      Object campChoice = null;
      while (campChoice == null) {
        parkMenu.printOneLine(" ");
        parkMenu.printOneLine("Which campground (enter 0 to cancel)? ");
        campChoice =
          parkMenu.getUserChoiceFromAllowables(campgrounds, allowableChoices);
      }
      Campground chosenCampground = null;
      if (campChoice.equals("cancel")) {
        return false;
      } else {
        chosenCampground = (Campground) campChoice;
      }

      boolean unavailableDates = true;
      LocalDate arrivalDate = null;
      LocalDate departureDate = null;

      while (unavailableDates) {
        arrivalDate =
          parkMenu.getDateInputFromUser(
            "What is the arrival date? (MM/DD/YYYY) "
          );

        if (arrivalDate.compareTo(LocalDate.now()) < 0) {
          parkMenu.printOneLine("Arrival date must be today or later.\n");
          continue;
        }

        departureDate =
          parkMenu.getDateInputFromUser(
            "What is the departure date? (MM/DD/YYYY) "
          );

        if (departureDate.compareTo(arrivalDate) <= 0) {
          parkMenu.printOneLine("Departure date must be after arrival date.\n");
          continue;
        }

        unavailableDates = false;
      }

      boolean reservationMade = pickSite(
        chosenCampground,
        arrivalDate,
        departureDate
      );
      if (reservationMade == true) {
        return reservationMade;
      }
    }
  }

  private boolean pickSite(
    Campground camp,
    LocalDate arrive,
    LocalDate depart
  ) {
    parkMenu.printOneLine("Results Matching Your Search Criteria\n");
    String str = String.format(
      "%-12s%-12s%-13s%-14s%-10s%-8s",
      "Site No.",
      "Max Occup.",
      "Accessible?",
      "Max RV Length",
      "Utility",
      "Cost"
    );
    parkMenu.printOneLine(str);
    List<Object> availableSites = siteDAO.getAvailableSites(
      camp,
      arrive,
      depart
    );

    if (availableSites.size() == 0) {
      parkMenu.printOneLine("There are no available sites.\n");
      parkMenu.printOneLine(
        "Please pick a new campground or enter alternate dates."
      );
      return false;
    }

    long days = arrive.until(depart, ChronoUnit.DAYS);

    for (Object site : availableSites) {
      String siteInfo = ((Site) site).siteInfo(camp, days);
      parkMenu.printOneLine(siteInfo);
    }

    List<Long> allowableChoices = new ArrayList<Long>();

    for (Object site : availableSites) {
      long id = ((Site) site).getSite_number();
      allowableChoices.add(id);
    }
    allowableChoices.add((long) 0);

    availableSites.add("cancel");
    Object siteChoice = null;

    while (siteChoice == null) {
      parkMenu.printOneLine("");
      parkMenu.printOneLine(
        "Which site should be reserved (enter 0 to cancel)? "
      );
      siteChoice =
        parkMenu.getUserChoiceFromAllowables(availableSites, allowableChoices);
    }
    Site chosenSite = null;
    if (siteChoice.equals("cancel")) {
      return false;
    } else {
      chosenSite = (Site) siteChoice;
    }

    String reservationName = parkMenu.getStringFromUser(
      "What name should " + "the reservation be made under? "
    );

    long confirmation = reservationDAO.createReservation(
      chosenSite,
      reservationName,
      arrive,
      depart
    );
    parkMenu.printOneLine(
      "The reservation has been made and the confirmation id is: " +
        confirmation
    );
    parkMenu.printOneLine("\n");
    return true;
  }

  private List<Object> showCampGrounds(Park park) {
    String str = String.format(
      "%-5s%-35s%-17s%-17s%-11s",
      " ",
      "Name",
      "Open",
      "Close",
      "Daily Fee"
    );
    parkMenu.printOneLine(str);

    List<Object> campgrounds = campgroundDAO.getAllCampgrounds(park);

    for (Object camp : campgrounds) {
      String campInfo = ((Campground) camp).campgroundInfo();
      parkMenu.printOneLine(campInfo);
    }
    return campgrounds;
  }
}
