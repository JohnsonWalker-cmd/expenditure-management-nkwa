package bank;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
  private String accountId;
  private String bankName;
  private double balance;
  private List<String> relatedExpenditures;

  public BankAccount(String accountId, String bankName, double balance){
    this.accountId = accountId ;
    this.bankName = bankName;
    this.balance = balance;
    this.relatedExpenditures = new ArrayList<>();
    
  }


  // Withdraw money
  public boolean withdraw(double amount){
    if (amount > balance){
      return false ;  // insufficient funds
    }
    balance -= amount;
    return true ;
  }

  // Add a related expenditure code 
  public void addExpenditureCode(String code){
    relatedExpenditures.add(code);
  }

  // 📄 Convert to file line for saving
  public String toFileString() {
    return String.join("|",
        accountId.trim(),
        bankName.trim(),
        String.valueOf(balance),
        String.join(",", relatedExpenditures) // store codes comma-separated
    );
  }

  @Override
  public String toString() {
    return "Account ID: " + accountId +
        "\nBank Name: " + bankName +
        "\nBalance: GHS " + String.format("%.2f", balance) +
        "\nRelated Expenditures: " + (relatedExpenditures.isEmpty() ? "None" : relatedExpenditures);
  }

  // 🧾 Getters
  public String getAccountId() {
    return accountId;
  }

  public String getBankName() {
    return bankName;
  }

  public double getBalance() {
    return balance;
  }

  public List<String> getRelatedExpenditures() {
    return relatedExpenditures;
  }

  // Used when loading from file
  public void setRelatedExpenditures(List<String> codes) {
    this.relatedExpenditures = codes;
  }

  public void deposit(double amount) {
    this.balance += amount;
  }
}
