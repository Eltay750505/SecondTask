package ru.gmail.gasimov.secondtask.builder;

public enum DepositXmlTag {
    BANKS,
    INDIVIDUAL_DEPOSIT,
    LEGAL_ENTITY_DEPOSIT,
    BANK_NAME,
    DEPOSITOR_NAME,
    DEPOSIT_TYPE,
    BANK_COUNTRY,
    ENTERPRISE_NAME,
    PROFITABILITY,
    TIME_CONSTRAINTS,
    AMOUNT_ON_DEPOSIT;

    private static final char SEPARATOR = '-';
    private static final char TO_REPLACE = '_';

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace(TO_REPLACE, SEPARATOR);
    }

}
