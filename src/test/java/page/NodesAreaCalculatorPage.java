package page;

import model.GPU;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NodesAreaCalculatorPage extends CalculatorGooglePage {

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

    public NodesAreaCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public NodesAreaCalculatorPage addGPU(GPU gpu) {
        checkboxGPU.click();

        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(selectGpuCount));

        selectOptionFromDropDownList(selectGpuCount, gpu.getAmount());
        selectOptionFromDropDownList(selectGpuType, gpu.getGpuType());

        return this;
    }

    public NodesAreaCalculatorPage addSsd(String ssd) {
        selectOptionFromDropDownList(selectSSD, ssd);
        return this;
    }

    public NodesAreaCalculatorPage selectNode(int n) {
        selectNodesCount.click();
        selectNodesCount.sendKeys(String.valueOf(n));
        return this;
    }
}
