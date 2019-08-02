package com.techelevator.campground.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Menu {
  private PrintWriter out;
  private Scanner in;

  public Menu(InputStream input, OutputStream output) {
    this.out = new PrintWriter(output);
    this.in = new Scanner(input);
  }

  public Object getChoiceFromOptions(List<Object> options) {
    Object choice = null;
    while (choice == null) {
      displayMenuOptions(options);
      choice = getChoiceFromUserInput(options);
    }
    return choice;
  }

  public Object getChoiceFromUserInput(List<Object> options) {
    Object choice = null;
    String userInput = in.nextLine();
    if (
      (options.get(options.size() - 1)).equals("quit") &&
        userInput.equalsIgnoreCase("q")
    ) {
      return options.get(options.size() - 1);
    }

    try {
      int selectedOption = Integer.valueOf(userInput);
      if (
        selectedOption <= options.size() &&
          selectedOption > 0 &&
          !(options.get(selectedOption - 1).equals("quit"))
      ) {
        choice = options.get(selectedOption - 1);
      }
    } catch (NumberFormatException e) {
      // eat the exception, an error message will be displayed below since choice will be null
    }
    if (choice == null) {
      out.println("\n*** " + userInput + " is not a valid option ***");
    }
    return choice;
  }

  public Object getUserChoiceFromAllowables(
    List<Object> options,
    List<Long> idNumbers
  ) {
    Object choice = null;
    String userInput = in.nextLine();

    try {
      long selectedOption = Long.valueOf(userInput);
      if (selectedOption == 0) {
        return "cancel";
      }
      if (idNumbers.contains(selectedOption)) {
        choice = options.get(idNumbers.indexOf(selectedOption));
      }
    } catch (NumberFormatException e) {
      // eat the exception, an error message will be displayed below since choice will be null
    }
    if (choice == null) {
      out.println("\n*** " + userInput + " is not a valid option ***");
    }
    return choice;
  }

  public LocalDate getDateInputFromUser(String message) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    while (true) {
      out.print(message);
      out.flush();
      String str = in.nextLine();
      LocalDate formattedDate = null;
      try {
        formattedDate = LocalDate.parse(str, formatter);
        return formattedDate;
      } catch (DateTimeParseException e) {
        out.println("Invalid date entry format");
      }
    }
  }

  public String getStringFromUser(String str) {
    out.print(str);
    out.flush();
    String userString = in.nextLine();
    return userString;
  }

  private void displayMenuOptions(List<Object> options) {
    out.println();
    for (int i = 0; i < options.size(); i++) {
      int optionNum = i + 1;
      if (options.get(i).equals("quit")) {
        out.println("   Q) " + options.get(i));
      } else {
        out.println("   " + optionNum + ") " + options.get(i));
      }
    }
    out.print("\nPlease choose an option >>> ");
    out.flush();
  }

  public void printOneLine(String str) {
    out.println();
    out.print(str);
    out.flush();
  }

  public void printMultipleLines(List<String> lines) {
    out.println();
    for (String s : lines) {
      out.println(s);
    }
    out.flush();
  }
}
