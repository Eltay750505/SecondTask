package ru.gmail.gasimov.secondtask.builder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomDepositBuilder extends AbstractBankDepositBuilder {
    private DocumentBuilder documentBuilder;


    public DomDepositBuilder() throws XmlParserCustomException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new XmlParserCustomException("Cant configure the domBuilder", e);
        }
    }

    @Override
    public void buildSetBanks(String pathName) throws XmlParserCustomException {
        Document document = null;
        try {
            document = documentBuilder.parse(pathName);
            Element root = document.getDocumentElement();
            String belarusBankTagName = BankXmlTagEnum.BELARUSBANK_DEPOSIT.toString();
            NodeList banksList = root.getElementsByTagName(belarusBankTagName);
        } catch (SAXException e) {
            throw new XmlParserCustomException("Cannot to parse current file" + e);
        } catch (IOException e) {
            throw new XmlParserCustomException("Cannot to open current file" + e);
        }
    }
}
