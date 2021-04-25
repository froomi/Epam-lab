package tests;


import business.actions.LoginActions;
import business.actions.MailActions;
import business.asserts.MailAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.BasePage;
import reader.DriverPropertiesFileReader;
import reader.LoginDataXmlReader;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TestExecution {

    LoginActions loginActions;
    MailActions mailActions;
    MailAssert mailAssert;
    DriverPropertiesFileReader driverPropertiesFileReader;

    @BeforeMethod
    public void testSetUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driverPropertiesFileReader = DriverPropertiesFileReader.getInstance();
        driver.manage().timeouts().implicitlyWait(driverPropertiesFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        BasePage.driverPool.set(driver);
        loginActions = new LoginActions();
        loginActions.openUrl();

    }

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
    public void gmailTest(LoginDataXmlReader loginDataXmlReader) {

        loginActions.login(loginDataXmlReader);

        mailActions = new MailActions();
        int amountOfMessages = mailActions.getMessagesAmount();
        int numberOfPointedMessages = mailActions.deleteMessages();

        mailAssert = new MailAssert();
        mailAssert.assertNumberOfMessagesAfterDeletingIsCorrect(amountOfMessages, numberOfPointedMessages);
        mailActions.undoDeleting();
        mailAssert.assertNumberOfMessagesAfterUndoingDeletingIsCorrect();

    }

    @AfterMethod
    public void driverClose(){
    BasePage.driverPool.get().close();
    }

}
