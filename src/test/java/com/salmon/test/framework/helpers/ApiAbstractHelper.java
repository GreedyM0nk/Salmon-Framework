package com.salmon.test.framework.helpers;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import lombok.Getter;

/**
 * Every Api Step definition class should extend this class
 */

@Getter
public abstract class ApiAbstractHelper {
    static final String APPLICATION_JSON = "application/json";
    static final String WRAPPED_JSON = "application/vnd.rhq.wrapped+json";
    private static final String APPLICATION_XML = "application/xml";
    private static final String TEXT_CSV = "text/csv";
    private static final String TEXT_HTML = "text/html";
    static final String REST_TEST_DUMMY = "-rest-test-dummy-";
    static Header acceptJson = new Header("Accept", APPLICATION_JSON);
    static Header acceptXml = new Header("Accept", APPLICATION_XML);
    static Header acceptHtml = new Header("Accept", TEXT_HTML);
    static Header acceptCsv = new Header("Accept", TEXT_CSV);
    static Header acceptWrappedJson = new Header("Accept", WRAPPED_JSON);

    /*Initial setup and configuration for Api*/

    public void setUp() throws Exception {

        RestAssured.baseURI = LoadProperties.getRunProps().getProperty("api.url");
        RestAssured.port = Integer.parseInt(LoadProperties.getRunProps().getProperty("site.port"));
        RestAssured.basePath = LoadProperties.getRunProps().getProperty("site.basepath");
        //RestAssured.authentication = basic("","");
    }

}