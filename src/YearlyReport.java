import java.util.HashMap;

public class YearlyReport {
    private int currentYear = 2024;
    private HashMap<Integer, int[]> yearlyReport; //месяц - [расходы, доходы]

    public YearlyReport() {
        yearlyReport = new HashMap<>();
    }

    public void addData(String data) {
        String[] reportData = data.split(",");
        int currentMonth = Integer.parseInt(reportData[0]);
        int currentSum = Integer.parseInt(reportData[1]);

        if (yearlyReport.containsKey(currentMonth)) {
            int[] totalMonthlyOperations = yearlyReport.get(currentMonth);
            if (reportData[2].equals("true")) {
                totalMonthlyOperations[0] = currentSum;
            } else {
                totalMonthlyOperations[1] = currentSum;
            }
        } else {
            int[] totalMonthlyOperations = new int[2];
            if (reportData[2].equals("true")) {
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
            System.out.println("рассматриваемый год - " + this.currentYear);
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
            System.out.println("За " + month + " месяц прибыль составила: " + (totalMonthlyOperations[1] - totalMonthlyOperations[0]));
        }
    }

    public void printAverageExpenses() {
        int yearlyAverageExpense = 0;
        for (int month : yearlyReport.keySet()) {
            int[] totalMonthlyOperations = yearlyReport.get(month);
            yearlyAverageExpense += totalMonthlyOperations[0];
        }
        System.out.println("Средний расход за все месяцы составил: " + yearlyAverageExpense);
    }

    public void printAverageIncome() {
        int yearlyAverageIncome = 0;
        for (int month : yearlyReport.keySet()) {
            int[] totalMonthlyOperations = yearlyReport.get(month);
            yearlyAverageIncome += totalMonthlyOperations[1];
        }
        System.out.println("Средний доход за все месяцы составил: " + yearlyAverageIncome);
    }

    public boolean isReportHasBeenRead() {
        return !yearlyReport.isEmpty();
    }
}
