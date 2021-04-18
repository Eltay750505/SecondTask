package ru.gmail.gasimov.secondtask.main;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gmail.gasimov.secondtask.builder.DepositBuilder;
import ru.gmail.gasimov.secondtask.builder.DomDepositBuilder;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.model.BankDeposit;

import java.util.Set;


public class MainForDomParser {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        String xmlPath = "src\\main\\resources\\data\\data.xml";
        try {
            DepositBuilder abstractBankDepositBuilder = new DomDepositBuilder();
            abstractBankDepositBuilder.buildSetBanks(xmlPath);
            Set<BankDeposit> bankDeposits = abstractBankDepositBuilder.getDepositSet();
            for (BankDeposit bankDeposit : bankDeposits) {
                System.out.println(bankDeposit);
            }
        } catch (XmlParserCustomException e) {
            LOGGER.log(Level.ERROR, "Cannot create current builder " + e);
        }


    }
}
