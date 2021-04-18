package ru.gmail.gasimov.secondtask.model;

import java.time.YearMonth;
import java.util.Objects;

public class IndividualDeposit extends BankDeposit {
    private String depositorName;

    public IndividualDeposit() {
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public IndividualDeposit(String depositorName) {
        this.depositorName = depositorName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        IndividualDeposit that = (IndividualDeposit) obj;
        return super.equals(that) &&
                Objects.equals(depositorName, that.depositorName);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int hash = 31;
        result += hash + this.depositorName.hashCode();
        return result;
    }

    private IndividualDeposit(Builder builder) {
        setId(builder.id);
        setBankName(builder.bankName);
        setProfitAbility(builder.profitAbility);
        setTimeConstraints(builder.timeConstraints);
        setAmountOnDeposit(builder.amountOnDeposit);
        depositorName = builder.depositorName;
        setBankCountry(builder.bankCountry);
        setDepositType(builder.depositType);
        setOpen(builder.isOpen);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String bankName;
        private double profitAbility;
        private YearMonth timeConstraints;
        private double amountOnDeposit;
        private String depositorName;

        private DepositType depositType;
        private BankCountry bankCountry;
        private boolean isOpen;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder bankName(String val) {
            bankName = val;
            return this;
        }

        public Builder profitAbility(double val) {
            profitAbility = val;
            return this;
        }

        public Builder timeConstraints(YearMonth val) {
            timeConstraints = val;
            return this;
        }

        public Builder isOpen(boolean val){
            isOpen = val;
            return this;
        }

        public Builder amountOnDeposit(double val) {
            amountOnDeposit = val;
            return this;
        }

        public Builder depositType(DepositType val) {
            depositType = val;
            return this;
        }

        public Builder bankCountry(BankCountry val) {
            bankCountry = val;
            return this;
        }

        public Builder depositorName(String val) {
            depositorName = val;
            return this;
        }

        public IndividualDeposit build() {
            return new IndividualDeposit(this);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Individual Deposit {").append(System.lineSeparator());
        sb.append(" id = ").append(super.getId()).append(System.lineSeparator());
        sb.append(" bankName = ").append(super.getBankName()).append(System.lineSeparator());
        sb.append(" profitAbility = ").append(super.getProfitAbility()).append(System.lineSeparator());
        sb.append(" timeConstraints = ").append(super.getTimeConstraints()).append(System.lineSeparator());
        sb.append(" amountOnDeposit = ").append(super.getAmountOnDeposit()).append(System.lineSeparator());
        sb.append(" isOpen = ").append(super.isOpen()).append(System.lineSeparator());
        sb.append(" bankCountry = ").append(super.getBankCountry()).append(System.lineSeparator());
        sb.append(" depositType = ").append(super.getDepositType()).append(System.lineSeparator());
        sb.append(" depositorName = ").append(depositorName).append(System.lineSeparator());
        sb.append('}').append(System.lineSeparator());
        sb.append("=========================================================");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
