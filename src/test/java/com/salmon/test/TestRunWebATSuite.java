package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features/Webregister.feature", // Path to your feature file
        glue = "com.salmon.test.step_definitions", // Package containing step definitions
        tags = {"@gui"}, // Tags to filter scenarios
        monochrome = true, // Makes console output more readable
        plugin = {
                "pretty",
                "html:target/cucumber-report",
                "json:target/cucumber-report/cucumber.json"
        }
)
public class TestRunWebATSuite extends AbstractTestNGCucumberTests {}