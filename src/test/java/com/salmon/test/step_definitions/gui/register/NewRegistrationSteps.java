package com.salmon.test.step_definitions.gui.register;


import com.salmon.test.pageobjects.NewRegistrationPage;
import cucumber.api.java.en.When;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.ALPHANUMERIC;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.junit.Assert.assertTrue;

public class NewRegistrationSteps {

        private NewRegistrationPage newRegistrationPage;

        private String  loginIdData = random(6,ALPHANUMERIC);
        private String  passwordData = random(6,ALPHANUMERIC);
        private String firstNameData = random(6,ALPHABETS);
        private String lastNameData = random(6,ALPHABETS);
        private String postCodeData = random(2,ALPHABETS) + random(2,NUMERIC) + " " + random(3,ALPHANUMERIC);
        private String address1Data = random(6,ALPHABETS);
        private String townOrCityData= random(6,ALPHABETS);
        private String emailAddressData = random(6,ALPHABETS);




    public NewRegistrationSteps(NewRegistrationPage newRegistrationPage){
            this.newRegistrationPage = newRegistrationPage;
        }


    @When("^i register a customer on New Registration page$")
    public void i_register_a_customer_on_New_Registration_page() throws Throwable {
            assertTrue("New Registration Form is Displayed",newRegistrationPage.checkNewRegistrationForm());
            enterUserRegistrationDetails();

    }



    public void enterUserRegistrationDetails(){

        newRegistrationPage.loginIdText().sendKeys(loginIdData);
        newRegistrationPage.passwordText().sendKeys(passwordData);
        newRegistrationPage.verifyPasswordText().sendKeys(passwordData);

        newRegistrationPage.selectTitle().selectByVisibleText("Dr.");
        newRegistrationPage.firstNameText().sendKeys(firstNameData);
        newRegistrationPage.lastNameText().sendKeys(lastNameData);

        newRegistrationPage.postCodeText().sendKeys(postCodeData);
        newRegistrationPage.address1Text().sendKeys(address1Data);
        newRegistrationPage.townOrCityText().sendKeys(townOrCityData);

        newRegistrationPage.emailAddressText().sendKeys(emailAddressData);
        newRegistrationPage.confirmEmailAddressText().sendKeys(emailAddressData);

        newRegistrationPage.birthDayText().selectByVisibleText("1");
        newRegistrationPage.birthMonthText().selectByVisibleText("1");
        newRegistrationPage.birthYearText().selectByVisibleText("1982");

        newRegistrationPage.acceptTermsAndConditions(true);

        newRegistrationPage.submitRegistration();

    }
}