package com.salmon.test.framework.helpers;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class LoadProperties {
    private static  final Logger LOG = LoggerFactory.getLogger(LoadProperties.class);
    @Getter
    private static Properties runProps;

    public static void loadRunConfigProps(String configPropertyFileLocation) {

        runProps = new Properties();
        try (InputStream inputStream = LoadProperties.class.getResourceAsStream(configPropertyFileLocation)) {
            runProps.load(inputStream);
            setUpEnvironmentURLFor("site.url");
            setUpEnvironmentURLFor("api.url");
            setUpEnvironmentURLFor("site.port");
            setUpEnvironmentURLFor("site.basepath");
            setUpEnvironmentURLFor("browser");
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
    }

    protected static void setUpEnvironmentURLFor(String key) {
        String value = getRunProps().getProperty(key);
        if (StringUtils.startsWith(value, "http://")) {
            return;
        }
        String urlFromVMOptions = System.getProperty(key);
        if (null != urlFromVMOptions) {
            LoadProperties.getRunProps().put(key, urlFromVMOptions);
        }
    }
}
