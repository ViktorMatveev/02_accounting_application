package ru.accounting_application.service;

import ru.accounting_application.model.YearlyReport;
import ru.accounting_application.utils.MonthTotalPerYear;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationRunner {
    // TODO: 15.02.2025 Переписать, метод должен влезать на экран!
    public static void run() {
        // TODO: 15.02.2025 Подумать над разделением на классы для хранения и обработки данных и для выводы этих данных
        //(хранение перенести в репортСервис)
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
        ReportService reportService = new ReportService();
        while (true) {
            try {
                printMenu();
                int command = scanner.nextInt();
                // TODO: 15.02.2025 Переписать switch по модному Пример:
/*                switch (command) {
                    case 1 -> reportService.generateYearlyReport();
                    case 2 -> reportService.compareMonthAndYearlyReports(monthTotalPerYear);
                }*/
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
                            // TODO: 15.02.2025 Переделать на многострочный текст
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
                            // TODO: 15.02.2025 Переделать на многострочный текст
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
