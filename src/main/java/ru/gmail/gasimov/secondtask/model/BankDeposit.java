package ru.gmail.gasimov.secondtask.model;

import java.time.YearMonth;
import java.util.Objects;

public abstract class BankDeposit {
    private String id;
    private String bankName;

    private double profitAbility;

    private YearMonth timeConstraints;
    private double amountOnDeposit;
    private boolean isOpen;
    private BankCountry bankCountry;
    private DepositType depositType;
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public BankCountry getBankCountry() {
        return bankCountry;
    }

    public void setBankCountry(BankCountry bankCountry) {
        this.bankCountry = bankCountry;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BankDeposit banks = (BankDeposit) obj;

        return Double.compare(banks.profitAbility, profitAbility) == 0 &&
                Double.compare(banks.amountOnDeposit, amountOnDeposit) == 0 &&
                Objects.equals(id, banks.id) &&
                Objects.equals(bankName, banks.bankName) &&
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankDeposit{");
        sb.append("id='").append(id).append('\'');
        sb.append(", bankName='").append(bankName).append('\'');
        sb.append(", profitAbility=").append(profitAbility);
        sb.append(", timeConstraints=").append(timeConstraints);
        sb.append(", amountOnDeposit=").append(amountOnDeposit);
        sb.append(", isOpen=").append(isOpen);
        sb.append(", bankCountry=").append(bankCountry);
        sb.append(", depositType=").append(depositType);
        sb.append('}');
        return sb.toString();
    }
}
