package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.CustomCondition;

public class InstancesAreaCalculatorPage extends CalculatorGooglePage {


    @FindBy(xpath = "//*[@id=\"input_61\"]")
    private WebElement instancesInput;

    @FindBy(xpath = "//*[@id = 'select_87']")
    private WebElement selectMachineType;

    @FindBy(xpath = "//*[@id='select_89']")
    private WebElement selectLocation;

    @FindBy(xpath = "//*[@id='select_96']")
    private WebElement selectCommittedUsage;


    public InstancesAreaCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public InstancesAreaCalculatorPage inputInstances(String instances) {
        CustomCondition.waitElementPresence(driver, By.id("input_61"), 5);
        instancesInput.sendKeys(instances);
        return this;
    }

    public InstancesAreaCalculatorPage selectMachineType(String type) {
        selectOptionFromDropDownList(selectMachineType, type);
        return this;
    }

    public InstancesAreaCalculatorPage selectLocation(String location) {
        selectOptionFromDropDownList(selectLocation, location);
        return this;
    }

    public InstancesAreaCalculatorPage selectCommittedUsage(String year) {
        selectOptionFromDropDownList(selectCommittedUsage, year);
        return this;
    }


}
