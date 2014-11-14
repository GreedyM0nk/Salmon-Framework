package com.salmon.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "target/test-classes", tags = {"@gui"}, monochrome = true, format = {
        "pretty", "html:target/cucumber-report/runwebat",
        "json:target/cucumber-report/runwebat/cucumber.json"})
public class RunWebATSuite {
}
