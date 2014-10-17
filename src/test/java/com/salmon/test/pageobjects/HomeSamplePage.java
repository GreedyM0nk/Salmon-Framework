package com.salmon.test.pageobjects;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Sample page object class which defines all the elements for a specific page.
* Every  page object class should extend "PageObject" class
* */
public class HomeSamplePage extends PageObject {
    protected static final Logger LOG = LoggerFactory.getLogger(HomeSamplePage.class);

    private  By headerSignInLink = By.id("headerSignInLink");

      public void clickSignInLink(){
        waitForExpectedElement(headerSignInLink).click();
    }

}
