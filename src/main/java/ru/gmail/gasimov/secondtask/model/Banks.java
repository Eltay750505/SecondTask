package ru.gmail.gasimov.secondtask.model;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Banks {
    private String id;
    private String bankName;
    private String depositor;
    private double profitAbility;
    private YearMonth timeConstraints;
    private double amountOnDeposit;
    private boolean isOpen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public double getProfitAbility() {
        return profitAbility;
    }

    public void setProfitAbility(double profitAbility) {
        this.profitAbility = profitAbility;
    }

    public YearMonth getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(YearMonth timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public double getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(double amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Banks banks = (Banks) obj;

        return Double.compare(banks.profitAbility, profitAbility) == 0 &&
                Double.compare(banks.amountOnDeposit, amountOnDeposit) == 0 &&
                Objects.equals(id, banks.id) &&
                Objects.equals(bankName, banks.bankName) &&
                Objects.equals(depositor, banks.depositor) &&
                Objects.equals(timeConstraints, banks.timeConstraints);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int hash = 31;
        result += hash + this.id.hashCode();
        result += hash + this.hashCode();
        return result;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
