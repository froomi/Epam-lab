package business.asserts;

import pages.BasePage;
import pages.MailPage;

import static org.testng.Assert.*;

public class MailAssert {

    int amountOfMessages;
    MailPage mailPage = new MailPage(BasePage.driverPool.get());

    public void assertNumberOfMessagesAfterDeletingIsCorrect(int amountOfMessages, int numberOfPointedMessages){

        this.amountOfMessages = amountOfMessages;
        assertEquals(amountOfMessages - numberOfPointedMessages, mailPage.getMessagesAmount());
    }

    public void assertNumberOfMessagesAfterUndoingDeletingIsCorrect(){
        assertEquals(amountOfMessages, mailPage.getMessagesAmount());
    }
}
