package ru.gmail.gasimov.secondtask.builder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Set;

public class DomDepositBuilder extends DepositBuilder {
    private DocumentBuilder documentBuilder;

    public DomDepositBuilder() throws XmlParserCustomException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new XmlParserCustomException("Cant configure the domBuilder", e);
        }
    }

    public Set<BankDeposit> getDeposits() {
        return super.getDepositSet();
    }

    @Override
    public void buildSetBanks(String pathName) throws XmlParserCustomException {
        if (pathName == null || pathName == ""){
            throw new XmlParserCustomException("Wrong pathName " + pathName);
        }
            Document document;
        try {
            document = documentBuilder.parse(pathName);
            Element root = document.getDocumentElement();

            String individualDepositTagName = DepositXmlTag.INDIVIDUAL_DEPOSIT.toString();
            String legalEntityDepositTagName = DepositXmlTag.LEGAL_ENTITY_DEPOSIT.toString();

            NodeList individualDeposits = root.getElementsByTagName(individualDepositTagName);
            NodeList legalEntityDeposits = root.getElementsByTagName(legalEntityDepositTagName);

            for (int i = 0; i < individualDeposits.getLength(); i++) {
                Element element = (Element) individualDeposits.item(i);
                BankDeposit deposit = buildEntity(element, 0);
                depositSet.add(deposit);
            }

            for (int i = 0; i < legalEntityDeposits.getLength(); i++) {
                Element element = (Element) legalEntityDeposits.item(i);
                BankDeposit deposit = buildEntity(element, 1);
                depositSet.add(deposit);
            }
        } catch (SAXException e) {
            throw new XmlParserCustomException("Cannot parse current file" + e);
        } catch (IOException e) {
            throw new XmlParserCustomException("Cannot open current file" + e);
        }
    }

    private BankDeposit buildEntity(Element element, int flag) {
        BankDeposit bankDeposit;
        if (flag == 0) {
            bankDeposit = new IndividualDeposit();
        } else {
            bankDeposit = new LegalEntityDeposit();
        }
        String idTag = DepositAttribute.ID.toString();
        String isOpenAttribute = DepositAttribute.IS_OPEN.toString();
        String bankNameTag = DepositXmlTag.BANK_NAME.toString();
        String profitAbilityTag = DepositXmlTag.PROFITABILITY.toString();
        String timeConstraintsTag = DepositXmlTag.TIME_CONSTRAINTS.toString();
        String amountOnDepositTag = DepositXmlTag.AMOUNT_ON_DEPOSIT.toString();
        String depositTypeTag = DepositXmlTag.DEPOSIT_TYPE.toString();
        String bankCountryTag = DepositXmlTag.BANK_COUNTRY.toString();
        String depositorNameTag = DepositXmlTag.DEPOSITOR_NAME.toString();
        String enterPriseNameTag = DepositXmlTag.ENTERPRISE_NAME.toString();

        String id = element.getAttribute(idTag);
        Boolean isOpen = Boolean.parseBoolean(element.getAttribute(isOpenAttribute));
        String bankName = getElementTextContent(element, bankNameTag);
        double profitAbility = Double.parseDouble(getElementTextContent(element, profitAbilityTag));
        YearMonth timeConstraints = YearMonth.parse(getElementTextContent(element, timeConstraintsTag));
        double amountOnDeposit = Double.parseDouble(getElementTextContent(element, amountOnDepositTag));
        DepositType depositType = DepositType.valueOf(getElementTextContent(element, depositTypeTag));
        BankCountry bankCountry = BankCountry.valueOf(getElementTextContent(element, bankCountryTag));

        if (bankDeposit instanceof IndividualDeposit) {
            String depositorName = getElementTextContent(element, depositorNameTag);
            bankDeposit = IndividualDeposit
                    .newBuilder()
                    .id(id)
                    .isOpen(isOpen)
                    .bankName(bankName)
                    .profitAbility(profitAbility)
                    .timeConstraints(timeConstraints)
                    .amountOnDeposit(amountOnDeposit)
                    .depositType(depositType)
                    .bankCountry(bankCountry)
                    .depositorName(depositorName)
                    .build();
        } else {
            String enterPriseName = getElementTextContent(element, enterPriseNameTag);
            bankDeposit = LegalEntityDeposit
                    .newBuilder()
                    .id(id)
                    .isOpen(isOpen)
                    .bankName(bankName)
                    .profitAbility(profitAbility)
                    .timeConstraints(timeConstraints)
                    .amountOnDeposit(amountOnDeposit)
                    .depositType(depositType)
                    .bankCountry(bankCountry)
                    .enterpriseName(enterPriseName)
                    .build();
        }

        return bankDeposit;
    }

    private String getElementTextContent(Element element, String nameTag) {
        NodeList nodeList = element.getElementsByTagName(nameTag);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
