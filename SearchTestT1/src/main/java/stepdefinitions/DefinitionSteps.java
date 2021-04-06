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
import pages.*;
import static org.testng.Assert.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ImagesPage imagesPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getHomePage();
        imagesPage = pageFactoryManager.getImagesPage();
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
    }


    @Given("User opens {string} page")
    public void userOpensHomePagePage(final String url) {
        homePage.openHomePage(url);
    }

    @And("User checks search field visibility")
    public void userChecksSearchFieldVisibility() {
        assertTrue(homePage.isSearchFieldVisible());
    }

    @When("User enters {string} into search field")
    public void userEntersSearchWordIntoSearchField(final String searchWord) {
        homePage.enterSearchWord(searchWord);
    }

    @And("User checks Images tab visibility")
    public void userChecksImagesTabVisibility() {
        searchResultsPage.waitVisibilityOfElement(10, searchResultsPage.getImagesTab());
        assertTrue(searchResultsPage.isImagesTabVisible());
    }

    @And("User clicks on the Images tab")
    public void userClicksOnTheImagesTab() {
        searchResultsPage.clickOnImagesTab();
    }

    @Then("User verifies that Images tab is opened by checking that url contains {string}")
    public void userVerifiesThatImagesTabIsOpened(final String identifierWord) {
        assertTrue(imagesPage.checkThatUrlContainsIdentifierWord(identifierWord)); // Identifier word in the url for images tab isn't so obvious comparatively
    }                                                                              // video or news tab where one is a short version of the tab name


    @After
    public void tearDown() {
        driver.close();
    }

}
