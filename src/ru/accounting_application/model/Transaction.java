package ru.accounting_application.model;

public class Transaction {
    private final String NAME;
    private final boolean IS_EXPENSE;
    private final int QUANTITY;
    private final int UNIT_PRICE;

    public Transaction(String transactionDataLine) {
        String[] transactionData = transactionDataLine.split(",");
        this.NAME = transactionData[0];
        this.IS_EXPENSE = transactionData[1].equals("TRUE");
        this.QUANTITY = Integer.parseInt(transactionData[2]);
        this.UNIT_PRICE = Integer.parseInt(transactionData[3]);
    }

    public String getName() {
        return NAME;
    }

    public boolean getIsExpense() {
        return IS_EXPENSE;
    }

    public int getQuantity() {
        return QUANTITY;
    }

    public int getUnitPrice() {
        return UNIT_PRICE;
    }

    public String toString() {
        return NAME + " - " + UNIT_PRICE * QUANTITY;
    }

}
