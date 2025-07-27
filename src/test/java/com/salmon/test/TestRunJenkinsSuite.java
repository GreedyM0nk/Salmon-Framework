package com.salmon.test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/**
 * TestNG runner for Jenkins automation suite using Cucumber.
 * This runner executes both GUI and API tests with tags "@gui" and "@api".
 * Reports are generated in HTML and JSON formats under target/cucumber-report/runjenkins.
 */
@CucumberOptions(
    features = "target/test-classes", // Path to compiled feature files
    glue = "com.salmon.test.step_definitions", // Step definitions package
    tags = "@gui,@api", // Run scenarios tagged with @gui or @api
    monochrome = true, // Cleaner console output
    plugin = {
        "pretty",
        "html:target/jenkins-report/runjenkins/runjenkins.html",
        "json:target/cucumber-report/runjenkins/cucumber.json"
    }
)
public class TestRunJenkinsSuite extends AbstractTestNGCucumberTests {
    // No additional code needed; Cucumber and TestNG handle execution.
}
