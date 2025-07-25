package com.salmon.test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/**
 * TestNG runner for single suite execution using Cucumber.
 * Generates HTML and JSON reports under target/cucumber-report/single.
 */
@CucumberOptions(
    features = "src/test/resources/features", // Update this path if your feature files are elsewhere
    glue = "com.salmon.test.step_definitions", // Step definitions package
    monochrome = true, // Cleaner console output
    dryRun = true, // Set to false to run tests, true to check step definitions
    plugin = {
        "pretty",
        "html:target/cucumber-report/single",
        "json:target/cucumber-report/single/cucumber.json"
    }
)
public class TestRunSingleSuite extends AbstractTestNGCucumberTests {
    // No additional code needed; Cucumber and TestNG handle execution.
}