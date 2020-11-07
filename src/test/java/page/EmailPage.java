package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.CustomCondition;

public class EmailPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='mail_address']")
    WebElement emailAdress;

    @FindBy(xpath = "//*[@id=\"mail_messages_content\"]")
    WebElement message;

    @FindBy(xpath = "//*[@id=\"inbox_count_number\"]")
    WebElement inboxCountNumber;

    @FindBy(xpath = "//td/h3[contains(text(), \"USD\")]")
    WebElement resultEstimate;

    public EmailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EmailPage open() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open('https://10minutemail.com/');");
        return this;
    }

    public String getAddress() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeToBeNotEmpty(emailAdress, "value"));
        return emailAdress.getAttribute("value");
    }

    public String getEstimateFromMessage() {

        try {
            new WebDriverWait(driver, 20)
                    .until(CustomCondition.waitForEmail(inboxCountNumber));
        } catch (TimeoutException e) {
            driver.navigate().refresh();
            new WebDriverWait(driver, 20)
                    .until(CustomCondition.waitForEmail(inboxCountNumber));
        }

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(message));

        message.click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(resultEstimate));

        return resultEstimate.getText();
    }
}
