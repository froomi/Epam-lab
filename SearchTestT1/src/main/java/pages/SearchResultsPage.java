package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Images') or contains(text(), 'Зображення')]")
    private WebElement imagesTab;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getImagesTab(){
        return imagesTab;
    }

    public boolean isImagesTabVisible(){
        return imagesTab.isDisplayed();
    }

    public void clickOnImagesTab(){
        imagesTab.click();
    }
}
