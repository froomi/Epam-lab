package business.actions;

import pages.BasePage;
import pages.MailPage;
import reader.DriverPropertiesFileReader;

public class MailActions{

    DriverPropertiesFileReader driverPropertiesFileReader = DriverPropertiesFileReader.getInstance();

    public MailPage getMailPage(){
        return new MailPage(BasePage.driverPool.get());
    }

    public int deleteMessages() {

        int deletedMessages = getMailPage().clickOnThreeCheckboxes();
        getMailPage().waitElementToBeClickable(driverPropertiesFileReader.getElementToBeClickableWait(), getMailPage().getDeleteButton());
        getMailPage().clickOnDeleteButton();
        return deletedMessages;
    }

    public void undoDeleting(){
        getMailPage().waitVisibilityOfElement(driverPropertiesFileReader.getVisibilityOfElementWait(), getMailPage().getUndoButton());
        getMailPage().clickOnUndoButton();
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int getMessagesAmount(){
        getMailPage().waitVisibilityOfElement(driverPropertiesFileReader.getVisibilityOfElementWait(), getMailPage().getWebElementOfMessagesAmount());
        return getMailPage().getMessagesAmount();
    }

}
