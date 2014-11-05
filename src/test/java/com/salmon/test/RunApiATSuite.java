package com.salmon.test;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes", tags = {"@api"}, format = {"pretty","html:target/cucumber-report/runapiat","json:target/cucumber-report/runapiat/cucumber.json"})
public class RunApiATSuite {
}
