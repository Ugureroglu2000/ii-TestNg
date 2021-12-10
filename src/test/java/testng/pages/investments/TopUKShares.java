package testng.pages.investments;

import org.openqa.selenium.support.PageFactory;
import testng.pages.BasePage;
import testng.utils.Driver;

public class TopUKShares extends BasePage {
    public TopUKShares() { PageFactory.initElements(Driver.get(),this);      }


}
