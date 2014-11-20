package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "target/test-classes", tags = "@gui,@api", monochrome = true, plugin = {
		"pretty", "html:target/cucumber-report/runjenkins",
		"json:target/cucumber-report/runjenkins/cucumber.json" })
public class RunJenkinsSuite extends  AbstractTestNGCucumberTests{
}
