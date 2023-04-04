package Config.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFrom {
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Exception -- IOException | FileNotFoundException :" + e.getMessage());
        }
        return properties;
    }
}
