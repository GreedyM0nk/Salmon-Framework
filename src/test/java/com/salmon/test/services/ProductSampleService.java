package com.salmon.test.services;

import com.salmon.test.framework.helpers.UrlBuilder;

import java.net.URL;

public class ProductSampleService extends UrlBuilder {

    public URL getListOfColours(){
      return  getApiUrlForEndPoint("/en/api/products/colors");
    }
}
