package test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.EmailPage;
import utils.ParseString;

import java.util.ArrayList;

// mvn -Dbrowser=chrome -DsuiteXmlFile=src\test\resources\testng-smoke.xml clean test
// mvn -Dbrowser=chrome -Denvironment=calculator -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml clean test

public class TestCalculator extends CommonConditionTest {

    /*@Test
    public void test(){
        System.out.println("test1");
        Assert.assertTrue(true);
    }

    @Test
    public void test2(){
        System.out.println("test2");
        Assert.assertTrue(true);
    }

    @Test
    public void test3(){
        System.out.println("test3");
        Assert.assertTrue(true);
    }*/

    @Test
    public void checkCostFromEmail() {

        String totalEstimate = calculatorGooglePage.getTotalCost();

        calculatorGooglePage.emailEstimate();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open('https://10minutemail.com/');");

        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(handles.get(1));

        EmailPage emailPage = new EmailPage(driver);

        String emailAddress = emailPage.getAdress();
        driver.switchTo().window(handles.get(0));

        calculatorGooglePage.switchFrame().sendToEmail(emailAddress);


        driver.switchTo().window(handles.get(1));

        String totalEstimateFromEmail = emailPage.getEstimateFromMessage();

        Assert.assertEquals(ParseString.getCostFromStringEstimate(totalEstimate),
                ParseString.getCostFromStringEstimate(totalEstimateFromEmail));
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
                ParseString.findDoubleInString(calculatorGooglePage.getResultCostMonth()),
                Double.parseDouble(READY_COST_MONTH));
    }



}
