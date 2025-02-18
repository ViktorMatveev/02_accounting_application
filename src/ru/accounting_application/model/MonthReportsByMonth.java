package ru.accounting_application.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MonthReportsByMonth {
    private final Map<Integer, MonthlyReport> monthReportsByMonth = new HashMap<>();

    public void saveMonthReport(int month, MonthlyReport monthlyReport) {
        monthReportsByMonth.put(month, monthlyReport);
    }

    public Set<Integer> getAllMonthReportsMonths() {
        return monthReportsByMonth.keySet();
    }

    public boolean isMonthReportsHasBeenRead() {
        return !monthReportsByMonth.isEmpty();
    }

    public void printAllMonthReportsStatistic() {
        for (int month : monthReportsByMonth.keySet()) {
            MonthlyReport monthlyReport = monthReportsByMonth.get(month);
            System.out.printf("Статистика за %d месяц: \n", month);
            monthlyReport.printMostProfitableProduct();
            monthlyReport.printMostExpendableProduct();
        }
    }

    public MonthlyReport getMonthReportByCurrentMonth(int month) {
        return monthReportsByMonth.get(month);
    }
}
