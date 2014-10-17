package com.salmon.test;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes", tags = {"@api"}, format = {"pretty","html:target/cucumber-report/run2at","json:target/cucumber-report/run2at/cucumber.json"})
public class Run2AT {
}
