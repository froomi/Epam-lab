package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.Keys.ENTER;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[contains(@name, 'q')]")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url){
        driver.get(url);
    }



    public boolean isSearchFieldVisible(){
        return searchField.isDisplayed();
    }

    public void enterSearchWord(final String searchWord){
        searchField.sendKeys(searchWord, ENTER);
    }

}
