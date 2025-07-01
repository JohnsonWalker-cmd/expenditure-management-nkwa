package expenditures;

import java.io.*;
import java.util.Scanner;

public class ExpenditureManager {

  public static void addExpenditure(Scanner scanner) {
    System.out.println("\n--- Add New Expenditure ---");

    System.out.print("Expenditure Code: ");
    String code = scanner.next();

    System.out.print("Amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // flush newline

    System.out.print("Date (YYYY-MM-DD): ");
    String date = scanner.nextLine();

    System.out.print("Phase (e.g., construction, marketing): ");
    String phase = scanner.nextLine();

    System.out.print("Category: ");
    String category = scanner.nextLine();

    System.out.print("Bank Account ID: ");
    String bankId = scanner.nextLine();

    String entry = code + "|" + amount + "|" + date + "|" + phase + "|" + category + "|" + bankId;

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
}
