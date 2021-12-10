package testng.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testng.utils.ConfigurationReader;
import testng.utils.Driver;

public class HomePage extends BasePage{
    public HomePage() { PageFactory.initElements(Driver.get(),this);      }

    @FindBy(id="homepage-banner")
    public WebElement HomePageBanner;

}
