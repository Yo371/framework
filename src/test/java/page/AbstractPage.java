package page;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToHandle(int n) {
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(n));
    }
}
