package page;

import org.openqa.selenium.support.PageFactory;
import service.CustomCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class HomeGoogleCloudPage extends Page{
    private static final String URL_HOME = "https://cloud.google.com/";


    @FindBy(css = "div.devsite-search-container")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@name=\"q\"]")
    private WebElement searchArea;

    public HomeGoogleCloudPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ResultGooglePage searchByText(String text){

        searchButton.click();
        CustomCondition.waitElementPresence(driver, By.xpath("//input[@name=\"q\"]"), 5);
        searchArea.sendKeys(text);
        searchArea.sendKeys(Keys.ENTER);

        CustomCondition.waitElementsPresence(driver, By.className("gs-title"), 40);
        return new ResultGooglePage(driver);
    }


    public HomeGoogleCloudPage open(){
        driver.get(URL_HOME);

        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        return this;
    }

}
