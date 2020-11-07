package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.CustomCondition;


public class HomeGoogleCloudPage extends AbstractPage {
    private static final String URL_HOME = "https://cloud.google.com/";


    @FindBy(css = "div.devsite-search-container")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@name=\"q\"]")
    private WebElement searchArea;

    public HomeGoogleCloudPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ResultGooglePage searchByText(String text) {

        CustomCondition.waitElementPresence(driver, By.cssSelector("div.devsite-search-container"), 5);
        searchButton.click();
        CustomCondition.waitElementPresence(driver, By.xpath("//input[@name=\"q\"]"), 5);
        searchArea.sendKeys(text);
        searchArea.sendKeys(Keys.ENTER);

        CustomCondition.waitElementsPresence(driver, By.className("gs-title"), 40);
        return new ResultGooglePage(driver);
    }


    public HomeGoogleCloudPage open() {
        driver.get(URL_HOME);

        return this;
    }

}
