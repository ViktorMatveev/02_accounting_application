package ru.accounting_application.service;

import ru.accounting_application.model.MonthlyReport;
import ru.accounting_application.model.Transaction;
import ru.accounting_application.model.YearlyReport;
import ru.accounting_application.utils.FileReader;
import ru.accounting_application.utils.MonthTotalPerYear;

import java.util.List;

public class ReportService {
    private static final String YEAR_REPORT_NAME = "y.2024.csv";
    private static final FileReader FILE_READER = new FileReader();

    public MonthTotalPerYear generateAllMonthlyReports() {
        MonthTotalPerYear monthTotalPerYearReport = new MonthTotalPerYear();
        for (int i = 9; i <= 12; i++) {
            String fileName = "m." + (202400 + i) + ".csv";

            List<String> fileLines = FILE_READER.readFileContests(fileName);
            MonthlyReport monthlyReport = new MonthlyReport();
            List<Transaction> monthlyExpenses = monthlyReport.getMonthlyExpenses();
            List<Transaction> monthlyIncomes = monthlyReport.getMonthlyIncome();

            for (int j = 1; j < fileLines.size(); j++) {
                Transaction transaction = new Transaction(fileLines.get(j));
                if (transaction.getIsExpense()) {
                    monthlyExpenses.add(transaction);
                } else {
                    monthlyIncomes.add(transaction);
                }
                monthTotalPerYearReport.addData(i, monthlyReport);
            }
        }
        System.out.println("Данные успешно сохранены");
        return monthTotalPerYearReport;
    }

    public YearlyReport generateYearlyReport() {
        YearlyReport yearlyReport = new YearlyReport();
        List<String> fileLines = FILE_READER.readFileContests(YEAR_REPORT_NAME);
        for (int i = 1; i < fileLines.size(); i++) {
            yearlyReport.addData(fileLines.get(i));
        }
        System.out.println("Данные успешно сохранены");
        return yearlyReport;
    }

    public void compareMonthAndYearlyReports(MonthTotalPerYear monthTotalPerYearReport, YearlyReport yearlyReport) {
        for (int month : monthTotalPerYearReport.getKeys()) {
            MonthlyReport monthlyReport = monthTotalPerYearReport.getDataPerMonth(month);
            int[] monthlyTransactionsFromYearlyReport = yearlyReport.getDataPerMonth(month);

            int monthExpense1 = monthlyReport.getMonthTotalExpenses();
            int monthExpense2 = monthlyTransactionsFromYearlyReport[0];
            int monthIncome1 = monthlyReport.getMonthTotalIncome();
            int monthIncome2 = monthlyTransactionsFromYearlyReport[1];
            if ((monthExpense1 == monthExpense2) && (monthIncome1 == monthIncome2)) {
                System.out.printf("За %d месяц ошибок в отчетах нет\n", month);
            } else {
                if (monthExpense1 != monthExpense2) {
                    System.out.printf("Проверить расходы за %d месяц. (%d/%d)\n", month, monthExpense1, monthExpense2);
                }
                if (monthIncome1 != monthIncome2) {
                    System.out.printf("Проверить расходы за %d месяц. (%d/%d)\n", month, monthIncome1, monthIncome2);
                }
            }
        }
    }
}
