package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.MailPage;
import reader.DriverPropertiesFileReader;
import reader.LoginDataXmlReader;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.*;

public class DefinitionSteps {

    WebDriver driver;
    HomePage homePage;
    MailPage mailPage;
    PageFactoryManager pageFactoryManager;
    DriverPropertiesFileReader driverPropertiesFileReader;
    LoginDataXmlReader loginDataXmlReader;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driverPropertiesFileReader = new DriverPropertiesFileReader();
        driver.manage().timeouts().implicitlyWait(driverPropertiesFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getHomePage();
        mailPage = pageFactoryManager.getMailPage();
        driverPropertiesFileReader = new DriverPropertiesFileReader();

    }


    @Given("User opens home page")
    public void userOpensHomePagePage(){
        homePage.openHomePage(driverPropertiesFileReader.getUrl());
    }

    @And("User inputs login and presses Enter button")
    public void userInputsLoginAndPressesEnterButton() {
        loginDataXmlReader = new LoginDataXmlReader(1);
        homePage.enterLogin(loginDataXmlReader.getLogin());
    }

    @And("User inputs password and presses Enter button again")
    public void userInputsPasswordAndPressesEnterButtonAgain() {
        homePage.waitVisibilityOfElement(driverPropertiesFileReader.getVisibilityOfElementWait(), homePage.getPasswordField());
        homePage.enterPassword(loginDataXmlReader.getPassword());
    }

    @And("User clicks on first three messages' checkboxes")
    public void userClicksOnFirstThreeMessagesCheckboxes() {
        mailPage.clickOnThreeCheckboxes();

    }

    int amount = 0;

    @And("User gets amount of messages")
    public void userGetsAmountOfMessages() {
        amount = mailPage.getMessagesAmount();
    }

    @And("User deletes pointed messages")
    public void userDeletesPointedMessages() {
        mailPage.clickOnDeleteButton();
    }

    @And("User checks that messages were deleted")
    public void userChecksThatMessagesWereDeleted() {
        int counter = mailPage.getCounter();
        assertEquals(amount - counter, mailPage.getMessagesAmount());
    }

    @Then("User clicks on Undo button")
    public void userClicksOnUndoButton() {
        mailPage.clickOnUndoButton();
    }

    @And("User checks that messages were returned")
    public void userChecksThatMessagesWereReturned() {
        assertEquals(amount, mailPage.getMessagesAmount());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
