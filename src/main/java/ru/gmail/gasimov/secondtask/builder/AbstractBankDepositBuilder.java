package ru.gmail.gasimov.secondtask.builder;

import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.model.Banks;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBankDepositBuilder {
    protected Set<Banks> banks;

    public AbstractBankDepositBuilder() {
        banks = new HashSet<>();
    }

    public AbstractBankDepositBuilder(Set<Banks> banks) {
        this.banks = banks;
    }

    public Set<Banks> getBanks() {
        return banks;
    }

    public abstract void buildSetBanks(String pathName) throws XmlParserCustomException;
}
