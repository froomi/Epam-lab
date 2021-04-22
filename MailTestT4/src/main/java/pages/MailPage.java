package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MailPage extends BasePage {

    @FindBy(xpath = "//div[contains(@role, 'checkbox')]")
    List<WebElement> checkboxes;

    @FindBy(xpath = "//div[contains(@aria-label, 'Видалити')]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[contains(@class, 'J-J5-Ji amH J-JN-I')]/span/span[2]")
    WebElement messagesAmount;

    @FindBy(xpath = "//span[contains(@aria-label, 'Скасувати')]")
    WebElement undoButton;

    public MailPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnThreeCheckboxes(){
        for(int i = 0; i < 3; i++) {
            checkboxes.get(i).click();
        }
    }

    public WebElement getDeleteButton(){
        return deleteButton;
    }

    public void clickOnDeleteButton(){
        deleteButton.click();
    }

    public int getMessagesAmount(){
        return Integer.parseInt(messagesAmount.getText());
    }

    public WebElement getUndoButton(){
        return undoButton;
    }

    public void clickOnUndoButton(){
        undoButton.click();
    }

}
