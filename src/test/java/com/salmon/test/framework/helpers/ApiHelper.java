package com.salmon.test.framework.helpers;

import io.restassured.http.Header;

/**
 * Base API helper for step definitions.
 * Provides common content-type headers for API requests.
 */
public class ApiHelper {
    public static final String APPLICATION_JSON = "application/json";
    public static final String WRAPPED_JSON = "application/vnd.rhq.wrapped+json";
    public static final String APPLICATION_XML = "application/xml";
    public static final String TEXT_CSV = "text/csv";
    public static final String TEXT_HTML = "text/html";
    public static final String REST_TEST_DUMMY = "-rest-test-dummy-";

    public static final Header ACCEPT_JSON = new Header("Accept", APPLICATION_JSON);
    public static final Header ACCEPT_XML = new Header("Accept", APPLICATION_XML);
    public static final Header ACCEPT_HTML = new Header("Accept", TEXT_HTML);
    public static final Header ACCEPT_CSV = new Header("Accept", TEXT_CSV);
    public static final Header ACCEPT_WRAPPED_JSON = new Header("Accept", WRAPPED_JSON);
}