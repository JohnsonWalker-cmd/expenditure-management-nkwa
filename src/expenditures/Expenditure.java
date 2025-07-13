package expenditures;

public class Expenditure {
  private String code;
  private double amount;
  private String date;
  private String phase;
  private String category;
  private String bankId;

  public Expenditure(String code, double amount, String date, String phase, String category, String bankId) {
    this.code = code;
    this.amount = amount;
    this.date = date;
    this.phase = phase;
    this.category = category;
    this.bankId = bankId;
  }

  // Getters
  public String getCode() {
    return code;
  }

  public double getAmount() {
    return amount;
  }

  public String getDate() {
    return date;
  }

  public String getPhase() {
    return phase;
  }

  public String getCategory() {
    return category;
  }

  public String getBankId() {
    return bankId;
  }

  // Convert to line for saving
  public String toFileString() {
    return String.join("|",
        code.trim(),
        String.valueOf(amount).trim(),
        date.trim(),
        phase.trim(),
        category.trim(),
        bankId.trim());
  }

  @Override
  public String toString() {
    return "Code: " + code +
        ", Amount: " + amount +
        ", Date: " + date +
        ", Phase: " + phase +
        ", Category: " + category +
        ", Bank ID: " + bankId;
  }
}
