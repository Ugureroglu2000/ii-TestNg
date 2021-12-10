package testng.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private Driver(){}

    public static WebDriver driver;

    public static WebDriver get(){
        if (driver == null) {
            String browser=ConfigurationReader.get("browser");

            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case"edge":
                    WebDriverManager.edgedriver().setup();
                    driver=new EdgeDriver();
                    break;
            }}
        return driver;
    }
    public  static  void closeDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
