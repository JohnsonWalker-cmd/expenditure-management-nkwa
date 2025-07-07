
package expenditures;

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ExpenditureManager {

  private static Map<String, String> expenditureMap = new HashMap<>();

  // Add new expenditure
  public static void addExpenditure(Scanner scanner) {
    System.out.println("\n--- Add New Expenditure ---");

    System.out.print("Expenditure Code: ");
    String code = scanner.next();

    // prevent duplicate codes
    if (expenditureMap.containsKey(code)) {
      System.out.println("Expenditure code already exists. Please use a different code.");
      return;
    }

    System.out.print("Amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // flush newline

    System.out.print("Date (YYYY-MM-DD): ");
    String date = scanner.nextLine();

    System.out.print("Phase (e.g., construction, marketing, sales): ");
    String phase = scanner.nextLine();

    System.out.print("Category: ");
    String category = scanner.nextLine();

    System.out.print("Bank Account ID: ");
    String bankId = scanner.nextLine();

    String entry = code + " | " + amount + " | " + date + " | " + phase + " | " + category + " | " + bankId;

    // Store in memory
    expenditureMap.put(code, entry);

    // Save to file
    File dir = new File("data");
    if (!dir.exists()) {
      dir.mkdirs();
    }

    File file = new File("data/expenditures.txt");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
      writer.write(entry);
      writer.newLine();
      System.out.println("✅ Expenditure saved successfully.");
    } catch (IOException e) {
      System.out.println("❌ Failed to save expenditure: " + e.getMessage());
    }
  }

  // View all expenditures
  public static void viewAllExpenditures() {
    if (expenditureMap.isEmpty()) {
      System.out.println("No expenditures available.");
      return;
    }

    System.out.println("\n--- All Expenditures ---");
    for (String entry : expenditureMap.values()) {
      System.out.println(entry);
    }
  }

  // Load expenditures from file at program start
  public static void loadExpendituresFromFile() {
        File file = new File("data/expenditures.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
              String[] parts = line.split("\\|");
                if (parts.length >= 1) {
                    String code = parts[0].trim();
                    expenditureMap.put(code, line);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Failed to load expenditures: " + e.getMessage());
        }
    }
}
