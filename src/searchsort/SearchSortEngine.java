package searchsort;

import expenditures.Expenditure;
import expenditures.ExpenditureManager;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SearchSortEngine {

  // 🔍 Search by Category
  public static void searchByCategory(Scanner scanner) {
    System.out.print("Enter category to search: ");
    String input = scanner.nextLine().trim().toLowerCase();

    boolean found = false;
    for (Expenditure exp : ExpenditureManager.getExpenditureMap().values()) {
      if (exp.getCategory().toLowerCase().equals(input)) {
        System.out.println(exp);
        found = true;
      }
    }

    if (!found) {
      System.out.println("No expenditures found in this category.");
    }
  }

  // 🔍 Search by Amount Range
  public static void searchByAmountRange(Scanner scanner) {
    try {
      System.out.print("Enter minimum amount: ");
      double min = scanner.nextDouble();

      System.out.print("Enter maximum amount: ");
      double max = scanner.nextDouble();
      scanner.nextLine(); // flush newline

      boolean found = false;
      for (Expenditure exp : ExpenditureManager.getExpenditureMap().values()) {
        double amt = exp.getAmount();
        if (amt >= min && amt <= max) {
          System.out.println(exp);
          found = true;
        }
      }

      if (!found) {
        System.out.println("No expenditures found within the specified amount range.");
      }
    } catch (InputMismatchException e) {
      System.out.println("Invalid amount input. Please enter valid numbers.");
      scanner.nextLine(); // flush bad input
    }
  }

  // 🔍 Search by Bank Account
  public static void searchByBankAccount(Scanner scanner) {
    System.out.print("Enter Bank Account ID: ");
    String input = scanner.nextLine().trim();

    boolean found = false;
    for (Expenditure exp : ExpenditureManager.getExpenditureMap().values()) {
      if (exp.getBankId().equalsIgnoreCase(input)) {
        System.out.println(exp);
        found = true;
      }
    }

    if (!found) {
      System.out.println("No expenditures found for this bank account.");
    }
  }

  // 🔍 Search by Date Range
  public static void searchByDateRange(Scanner scanner) {
    try {
      System.out.print("Enter start date (YYYY-MM-DD): ");
      LocalDate start = LocalDate.parse(scanner.nextLine().trim());

      System.out.print("Enter end date (YYYY-MM-DD): ");
      LocalDate end = LocalDate.parse(scanner.nextLine().trim());

      boolean found = false;
      for (Expenditure exp : ExpenditureManager.getExpenditureMap().values()) {
        LocalDate expDate = LocalDate.parse(exp.getDate());
        if ((expDate.isEqual(start) || expDate.isAfter(start)) &&
            (expDate.isEqual(end) || expDate.isBefore(end))) {
          System.out.println(exp);
          found = true;
        }
      }

      if (!found) {
        System.out.println("No expenditures found in the given date range.");
      }
    } catch (DateTimeParseException e) {
      System.out.println("Invalid date format. Please use YYYY-MM-DD.");
    }
  }
}
