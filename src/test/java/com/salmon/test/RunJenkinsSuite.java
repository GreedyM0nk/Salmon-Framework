package com.salmon.test;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes",tags = "@gui,@api", format = {"pretty","html:target/cucumber-report/runjenkins","json:target/cucumber-report/runjenkins/cucumber.json"})
public class RunJenkinsSuite {
}
