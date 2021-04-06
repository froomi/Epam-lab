package pages;

import org.openqa.selenium.WebDriver;

public class ImagesPage extends BasePage {

    public ImagesPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkThatUrlContainsIdentifierWord(final String identifierWord){
        return driver.getCurrentUrl().contains(identifierWord);
     }
}
