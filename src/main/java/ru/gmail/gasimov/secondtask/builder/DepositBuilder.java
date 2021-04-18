package ru.gmail.gasimov.secondtask.builder;

import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.model.BankDeposit;

import java.util.HashSet;
import java.util.Set;

public abstract class DepositBuilder {
    protected Set<BankDeposit> depositSet;

    public DepositBuilder() {
        depositSet = new HashSet<>();
    }

    public DepositBuilder(Set<BankDeposit> banks) {
        this.depositSet = banks;
    }

    public Set<BankDeposit> getDepositSet() {
        return depositSet;
    }

    public abstract void buildSetBanks(String pathName) throws XmlParserCustomException;
}
