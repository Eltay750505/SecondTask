package ru.gmail.gasimov.secondtask.builder;

import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.model.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Optional;

public class StaxDepositBuilder extends DepositBuilder {
    private static final char TO_REPLACE = '-';
    private static final char NEW_CHAR = '_';

    private final String INDIVIDUAL_DEPOSIT_TAG = DepositXmlTag.INDIVIDUAL_DEPOSIT.toString();
    private final String LEGAL_ENTITY_DEPOSIT_TAG = DepositXmlTag.LEGAL_ENTITY_DEPOSIT.toString();

    private XMLInputFactory inputFactory;

    public StaxDepositBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        depositSet = new HashSet<>();
    }

    @Override
    public void buildSetBanks(String pathName) throws XmlParserCustomException {
        if (pathName == null || pathName == ""){
            throw new XmlParserCustomException("Wrong pathName " + pathName);
        }
        File file = new File(pathName);

        if (!file.exists() || file.isDirectory()) {
            throw new XmlParserCustomException("Unable to open file with path: " + pathName);
        }

        XMLStreamReader reader;
        String name;

        try {
            Source source = new StreamSource(pathName);
            reader = inputFactory.createXMLStreamReader(source);

            while (reader.hasNext()) {
                int type = reader.next();

                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    if (name.equals(INDIVIDUAL_DEPOSIT_TAG)) {
                        BankDeposit deposit = new IndividualDeposit();
                        buildEntity(deposit, reader);
                        depositSet.add(deposit);
                    }

                    if (name.equals(LEGAL_ENTITY_DEPOSIT_TAG)) {
                        BankDeposit deposit = new LegalEntityDeposit();
                        buildEntity(deposit, reader);
                        depositSet.add(deposit);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new XmlParserCustomException("Error was called during file reading ", e);
        }
    }

    private void buildEntity(BankDeposit deposit, XMLStreamReader reader) throws XMLStreamException, XmlParserCustomException {
        String idAttribute = DepositAttribute.ID.toString();
        String isOpenAttribute = DepositAttribute.IS_OPEN.toString();
        String depositId = reader.getAttributeValue(null, idAttribute);
        String isOpen = reader.getAttributeValue(null, isOpenAttribute);

        deposit.setId(depositId);
        boolean isOpenBoolean = Boolean.parseBoolean(isOpen);
        deposit.setOpen(isOpenBoolean);
        String name;

        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    name = reader.getLocalName();
                    String constantName = castToEnum(name);
                    DepositXmlTag tag = DepositXmlTag.valueOf(constantName);
                    initializeFields(reader, tag, deposit);
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    name = reader.getLocalName();

                    if (name.equals(INDIVIDUAL_DEPOSIT_TAG) || name.equals(LEGAL_ENTITY_DEPOSIT_TAG)) {
                        return;
                    }
                    break;
                }
            }
        }
    }

    private void initializeFields(XMLStreamReader reader, DepositXmlTag tag, BankDeposit deposit) throws XmlParserCustomException, XMLStreamException {
        String data = getTextContent(reader)
                .orElseThrow(() -> new XmlParserCustomException("Unable to get text content"));


        switch (tag) {
            case BANK_NAME: {
                deposit.setBankName(data);
                break;
            }
            case PROFITABILITY: {
                deposit.setProfitAbility(Double.parseDouble(data));
                break;
            }
            case TIME_CONSTRAINTS: {
                YearMonth yearMonth = YearMonth.parse(data);
                deposit.setTimeConstraints(yearMonth);
                break;
            }
            case AMOUNT_ON_DEPOSIT: {
                deposit.setAmountOnDeposit(Double.parseDouble(data));
                break;
            }
            case DEPOSIT_TYPE: {
                DepositType depositType = DepositType.valueOf(data);
                deposit.setDepositType(depositType);
                break;
            }
            case BANK_COUNTRY: {
                BankCountry bankCountry = BankCountry.valueOf(data);
                deposit.setBankCountry(bankCountry);
                break;
            }
            case DEPOSITOR_NAME: {
                IndividualDeposit depositTemp = (IndividualDeposit) deposit;
                depositTemp.setDepositorName(data);
                break;
            }
            case ENTERPRISE_NAME: {
                LegalEntityDeposit depositTemp = (LegalEntityDeposit) deposit;
                depositTemp.setEnterpriseName(data);
                break;
            }
            default: {
                throw new EnumConstantNotPresentException(
                        tag.getDeclaringClass(), tag.name());
            }
        }
    }

    private Optional<String> getTextContent(XMLStreamReader reader) throws XMLStreamException {
        Optional<String> result = Optional.empty();

        if (reader.hasNext()) {
            reader.next();
            String text = reader.getText();
            result = Optional.of(text);
        }

        return result;
    }

    private String castToEnum(String qName) {
        return qName
                .trim()
                .replace(TO_REPLACE, NEW_CHAR)
                .toUpperCase();
    }
}
