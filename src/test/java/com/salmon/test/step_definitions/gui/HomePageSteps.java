package com.salmon.test.step_definitions.gui;


import com.salmon.test.pageobjects.HomePage;
import cucumber.api.java.en.And;

public class HomePageSteps {

   private HomePage homePage =  new HomePage();


    @And("^i click <\"([^\"]*)\"> on the Home Page$")
    public void i_click_on_the_Home_Page(String pageToNavigate) throws Throwable {
        if (pageToNavigate.equals("SIGN_IN")){
            homePage.clickSignInLink();
        }
    }

}