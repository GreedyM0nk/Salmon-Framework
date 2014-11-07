package com.salmon.test.step_definitions.gui.register;

import com.salmon.test.pageobjects.SignInOrRegisterPage;
import cucumber.api.java.en.And;



public class SignInOrRegisterSteps {

    private SignInOrRegisterPage signInOrRegisterPage = new SignInOrRegisterPage();


    @And("^i click on Register for New Registration$")
    public void i_click_on_Register_for_New_Registration() throws Throwable {
        signInOrRegisterPage.clickRegisterButton();
    }

}
