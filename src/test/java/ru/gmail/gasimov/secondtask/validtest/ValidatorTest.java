package ru.gmail.gasimov.secondtask.validtest;

import junit.framework.TestCase;
import org.testng.annotations.Test;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.validator.BankXmlValidator;

import java.net.URL;

public class ValidatorTest extends TestCase {


    @Test
    public void testValidateXMLFile() throws XmlParserCustomException {
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("src\\test\\resources\\testData.xml");

        boolean actual = BankXmlValidator.validateXMLFile(resource.getPath());
        assertTrue(actual);
    }

    @Test
    public void testValidateXMLFileInvalid() throws XmlParserCustomException {
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("src\\test\\resources\\testDataForValidator.xml");

        boolean actual = BankXmlValidator.validateXMLFile(resource.getPath());
        assertFalse(actual);
    }
}
