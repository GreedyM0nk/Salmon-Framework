package com.salmon.test.step_definitions.api;

import com.salmon.test.framework.helpers.DatabaseHelper;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */

public class DatabaseSteps extends DatabaseHelper {

    private List results;

    @io.cucumber.java.en.When("^I run the query to get list of users \"(.*?)\" from mysql database$")
    public void i_run_the_query_to_get_list_of_users_from_mysql_database(String sqlQuery) throws Throwable {
        results = DatabaseHelper.executeQuery(sqlQuery);

    }

    @io.cucumber.java.en.Then("^the list of users is \"(.*?)\"$")
    public void the_list_of_users_is(String checkResult) throws Throwable {
        Assert.assertTrue( results.size() > 0);
    }
}