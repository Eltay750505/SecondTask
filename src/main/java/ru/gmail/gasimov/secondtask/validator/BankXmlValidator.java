package ru.gmail.gasimov.secondtask.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class BankXmlValidator {
    private static Logger logger = LogManager.getLogger();
    private static final String SCHEMA_NAME = "src\\main\\resources\\data\\schema.xsd";
    private static final URL SCHEMA_URL;

    static {
        ClassLoader loader = BankXmlValidator.class.getClassLoader();
        SCHEMA_URL = loader.getResource(SCHEMA_NAME);
    }

    public static boolean validateXMLFile(String xmlPath) throws XmlParserCustomException {
        boolean isValid = true;

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);

        try {
            Schema schema = factory.newSchema(SCHEMA_URL);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
        } catch (IOException e) {
            throw new XmlParserCustomException("Cannot open file: " + xmlPath, e);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "File " + xmlPath + " is not valid: ", e);
            isValid = false;
        } catch (NullPointerException e){
            throw new XmlParserCustomException("Null pointer exception was called" + xmlPath, e);
        }
        return isValid;
    }
}
