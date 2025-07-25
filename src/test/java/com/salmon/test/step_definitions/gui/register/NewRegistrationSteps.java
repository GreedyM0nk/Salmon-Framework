package com.salmon.test.step_definitions.gui.register;

import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.NewRegistrationPage;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.ALPHANUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class NewRegistrationSteps {

    private final NewRegistrationPage newRegistrationPage;

    private final String loginIdData = random(6, ALPHANUMERIC);
    private final String passwordData = random(6, ALPHANUMERIC);
    private final String titleData = "Dr.";
    private final String firstNameData = random(6, ALPHABETS);
    private final String lastNameData = random(6, ALPHABETS);
    private final String postCodeData = "UB10 9DW";
    private final String address1Data = random(6, ALPHABETS);
    private final String townOrCityData = random(6, ALPHABETS);
    private final String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private final String birthDateData = "1";
    private final String birthMonthData = "1";
    private final String birthYearData = "1982";

    public NewRegistrationSteps(NewRegistrationPage newRegistrationPage) {
        this.newRegistrationPage = newRegistrationPage;
    }

    @When("^i fill in the registration form on New Registration page$")
    public void fillInRegistrationFormOnNewRegistrationPage() throws Exception {
        Assert.assertTrue(newRegistrationPage.checkNewRegistrationForm(), "New Registration Form is Displayed");
        enterUserRegistrationDetails();
    }

    public void enterUserRegistrationDetails() {
        newRegistrationPage.loginIdText().sendKeys(loginIdData);
        newRegistrationPage.passwordText().sendKeys(passwordData);
        newRegistrationPage.verifyPasswordText().sendKeys(passwordData);

        newRegistrationPage.selectTitle().selectByVisibleText(titleData);
        newRegistrationPage.firstNameText().sendKeys(firstNameData);
        newRegistrationPage.lastNameText().sendKeys(lastNameData);

        newRegistrationPage.postCodeText().sendKeys(postCodeData);
        newRegistrationPage.address1Text().sendKeys(address1Data);
        newRegistrationPage.townOrCityText().sendKeys(townOrCityData);

        newRegistrationPage.emailAddressText().sendKeys(emailAddressData);
        newRegistrationPage.confirmEmailAddressText().sendKeys(emailAddressData);

        newRegistrationPage.birthDayText().selectByVisibleText(birthDateData);
        newRegistrationPage.birthMonthText().selectByVisibleText(birthMonthData);
        newRegistrationPage.birthYearText().selectByVisibleText(birthYearData);

        newRegistrationPage.acceptTermsAndConditions(true);

        newRegistrationPage.submitRegistration();
    }
}