package ru.gmail.gasimov.secondtask.builder;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxDepositBuilder extends DepositBuilder {
    private XMLReader xmlReader;
    private DepositHandler depositHandler;

    public SaxDepositBuilder() throws XmlParserCustomException {
        depositHandler = new DepositHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            xmlReader = parser.getXMLReader();
            xmlReader.setContentHandler(depositHandler);
            xmlReader.setErrorHandler(new DepositErrorHandler());
        } catch (SAXException | ParserConfigurationException e) {
            throw new XmlParserCustomException("Unable to configure SAX parser", e);
        }
    }

    @Override
    public void buildSetBanks(String pathName) throws XmlParserCustomException {
        if (pathName == null || pathName == ""){
            throw new XmlParserCustomException("Wrong pathName " + pathName);
        }
        try {
            xmlReader.parse(pathName);
        } catch (IOException e) {
            throw new XmlParserCustomException("Unable to open XML file (" + pathName + ")", e);
        } catch (SAXException e) {
            throw new XmlParserCustomException("Unable to parse XML file (" + pathName + ")", e);
        }
        depositSet = depositHandler.getDeposits();
    }
}
