package test;

import model.GPU;
import page.CalculatorGooglePage;
import page.EmailPage;
import page.HomeGoogleCloudPage;
import page.ResultGooglePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ParseString;

import java.util.ArrayList;

public class TestGoogleCalculator {
    private WebDriver driver;
    private CalculatorGooglePage calculatorGooglePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = new ChromeDriver();
        calculatorGooglePage = addOptionsToCalculatorAndEstimateAll();
    }

    @Test
    public void checkVMClass() {
        Assert.assertEquals(calculatorGooglePage.getResultVMclass(), "regular");
    }

    @Test
    public void checkInstanceType() {
        Assert.assertEquals(calculatorGooglePage.getResultInstanceTypeCompute(), "e2-standard-8");
    }

    @Test
    public void checkRegion() {
        Assert.assertEquals(calculatorGooglePage.getResultComputeRegion(), "Frankfurt");
    }

    @Test
    public void checkCostPerMonth() {

        Assert.assertEquals(
                ParseString.findDoubleInString(calculatorGooglePage.getResultCostMonth()), 635.29);
    }

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


    private CalculatorGooglePage addOptionsToCalculatorAndEstimateAll() {
        ResultGooglePage resultGooglePage = new HomeGoogleCloudPage(driver).open()
                .searchByText("Google Cloud Platform Pricing Calculator");

        calculatorGooglePage = resultGooglePage.chooseLinkContainsSearchingText("#");

        return calculatorGooglePage.inputInstances("#").selectMachineType("#")
                .selectLocation("#").selectCommittedUsage("#").addGPU(new GPU(1,"#"))
                .selectNode(1).addSsd("#").estimateInstancesField().estimateNodesField();
    }



    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
