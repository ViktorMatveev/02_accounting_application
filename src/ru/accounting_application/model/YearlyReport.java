package ru.accounting_application.model;

import java.util.HashMap;
import java.util.Map;

public class YearlyReport {
    private static final int CURRENT_YEAR = 2024;
    private static final String TRUE = "true";
    private final Map<Integer, int[]> yearlyReport = new HashMap<>();

    public void addData(String data) {
        String[] reportData = data.split(",");
        int currentMonth = Integer.parseInt(reportData[0]);
        int currentSum = Integer.parseInt(reportData[1]);

        if (yearlyReport.containsKey(currentMonth)) {
            int[] totalMonthlyOperations = yearlyReport.get(currentMonth);
            if (reportData[2].equals(TRUE)) {
                totalMonthlyOperations[0] = currentSum;
            } else {
                totalMonthlyOperations[1] = currentSum;
            }
        } else {
            int[] totalMonthlyOperations = new int[2];
            if (reportData[2].equals(TRUE)) {
                totalMonthlyOperations[0] = currentSum;
            } else {
                totalMonthlyOperations[1] = currentSum;
            }
            yearlyReport.put(Integer.parseInt(reportData[0]), totalMonthlyOperations);
        }
    }

    public int[] getDataPerMonth(int month) {
        return yearlyReport.get(month);
    }

    public void printStatistic() {
        if (!yearlyReport.isEmpty()) {
            System.out.printf("рассматриваемый год - %d", CURRENT_YEAR);
            printProfit();
            printAverageExpenses();
            printAverageIncome();
        } else {
            System.out.println("Данных нет");
        }

    }

    public void printProfit() {
        for (int month : yearlyReport.keySet()) {
            int[] totalMonthlyOperations = yearlyReport.get(month);
            System.out.printf("За %d месяц прибыль составила: %d", month, (totalMonthlyOperations[1] - totalMonthlyOperations[0]));
        }
    }

    public void printAverageExpenses() {
        int yearlyAverageExpense = 0;
        for (int month : yearlyReport.keySet()) {
            int[] totalMonthlyOperations = yearlyReport.get(month);
            yearlyAverageExpense += totalMonthlyOperations[0];
        }
        System.out.printf("Средний расход за все месяцы составил: %d", yearlyAverageExpense);
    }

    public void printAverageIncome() {
        int yearlyAverageIncome = 0;
        for (int month : yearlyReport.keySet()) {
            int[] totalMonthlyOperations = yearlyReport.get(month);
            yearlyAverageIncome += totalMonthlyOperations[1];
        }
        System.out.printf("Средний доход за все месяцы составил: %d", yearlyAverageIncome);
    }

    public boolean isReportHasBeenRead() {
        return !yearlyReport.isEmpty();
    }
}
