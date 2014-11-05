package com.salmon.test.step_definitions;


import com.salmon.test.pageobjects.HomeSamplePage;
import cucumber.api.java.en.And;

public class HomePageSteps {

   private HomeSamplePage homePage =  new HomeSamplePage();


    @And("^i click on Sign In on the Home Page$")
    public void i_click_on_Sign_In_on__the_Home_Page() throws Throwable {
        homePage.clickSignInLink();
    }

}