package com.salmon.test.framework.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Utility class for building URLs and URIs for site and API endpoints.
 * Loads base and API URLs from environment.properties.
 */
public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static final String RUN_CONFIG_PROPERTIES = "environment.properties";
    private static URL basePath;
    private static URL apiUrl;

    // Static block to initialize URLs from properties
    static {
        try {
            LoadProperties.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
            String siteUrl = LoadProperties.getRunProps().getProperty("site.url");
            String apiUrlStr = LoadProperties.getRunProps().getProperty("api.url");

            if (siteUrl != null && !siteUrl.isEmpty()) {
                basePath = URI.create(siteUrl).toURL(); // Avoid deprecated URL(String)
            } else {
                LOG.error("site.url property is missing or empty in environment.properties");
            }
            if (apiUrlStr != null && !apiUrlStr.isEmpty()) {
                apiUrl = URI.create(apiUrlStr).toURL(); // Avoid deprecated URL(String)
            } else {
                LOG.error("api.url property is missing or empty in environment.properties");
            }
        } catch (IllegalArgumentException | MalformedURLException e) {
            LOG.error("Malformed URL in environment.properties: {}", e.getMessage());
        }
    }

    /**
     * Navigates the WebDriver to the home page using the site.url property.
     */
    public static void startAtHomePage() {
        String url = getUrl("site.url");
        if (url != null && !url.isEmpty()) {
            WebDriverHelper.getWebDriver().navigate().to(url);
        } else {
            LOG.error("site.url property is missing or empty in environment.properties");
        }
    }

    /**
     * Returns the API URL for a given endpoint.
     * @param endpoint the API endpoint path
     * @return the full API URL
     */
    public static URL getApiUrlForEndPoint(String endpoint) {
        return createApiUrl(endpoint);
    }

    /**
     * Returns the base path URI for the site.
     * @return the base site URI, or null if not configured
     */
    public static URI getBasePathURI() {
        String siteUrl = LoadProperties.getRunProps().getProperty("site.url");
        if (siteUrl != null && !siteUrl.isEmpty()) {
            return URI.create(siteUrl);
        } else {
            LOG.error("site.url property is missing or empty in environment.properties");
            return null;
        }
    }

    /**
     * Gets a property value from the run configuration.
     * @param applicationUrl the property key
     * @return the property value, or null if missing
     */
    public static String getUrl(String applicationUrl) {
        String value = LoadProperties.getRunProps().getProperty(applicationUrl);
        if (value == null || value.isEmpty()) {
            LOG.error("{} property is missing or empty in environment.properties", applicationUrl);
        }
        return value;
    }

    /**
     * Creates an API URL for the given endpoint.
     * @param endpoint the API endpoint path
     * @return the full API URL
     */
    public static URL createApiUrl(String endpoint) {
        try {
            if (apiUrl == null) {
                throw new IllegalStateException("api.url property is missing in environment.properties");
            }
            // Build the new URL using URI for safety
            URI baseApiUri = apiUrl.toURI();
            URI endpointUri = baseApiUri.resolve(endpoint);
            return endpointUri.toURL();
        } catch (Exception e) {
            throw new RuntimeException("Malformed API endpoint URL: " + endpoint, e);
        }
    }

    /**
     * Creates a site URL for the given path.
     * @param path the site path
     * @return the full site URL
     */
    public static URL createUrl(String path) {
        try {
            if (basePath == null) {
                throw new IllegalStateException("site.url property is missing in environment.properties");
            }
            // Build the new URL using URI for safety
            URI baseSiteUri = basePath.toURI();
            URI pathUri = baseSiteUri.resolve(path);
            return pathUri.toURL();
        } catch (Exception e) {
            throw new RuntimeException("Malformed site path URL: " + path, e);
        }
    }
}
