package com.salmon.test.step_definitions.gui;


import com.salmon.test.page_objects.HomePage;
//import cucumber.api.java.en.Given;
import io.cucumber.java.en.Given;

public class HomePageSteps {

   private HomePage homePage;

    public HomePageSteps(HomePage homePage){
        this.homePage = homePage;
    }


    @io.cucumber.java.en.Given("^i click \"(.*?)\" on the Home Page$")
    public void i_click_on_the_Home_Page(String pageToNavigate) throws Throwable {
        if (pageToNavigate.equals("SIGN_IN")){
            homePage.clickSignInLink();
        } else if (pageToNavigate.equals("SIGN_OUT")){
            homePage.clickSignOutLink();
        }
    }

}