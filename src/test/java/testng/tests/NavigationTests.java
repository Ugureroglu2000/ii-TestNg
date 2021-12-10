package testng.tests;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testng.TestBase;
import testng.pages.HomePage;
import testng.utils.BrowserUtils;
import testng.utils.ConfigurationReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NavigationTests extends TestBase {
    String url= ConfigurationReader.get("url");
    HomePage homePage=new HomePage();


    @Test
    public  void verifyServices(){
        Assert.assertEquals(verifyModulesTabs("Services", "Trading account"),"Trading account");
    }
//    Normally I verifythe pagename in verifyModulesTab method but in some pages tabname and
//    the pageName object is not matching, so we return the name as String and assert inside the test
    @Test
    public  void verifyPensions() {
        Assert.assertEquals(verifyModulesTabs("Pensions", "What is a SIPP?"),"What is a SIPP?");
    }

    @Test
    public  void verifyInvestments() {
        Assert.assertEquals(verifyModulesTabs("Investments", "Top UK shares"),"Top UK shares ERROR");
    }
//    Normally this methods Pass, to check if the report is working or not, we put the expected value with Error

    @Test
    public  void verifyHelpLearning() {
        Assert.assertEquals(verifyModulesTabs("Help & Learning", "Super 60 Investments"),"ii Super 60");
    }

    public String verifyModulesTabs(String Modules,String Tabs){
        BrowserUtils.clickSafely(homePage.HomePageButton);
        extentLogger =report.createTest("ii-"+Modules+" - "+Tabs);
        String ActualUrl=driver.getCurrentUrl();
        extentLogger.info("Report is created ");
        Assert.assertEquals(ActualUrl,"https://www.ii.co.uk/");
        extentLogger.pass("Browser is at the expected URl");
        homePage.goToModuleTab(Modules,Tabs);
        return homePage.pageNames.get(homePage.pageNames.size()-1).getText();
//        First I get the name as the third element , but in other modules it goes 2 and and 4 ;
//        so we get the pagename element dynamically as the last element.
    }


}
