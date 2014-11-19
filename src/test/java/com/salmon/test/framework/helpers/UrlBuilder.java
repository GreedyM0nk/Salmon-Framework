package com.salmon.test.framework.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class UrlBuilder {
    private static  URL basePath;
    private static URL apiUrl;
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";


    static {
        try {
            LoadProperties.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
            basePath = new URL(LoadProperties.getRunProps().getProperty("site.url"));
            apiUrl = new URL(LoadProperties.getRunProps().getProperty("api.url"));
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage());
        }

    }

    public static void startAtHomePage() {
        WebDriverHelper.getWebDriver().navigate().to(getUrl("site.url"));
    }

    public  static URL getApiUrlForEndPoint(String endpoint){
        return createApiUrl(endpoint);
    }

    public static URI getBasePathURI() {
        return URI.create(LoadProperties.getRunProps().getProperty(""));
    }


    public static String getUrl(String applicationUrl) {
        return LoadProperties.getRunProps().getProperty(applicationUrl);
    }

    public static URL  createApiUrl(String endpoint) {
        try {
            return new URL(apiUrl.getProtocol(), apiUrl.getHost(), apiUrl.getPort(), endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static URL  createUrl(String path) {
        try {
            return new URL(basePath.getProtocol(), basePath.getHost(), basePath.getPort(), path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
