package testng.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testng.utils.ConfigurationReader;
import testng.utils.Driver;

public class LoginPage {
    public LoginPage() { PageFactory.initElements(Driver.get(),this);      }

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    public WebElement LoginButton;
//    @FindBy(xpath = "//input[contains(@id,'username_textbox')]")
    @FindBy(xpath = "//*[@name='j_username']")
    public WebElement UsernameInputBox;
//    @FindBy(xpath = "//input[contains(@id,'password_textbox')]")
    @FindBy(xpath = "//*[@name='j_password']")
    public WebElement PasswordInputBox;
    @FindBy(xpath = "//input[contains(@id,'rememberMeCkBox_checkbox')]")
    public WebElement RemembermeCheckBox;
//    @FindBy(xpath = "//button[contains(@id,'submit_button')]")

    @FindBy(xpath = "//span[contains(text(),'Login failed')]")
    public WebElement FailedCheck;
    @FindBy(xpath = "//span[contains(text(),'Reason:')]")
    public WebElement FailureReason;
    @FindBy(xpath = "//span[contains(text(),'User names and passwords are case')]")
    public WebElement PropertyOfUsernamePassword;
    @FindBy(xpath = "//span[contains(text(),'User locked out - ') and contains(text(), ' failed attempts have been made.')]")
    public WebElement UserLockedMessage;

    public  void login(){
        String username= ConfigurationReader.get("username");
        String password=ConfigurationReader.get("password");
        UsernameInputBox.sendKeys(username);
        PasswordInputBox.sendKeys(password);
        LoginButton.click();
    }
    public  void login(String user,String pass){
        UsernameInputBox.sendKeys(user);
        PasswordInputBox.sendKeys(pass);
        LoginButton.click();
    }


}
