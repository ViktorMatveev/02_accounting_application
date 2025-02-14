import java.util.HashMap;
import java.util.Set;
/*
мапа, где
    ключ - номер месяца
    значение - массив
 */


public class MonthTotalPerYear {
    HashMap<Integer, MonthlyReport> monthTotalPerYearReport;

    public MonthTotalPerYear() {
        monthTotalPerYearReport = new HashMap<>();
    }

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
            System.out.println("Статистика за " + month + " месяц:");
            monthlyReport.printMostProfitableProduct();
            monthlyReport.printMostExpendableProduct();
        }
    }

    public MonthlyReport getDataPerMonth(int month) {
        return monthTotalPerYearReport.get(month);
    }
}
