package testng.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static testng.utils.Driver.*;

public class BrowserUtils {
    private static WebDriverWait wait = new WebDriverWait(driver,10);
    public static void clickSafely(WebElement element) {
          wait.until(ExpectedConditions.elementToBeClickable(element));
          element.click();
    }

}
