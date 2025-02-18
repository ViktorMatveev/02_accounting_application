package ru.accounting_application.model;

import java.util.HashMap;
import java.util.Map;

public class YearlyReport {
    private static final int CURRENT_YEAR = 2024;
    private static final String TRUE = "true";
    private Map<Integer, MonthTotalTransaction> totalTransactionsByMonth = new HashMap<>();

    public void saveTotalTransaction(String fileReportLine) {
        String[] transactionData = fileReportLine.split(",");
        int currentMonth = Integer.parseInt(transactionData[0]);
        int currentTransaction = Integer.parseInt(transactionData[1]);
        boolean isCurrentTransactionExpense = transactionData[2].equals(TRUE);

        if (totalTransactionsByMonth.containsKey(currentMonth)) {
            MonthTotalTransaction currentMonthTotalTransactions = totalTransactionsByMonth.get(currentMonth);
            if (isCurrentTransactionExpense) {
                currentMonthTotalTransactions.setMonthTotalExpense(currentTransaction);
            } else {
                currentMonthTotalTransactions.setMonthTotalIncome(currentTransaction);
            }
        } else {
            MonthTotalTransaction currentMonthTotalTransactions = new MonthTotalTransaction();
            if (isCurrentTransactionExpense) {
                currentMonthTotalTransactions.setMonthTotalExpense(currentTransaction);
            } else {
                currentMonthTotalTransactions.setMonthTotalIncome(currentTransaction);
            }
            totalTransactionsByMonth.put(currentMonth, currentMonthTotalTransactions);
        }
    }

    public MonthTotalTransaction getTotalTransactionsByMonth(int month) {
        return totalTransactionsByMonth.get(month);
    }

    public void printStatistic() {
        if (!totalTransactionsByMonth.isEmpty()) {
            System.out.printf("рассматриваемый год - %d\n", CURRENT_YEAR);
            printProfit();
            printAverageExpenses();
            printAverageIncome();
        } else {
            System.out.println("Данных нет");
        }
    }

    public void printProfit() {
        for (int month : totalTransactionsByMonth.keySet()) {
            MonthTotalTransaction currentMonthTransactions = totalTransactionsByMonth.get(month);
            System.out.printf("За %d месяц прибыль составила: %d\n", month, (currentMonthTransactions.getMonthTotalIncome() - currentMonthTransactions.getMonthTotalExpense()));
        }
    }

    public void printAverageExpenses() {
        int yearlyAverageExpense = 0;
        for (int month : totalTransactionsByMonth.keySet()) {
            MonthTotalTransaction currentMonthTransactions = totalTransactionsByMonth.get(month);
            yearlyAverageExpense += currentMonthTransactions.getMonthTotalExpense();
        }
        System.out.printf("Средний расход за все месяцы составил: %d\n", yearlyAverageExpense);
    }

    public void printAverageIncome() {
        int yearlyAverageIncome = 0;
        for (int month : totalTransactionsByMonth.keySet()) {
            MonthTotalTransaction currentMonthTransactions = totalTransactionsByMonth.get(month);
            yearlyAverageIncome += currentMonthTransactions.getMonthTotalIncome();
        }
        System.out.printf("Средний доход за все месяцы составил: %d\n", yearlyAverageIncome);
    }

    public boolean isReportHasBeenRead() {
        return !totalTransactionsByMonth.isEmpty();
    }
}
