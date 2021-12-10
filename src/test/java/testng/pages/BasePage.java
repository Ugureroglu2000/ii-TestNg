package testng.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import testng.utils.Driver;
import static testng.utils.BrowserUtils.*;

import java.util.List;

public abstract class BasePage {

    public BasePage() { PageFactory.initElements(Driver.get(),this);      }

    @FindBy(xpath = "//section[starts-with(@class,'chakra-modal__content')]")
    public WebElement CookiesWindow;

    @FindBy(xpath = "//section[starts-with(@class,'chakra-modal__content')]//button")
    public WebElement acceptCookiesButton;

    @FindBy(xpath = "//div[starts-with(@data-testid,'breadcrumb-item')]")
    public List<WebElement> pageNames;

    @FindBy(xpath = "//a[@title='Interactive Investor']")
    public WebElement HomePageButton;

    @FindBy(xpath= "//div[starts-with(@id,'navigationItem')]/../div/span")
    public List<WebElement> Modules;

    @FindBy(xpath = "//div[@id='navigationItemServices']//a")
    public List<WebElement> ServicesTabs;
    @FindBy(xpath = "//div[@id='navigationItemPensions']//a")
    public List<WebElement> PensionsTabs;
    @FindBy(xpath = "//div[@id='navigationItemInvestments']//a")
    public List<WebElement> InvestmentsTabs;
    @FindBy(xpath = "//div[@id='navigationItemHelpLearning']//a")
    public List<WebElement> HelpLearningTabs;

    @FindBy(xpath = "//span[@title='News']")
    public WebElement NewsButton;

    @FindBy(xpath ="(//span[.='Sign up'])[1]")
    public WebElement SignUpButton;

    @FindBy(xpath ="(//span[.='Search'])[1]")
    public WebElement SearchButton;

    @FindBy(xpath ="(//span[.='Log in'])[1]")
    public WebElement LoginButton;


    public void goToModule(String module) {
    for (int i=0;i<Modules.size();i++){
        String a=Modules.get(i).getAttribute("Title");
        if(Modules.get(i).getAttribute("Title").equalsIgnoreCase(module)){
            clickSafely(Modules.get(i));
            break;
        }
    }
// we get the attribute name not the text name
//

    }
    public void goToTabs(List<WebElement> Tabs,String tab) {
        for (int i = 0; i < Tabs.size(); i++) {
            String a=Tabs.get(i).getText();
            if (Tabs.get(i).getText().equalsIgnoreCase(tab)) {
                clickSafely(Tabs.get(i));
                break;
            }
        }
    }

    public  void goToModuleTab(String module, String tab){
        goToModule(module);
        List<WebElement> Tabs=null;
        if(module.equalsIgnoreCase("Services")){
            Tabs=ServicesTabs;
        }else if(module.equalsIgnoreCase("Pensions")){
            Tabs=PensionsTabs;
        }else if(module.equalsIgnoreCase(("Investments"))){
            Tabs=InvestmentsTabs;
        }else if(module.equalsIgnoreCase("Help & Learning")){
            Tabs=HelpLearningTabs;
        }else{
            Assert.fail("Check your Typing module Name, Error");
        }
        goToTabs(Tabs,tab);

        }
//        Tab names in each module is changing so we need to get each of them to save different list so that we can use here


}
