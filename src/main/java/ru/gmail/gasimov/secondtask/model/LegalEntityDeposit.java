package ru.gmail.gasimov.secondtask.model;

import java.time.YearMonth;
import java.util.Objects;

public class LegalEntityDeposit extends BankDeposit {

    private String enterpriseName;

    public LegalEntityDeposit() {
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    private LegalEntityDeposit(Builder builder) {
        setId(builder.id);
        setBankName(builder.bankName);
        setProfitAbility(builder.profitAbility);
        setTimeConstraints(builder.timeConstraints);
        setAmountOnDeposit(builder.amountOnDeposit);
        enterpriseName = builder.enterpriseName;
        setBankCountry(builder.bankCountry);
        setDepositType(builder.depositType);
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
        private String enterpriseName;
        public BankCountry bankCountry;
        public DepositType depositType;
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

        public Builder depositType(DepositType val) {
            depositType = val;
            return this;
        }

        public Builder bankCountry(BankCountry val) {
            bankCountry = val;
            return this;
        }

        public Builder timeConstraints(YearMonth val) {
            timeConstraints = val;
            return this;
        }

        public Builder amountOnDeposit(double val) {
            amountOnDeposit = val;
            return this;
        }

        public Builder enterpriseName(String val) {
            enterpriseName = val;
            return this;
        }

        public Builder isOpen(boolean val) {
            isOpen = val;
            return this;
        }

        public LegalEntityDeposit build() {
            return new LegalEntityDeposit(this);
        }
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
        LegalEntityDeposit that = (LegalEntityDeposit) obj;
        return super.equals(that) &&
                Objects.equals(enterpriseName, that.enterpriseName);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int hash = 31;
        result += hash + this.enterpriseName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Legal Entity Deposit {").append(System.lineSeparator());
        sb.append(" id = ").append(super.getId()).append(System.lineSeparator());
        sb.append(" bankName = ").append(super.getBankName()).append(System.lineSeparator());
        sb.append(" profitAbility = ").append(super.getProfitAbility()).append(System.lineSeparator());
        sb.append(" timeConstraints = ").append(super.getTimeConstraints()).append(System.lineSeparator());
        sb.append(" amountOnDeposit = ").append(super.getAmountOnDeposit()).append(System.lineSeparator());
        sb.append(" isOpen = ").append(super.isOpen()).append(System.lineSeparator());
        sb.append(" bankCountry = ").append(super.getBankCountry()).append(System.lineSeparator());
        sb.append(" depositType = ").append(super.getDepositType()).append(System.lineSeparator());
        sb.append(" enterpriseName = ").append(enterpriseName);
        sb.append('}').append(System.lineSeparator());
        sb.append("=========================================================");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
