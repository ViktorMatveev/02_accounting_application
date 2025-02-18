package ru.accounting_application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyReport {
    private static final String EXPENSES = "расходы";
    private static final String INCOMES = "доходы";
    private String mostProfitableProductName;
    private int mostProfitableProductValue;
    private String mostExpendableProductName;
    private int mostExpendableProductValue;
    private int monthTotalExpenses;
    private int monthTotalIncome;
    private Map<String, List<Transaction>> transactionsByCategories = new HashMap<>();

    public MonthlyReport() {
        transactionsByCategories.put(EXPENSES, new ArrayList<>());
        transactionsByCategories.put(INCOMES, new ArrayList<>());
    }

    public List<Transaction> getMonthlyExpenses() {
        return transactionsByCategories.get(EXPENSES);
    }

    public List<Transaction> getMonthlyIncome() {
        return transactionsByCategories.get(INCOMES);
    }

    public void printMostProfitableProduct() {
        List<Transaction> incomeTransactions = transactionsByCategories.get(INCOMES);
        for (Transaction transaction : incomeTransactions) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            if (transactionValue > mostProfitableProductValue) {
                mostProfitableProductValue = transactionValue;
                mostProfitableProductName = transaction.getName();
            }
        }
        System.out.printf("Самый прибыльный товар: %s (доход составил: %d)\n", mostProfitableProductName, mostProfitableProductValue);
    }

    public void printMostExpendableProduct() {
        List<Transaction> expenseTransaction = transactionsByCategories.get(EXPENSES);
        for (Transaction transaction : expenseTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            if (transactionValue > mostExpendableProductValue) {
                mostExpendableProductValue = transactionValue;
                mostExpendableProductName = transaction.getName();
            }
        }
        System.out.printf("Самый большой расход: %s (расход составил: %d)\n", mostExpendableProductName, mostExpendableProductValue);
    }

    public int getMonthTotalExpenses() {
        List<Transaction> expenseTransaction = transactionsByCategories.get(EXPENSES);
        for (Transaction transaction : expenseTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            monthTotalExpenses += transactionValue;
        }
        return monthTotalExpenses;
    }

    public int getMonthTotalIncome() {
        List<Transaction> incomeTransaction = transactionsByCategories.get(INCOMES);
        for (Transaction transaction : incomeTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            monthTotalIncome += transactionValue;
        }
        return monthTotalIncome;
    }
}