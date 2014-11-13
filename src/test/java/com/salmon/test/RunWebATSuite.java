package com.salmon.test;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes", tags = {"@gui"},monochrome=true, format = {"pretty","html:target/cucumber-report/runwebat","json:target/cucumber-report/runwebat/cucumber.json"})
public class RunWebATSuite {
}

