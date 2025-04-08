package com.salmon.test.framework.helpers;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//public  class LoadProperties {
//    private static  final Logger LOG = LoggerFactory.getLogger(LoadProperties.class);
//    @Getter
//    private static Properties runProps;
//
//    public static void loadRunConfigProps(String configPropertyFileLocation) {
//
//        runProps = new Properties();
//        try (InputStream inputStream = LoadProperties.class.getResourceAsStream(configPropertyFileLocation)) {
//            runProps.load(inputStream);
//            setUpEnvironmentURLFor("site.url");
//            setUpEnvironmentURLFor("api.url");
//            setUpEnvironmentURLFor("site.port");
//            setUpEnvironmentURLFor("site.basepath");
//            setUpEnvironmentURLFor("browser");
//            setUpEnvironmentURLFor("platform");
//            setUpEnvironmentURLFor("driver.root.dir");
//        } catch (IOException e) {
//            LOG.info(e.getMessage());
//        }
//    }
//
//    protected static void setUpEnvironmentURLFor(String key) {
//        String value = getRunProps().getProperty(key);
//        LOG.warn("Properties : key  " + key +" value :" + value);
//
//        if (StringUtils.startsWith(value, "http://")) {
//            return;
//        }
//        String urlFromVMOptions = System.getProperty(key);
//        if (null != urlFromVMOptions) {
//
//            LoadProperties.getRunProps().put(key, urlFromVMOptions);
//        }
//    }
//}
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

public class LoadProperties {
    private static final Logger LOG = LoggerFactory.getLogger(LoadProperties.class);

    @Getter
    private static final Properties runProps = new Properties();

    public static void loadRunConfigProps(String configPropertyFileLocation) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configPropertyFileLocation)) {
            if (inputStream == null) {
                throw new IOException("Properties file not found: " + configPropertyFileLocation);
            }
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

    public static String getProperty(String key, String defaultValue) {
        if (runProps.isEmpty()) {
            throw new IllegalStateException("Properties file not loaded. Call loadRunConfigProps() first.");
        }
        return runProps.getProperty(key, defaultValue);
    }

    protected static void setUpEnvironmentURLFor(String key) {
        String value = getProperty(key, null);
        if (value == null) {
            LOG.warn("Property '{}' not found. Skipping setup for this key.", key);
            return;
        }
        LOG.warn("Properties: key '{}' value '{}'", key, value);

        if (!StringUtils.startsWith(value, "http://")) {
            String urlFromVMOptions = System.getProperty(key);
            if (urlFromVMOptions != null) {
                runProps.put(key, urlFromVMOptions);
            }
        }
    }

    public static Properties getRunProps() {
        Properties unmodifiableProps = new Properties();
        unmodifiableProps.putAll(Collections.unmodifiableMap(runProps));
        return unmodifiableProps;

    }
}