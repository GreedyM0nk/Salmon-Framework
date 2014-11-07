package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Sample page object class which defines all the elements for a specific page.
 * Every  page object class should extend "PageObject" class
 */


public class NewRegistrationPage extends PageObject {

    private By newRegistrationForm = By.cssSelector("#pageregistration #Register");

    private By loginIdText = By.name("logonId");
    private By passwordText = By.name("logonPassword");
    private By verifyPasswordText = By.name("logonPasswordVerify");

    private By titleList = By.name("personTitle");
    private By firstNameText = By.name("firstName");
    private By lastNameText = By.name("lastName");

    private By postCodeText = By.name("zipCode");
    private By address1Text = By.name("address1");
    private By townOrCityText = By.name("city");

    private By emailText = By.name("email1");
    private By confirmEmailText = By.name("email2");


    private By birthDay = By.name("birth_date");
    private By birthMonth = By.name("birth_month");
    private By birthYear = By.name("birth_year");

    private By termsAndConditionsCheckBox = By.name("termsAndCons");

    private By submitButton = By.id("WC_UserRegistrationAddForm_links_1");


    public boolean checkNewRegistrationForm() {
        return waitForExpectedElement(newRegistrationForm).isDisplayed();
    }

    public WebElement loginIdText() {
        return waitForExpectedElement(loginIdText);
    }

    public WebElement passwordText() {
        return waitForExpectedElement(passwordText);
    }

    public WebElement verifyPasswordText() {
        return waitForExpectedElement(verifyPasswordText);
    }


    public Select selectTitle() {
        return new Select(waitForExpectedElement(titleList));
    }

    public WebElement firstNameText() {
        return waitForExpectedElement(firstNameText);
    }
    public WebElement lastNameText() {
        return waitForExpectedElement(lastNameText);
    }
    public WebElement postCodeText() {
        return waitForExpectedElement(postCodeText);
    }

    public WebElement address1Text() {
        return waitForExpectedElement(address1Text);
    }

    public WebElement townOrCityText() {
        return waitForExpectedElement(townOrCityText);
    }


    public WebElement emailAddressText() {
        return waitForExpectedElement(emailText);
    }

    public WebElement confirmEmailAddressText() {
        return waitForExpectedElement(confirmEmailText);
    }


    public Select birthDayText() {
        return new Select(waitForExpectedElement(birthDay));
    }

    public Select birthMonthText() {
        return new Select(waitForExpectedElement(birthMonth));
    }

    public Select birthYearText() {
        return new Select(waitForExpectedElement(birthYear));
    }


    public void acceptTermsAndConditions(boolean yesOrNo) {
        WebElement termsAndConditionsWebElement = waitForExpectedElement(termsAndConditionsCheckBox);
        boolean selected = termsAndConditionsWebElement.isSelected();
        if (yesOrNo && !selected) {
            termsAndConditionsWebElement.click();
        } else if (!yesOrNo && selected) {
            termsAndConditionsWebElement.click();
        }
    }

    public void submitRegistration() {
        waitForExpectedElement(submitButton).click();
    }


}
