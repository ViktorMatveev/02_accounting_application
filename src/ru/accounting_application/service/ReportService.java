package ru.accounting_application.service;

import ru.accounting_application.model.*;
import ru.accounting_application.utils.FileReader;

import java.util.List;
import java.util.Scanner;

public class ReportService {
    private static final String YEAR_REPORT_NAME = "y.2024.csv";
    private static final FileReader FILE_READER = new FileReader();
    public YearlyReport yearlyReport = new YearlyReport();
    public MonthReportsByMonth monthReportsByMonth = new MonthReportsByMonth();
    private static final String READ_REPORTS_MESSAGE = """
                Считать данные?
                1 - Да
                2 - Нет
                """;

    public void generateAllMonthlyReports() {
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
                monthReportsByMonth.saveMonthReport(i, monthlyReport);
            }
        }
        System.out.println("Данные успешно сохранены");
    }

    public void generateYearlyReport() {
        List<String> fileLines = FILE_READER.readFileContests(YEAR_REPORT_NAME);
        for (int i = 1; i < fileLines.size(); i++) {
            yearlyReport.saveTotalTransaction(fileLines.get(i));
        }
        System.out.println("Данные успешно сохранены");
    }

    public void compareMonthAndYearlyReports() {
        for (int month : monthReportsByMonth.getAllMonthReportsMonths()) {
            MonthlyReport currentMonthlyReport = monthReportsByMonth.getMonthReportByCurrentMonth(month);
            MonthTotalTransaction currentMonthTransactions = yearlyReport.getTotalTransactionsByMonth(month);

            int monthExpense1 = currentMonthlyReport.getMonthTotalExpenses();
            int monthExpense2 = currentMonthTransactions.getMonthTotalExpense();
            int monthIncome1 = currentMonthlyReport.getMonthTotalIncome();
            int monthIncome2 = currentMonthTransactions.getMonthTotalIncome();
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

    public void printCompareReports(Scanner scanner) {
        if (!yearlyReport.isReportHasBeenRead()) {
            System.out.println("Данные годового отчета не были прочитаны!");
        }
        if (!monthReportsByMonth.isMonthReportsHasBeenRead()) {
            System.out.println("Данные месячных отчетов не были прочитаны!");
        }
        if (yearlyReport.isReportHasBeenRead() && monthReportsByMonth.isMonthReportsHasBeenRead()) {
            compareMonthAndYearlyReports();
        } else {
            System.out.println(READ_REPORTS_MESSAGE);
            int answer = scanner.nextInt();
            if (answer == 1) {
                if (!monthReportsByMonth.isMonthReportsHasBeenRead()) {
                    generateAllMonthlyReports();
                    System.out.println("месячные отчеты готовы");
                }
                if (!yearlyReport.isReportHasBeenRead()) {
                    generateYearlyReport();
                    System.out.println("годовой отчет готов");
                }
                compareMonthAndYearlyReports();
            } else {
                System.out.println("Выход в меню..");
            }
        }
    }

    public void printAllMonthReportsStatistic(Scanner scanner) {
        if (monthReportsByMonth.isMonthReportsHasBeenRead()) {
            monthReportsByMonth.printAllMonthReportsStatistic();
        } else {
            System.out.printf("Данные месячных отчетов не были прочитаны! %s", READ_REPORTS_MESSAGE);
            int answer = scanner.nextInt();
            if (answer == 1) {
                generateAllMonthlyReports();
                monthReportsByMonth.printAllMonthReportsStatistic();
            } else {
                System.out.println("Выход в меню..");
            }
        }
    }

    public void printYearReportStatistic(Scanner scanner) {
        if (yearlyReport.isReportHasBeenRead()) {
            yearlyReport.printStatistic();
        } else {
            System.out.printf("Данные годового отчета не были прочитаны! %s", READ_REPORTS_MESSAGE);
            int answer = scanner.nextInt();
            if (answer == 1) {
                generateYearlyReport();
                yearlyReport.printStatistic();
            } else {
                System.out.println("Выход в меню..");
            }
        }
    }
}
