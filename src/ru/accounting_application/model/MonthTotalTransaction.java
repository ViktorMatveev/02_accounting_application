package ru.accounting_application.model;

public class MonthTotalTransaction {
    private int monthTotalExpense;
    private int monthTotalIncome;

    public void setMonthTotalExpense(int monthTotalExpense) {
        this.monthTotalExpense = monthTotalExpense;
    }

    public void setMonthTotalIncome(int monthTotalIncome) {
        this.monthTotalIncome = monthTotalIncome;
    }

    public int getMonthTotalExpense() {
        return monthTotalExpense;
    }

    public int getMonthTotalIncome() {
        return monthTotalIncome;
    }
}
