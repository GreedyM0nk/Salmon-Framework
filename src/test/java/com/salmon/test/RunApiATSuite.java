package com.salmon.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "target/test-classes", tags = { "@api" }, monochrome = true, format = {
		"pretty", "html:target/cucumber-report/runapiat",
		"json:target/cucumber-report/runapiat/cucumber.json" })
public class RunApiATSuite {
}
