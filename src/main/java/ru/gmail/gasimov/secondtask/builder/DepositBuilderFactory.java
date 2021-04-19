package ru.gmail.gasimov.secondtask.builder;

import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;

public class DepositBuilderFactory {
    private enum TypeParser {
        SAX, DOM, STAX
    }

    public DepositBuilderFactory() {
    }

    public static DepositBuilder createDepositBuilder(String typeParser) throws XmlParserCustomException {
        TypeParser type = TypeParser.valueOf(typeParser);
        switch (type) {
            case DOM: {
                return new DomDepositBuilder();
            }
            case SAX: {
                return new SaxDepositBuilder();
            }
            case STAX: {
                return new StaxDepositBuilder();
            }
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
