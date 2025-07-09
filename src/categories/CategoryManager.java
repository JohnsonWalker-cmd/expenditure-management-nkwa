package categories;

import java.io.*;
import java.util.*;

public class CategoryManager {
  private static Set<String> categorySet = new HashSet<>();
  private static final String CATEGORY_FILE = "data/categories.txt";

  // Load categories at start
  public static void loadCategoriesFromFile() {
    File file = new File(CATEGORY_FILE);
    if (!file.exists())
      return;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        categorySet.add(line.trim().toLowerCase());
      }
    } catch (IOException e) {
      System.out.println(" Failed to load categories: " + e.getMessage());
    }
  }

  // Add new category
  public static void addCategory(Scanner scanner) {
    System.out.print("Enter category name: ");
    String input = scanner.nextLine().trim().toLowerCase();

    if (categorySet.contains(input)) {
      System.out.println("⚠️ Category already exists!");
      return;
    }

    categorySet.add(input);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(CATEGORY_FILE, true))) {
      writer.write(input);
      writer.newLine();
      System.out.println("✅ Category added successfully.");
    } catch (IOException e) {
      System.out.println("❌ Failed to save category: " + e.getMessage());
    }
  }

  // View all categories
  public static void viewCategories() {
    if (categorySet.isEmpty()) {
      System.out.println("No categories available.");
      return;
    }

    System.out.println("\n--- Available Categories ---");
    for (String cat : categorySet) {
      System.out.println("- " + capitalize(cat));
    }
  }

  // Utility to capitalize output
  private static String capitalize(String word) {
    if (word == null || word.isEmpty())
      return word;
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }
}
