package ru.accounting_application.service;

import ru.accounting_application.model.YearlyReport;
import ru.accounting_application.utils.MonthTotalPerYear;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationRunner {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
        ReportService reportService = new ReportService();
        while (true) {
            try {
                printMenu();
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        monthTotalPerYear = reportService.generateAllMonthlyReports();
                        break;
                    case 2:
                        yearlyReport = reportService.generateYearlyReport();
                        break;
                    case 3:
                        if (!yearlyReport.isReportHasBeenRead()) {
                            System.out.println("Данные годового отчета не были прочитаны!");
                        }
                        if (!monthTotalPerYear.isReportsHasBeenRead()) {
                            System.out.println("Данные месячных отчетов не были прочитаны!");
                        }
                        if (yearlyReport.isReportHasBeenRead() && monthTotalPerYear.isReportsHasBeenRead()) {
                            reportService.compareMonthAndYearlyReports(monthTotalPerYear, yearlyReport);
                        } else {
                            System.out.println("Считать данные?");
                            System.out.println("1 - Да");
                            System.out.println("2 - Нет");
                            int answer = scanner.nextInt();
                            if (answer == 1) {
                                if (!monthTotalPerYear.isReportsHasBeenRead()) {
                                    monthTotalPerYear = reportService.generateAllMonthlyReports();
                                    System.out.println("месячные отчеты готовы");
                                }
                                if (!yearlyReport.isReportHasBeenRead()) {
                                    yearlyReport = reportService.generateYearlyReport();
                                    System.out.println("годовой отчет готов");
                                }
                                reportService.compareMonthAndYearlyReports(monthTotalPerYear, yearlyReport);
                            } else {
                                System.out.println("Выход в меню..");
                            }
                        }
                        break;
                    case 4:
                        if (monthTotalPerYear.isReportsHasBeenRead()) {
                            monthTotalPerYear.printMonthlyPerYearStatistic();
                        } else {
                            System.out.println("Данные месячных отчетов не были прочитаны! \n Считать данные?");
                            System.out.println("1 - Да");
                            System.out.println("2 - Нет");
                            int answer = scanner.nextInt();
                            if (answer == 1) {
                                monthTotalPerYear = reportService.generateAllMonthlyReports();
                                monthTotalPerYear.printMonthlyPerYearStatistic();
                            } else {
                                System.out.println("Выход в меню..");
                            }
                        }
                        break;
                    case 5:
                        if (yearlyReport.isReportHasBeenRead()) {
                            yearlyReport.printStatistic();
                        } else {
                            System.out.println("Данные годового отчета не были прочитаны! \n Считать данные?");
                            System.out.println("1 - Да");
                            System.out.println("2 - Нет");
                            int answer = scanner.nextInt();
                            if (answer == 1) {
                                yearlyReport = reportService.generateYearlyReport();
                                yearlyReport.printStatistic();
                            } else {
                                System.out.println("Выход в меню..");
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Exit");
                        return;
                    default:
                        System.out.println("Такой команды нет");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод команды!");
                scanner.next();
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                
                Выберите нужное действие:
                1. Считать все месячные отчеты.
                2. Считать годовой отчет.
                3. Сверить отчеты.
                4. Вывести информацию обо всех месячных отчетах.
                5. Вывести информацию о годовом отчете.
                0. Выйти.""");
    }
}
