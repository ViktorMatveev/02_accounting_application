package ru.accounting_application.model;

public class Transaction {
    private String name;
    private boolean isExpense;
    private int quantity;
    private int unitPrice;

    public Transaction(String transactionDataLine) {
        String[] transactionData = transactionDataLine.split(",");
        this.name = transactionData[0];
        this.isExpense = transactionData[1].equals("TRUE");
        this.quantity = Integer.parseInt(transactionData[2]);
        this.unitPrice = Integer.parseInt(transactionData[3]);
    }

    public String getName() {
        return name;
    }

    public boolean getIsExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String toString() {
        return name + " - " + unitPrice * quantity;
    }

}
