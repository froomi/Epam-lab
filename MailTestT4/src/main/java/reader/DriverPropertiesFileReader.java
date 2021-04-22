package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DriverPropertiesFileReader {
    private Properties properties;
    private final String propertyFileName = "src/test/resources/driver.properties";

    public DriverPropertiesFileReader() {

        try(
              BufferedReader reader = new BufferedReader(new FileReader(propertyFileName))
        ) {
            properties = new Properties();
            try{
                properties.load(reader);
            }catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getImplicitlyWait(){
        String implicitlyWait = properties.getProperty("implicitlyWait");
        return Integer.parseInt(implicitlyWait);
    }



    public int getVisibilityOfElementWait(){
        String visibilityOfElementWait = properties.getProperty("visibilityOfElementWait");
        return Integer.parseInt(visibilityOfElementWait);
    }


    public int getElementToBeClickableWait(){
        String elementToBeClickableWait = properties.getProperty("elementToBeClickableWait");
        return Integer.parseInt(elementToBeClickableWait);
    }
    public String getUrl(){
        return properties.getProperty("url");
    }

}
