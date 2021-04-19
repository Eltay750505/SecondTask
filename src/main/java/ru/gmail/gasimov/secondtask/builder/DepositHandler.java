package ru.gmail.gasimov.secondtask.builder;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.gmail.gasimov.secondtask.model.*;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DepositHandler extends DefaultHandler {
    private static final String TO_REPLACE = "-";
    private static final String NEW_CHAR = "_";
    private Set<BankDeposit> bankDeposits;
    private BankDeposit current;
    private EnumSet<DepositXmlTag> tagsWithTextContent;
    private DepositXmlTag currentTag;

    public DepositHandler() {
        bankDeposits = new HashSet<>();
        tagsWithTextContent = EnumSet.range(DepositXmlTag.BANK_NAME, DepositXmlTag.AMOUNT_ON_DEPOSIT);
    }

    public Set<BankDeposit> getDeposits() {
        return bankDeposits;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String individualDepositTag = DepositXmlTag.INDIVIDUAL_DEPOSIT.toString();
        String legalEntityDepositTag = DepositXmlTag.LEGAL_ENTITY_DEPOSIT.toString();

        if (individualDepositTag.equals(qName) || legalEntityDepositTag.equals(qName)) {
            current = individualDepositTag.equals(qName)
                    ? new IndividualDeposit() : new LegalEntityDeposit();

            String idAttribute = DepositAttribute.ID.toString();

            for (int i = 0; i < attributes.getLength(); i++) {
                String attributeName = attributes.getQName(i);

                if (attributeName.equals(idAttribute)) {
                    String depositId = attributes.getValue(i);
                    current.setId(depositId);
                } else {
                    String isOpen = attributes.getValue(i);
                    boolean isOpenBoolean = Boolean.parseBoolean(isOpen);
                    current.setOpen(isOpenBoolean);
                }
            }
        } else {
            String castToEnum = castToEnum(qName);
            DepositXmlTag tag = DepositXmlTag.valueOf(castToEnum);

            if (tagsWithTextContent.contains(tag)) {
                currentTag = tag;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);

        if (currentTag != null) {
            switch (currentTag) {
                case BANK_NAME: {
                    current.setBankName(data);
                    break;
                }
                case PROFITABILITY: {
                    current.setProfitAbility(Double.parseDouble(data));
                    break;
                }
                case TIME_CONSTRAINTS: {
                    YearMonth yearMonth = YearMonth.parse(data);
                    current.setTimeConstraints(yearMonth);
                    break;
                }
                case AMOUNT_ON_DEPOSIT: {
                    current.setAmountOnDeposit(Double.parseDouble(data));
                    break;
                }
                case DEPOSIT_TYPE: {
                    DepositType depositType = DepositType.valueOf(data);
                    current.setDepositType(depositType);
                    break;
                }
                case BANK_COUNTRY: {
                    BankCountry bankCountry = BankCountry.valueOf(data);
                    current.setBankCountry(bankCountry);
                    break;
                }
                case DEPOSITOR_NAME: {
                    IndividualDeposit deposit = (IndividualDeposit) current;
                    deposit.setDepositorName(data);
                    break;
                }
                case ENTERPRISE_NAME: {
                    LegalEntityDeposit deposit = (LegalEntityDeposit) current;
                    deposit.setEnterpriseName(data);
                    break;
                }
                default: {
                    throw new EnumConstantNotPresentException(
                            currentTag.getDeclaringClass(), currentTag.name());
                }
            }

            currentTag = null;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String individualDepositTag = DepositXmlTag.INDIVIDUAL_DEPOSIT.toString();
        String legalEntityDepositTag = DepositXmlTag.LEGAL_ENTITY_DEPOSIT.toString();

        if (individualDepositTag.equals(qName) || legalEntityDepositTag.equals(qName)) {
            bankDeposits.add(current);
            current = null;
        }
    }

    private String castToEnum(String qName) {
        return qName
                .trim()
                .replace(TO_REPLACE, NEW_CHAR)
                .toUpperCase();
    }
}