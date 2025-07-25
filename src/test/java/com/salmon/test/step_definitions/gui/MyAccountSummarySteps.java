package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.MyAccountSummaryPage;
import com.salmon.test.step_definitions.gui.register.NewRegistrationSteps;
import io.cucumber.java.en.Then;

import static org.testng.Assert.assertEquals;

public class MyAccountSummarySteps {

    private final NewRegistrationSteps newRegistrationSteps;
    private final MyAccountSummaryPage myAccountSummaryPage;

    public MyAccountSummarySteps(MyAccountSummaryPage myAccountSummaryPage, NewRegistrationSteps newRegistrationSteps) {
        this.newRegistrationSteps = newRegistrationSteps;
        this.myAccountSummaryPage = myAccountSummaryPage;
    }

    @Then("^i am registered successfully and can view \"([^\"]*)\" page$")
    public void iAmRegisteredSuccessfullyAndCanViewPage(String pageTitle) throws Exception {
        assertEquals(pageTitle, myAccountSummaryPage.getMyaccountSummaryTitle(), "Page title mismatch");

        // Defensive null check
        if (myAccountSummaryPage.getPersonalInformation() == null || myAccountSummaryPage.getPersonalInformation().size() < 4) {
            throw new Exception("Personal information is missing or incomplete on My Account Summary page.");
        }

        String nameOfTheCustomer = myAccountSummaryPage.getPersonalInformation().get(0).getText();
        String address = myAccountSummaryPage.getPersonalInformation().get(1).getText();
        String townOrCity = myAccountSummaryPage.getPersonalInformation().get(2).getText();
        String emailAddress = myAccountSummaryPage.getPersonalInformation().get(3).getText();

        assertEquals(newRegistrationSteps.getFirstNameData() + " " + newRegistrationSteps.getLastNameData(), nameOfTheCustomer, "Customer name mismatch");
        assertEquals(newRegistrationSteps.getAddress1Data(), address, "Address mismatch");
        assertEquals(newRegistrationSteps.getTownOrCityData(), townOrCity, "Town/City mismatch");
        assertEquals(newRegistrationSteps.getEmailAddressData(), emailAddress, "Email address mismatch");
    }
}
