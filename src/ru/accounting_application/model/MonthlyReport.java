package ru.accounting_application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyReport {
    private static final String EXPENSES = "расходы";
    private static final String INCOMES = "доходы";
    private String mostProfitableProductName;
    private int mostProfitableProductValue = 0;
    private String mostExpendableProductName;
    private int mostExpendableProductValue = 0;
    private int monthTotalExpenses;
    private int monthTotalIncome;
    private final Map<String, ArrayList<Transaction>> MONTHLY_REPORT_BY_CATEGORIES = new HashMap<>();

    public MonthlyReport() {
        MONTHLY_REPORT_BY_CATEGORIES.put(EXPENSES, new ArrayList<>());
        MONTHLY_REPORT_BY_CATEGORIES.put(INCOMES, new ArrayList<>());
    }

    public List<Transaction> getMonthlyExpenses() {
        return MONTHLY_REPORT_BY_CATEGORIES.get(EXPENSES);
    }

    public List<Transaction> getMonthlyIncome() {
        return MONTHLY_REPORT_BY_CATEGORIES.get(INCOMES);
    }

    public void printMostProfitableProduct() {
        List<Transaction> incomeTransactions = MONTHLY_REPORT_BY_CATEGORIES.get(INCOMES);
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
        List<Transaction> expenseTransaction = MONTHLY_REPORT_BY_CATEGORIES.get(EXPENSES);
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
        List<Transaction> expenseTransaction = MONTHLY_REPORT_BY_CATEGORIES.get(EXPENSES);
        for (Transaction transaction : expenseTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            monthTotalExpenses += transactionValue;
        }
        return monthTotalExpenses;
    }

    public int getMonthTotalIncome() {
        List<Transaction> incomeTransaction = MONTHLY_REPORT_BY_CATEGORIES.get(INCOMES);
        for (Transaction transaction : incomeTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            monthTotalIncome += transactionValue;
        }
        return monthTotalIncome;
    }
}