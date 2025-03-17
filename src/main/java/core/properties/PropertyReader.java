package core.properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//SRP -> is responsible for only one task - reading configuration properties from files
//OCP -> class can be extended (ex. to support other configuration file formats),
// but it does not change to add new features.
public class PropertyReader {
    private static final String BASE_PROPERTY_PATH = "src/test/resources/properties/%s";
    private static final Properties properties = new Properties();

    // Static block ensures properties are loaded before any test runs
    static {
        String testData = System.getProperty("testData", "amazonTestData.properties");
        loadProperties(testData);
    }

    private static void loadProperties(String propertyFilePath) {
        String fullPath = String.format(BASE_PROPERTY_PATH, propertyFilePath);
        System.out.println("Loading properties from: " + fullPath);

        try (FileInputStream inputStream = new FileInputStream(fullPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + propertyFilePath, e);
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

    @BeforeSuite
    @Parameters("testData")
    public void initProperties(String testData) {
        loadProperties(testData);
    }
}