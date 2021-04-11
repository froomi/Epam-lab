package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class SentPage extends BasePage{

    @FindBy(xpath = "//div[contains(text(), 'Кому') and contains(@class, 'yW')]/span[1]")
    private List<WebElement> messageReceiverName;

    public SentPage(WebDriver driver) {
        super(driver);
    }


    public String getMessageReceiverName(){
        return messageReceiverName.get(0).getAttribute("email"); //get last receiver's email
    }

}
