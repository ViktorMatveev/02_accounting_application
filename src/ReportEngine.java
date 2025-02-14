import java.util.ArrayList;

public class ReportEngine {
    FileReader fileReader = new FileReader();

    public MonthTotalPerYear generateAllMonthlyReports() {
        MonthTotalPerYear monthTotalPerYearReport = new MonthTotalPerYear();
        for (int i = 9; i <= 12; i++) {
            String fileName = "m." + (202400 + i) + ".csv";

            ArrayList<String> fileLines = fileReader.readFileContests(fileName);
            MonthlyReport monthlyReport = new MonthlyReport();
            ArrayList<Transaction> monthlyExpenses = monthlyReport.getMonthlyExpenses();
            ArrayList<Transaction> monthlyIncomes = monthlyReport.getMonthlyIncome();

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
        String fileName = "y.2024.csv";
        ArrayList<String> fileLines = fileReader.readFileContests(fileName);
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
                System.out.println("За " + month + " месяц ошибок в отчетах нет");
            } else {
                if (monthExpense1 != monthExpense2) {
                    System.out.println("Проверить расходы за " + month + " месяц. (" + monthExpense1 + "/" + monthExpense2 + ")");
                }
                if (monthIncome1 != monthIncome2) {
                    System.out.println("Проверить доходы за " + month + " месяц. (" + monthIncome1 + "/" + monthIncome2 + ")");
                }
            }
        }
    }


}
