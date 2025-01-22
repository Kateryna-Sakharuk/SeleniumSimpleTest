package core.properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private final String BASE_PROPERTY_PATH = "src/test/resources/properties/%s";
    private static final Properties properties = new Properties();

    public PropertyReader(String testData) {
        loadProperties(testData);
    }
    @BeforeSuite
    @Parameters("testData")
    public void loadProperties(String propertyFilePath) {
        try (FileInputStream inputStream = new FileInputStream(String.format(BASE_PROPERTY_PATH, propertyFilePath))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }
    public static String getProperty(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalStateException("Property value for key '" + key + "' is null");
        }
        return value;
    }
}
