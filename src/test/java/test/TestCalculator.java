package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.EmailPage;
import utils.StringParser;


public class TestCalculator extends CommonConditionTest {


    @Test
    public void checkCostFromEmail() {

        String totalEstimate = calculatorGooglePage.getTotalCost();

        calculatorGooglePage.emailEstimate();

        EmailPage emailPage = new EmailPage(driver).open();

        calculatorGooglePage.switchToHandle(1);

        String emailAddress = emailPage.getAddress();

        emailPage.switchToHandle(0);

        calculatorGooglePage.switchFrame().getEmailPopUpPage().sendToEmail(emailAddress);

        calculatorGooglePage.switchToHandle(1);

        String totalEstimateFromEmail = emailPage.getEstimateFromMessage();

        Assert.assertEquals(StringParser.getCostFromStringEstimate(totalEstimate),
                StringParser.getCostFromStringEstimate(totalEstimateFromEmail));

    }

    @Test
    public void checkInstanceType() {
        Assert.assertEquals(calculatorGooglePage.getResultInstanceTypeCompute(), MACHINE_TYPE);
    }

    @Test
    public void checkRegion() {
        Assert.assertEquals(calculatorGooglePage.getResultComputeRegion(), LOCATION);
    }

    @Test
    public void checkCostPerMonth() {

        Assert.assertEquals(
                StringParser.findDoubleInString(calculatorGooglePage.getResultCostMonth()),
                Double.parseDouble(READY_COST_MONTH));
    }


}
