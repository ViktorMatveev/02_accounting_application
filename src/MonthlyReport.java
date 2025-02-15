import java.util.ArrayList;
import java.util.HashMap;

/*
мапа, где:
    ключ - тип операции (расход/доход)
    значение - список операций
 */

// TODO: 15.02.2025 Комментарии нужны только полезные

public class MonthlyReport {
    private final String EXPENSES = "расходы"; // TODO: 15.02.2025 Константы в джава объявляются через следующие слова: private static final
    private final String INCOMES = "доходы";// TODO: 15.02.2025 Добавить static
    private String mostProfitableProductName;
    private int mostProfitableProductValue = 0;
    private String mostExpendableProductName;
    private int mostExpendableProductValue = 0;
    private int monthTotalExpenses;
    private int monthTotalIncome;

    HashMap<String, ArrayList<Transaction>> monthlyReportByCategories;

    public MonthlyReport() {
        monthlyReportByCategories = new HashMap<>();
        monthlyReportByCategories.put(EXPENSES, new ArrayList<Transaction>()); // TODO: 15.02.2025 Убрать уточнение дженерика
        monthlyReportByCategories.put(INCOMES, new ArrayList<Transaction>());
    }

    public ArrayList<Transaction> getMonthlyExpenses() {
        return monthlyReportByCategories.get(EXPENSES);
    }

    public ArrayList<Transaction> getMonthlyIncome() {
        return monthlyReportByCategories.get(INCOMES);
    }

    public void printMostProfitableProduct() {
        ArrayList<Transaction> incomeTransactions = monthlyReportByCategories.get(INCOMES);
        for (Transaction transaction : incomeTransactions) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            if (transactionValue > mostProfitableProductValue) {
                mostProfitableProductValue = transactionValue;
                mostProfitableProductName = transaction.getName();
            }
        }
        System.out.println("Самый прибыльный товар: " + mostProfitableProductName + " (доход составил: " + mostProfitableProductValue + ")");
    }

    public void printMostExpendableProduct() {
        ArrayList<Transaction> expenseTransaction = monthlyReportByCategories.get(EXPENSES);
        for (Transaction transaction : expenseTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            if (transactionValue > mostExpendableProductValue) {
                mostExpendableProductValue = transactionValue;
                mostExpendableProductName = transaction.getName();
            }
        }
        System.out.println("Самый большой расход: " + mostExpendableProductName + " (расход составил: " + mostExpendableProductValue + ")");
    }

    public int getMonthTotalExpenses() {
        ArrayList<Transaction> expenseTransaction = monthlyReportByCategories.get(EXPENSES);
        for (Transaction transaction : expenseTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            monthTotalExpenses += transactionValue;
        }
        return monthTotalExpenses;
    }

    public int getMonthTotalIncome() {
        ArrayList<Transaction> incomeTransaction = monthlyReportByCategories.get(INCOMES);
        for (Transaction transaction : incomeTransaction) {
            int transactionValue = transaction.getUnitPrice() * transaction.getQuantity();
            monthTotalIncome += transactionValue;
        }
        return monthTotalIncome;
    }
}