package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ResultGooglePage extends Page {


    public ResultGooglePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CalculatorGooglePage chooseLinkContainsSearchingText(String searchText) {
        String xpath = "//a[@class='gs-title']/b[contains(text(),'%s')]";
        xpath = String.format(xpath, searchText);

        WebElement link = driver.findElement(By.xpath(xpath));

        link.click();

        return new CalculatorGooglePage(driver);
    }
}
