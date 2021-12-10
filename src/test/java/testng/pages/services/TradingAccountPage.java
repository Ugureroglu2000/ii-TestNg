package testng.pages.services;

import org.openqa.selenium.support.PageFactory;
import testng.pages.BasePage;
import testng.utils.Driver;

public class TradingAccountPage extends BasePage {
    public TradingAccountPage() { PageFactory.initElements(Driver.get(),this);      }




}
