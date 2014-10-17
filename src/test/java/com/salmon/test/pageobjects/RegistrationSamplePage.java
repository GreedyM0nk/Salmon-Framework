package com.salmon.test.pageobjects;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*Sample page object class which defines all the elements for a specific page.
* Every  page object class should extend "PageObject" class
* */


public class RegistrationSamplePage extends PageObject {

    public RegistrationSamplePage( ) {
    RegistrationSamplePage registrationSamplePage = PageFactory.initElements(webDriver, RegistrationSamplePage.class);
    }

    @FindBy(id = "headerSignInLink")
    private WebElement headerSignInLinkUsingFindBy;



    public void getHeaderSignInLinkUsingFindBy(){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("headerSignInLink")));
        headerSignInLinkUsingFindBy.click();

    }



}
