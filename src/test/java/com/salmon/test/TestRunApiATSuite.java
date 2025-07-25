package com.salmon.test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/**
 * TestNG runner for API automation suite using Cucumber.
 */
@CucumberOptions(
    features = "src/test/resources/features/api", // Use your actual API feature files location
    glue = "com.salmon.test.step_definitions",
    tags = "@api",
    monochrome = true,
    plugin = {
        "pretty",
        "html:target/cucumber-report/runapiat",
        "json:target/cucumber-report/runapiat/cucumber.json"
    }
)
public class TestRunApiATSuite extends AbstractTestNGCucumberTests {
}
