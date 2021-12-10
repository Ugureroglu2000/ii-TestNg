package testng;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import testng.pages.HomePage;
import testng.utils.BrowserUtils;
import testng.utils.ConfigurationReader;
import testng.utils.Driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;
    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;

    @BeforeTest
    public void beforeTest(){
        // Reports setup
        report = new ExtentReports();
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/Screenshots/AAreport.html");
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("ii-TestNG");
        report.setSystemInfo("Environment","QA");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));

    }
    String url= ConfigurationReader.get("url");
    @BeforeClass
    public void beforeClass(){
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver,10);
        driver.get(url);
        BrowserUtils.clickSafely(new HomePage().acceptCookiesButton);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws InterruptedException, IOException {
        if(result.getStatus()==ITestResult.FAILURE) {   // if it fails
            extentLogger.fail(result.getName());
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot ts=(TakesScreenshot)Driver.get();
            File file=ts.getScreenshotAs(OutputType.FILE);
            String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + result.getName() + date + ".png";
            File finalDestination = new File(target);

            FileUtils.copyFile(file,finalDestination);
            extentLogger.addScreenCaptureFromPath(result.getName() + date + ".png");
            extentLogger.fail(result.getThrowable());

        }

    }
    @AfterClass
    public void afterClass(){

        Driver.closeDriver();
    }
    @AfterTest
    public void afterTest(){
        //Closing the report report.flush();
        report.flush();
    }
}
