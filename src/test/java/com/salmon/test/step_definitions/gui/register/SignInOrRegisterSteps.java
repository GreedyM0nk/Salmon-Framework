package com.salmon.test.step_definitions.gui.register;

import com.salmon.test.page_objects.SignInOrRegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SignInOrRegisterSteps {

    private final SignInOrRegisterPage signInOrRegisterPage;

    public SignInOrRegisterSteps(SignInOrRegisterPage signInOrRegisterPage) {
        this.signInOrRegisterPage = signInOrRegisterPage;
    }

    @And("^i click on Register for New Registration$")
    public void clickOnRegisterForNewRegistration() throws Exception {
        signInOrRegisterPage.clickRegisterButton();
    }

    @Then("^i am signed out successfully and can view Sign In Page$")
    public void signedOutSuccessfullyAndCanViewSignInPage() throws Exception {
        Assert.assertTrue(
            signInOrRegisterPage.getSignInOrRegisterTitle().contains("Sign in or Register for a Lloydspharmacy account"),
            "Sign In Or Register Title"
        );
    }

    @Given("^i enter loginId \"(.*?)\" and password \"(.*?)\"$")
    public void enterLoginIdAndPassword(String loginId, String password) throws Exception {
        signInOrRegisterPage.loginIdText().clear();
        signInOrRegisterPage.loginIdText().sendKeys(loginId);

        signInOrRegisterPage.passwordText().clear();
        signInOrRegisterPage.passwordText().sendKeys(password);
    }

    @When("^i click on Sign In$")
    public void clickOnSignIn() throws Exception {
        signInOrRegisterPage.clickSignInButton();
    }

    @Then("^i can see the validation message \"(.*?)\"$")
    public void canSeeTheValidationMessage(String expectedErrorMessage) throws Exception {
        Assert.assertTrue(
            signInOrRegisterPage.getErrorMessage().contains(expectedErrorMessage),
            "Verify Error Message"
        );
    }
}
