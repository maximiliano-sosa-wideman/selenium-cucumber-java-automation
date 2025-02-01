package hellocucumber.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ReadProperties {
    private final Properties configProp = new Properties();

    private ReadProperties() {
        try {
            InputStream in;
            in = new FileInputStream("qaenv.properties");
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder {
        private static final ReadProperties INSTANCE = new ReadProperties();
    }

    public static ReadProperties getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Double getPropertyDouble(String key) {
        return Double.valueOf(configProp.getProperty(key));
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }
}
