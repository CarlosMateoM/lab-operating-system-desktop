package lab.operating.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author C.Mateo
 */
public class PropertiesUtil {

    private static final String PROPERTIES_FILE = "production.properties";
    private static final Properties properties = loadProperties();

    private PropertiesUtil() {
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input =new FileInputStream(new File(PROPERTIES_FILE))) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
