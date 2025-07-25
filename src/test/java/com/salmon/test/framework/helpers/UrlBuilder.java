package com.salmon.test.framework.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Utility class for building URLs and URIs for site and API endpoints.
 */
public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static URL basePath;
    private static URL apiUrl;

    static {
        try {
            LoadProperties.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
            String siteUrl = LoadProperties.getRunProps().getProperty("site.url");
            String apiUrlStr = LoadProperties.getRunProps().getProperty("api.url");
            if (siteUrl != null) {
                basePath = new URL(siteUrl);
            }
            if (apiUrlStr != null) {
                apiUrl = new URL(apiUrlStr);
            }
        } catch (MalformedURLException e) {
            LOG.error("Malformed URL in environment.properties: {}", e.getMessage());
        }
    }

    /**
     * Navigates the WebDriver to the home page.
     */
    public static void startAtHomePage() {
        String url = getUrl("site.url");
        if (url != null) {
            WebDriverHelper.getWebDriver().navigate().to(url);
        } else {
            LOG.error("site.url property is missing in environment.properties");
        }
    }

    /**
     * Returns the API URL for a given endpoint.
     */
    public static URL getApiUrlForEndPoint(String endpoint) {
        return createApiUrl(endpoint);
    }

    /**
     * Returns the base path URI for the site.
     */
    public static URI getBasePathURI() {
        String siteUrl = LoadProperties.getRunProps().getProperty("site.url");
        if (siteUrl != null) {
            return URI.create(siteUrl);
        } else {
            LOG.error("site.url property is missing in environment.properties");
            return null;
        }
    }

    /**
     * Gets a property value from the run configuration.
     */
    public static String getUrl(String applicationUrl) {
        return LoadProperties.getRunProps().getProperty(applicationUrl);
    }

    /**
     * Creates an API URL for the given endpoint.
     */
    public static URL createApiUrl(String endpoint) {
        try {
            if (apiUrl == null) {
                throw new IllegalStateException("api.url property is missing in environment.properties");
            }
            return new URL(apiUrl.getProtocol(), apiUrl.getHost(), apiUrl.getPort(), endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed API endpoint URL: " + endpoint, e);
        }
    }

    /**
     * Creates a site URL for the given path.
     */
    public static URL createUrl(String path) {
        try {
            if (basePath == null) {
                throw new IllegalStateException("site.url property is missing in environment.properties");
            }
            return new URL(basePath.getProtocol(), basePath.getHost(), basePath.getPort(), path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed site path URL: " + path, e);
        }
    }
}
