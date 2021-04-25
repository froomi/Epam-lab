package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.Keys.ENTER;

public class LoginPage extends BasePage {

    @FindBy(name = "identifier")
    private WebElement loginField;

    @FindBy(name = "password")
    private WebElement passwordField;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage(String url){
        driver.get(url);
    }

    public void enterLogin(final String login){
        loginField.sendKeys(login, ENTER);
    }

    public WebElement getPasswordField(){
        return passwordField;
    }

    public void enterPassword(final String password){
        passwordField.sendKeys(password, ENTER);
    }


}
