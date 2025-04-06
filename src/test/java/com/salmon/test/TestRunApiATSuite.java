package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "target/test-classes", tags = { "@api" }, monochrome = true, plugin = {
		"pretty", "html:target/cucumber-report/runapiat",
		"json:target/cucumber-report/runapiat/cucumber.json" })
public class TestRunApiATSuite extends AbstractTestNGCucumberTests{
}
