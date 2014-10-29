package com.salmon.test.framework.helpers;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class LoadProperties extends WebDriverHelper {
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static  final Logger LOG = LoggerFactory.getLogger(LoadProperties.class);
    @Getter
    private static Properties runProps;

    public static void loadRunConfigProps() {
        runProps = new Properties();
        try (InputStream is = WebDriverHelper.class.getResourceAsStream(RUN_CONFIG_PROPERTIES)) {
            runProps.load(is);
            setUpEnvironmentURLFor("site.url");
            setUpEnvironmentURLFor("api.url");
            setUpEnvironmentURLFor("site.port");
            setUpEnvironmentURLFor("site.basepath");
            setUpEnvironmentURLFor("browser");
            setUpEnvironmentURLFor("browser");
            setUpEnvironmentURLFor("webdriver.chrome.driver");

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
