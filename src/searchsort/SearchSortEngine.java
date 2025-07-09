package searchsort;

import java.util.*;
import expenditures.ExpenditureManager;

public class SearchSortEngine {

  public static void searchByCategory(Scanner scanner) {
    System.out.print("Enter category to search: ");
    String input = scanner.nextLine().trim().toLowerCase();

    Map<String, String> expenditures = ExpenditureManager.getExpenditureMap();

    boolean found = false;
    System.out.println("\n--- Expenditures in category: " + input + " ---");
    for (String record : expenditures.values()) {
      String[] parts = record.split("\\|");
      if (parts.length >= 6) {
        String category = parts[4].trim().toLowerCase();
        if (category.equals(input)) {
          System.out.println(record);
          found = true;
        }
      }
    }

    if (!found) {
      System.out.println("No expenditures found for category: " + input);
    }
  }

  // Other search methods will go here later...
  public static void searchByDateRange(Scanner scanner) {
    System.out.print("Enter start date (YYYY-MM-DD): ");
    String startInput = scanner.nextLine().trim();
    System.out.print("Enter end date (YYYY-MM-DD): ");
    String endInput = scanner.nextLine().trim();

    try {
      java.time.LocalDate startDate = java.time.LocalDate.parse(startInput);
      java.time.LocalDate endDate = java.time.LocalDate.parse(endInput);

      Map<String, String> expenditures = ExpenditureManager.getExpenditureMap();
      boolean found = false;

      System.out.println("\n--- Expenditures from " + startDate + " to " + endDate + " ---");

      for (String record : expenditures.values()) {
        String[] parts = record.split("\\|");
        if (parts.length >= 3) {
          String dateStr = parts[2].trim(); // Expenditure date
          java.time.LocalDate recordDate = java.time.LocalDate.parse(dateStr);

          if ((recordDate.isEqual(startDate) || recordDate.isAfter(startDate)) &&
              (recordDate.isEqual(endDate) || recordDate.isBefore(endDate))) {
            System.out.println(record);
            found = true;
          }
        }
      }

      if (!found) {
        System.out.println("No expenditures found in the selected date range.");
      }

    } catch (Exception e) {
      System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
    }
  }

}
