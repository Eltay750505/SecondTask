package ru.gmail.gasimov.secondtask.builder;

public enum DepositAttribute {
    ID,
    IS_OPEN;

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace(UNDERSCORE, HYPHEN);
    }
}
