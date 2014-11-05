package com.salmon.test;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes",  format = {"pretty","html:target/cucumber-report/single","json:target/cucumber-report/single/cucumber.json"})
public class RunSingleSuite {
}

