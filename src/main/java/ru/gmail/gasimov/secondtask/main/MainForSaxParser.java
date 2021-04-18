package ru.gmail.gasimov.secondtask.main;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gmail.gasimov.secondtask.builder.SaxDepositBuilder;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.model.BankDeposit;

import java.util.Set;

public class MainForSaxParser {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        String xmlPath = "src\\main\\resources\\data\\data.xml";
        try {
            SaxDepositBuilder saxDepositBuilder = new SaxDepositBuilder();
            saxDepositBuilder.buildSetBanks(xmlPath);
            Set<BankDeposit> depositSet = saxDepositBuilder.getDepositSet();
            for (BankDeposit bankDeposit : depositSet) {
                System.out.println(bankDeposit);
            }
        } catch (XmlParserCustomException e) {
            LOGGER.log(Level.ERROR, "Cannot create current builder " + e);
        }
    }
}
