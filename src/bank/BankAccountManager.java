package bank;

import java.io.*;
import java.util.*;

public class BankAccountManager {

  private static Map<String, BankAccount> accountMap = new HashMap<>();
  private static final String BANK_FILE = "data/bank_accounts.txt";

  // 🔁 Load all bank accounts from file
  public static void loadFromFile() {
    File file = new File(BANK_FILE);
    if (!file.exists())
      return;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("\\|");
        if (parts.length >= 3) {
          String id = parts[0].trim();
          String name = parts[1].trim();
          double balance = Double.parseDouble(parts[2].trim());

          BankAccount account = new BankAccount(id, name, balance);

          if (parts.length == 4 && !parts[3].trim().isEmpty()) {
            List<String> expCodes = Arrays.asList(parts[3].split(","));
            account.setRelatedExpenditures(new ArrayList<>(expCodes));
          }

          accountMap.put(id, account);
        }
      }
    } catch (IOException | NumberFormatException e) {
      System.out.println("Failed to load bank accounts: " + e.getMessage());
    }
  }

  // 💾 Save all accounts to file
  public static void saveToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(BANK_FILE))) {
      for (BankAccount account : accountMap.values()) {
        writer.write(account.toFileString());
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println("Failed to save bank accounts: " + e.getMessage());
    }
  }

  // Add new bank account
  public static void addBankAccount(Scanner scanner) {
    System.out.print("Enter Bank Account ID: ");
    String id = scanner.nextLine().trim();

    if (accountMap.containsKey(id)) {
      System.out.println("Account ID already exists.");
      return;
    }

    System.out.print("Enter Bank Name: ");
    String name = scanner.nextLine().trim();

    System.out.print("Enter Initial Balance: ");
    double balance = scanner.nextDouble();
    scanner.nextLine(); // flush newline

    BankAccount account = new BankAccount(id, name, balance);
    accountMap.put(id, account);
    saveToFile();

    System.out.println("Bank account added successfully.");
  }

  // 📊 View all bank accounts
  public static void viewAllBankAccounts() {
    if (accountMap.isEmpty()) {
      System.out.println("No bank accounts available.");
      return;
    }

    System.out.println("\n--- Bank Accounts ---");
    for (BankAccount account : accountMap.values()) {
      System.out.println(account);
      System.out.println("-----------------------------");
    }
  }

  // Deduct funds when spending
  public static boolean deductFromAccount(String bankId, double amount) {
    BankAccount account = accountMap.get(bankId);
    if (account == null)
      return false;

    boolean success = account.withdraw(amount);
    if (success) {
      saveToFile(); // persist the new balance
    }
    return success;
  }

  // Link an expenditure to an account
  public static void linkExpenditure(String bankId, String expCode) {
    BankAccount account = accountMap.get(bankId);
    if (account != null) {
      account.addExpenditureCode(expCode);
      saveToFile(); // persist the update
    }
  }

  // 📚 Get all accounts (useful for other modules)
  public static Map<String, BankAccount> getAllAccounts() {
    return accountMap;
  }
}
