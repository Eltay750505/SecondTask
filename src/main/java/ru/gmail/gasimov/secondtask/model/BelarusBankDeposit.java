package ru.gmail.gasimov.secondtask.model;

import java.time.YearMonth;
import java.util.Objects;

public class BelarusBankDeposit extends Banks {
    private BankCountryEnum bankCountryEnum;
    private DepositTypeEnum depositTypeEnum;

    public BelarusBankDeposit() {
        this.bankCountryEnum = BankCountryEnum.BELARUS;
        this.depositTypeEnum = DepositTypeEnum.DEMAND;
    }

    private BelarusBankDeposit(Builder builder) {
        setId(builder.id);
        setBankName(builder.bankName);
        setDepositor(builder.depositor);
        setProfitAbility(builder.profitAbility);
        setTimeConstraints(builder.timeConstraints);
        setAmountOnDeposit(builder.amountOnDeposit);
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
        BelarusBankDeposit that = (BelarusBankDeposit) obj;
        return super.equals(that) &&
                Objects.equals(depositTypeEnum, that.depositTypeEnum) &&
                Objects.equals(bankCountryEnum, that.bankCountryEnum);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int hash = 31;
        result += hash + this.depositTypeEnum.hashCode();
        result += hash + this.bankCountryEnum.hashCode();
        return result;
    }

    public BankCountryEnum getBankCountryEnum() {
        return bankCountryEnum;
    }

    public DepositTypeEnum getDepositTypeEnum() {
        return depositTypeEnum;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String id;
        private String accountId;
        private String bankName;
        private String depositor;
        private double profitAbility;
        private YearMonth timeConstraints;
        private double amountOnDeposit;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder accountId(String val) {
            accountId = val;
            return this;
        }

        public Builder bankName(String val) {
            bankName = val;
            return this;
        }

        public Builder depositor(String val) {
            depositor = val;
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

        public Builder amountOnDeposit(double val) {
            amountOnDeposit = val;
            return this;
        }

        public BelarusBankDeposit build() {
            return new BelarusBankDeposit(this);
        }
    }
}
