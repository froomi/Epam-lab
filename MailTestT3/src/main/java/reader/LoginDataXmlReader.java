package reader;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;


public class LoginDataXmlReader {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db;
    Document doc;
    Element element;

    public LoginDataXmlReader(int userNumber) {
        userNumber -= 1;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("src/test/resources/loginData.xml"));

            NodeList list = doc.getElementsByTagName("user");

            Node node = list.item(userNumber);
            element = (Element) node;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin() {
        return element.getElementsByTagName("login").item(0).getTextContent();
    }

    public String getPassword() {
        return element.getElementsByTagName("password").item(0).getTextContent();
    }
}
