package com.salmon.test.framework.helpers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
    private static final Logger LOG = LoggerFactory.getLogger(LoadProperties.class);

    private static final Properties runProps = new Properties();

    /**
     * Loads the environment properties from the given file location.
     */
    public static synchronized void loadRunConfigProps(String configPropertyFileLocation) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configPropertyFileLocation)) {
            if (inputStream == null) {
                throw new IOException("Properties file not found: " + configPropertyFileLocation);
            }
            runProps.clear();
            runProps.load(inputStream);
            setUpEnvironmentURLFor("site.url");
            setUpEnvironmentURLFor("api.url");
            setUpEnvironmentURLFor("site.port");
            setUpEnvironmentURLFor("site.basepath");
            setUpEnvironmentURLFor("browser");
            setUpEnvironmentURLFor("platform");
            setUpEnvironmentURLFor("driver.root.dir");
        } catch (IOException e) {
            LOG.error("Failed to load properties from {}", configPropertyFileLocation, e);
        }
    }

    /**
     * Gets a property value, or returns the default if not found.
     */
    public static String getProperty(String key, String defaultValue) {
        if (runProps.isEmpty()) {
            throw new IllegalStateException("Properties file not loaded. Call loadRunConfigProps() first.");
        }
        return runProps.getProperty(key, defaultValue);
    }

    /**
     * Ensures environment URLs are set up, possibly overridden by VM options.
     */
    protected static void setUpEnvironmentURLFor(String key) {
        String value = getProperty(key, null);
        if (value == null) {
            LOG.warn("Property '{}' not found. Skipping setup for this key.", key);
            return;
        }
        LOG.info("Properties: key '{}' value '{}'", key, value);

        if (!StringUtils.startsWith(value, "http://")) {
            String urlFromVMOptions = System.getProperty(key);
            if (urlFromVMOptions != null) {
                runProps.put(key, urlFromVMOptions);
                LOG.info("Overriding property '{}' with VM option value '{}'", key, urlFromVMOptions);
            }
        }
    }

    /**
     * Returns the loaded properties.
     */
    public static Properties getRunProps() {
        return runProps;
    }
}