package com.salmon.test.pageobjects;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

/*Sample page object class which defines all the elements for a specific page.
* Every  page object class should extend "PageObject" class
* */


public class SignInSamplePage extends PageObject {

private By registerButton = By.id("WC_AccountDisplay_links_3");


    public void clickRegisterButton(){waitForExpectedElement(registerButton);
    }

}
