package com.salmon.test;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes", tags = {"@gui"}, format = {"pretty","html:target/cucumber-report/run1at","json:target/cucumber-report/run1at/cucumber.json"})
public class Run1AT {
}

