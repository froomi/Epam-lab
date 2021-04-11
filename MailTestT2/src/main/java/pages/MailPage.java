package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MailPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'T-I T-I-KE L3' )]")
    private WebElement composeButton;

    @FindBy(name = "to")
    private WebElement messageReceiverNameField;

    @FindBy(css = ".editable")
    private WebElement messageForm;

    @FindBy(xpath = "//div[contains(@aria-label, 'Enter')]")
    private WebElement sendButton;

    @FindBy(xpath = "//span[contains(text(), 'надіслано')]")
    private WebElement notificationMessage;

    @FindBy(linkText = "Надіслані")
    private WebElement sentButton;

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getComposeButton(){
        return composeButton;
    }

    public void clickOnComposeButton(){
        composeButton.click();
    }

    public void inputMessageReceiverName(final String email){
        messageReceiverNameField.sendKeys(email);
    }

    public void inputMessage(final String message){
        messageForm.sendKeys(message);
    }

    public void clickOnSendButton(){
        sendButton.click();
    }

    public boolean notificationMessageIsVisible(){
        return notificationMessage.isDisplayed();
    }

    public void clickOnSentButton(){
        sentButton.click();
    }

    public WebElement getNotificationMessage(){
        return notificationMessage;
    }


}
