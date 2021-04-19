package ru.gmail.gasimov.secondtask.buildertest;

import junit.framework.TestCase;
import org.junit.Test;
import ru.gmail.gasimov.secondtask.builder.DepositBuilder;
import ru.gmail.gasimov.secondtask.builder.DepositBuilderFactory;
import ru.gmail.gasimov.secondtask.exception.XmlParserCustomException;
import ru.gmail.gasimov.secondtask.model.*;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BankXmlBuilderTest extends TestCase {
    private Set<BankDeposit> expectedSet;
    private static final List<String> PARSER_NAMES = Arrays.asList(new String[]{"DOM", "SAX", "STAX"});
    private static final String PATH_NAME = "src\\test\\resources\\testData.xml";

    public void setUp(){
        expectedSet = new HashSet<>();

        for (int i = 1; i <= 3; i++) {
            IndividualDeposit individualDeposit = IndividualDeposit
                    .newBuilder()
                    .id("w" + i)
                    .isOpen(true)
                    .bankName("Belarusbank")
                    .profitAbility(2.3)
                    .timeConstraints(YearMonth.parse("2020-01"))
                    .amountOnDeposit(610.0)
                    .depositType(DepositType.TERM)
                    .bankCountry(BankCountry.BELARUS)
                    .depositorName("Egor")
                    .build();
            expectedSet.add(individualDeposit);
        }

        for (int i = 4; i <= 6; i++) {
            LegalEntityDeposit legalEntityDeposit = LegalEntityDeposit
                    .newBuilder()
                    .id("w" + i)
                    .isOpen(true)
                    .bankName("BPSSberbank")
                    .profitAbility(5.9)
                    .timeConstraints(YearMonth.parse("2020-07"))
                    .amountOnDeposit(122.0)
                    .depositType(DepositType.TERM)
                    .bankCountry(BankCountry.BELARUS)
                    .enterpriseName("ISSOFT")
                    .build();
            expectedSet.add(legalEntityDeposit);
        }
    }

    @Test
    public void testDomParser() throws XmlParserCustomException {
        String typeParser = PARSER_NAMES.get(0);
        DepositBuilder depositBuilder = DepositBuilderFactory
                .createDepositBuilder(typeParser);
        depositBuilder.buildSetBanks(PATH_NAME);
        Set<BankDeposit> actualSet = depositBuilder.getDepositSet();
        assertEquals(expectedSet, actualSet);
    }

    @Test
    public void testSaxParser() throws XmlParserCustomException {
        String typeParser = PARSER_NAMES.get(1);
        DepositBuilder depositBuilder = DepositBuilderFactory
                .createDepositBuilder(typeParser);
        depositBuilder.buildSetBanks(PATH_NAME);
        Set<BankDeposit> actualSet = depositBuilder.getDepositSet();
        assertEquals(expectedSet, actualSet);
    }

    @Test
    public void testStaxParser() throws XmlParserCustomException {
        String typeParser = PARSER_NAMES.get(2);
        DepositBuilder depositBuilder = DepositBuilderFactory
                .createDepositBuilder(typeParser);
        depositBuilder.buildSetBanks(PATH_NAME);
        Set<BankDeposit> actualSet = depositBuilder.getDepositSet();
        assertEquals(expectedSet, actualSet);
    }
}