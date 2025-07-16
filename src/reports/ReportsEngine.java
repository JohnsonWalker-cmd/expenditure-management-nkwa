package reports;

import expenditures.Expenditure;
import expenditures.ExpenditureManager;

import java.time.LocalDate;
import java.util.*;

public class ReportsEngine {

  // Total spent per month
  public static void showMonthlyBurnRate() {
    Map<String, Double> monthlyTotals = new TreeMap<>(); // ordered by month

    for (Expenditure exp : ExpenditureManager.getExpenditureMap().values()) {
      LocalDate date = LocalDate.parse(exp.getDate());
      String month = date.getMonth() + " " + date.getYear(); // e.g. JULY 2025

      monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + exp.getAmount());
    }

    System.out.println("\n Monthly Burn Rate:");
    monthlyTotals.forEach((month, total) -> System.out.printf("- %s: GHS %.2f%n", month, total));
  }

  // 🏗️ Total spent per phase
  public static void showExpenditureByPhase() {
    Map<String, Double> phaseTotals = new HashMap<>();

    for (Expenditure exp : ExpenditureManager.getExpenditureMap().values()) {
      String phase = exp.getPhase().toLowerCase();
      phaseTotals.put(phase, phaseTotals.getOrDefault(phase, 0.0) + exp.getAmount());
    }

    System.out.println("\n Expenditure by Phase:");
    phaseTotals.forEach((phase, total) -> System.out.printf("- %s: GHS %.2f%n", capitalize(phase), total));
  }

  // 🧱 How much was spent on materials like cement/paint
  public static void showMaterialImpactAnalysis() {
    List<String> materials = Arrays.asList("cement", "paint", "electricals");
    double totalSpent = 0.0;
    double materialSpent = 0.0;

    for (Expenditure exp : ExpenditureManager.getExpenditureMap().values()) {
      double amt = exp.getAmount();
      totalSpent += amt;
      if (materials.contains(exp.getCategory().toLowerCase())) {
        materialSpent += amt;
      }
    }

    double percent = totalSpent > 0 ? (materialSpent / totalSpent) * 100 : 0;

    System.out.println("\n Material Cost Impact:");
    System.out.printf("- Total Expenditure: GHS %.2f%n", totalSpent);
    System.out.printf("- Material Cost (cement/paint/electricals): GHS %.2f%n", materialSpent);
    System.out.printf("- Percentage of total: %.2f%% %n", percent);
  }

  private static String capitalize(String word) {
    if (word == null || word.isEmpty())
      return word;
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }
}
