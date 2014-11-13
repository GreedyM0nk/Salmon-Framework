package com.salmon.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "target/test-classes", monochrome = true, format = {
		"pretty", "html:target/cucumber-report/single",
		"json:target/cucumber-report/single/cucumber.json" })
public class RunSingleSuite {

}
