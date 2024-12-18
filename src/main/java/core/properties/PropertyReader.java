package core.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class PropertyReader {
    private static final Properties properties = new Properties();
    static {
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/amazonTestData.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }
    public static String getProperty(String key){
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        return properties.getProperty(key);
    }
}
