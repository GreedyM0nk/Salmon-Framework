package com.salmon.test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/**
 * TestNG runner for web automation suite using Cucumber.
 */
@CucumberOptions(
    features = "src/test/resources/features/Webregister.feature",
    glue = "com.salmon.test.step_definitions",
    tags = "@gui",
    monochrome = true,
    plugin = {
        "pretty",
        "html:target/cucumber-report",
        "json:target/cucumber-report/cucumber.json"
    }
)
public class TestRunWebATSuite extends AbstractTestNGCucumberTests {}