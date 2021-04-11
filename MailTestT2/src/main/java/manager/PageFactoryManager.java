package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public MailPage getMailPage() {
        return new MailPage(driver);
    }

    public SentPage getSentPage() {
        return new SentPage(driver);
    }

}
