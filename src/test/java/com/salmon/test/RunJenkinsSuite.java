package com.salmon.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "target/test-classes", tags = "@gui,@api", monochrome = true, format = {
		"pretty", "html:target/cucumber-report/runjenkins",
		"json:target/cucumber-report/runjenkins/cucumber.json" })
public class RunJenkinsSuite {
}
