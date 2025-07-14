package receipts;

public class Receipt {
  private String receiptId ;
  private String expenditureCode;
  private String description;

  public Receipt(String receiptId , String expenditureCode, String description){
    this.receiptId = receiptId;
    this.expenditureCode = expenditureCode;
    this.description = description ;
  }

  public String getReceiptId(){
    return receiptId;
  }

  public String getExpenditureCode(){
    return expenditureCode;
  }

  public String getDescription(){
    return description;
  }

  public String toFileString(){
    return receiptId + "|" + expenditureCode + "|" + description;
  }

  @Override
  public String toString(){
    return "Receipt ID: " + receiptId +
        ", Linked to Expenditure: " + expenditureCode +
        ", Description: " + description;
  }
}
