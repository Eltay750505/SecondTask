package ru.gmail.gasimov.secondtask.builder;

public enum BankXmlTagEnum {
    BELARUSBANK_DEPOSIT,
    BANK_NAME,
    DEPOSITOR,
    PROFITABILITY,
    TIME_CONSTRAINTS,
    AMOUNT_ON_DEPOSIT,
    DEPOSIT_TYPE,
    BANK_COUNTRY;

    private static final char SEPARATOR = '-';
    private static final char TO_REPLACE = '_';

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace(TO_REPLACE, SEPARATOR);
    }

}
