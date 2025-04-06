package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/java/resources/features", monochrome = true, dryRun=true, plugin = {
        "pretty", "html:target/cucumber-report/single",
        "json:target/cucumber-report/single/cucumber.json"})
public class TestRunSingleSuite extends AbstractTestNGCucumberTests {
}