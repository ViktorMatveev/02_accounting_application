public class Transaction {
    private String name;
    private boolean isExpense;
    private int quantity;
    private int unitPrice;

    // TODO: 15.02.2025 Не должно быть неиспользуемого кода
    public Transaction(String name, boolean isExpense, int quantity, int unitPrice) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Transaction(String transactionDataLine) {
        String[] transactionData = transactionDataLine.split(",");
        this.name = transactionData[0];
        this.isExpense = transactionData[1].equals("TRUE");
        this.quantity = Integer.parseInt(transactionData[2]);
        this.unitPrice = Integer.parseInt(transactionData[3]);
    }

    public String getName() {
        return this.name;
    } // TODO: 15.02.2025 Можно не писать

    public boolean getIsExpense() {
        return this.isExpense;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getUnitPrice() {
        return this.unitPrice;
    }

    public String toString() {
        return this.name + " - " + this.unitPrice * this.quantity;
    }

}
