package com.salmon.test.pageobjects;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

/**
 * Sample page object class which defines all the elements for a specific page.
 * Every  Class which contains page objects should extend "PageObject" class
 * This gives access to the webdriver object and utility methods
 */
public class HomeSamplePage extends PageObject {

    private By headerSignInLink = By.cssSelector("#headerSignInLink a");

    public void clickSignInLink() {
        waitForExpectedElement(headerSignInLink).click();
    }

}
