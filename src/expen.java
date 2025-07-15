// File: Main.java
import java.util.Scanner;

import expenditures.ExpenditureManager;
import categories.CategoryManager;
import searchsort.SearchSortEngine;



public class expen{
    
    public static void main(String[] args) {
        CategoryManager.loadCategoriesFromFile();
        ExpenditureManager.loadExpendituresFromFile();
        bank.BankAccountManager.loadFromFile(); 
        receipts.ReceiptManager.loadReceiptsFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display the menu
            System.out.println("=====================================");
            System.out.println(" Nkwa Real Estate - Expense Tracker ");
            System.out.println("=====================================");
            System.out.println("1. Add Expenditure");
            System.out.println("2. View All Expenditures");
            System.out.println("3. Search Expenditures");
            System.out.println("4. Manage Categories");
            System.out.println("5. Bank Account Overview");
            System.out.println("6. Upload/View Receipts");
            System.out.println("7. Generate Reports");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");

            // Get user input
            // when the user input is not an integer
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-8): ");
                scanner.next();
            }
            choice = scanner.nextInt();

            // Handle user choice
            switch (choice) {
                case 1:
                    System.out.println(">> Add Expenditure module called...");
                    ExpenditureManager.addExpenditure(scanner);
                    break;
                case 2:
                    ExpenditureManager.viewAllExpenditures();
                    break;
                case 3:
                    System.out.println("\n--- Search Expenditures ---");
                    System.out.println("1. Search by Category");
                    System.out.println("2. Search by Date Range");
                    System.out.println("3. Search by Amount Range");
                    System.out.println("4. Search by Bank Account");
                    System.out.print("Choose option: ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (searchChoice) {
                        case 1:
                            SearchSortEngine.searchByCategory(scanner);
                            break;
                        case 2:
                            SearchSortEngine.searchByDateRange(scanner);
                            break;
                        case 3:
                            SearchSortEngine.searchByAmountRange(scanner);
                            break;
                        case 4:
                            SearchSortEngine.searchByBankAccount(scanner);
                            break;
                        default:
                            System.out.println("Invalid search option.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- Manage Categories ---");
                    System.out.println("1. Add Category");
                    System.out.println("2. View All Categories");
                    System.out.print("Choose option: ");
                    int subChoice = scanner.nextInt();
                    scanner.nextLine(); // flush newline

                    if (subChoice == 1) {
                        CategoryManager.addCategory(scanner);
                    } else if (subChoice == 2) {
                        CategoryManager.viewCategories();
                    } else {
                        System.out.println("Invalid category option.");
                    }
                    break;
                case 5:
                    System.out.println("\n--- Bank Account Management ---");
                    System.out.println("1. Add Bank Account");
                    System.out.println("2. View All Bank Accounts");
                    System.out.println("3. Show accounts with low balance");
                    System.out.print("Choose option: ");
                    int bankChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (bankChoice == 1) {
                        bank.BankAccountManager.addBankAccount(scanner);
                    } else if (bankChoice == 2) {
                        bank.BankAccountManager.viewAllBankAccounts();
                    } else if (bankChoice == 3){
                        bank.BankAccountManager.showLowBalanceAccounts();
                    } else {
                        System.out.println("Invalid option");
                    }
                    break;
                case 6:
                    System.out.println("\n--- Receipt Handling ---");
                    System.out.println("1. Upload New Receipt");
                    System.out.println("2. View All Uploaded Receipts");
                    System.out.println("3. Process Next Receipt (Queue)");
                    System.out.println("4. Review Most Recent Receipt (Stack)");
                    System.out.print("Choose option: ");
                    int receiptChoice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (receiptChoice) {
                        case 1:
                            receipts.ReceiptManager.uploadReceipt(scanner);
                            break;
                        case 2:
                            receipts.ReceiptManager.viewAllReceipts();
                            break;
                        case 3:
                            receipts.ReceiptManager.processNextUpload();
                            break;
                        case 4:
                            receipts.ReceiptManager.reviewLatestReceipt();
                            break;
                        default:
                            System.out.println("Invalid receipt option.");
                    }
                    break;
                case 7:
                    System.out.println(">> Generate Reports module called...");
                    break;
                case 8:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1 and 8.");
            }

            System.out.println(); // Add a blank line between cycles

        } while (choice != 8);

        scanner.close();
    }
}
