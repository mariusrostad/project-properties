package no.ldx.project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    public static final String CONFIG_DIR = "config.dir";
    public static final String ENV_DIR = "env.dir";
    public static final String DEFAULT_DIR = "config";

    /**
     * Uses {@link ProjectProperties#CONFIG_DIR} as the default argument expected when running the application
     *
     * @param filename Name of the file inside the directory of the command line argument config.dir
     */
    public static void init(String filename) {
        init(CONFIG_DIR, filename);
    }

    /**
     * Instantiate config file from command line argument location
     *
     * @param propertyFileArgumentLocation Command line argument key
     * @param filename Name of the file to load
     */
    public static void init(String propertyFileArgumentLocation, String filename) {
        Properties loadPropertyFile = loadPropertyFiles(propertyFileArgumentLocation, filename);

        appendSystemProperties(loadPropertyFile);
    }

    private static void appendSystemProperties(Properties loadPropertyFile) {
        Properties properties = System.getProperties();
        properties.putAll(loadPropertyFile);
        System.setProperties(properties);
    }

    private static Properties loadPropertyFiles(String dirArgument, String filename) {
        Properties properties = new Properties();

        loadPropertyFiles(properties, String.format("%s/%s", resolveDir(dirArgument), filename));

        return properties;
    }

    private static void loadPropertyFiles(Properties properties, String filename) {
        try (FileInputStream inputStream = new FileInputStream(filename)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ProjectPropertyFileException("Could not find file [" + filename + "]", e.getCause());
        }
    }

    public static String resolveDir(String propertyArgument) {
        return System.getProperty(propertyArgument, DEFAULT_DIR);
    }
}
