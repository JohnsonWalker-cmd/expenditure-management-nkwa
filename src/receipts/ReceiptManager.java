package receipts;

import java.io.*;
import java.util.*;

public class ReceiptManager {

  private static final String RECEIPT_FILE = "data/receipts.txt";
  private static Queue<Receipt> uploadQueue = new LinkedList<>();
  private static Stack<Receipt> reviewStack = new Stack<>();

  // ✅ Load all receipts from file into memory
  public static void loadReceiptsFromFile() {
    File file = new File(RECEIPT_FILE);
    if (!file.exists())
      return;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("\\|");
        if (parts.length == 3) {
          Receipt receipt = new Receipt(parts[0], parts[1], parts[2]);
          uploadQueue.offer(receipt);
          reviewStack.push(receipt);
        }
      }
    } catch (IOException e) {
      System.out.println("❌ Failed to load receipts: " + e.getMessage());
    }
  }

  // 💾 Save a single receipt to file
  private static void saveReceiptToFile(Receipt receipt) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECEIPT_FILE, true))) {
      writer.write(receipt.toFileString());
      writer.newLine();
    } catch (IOException e) {
      System.out.println("❌ Failed to save receipt: " + e.getMessage());
    }
  }

  // 📤 Upload a new receipt
  public static void uploadReceipt(Scanner scanner) {
    System.out.print("Enter Receipt ID: ");
    String receiptId = scanner.nextLine().trim();

    System.out.print("Enter Expenditure Code it belongs to: ");
    String expCode = scanner.nextLine().trim();

    System.out.print("Enter Description (file path, notes, etc.): ");
    String desc = scanner.nextLine().trim();

    Receipt receipt = new Receipt(receiptId, expCode, desc);

    uploadQueue.offer(receipt); // FIFO order
    reviewStack.push(receipt); // LIFO review
    saveReceiptToFile(receipt);

    System.out.println("✅ Receipt uploaded successfully.");
  }

  // 📥 View and process receipts from upload queue
  public static void processNextUpload() {
    if (uploadQueue.isEmpty()) {
      System.out.println("No receipts in upload queue.");
      return;
    }

    Receipt next = uploadQueue.poll(); // remove from queue
    System.out.println("📤 Processing Receipt:\n" + next);
  }

  // 🔄 Review most recent receipt
  public static void reviewLatestReceipt() {
    if (reviewStack.isEmpty()) {
      System.out.println("No receipts in review stack.");
      return;
    }

    Receipt recent = reviewStack.pop(); // LIFO
    System.out.println("🕵️ Reviewed Receipt:\n" + recent);
  }

  // 👀 View all uploaded receipts
  public static void viewAllReceipts() {
    if (uploadQueue.isEmpty()) {
      System.out.println("No receipts available.");
      return;
    }

    System.out.println("\n--- All Uploaded Receipts ---");
    for (Receipt receipt : uploadQueue) {
      System.out.println(receipt);
    }
  }
}
