package page;

import model.GPU;
import service.CustomCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CalculatorGooglePage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement iframeCloud;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement iframeInsideCloud;

    @FindBy(xpath = "//*[@id=\"input_61\"]")
    private WebElement instancesInput;

    @FindBy(css = "#select_87")
    private WebElement selectMachineType;

    @FindBy(xpath = "//*[@id=\"select_value_label_59\"]")
    private WebElement selectLocation;

    @FindBy(xpath = "//*[@id=\"select_value_label_60\"]")
    private WebElement selectCommittedUsage;


    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.soleTenant.addGPUs']")
    private WebElement checkboxGPU;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.soleTenant.gpuCount']")
    private WebElement selectGpuCount;


    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.soleTenant.gpuType']")
    private WebElement selectGpuType;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.soleTenant.ssd']")
    private WebElement selectSSD;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.soleTenant.nodesCount']")
    private WebElement selectNodesCount;

    @FindBy(xpath = "//button[@aria-label=\"Add to Estimate\"]")
    private List<WebElement> buttonsEstimateList;

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

    @FindBy(xpath = "//button[@aria-label=\"Email Estimate\"]")
    private WebElement emailButton;

    @FindBy(xpath = "//label[contains(text(), 'Email')]//following-sibling::input")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[@aria-label=\"Send Email\"]")
    private WebElement sendEmailButton;


    public CalculatorGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        CustomCondition.waitElementPresence(driver, By.xpath("//*[@id='cloud-site']/devsite-iframe/iframe"), 15);
        switchFrame();
    }

    public CalculatorGooglePage switchFrame() {
        driver.switchTo().frame(iframeCloud).switchTo().frame(iframeInsideCloud);
        return this;
    }

    public CalculatorGooglePage inputInstances(String instances) {
        CustomCondition.waitElementPresence(driver, By.id("input_61"), 5);
        instancesInput.sendKeys(instances);
        return this;
    }

    public CalculatorGooglePage selectMachineType(String type) {
        selectMachineType.click();

        CustomCondition.waitElementsVisibility(driver,
                5, By.xpath("//md-option[@ng-repeat='instance in typeInfo']"));

        String xpath
                = "//md-option[@ng-repeat='instance in typeInfo']/div[contains(text(), '%s')]";
        xpath = String.format(xpath, type);

        WebElement typeOption = driver.findElement(By.xpath(xpath));
        typeOption.click();
        return this;
    }

    public CalculatorGooglePage selectLocation(String location) {
        selectLocation.click();

        CustomCondition.waitElementsVisibility(driver, 5,
                By.xpath("//*[@id='select_container_90']"));

        String xpath = "//md-select-menu[@class='md-overflow']//md-option[@ng-repeat='item in listingCtrl.fullRegionList']/div[contains(text(), '%s')]";
        xpath = String.format(xpath, location);

        WebElement locationOption = driver.findElement(By.xpath(xpath));
        locationOption.click();

        return this;
    }

    public CalculatorGooglePage selectCommittedUsage(String year) {
        selectCommittedUsage.click();

        String xpath = "//div[@id='select_container_97']//md-option[@class='md-ink-ripple']/div[contains(text(), '%s')]";
        xpath = String.format(xpath, year);

        CustomCondition.waitElementsVisibility(driver, 5,
                By.xpath("//div[@id='select_container_97']"));

        WebElement usageYearOption = driver.findElement(By.xpath(xpath));
        usageYearOption.click();
        return this;
    }


    public CalculatorGooglePage addGPU(GPU gpu) {
        checkboxGPU.click();

        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(selectGpuCount));

        selectGpuCount.click();
        CustomCondition.waitElementsVisibility(driver, 5,
                By.xpath("//div[@id='select_container_353']"));

        String xpathCount = "//div[@id='select_container_353']//md-option/div[contains(text(), '%s')]";
        xpathCount = String.format(xpathCount, gpu.getAmount());
        WebElement gpuCountOption = driver.findElement(By.xpath(xpathCount));

        new WebDriverWait(driver, 4).until(
                ExpectedConditions.elementToBeClickable(gpuCountOption)
        );

        gpuCountOption.click();

        selectGpuType.click();
        CustomCondition.waitElementsVisibility(driver, 5,
                By.xpath("//div[@id='select_container_355']"));

        String xpathType = "//div[@id='select_container_355']//md-option/div[contains(text(), '%s')]";
        xpathType = String.format(xpathType, gpu.getGpuType());
        WebElement gpuTypeOption = driver.findElement(By.xpath(xpathType));

        gpuTypeOption.click();
        return this;
    }

    public CalculatorGooglePage addSsd(String ssd) {
        selectSSD.click();
        CustomCondition.waitElementsVisibility(driver, 5,
                By.xpath("//div[@id='select_container_117']"));

        String xpath = "//div[@id='select_container_117']//md-option/div[contains(text(), '%s')]";
        xpath = String.format(xpath, ssd);

        WebElement ssdOption = driver.findElement(By.xpath(xpath));
        ssdOption.click();
        return this;
    }

    public CalculatorGooglePage selectNode(int n) {
        selectNodesCount.click();
        selectNodesCount.sendKeys(String.valueOf(n));
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

    public CalculatorGooglePage sendToEmail(String email) {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(), 'Email')]//following-sibling::input")));

        inputEmail.sendKeys(email);

        sendEmailButton.click();


        return this;
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


}
