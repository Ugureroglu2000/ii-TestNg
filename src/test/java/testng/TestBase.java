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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
        htmlReporter.config().setReportName("SpidexWM Login");
        report.setSystemInfo("Environment","QA");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));

    }
    @BeforeMethod
    public void beforeMethod(){
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver,10);

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

       Thread.sleep(3000);
        Driver.closeDriver();
    }
    @AfterTest
    public void afterTest(){
        //Closing the report report.flush();
        report.flush();
    }
}
