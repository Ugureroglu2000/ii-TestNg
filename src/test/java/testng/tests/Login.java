package testng.tests;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testng.TestBase;
import testng.pages.LoginPage;
import testng.utils.ConfigurationReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login extends TestBase {
    String url= ConfigurationReader.get("url");


    @Test(dataProvider = "LoginData")
    public  void NegativeLogin(String user,String pass ){
//        System.out.println( user + pass);
        extentLogger =report.createTest("Negative Login");
        driver.get(url);
        LoginPage loginPage=new LoginPage();
        loginPage.login(user,pass);
        extentLogger.info("Username="+user+" and Password="+pass+" is typed");
        String ActualResult="a";
        String ExpectedResult="b";
        try{
            ActualResult=loginPage.FailedCheck.getText();
            ExpectedResult="Login failed. Please try again.";
            extentLogger.info("Couldnt log in as expected");
            if(ExpectedResult.contains(ActualResult)) {
                System.out.println("Username= " + user + "  Password= " + pass + " can not login");
                System.out.println(loginPage.FailureReason.getText());      }
        }catch (NoSuchElementException e){
            extentLogger.info(user+" and "+pass+" mustn't log in but Logged In.. Defect ???");
        }
        Assert.assertTrue(ExpectedResult.contains(ActualResult));
        extentLogger.pass("Everyhing works as expected");
    }

    @DataProvider
//    @Test
    public Object[][] LoginData(){
        Workbook wb=null;

        FileInputStream excellFile= null;
        try {
            excellFile = new FileInputStream("src/test/resources/DataLogin.xlsx");
            wb= WorkbookFactory.create(excellFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet ws=wb.getSheet("sheet1");
        Row row=ws.getRow(0);

        int rowNum=ws.getLastRowNum();

        Object[][] data=new Object[rowNum][2];
        for(int i=1;i<rowNum;i++) {
            for (int j = 0; j < 2; j++) {
                Cell cell = ws.getRow(i).getCell(j);
                if (cell != null) {
                    Object cellData = (Object) cell.toString();
                    data[i][j] = cellData;

                }

            }
        }

        return data;

    }



    @Test
    public void PositiveLogin(){
        extentLogger =report.createTest("Positive Login");
        driver.get(url);
        LoginPage loginPage=new LoginPage();
        loginPage.login();
        extentLogger.info("Username and Password is typed");
        extentLogger.pass("We can log in with correct credentials");
//        new LoginPage().login();
    }


}
