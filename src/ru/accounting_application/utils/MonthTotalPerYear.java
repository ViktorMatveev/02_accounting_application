package ru.accounting_application.utils;

import ru.accounting_application.model.MonthlyReport;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MonthTotalPerYear {
    private final Map<Integer, MonthlyReport> monthTotalPerYearReport = new HashMap<>(); //пример

    public void addData(int month, MonthlyReport monthlyReport) {
        monthTotalPerYearReport.put(month, monthlyReport);
    }

    public Set<Integer> getKeys() {
        return monthTotalPerYearReport.keySet();
    }

    public boolean isReportsHasBeenRead() {
        return !monthTotalPerYearReport.isEmpty();
    }

    public void printMonthlyPerYearStatistic() {
        for (int month : monthTotalPerYearReport.keySet()) {
            MonthlyReport monthlyReport = monthTotalPerYearReport.get(month);
            System.out.printf("Статистика за %d месяц: \n", month);
            monthlyReport.printMostProfitableProduct();
            monthlyReport.printMostExpendableProduct();
        }
    }

    public MonthlyReport getDataPerMonth(int month) {
        return monthTotalPerYearReport.get(month);
    }
}
