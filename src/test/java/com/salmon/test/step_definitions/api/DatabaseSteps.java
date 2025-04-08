package com.salmon.test.step_definitions.api;

import com.salmon.test.framework.helpers.DatabaseHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */

public class DatabaseSteps extends DatabaseHelper {

    private List<Map<String, Object>> results;

    @When("^I run the query to get list of users \"(.*?)\" from mysql database$")
    public void i_run_the_query_to_get_list_of_users_from_mysql_database(String sqlQuery) throws Throwable {
        results = DatabaseHelper.executeQuery(sqlQuery);
    }

    @Then("^the list of users is \"(.*?)\"$")
    public void the_list_of_users_is(String checkResult) throws Throwable {
        Assert.assertNotNull(results, "Results should not be null");
        Assert.assertFalse(results.isEmpty(), "Results should not be empty");
    }
}
