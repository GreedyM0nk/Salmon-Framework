package com.salmon.test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/**
 * TestNG runner for web automation suite using Cucumber.
 */
@CucumberOptions(
    features = "src/test/resources/features/web/",
    glue = "com.salmon.test.step_definitions",
    tags = "@gui",
    monochrome = true,
    plugin = {
        "pretty",
        "html:target/html-report/runwebat/runwebat.html",
        "json:target/cucumber-report/runwebat/cucumber.json"
    }
)
public class TestRunWebATSuite extends AbstractTestNGCucumberTests {}