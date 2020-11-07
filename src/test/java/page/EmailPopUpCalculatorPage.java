package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPopUpCalculatorPage extends CalculatorGooglePage {

    @FindBy(xpath = "//label[contains(text(), 'Email')]//following-sibling::input")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[@aria-label=\"Send Email\"]")
    private WebElement sendEmailButton;

    public EmailPopUpCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void sendToEmail(String email) {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(), 'Email')]//following-sibling::input")));

        inputEmail.sendKeys(email);

        sendEmailButton.click();

    }
}
