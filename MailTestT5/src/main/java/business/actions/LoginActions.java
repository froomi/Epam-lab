package business.actions;

import pages.BasePage;
import pages.LoginPage;
import reader.DriverPropertiesFileReader;
import reader.LoginDataXmlReader;

public class LoginActions{

    DriverPropertiesFileReader driverPropertiesFileReader = DriverPropertiesFileReader.getInstance();

    public void openUrl(){
        getLoginPage().openLoginPage(driverPropertiesFileReader.getUrl());
    }


    public LoginPage getLoginPage(){
        return new LoginPage(BasePage.driverPool.get());
    }

    public void login(LoginDataXmlReader loginDataXmlReader){
        getLoginPage().enterLogin(loginDataXmlReader.getLogin());
        getLoginPage().waitVisibilityOfElement(driverPropertiesFileReader.getVisibilityOfElementWait(), getLoginPage().getPasswordField());
        getLoginPage().enterPassword(loginDataXmlReader.getPassword());
    }

}
