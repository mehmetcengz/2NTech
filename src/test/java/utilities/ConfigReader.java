package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties;
    static {
        String filePath = "configuration.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInputStream);
        }   catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}