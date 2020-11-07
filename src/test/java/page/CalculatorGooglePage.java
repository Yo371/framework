package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.CustomCondition;

import java.util.List;

public class CalculatorGooglePage extends AbstractPage {

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement iframeCloud;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement iframeInsideCloud;

    @FindBy(xpath = "//button[@aria-label=\"Add to Estimate\"]")
    private List<WebElement> buttonsEstimateList;

    @FindBy(xpath = "//button[@aria-label=\"Email Estimate\"]")
    private WebElement emailButton;


    @FindBy(xpath = "//div[contains(text(), 'VM class')]")
    private WebElement resultVM;

    @FindBy(xpath = "//md-content[@id='compute']//div[contains(text(), 'Instance type')]")
    private WebElement resultInstanceTypeCompute;

    @FindBy(xpath = "//md-content[@id='compute']//div[contains(text(), 'Region')]")
    private WebElement resultRegionCompute;

    @FindBy(xpath = "//md-content[@id='compute']//b[contains(text(), 'Estimated Component Cost:')]")
    private WebElement resultCostMonth;

    @FindBy(xpath = "//b[contains(text(), \"Total Estimated Cost\")]")
    private WebElement resultTotalCost;


    public CalculatorGooglePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CalculatorGooglePage switchFrame() {
        CustomCondition.waitElementPresence(driver, By.xpath("//*[@id='cloud-site']/devsite-iframe/iframe"), 15);
        driver.switchTo().frame(iframeCloud).switchTo().frame(iframeInsideCloud);
        return this;
    }

    public CalculatorGooglePage estimateInstancesField() {

        /*JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", buttonsEstimateList.get(0));*/

        //buttonsEstimateList.get(0).click();

        buttonsEstimateList.get(0).sendKeys(Keys.ENTER);
        return this;
    }

    public CalculatorGooglePage estimateNodesField() {
        /*new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(buttonsEstimateList.get(1)));
         buttonsEstimateList.get(1).click();
        ElementClickInterceptedException: element click intercepted*/

        /*JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", buttonsEstimateList.get(1));*/


        buttonsEstimateList.get(1).sendKeys(Keys.ENTER);

        return this;
    }

    public CalculatorGooglePage emailEstimate() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(emailButton));
        emailButton.click();
        return this;
    }

    public InstancesAreaCalculatorPage getInstancesAreaPage() {
        return new InstancesAreaCalculatorPage(driver);
    }

    public NodesAreaCalculatorPage getNodesAreaPage() {
        return new NodesAreaCalculatorPage(driver);
    }

    public EmailPopUpCalculatorPage getEmailPopUpPage() {
        return new EmailPopUpCalculatorPage(driver);
    }


    public String getResultVMclass() {
        return resultVM.getText().split(":")[1].trim();
    }

    public String getResultInstanceTypeCompute() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//md-content[@id='compute']//div[contains(text(), 'Instance type')]")
                ));
        return resultInstanceTypeCompute.getText().split(":")[1].trim();
    }

    public String getResultComputeRegion() {

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//md-content[@id='compute']//div[contains(text(), 'Region')]")
                ));
        return resultRegionCompute.getText().split(":")[1].trim();
    }

    public String getResultCostMonth() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//md-content[@id='compute']//b[contains(text(), 'Estimated Component Cost:')]")
                ));
        return resultCostMonth.getText();
    }

    public String getTotalCost() {
        return resultTotalCost.getText();
    }

    protected void selectOptionFromDropDownList(WebElement webElement, String option) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(webElement));

        webElement.click();

        String id = webElement.getAttribute("aria-owns");
        String optionXpath = String
                .format("//*[@id = '%s']//md-select-menu//md-option//div[contains(text(), '%s')]",
                        id, option);

        WebElement optionElement = driver.findElement(By.xpath(optionXpath));

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(optionElement));

        optionElement.click();
    }
}
