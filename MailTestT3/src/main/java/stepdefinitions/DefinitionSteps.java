package stepdefinitions;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.MailPage;
import reader.DriverPropertiesFileReader;
import reader.LoginDataXmlReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.*;

public class DefinitionSteps {


    DriverPropertiesFileReader driverPropertiesFileReader;
    LoginDataXmlReader loginDataXmlReader;


    private static ThreadLocal<WebDriver>  driverPool = new ThreadLocal<>();


    @BeforeMethod
    public void testSetUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driverPool.set(driver);
        driverPropertiesFileReader = new DriverPropertiesFileReader();
        getHomePage().openHomePage(driverPropertiesFileReader.getUrl());

    }

    public WebDriver getDriver(){return driverPool.get();}
    public HomePage getHomePage() throws IOException { return new HomePage(getDriver()); }
    public MailPage getMailPage() throws IOException {return  new MailPage(getDriver()) ;}


    @DataProvider(name = "users", parallel = true)
    public Iterator<Object[]> users(){
        return Stream.of(
                new Object[]{new LoginDataXmlReader(1)},
                new Object[]{new LoginDataXmlReader(2) },
                new Object[]{new LoginDataXmlReader(3) },
                new Object[]{new LoginDataXmlReader(4) },
                new Object[]{new LoginDataXmlReader(5) }).iterator();

    }


    @Test(dataProvider = "users")
    public void gmailTest(LoginDataXmlReader loginDataXmlReader) throws IOException, InterruptedException {

        this.loginDataXmlReader = loginDataXmlReader;
        getHomePage().enterLogin(loginDataXmlReader.getLogin());
        getHomePage().waitVisibilityOfElement(driverPropertiesFileReader.getVisibilityOfElementWait(), getHomePage().getPasswordField());
        getHomePage().enterPassword(loginDataXmlReader.getPassword());

        Thread.sleep(4000);
        getMailPage().clickOnThreeCheckboxes();
        int amount = getMailPage().getMessagesAmount();
        getMailPage().waitVisibilityOfElement(driverPropertiesFileReader.getVisibilityOfElementWait(), getMailPage().getDeleteButton());
        getMailPage().waitElementToBeClickable(driverPropertiesFileReader.getElementToBeClickableWait(), getMailPage().getDeleteButton());
        getMailPage().clickOnDeleteButton();

        int counter = 3;
        assertEquals(amount - counter, getMailPage().getMessagesAmount());
        getMailPage().waitVisibilityOfElement(driverPropertiesFileReader.getVisibilityOfElementWait(), getMailPage().getUndoButton());
        getMailPage().clickOnUndoButton();
        Thread.sleep(500);
        assertEquals(amount, getMailPage().getMessagesAmount());
    }


@AfterMethod
public void driverClose(){
    driverPool.get().close();
}



}
