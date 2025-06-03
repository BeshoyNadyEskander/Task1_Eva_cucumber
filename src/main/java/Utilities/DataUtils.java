package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    public final static String TEST_DATA_PATH = "src/test/resources/TestData/";


    //TODO: Read data from json file
    public static String getJsonData(String jsonFilename, String field) {
        try {
            // Define object of file Reader
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");
            // Parse the JSON directly into a JsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    //TODO: get properties from any .properties file
    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);
    }

    //TODO: store in properties file
    public static void set(String fileName,String key ,String value)  {
        try {
            // create object from properties
            Properties   prop = new Properties();
            // create FileInputStream and put name of config file
            FileInputStream fis = new FileInputStream(TEST_DATA_PATH + fileName + ".properties");
            prop.load(fis);

            prop.setProperty(key, value);
            fis.close();

            FileOutputStream fos = new FileOutputStream(TEST_DATA_PATH + fileName + ".properties");
            prop.store(fos,"");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
