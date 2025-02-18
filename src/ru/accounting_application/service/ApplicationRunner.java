package ru.accounting_application.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationRunner {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        ReportService reportService = new ReportService();
        while (true) {
            try {
                printMenu();
                int command = scanner.nextInt();
                switch (command) {
                    case 1 -> reportService.generateAllMonthlyReports();
                    case 2 -> reportService.generateYearlyReport();
                    case 3 -> reportService.printCompareReports(scanner, askToReadReportFiles);
                    case 4 -> reportService.printAllMonthReportsStatistic(scanner, askToReadReportFiles);
                    case 5 -> reportService.printYearReportStatistic(scanner, askToReadReportFiles);
                    case 0 -> {
                        System.out.println("Exit");
                        return;
                    }
                    default -> System.out.println("Такой команды нет");
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

    public static String askToReadReportFiles = """
                Считать данные?
                1 - Да
                2 - Нет
                """;
}
