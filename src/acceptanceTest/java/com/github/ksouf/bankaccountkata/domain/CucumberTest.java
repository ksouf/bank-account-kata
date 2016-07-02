package com.github.ksouf.bankaccountkata.domain;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:build/cucumber"},
        features = "src/acceptanceTest/resources/features/")
public class CucumberTest {

}