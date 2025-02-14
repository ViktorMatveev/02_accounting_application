import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
        ReportEngine reportEngine = new ReportEngine();

        while (true) {
            try {
                printMenu();
                int command = scanner.nextInt();

                if (command == 1) {
                    monthTotalPerYear = reportEngine.generateAllMonthlyReports();
                } else if (command == 2) {
                    yearlyReport = reportEngine.generateYearlyReport();
                } else if (command == 3) {
                    if (!yearlyReport.isReportHasBeenRead()) {
                        System.out.println("Данные годового отчета не были прочитаны!");
                    }
                    if (!monthTotalPerYear.isReportsHasBeenRead()) {
                        System.out.println("Данные месячных отчетов не были прочитаны!");
                    }
                    if (yearlyReport.isReportHasBeenRead() && monthTotalPerYear.isReportsHasBeenRead()) {
                        reportEngine.compareMonthAndYearlyReports(monthTotalPerYear, yearlyReport);
                    } else {
                        System.out.println("Считать данные?");
                        System.out.println("1 - Да");
                        System.out.println("2 - Нет");
                        int answer = scanner.nextInt();
                        if (answer == 1) {
                            if (!monthTotalPerYear.isReportsHasBeenRead()) {
                                monthTotalPerYear = reportEngine.generateAllMonthlyReports();
                                System.out.println("месячные отчеты готовы");
                            }
                            if (!yearlyReport.isReportHasBeenRead()) {
                                yearlyReport = reportEngine.generateYearlyReport();
                                System.out.println("годовой отчет готов");
                            }
                            reportEngine.compareMonthAndYearlyReports(monthTotalPerYear, yearlyReport);
                        } else {
                            System.out.println("Выход в меню..");
                        }
                    }
                } else if (command == 4) {
                    if (monthTotalPerYear.isReportsHasBeenRead()) {
                        monthTotalPerYear.printMonthlyPerYearStatistic();
                    } else {
                        System.out.println("Данные месячных отчетов не были прочитаны! \n Считать данные?");
                        System.out.println("1 - Да");
                        System.out.println("2 - Нет");
                        int answer = scanner.nextInt();
                        if (answer == 1) {
                            monthTotalPerYear = reportEngine.generateAllMonthlyReports();
                            monthTotalPerYear.printMonthlyPerYearStatistic();
                        } else {
                            System.out.println("Выход в меню..");
                        }
                    }
                } else if (command == 5) {
                    if (yearlyReport.isReportHasBeenRead()) {
                        yearlyReport.printStatistic();
                    } else {
                        System.out.println("Данные годового отчета не были прочитаны! \n Считать данные?");
                        System.out.println("1 - Да");
                        System.out.println("2 - Нет");
                        int answer = scanner.nextInt();
                        if (answer == 1) {
                            yearlyReport = reportEngine.generateYearlyReport();
                            yearlyReport.printStatistic();
                        } else {
                            System.out.println("Выход в меню..");
                        }
                    }
                } else if (command == 0) {
                    System.out.println("Exit");
                    break;
                } else {
                    System.out.println("Такой команды нет");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод команды!");
                scanner.next();
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("Выберите нужно действие:");
        System.out.println("1. Считать все месячные отчеты.");
        System.out.println("2. Считать годовой отчет.");
        System.out.println("3. Сверить отчеты.");
        System.out.println("4. Вывести информацию обо всех месячных отчетах.");
        System.out.println("5. Вывести информацию о годовом отчете.");
        System.out.println("0. Выход.");
    }
}