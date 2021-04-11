package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.MailPage;
import pages.SentPage;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.*;

public class DefinitionSteps {


    WebDriver driver;
    HomePage homePage;
    MailPage mailPage;
    SentPage sentPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getHomePage();
        mailPage = pageFactoryManager.getMailPage();
        sentPage = pageFactoryManager.getSentPage();
    }


    @Given("User opens {string} page")
    public void userOpensHomePagePage(final String url) {
        homePage.openHomePage(url);
    }

    @And("User inputs {string} and presses Enter button")
    public void userInputsLoginAndPressesEnterButton(final String login) {
        homePage.enterLogin(login);
    }

    @And("User inputs {string} and presses Enter button again")
    public void userInputsPasswordAndPressesEnterButtonAgain(final String password) {
        homePage.waitVisibilityOfElement(10, homePage.getPasswordField());
        homePage.enterPassword(password);
    }

    @And("User clicks on Compose button")
    public void userClicksOnComposeButton() {
        mailPage.waitVisibilityOfElement(10, mailPage.getComposeButton());
        mailPage.clickOnComposeButton();
    }

    @When("User inputs another person email {string} account as a message receiver")
    public void userInputsAnotherEmailAccountAsAMessageReceiver(final String email) {
        mailPage.inputMessageReceiverName(email);
    }

    @And("User fills in message form with some {string}")
    public void userFillsInMessageFormWithSomeMessage(final String message) {
        mailPage.inputMessage(message);
    }

    @And("User clicks on send button")
    public void userClicksOnSendButton() {
        mailPage.clickOnSendButton();
    }

    @And("User checks that message was sent")
    public void userChecksThatLastMessageWasSent() {
        mailPage.waitVisibilityOfElement(10, mailPage.getNotificationMessage());
        assertTrue(mailPage.notificationMessageIsVisible());
    }

    @Then("User verifies that message was sent to the receiver by clicking on sent button")
    public void userVerifiesThatMessageWasSentToTheReceiverByClickingOnSentButton() {
        mailPage.clickOnSentButton();
    }

    @And("User checks that last message was sent right to {string}")
    public void userChecksThatLastMessageWasSentRightToEmail(final String email){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertEquals(email, sentPage.getMessageReceiverName());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
