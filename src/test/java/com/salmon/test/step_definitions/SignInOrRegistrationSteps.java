package com.salmon.test.step_definitions;

import com.salmon.test.pageobjects.SignInSamplePage;
import cucumber.api.java.en.And;



public class SignInOrRegistrationSteps {

    private SignInSamplePage signInSamplePage = new SignInSamplePage();

    @And("^i click on Register on New Registration page$")
    public void i_click_on_Register_on_New_Registration_page() throws Throwable {
        signInSamplePage.clickRegisterButton();
    }
}
