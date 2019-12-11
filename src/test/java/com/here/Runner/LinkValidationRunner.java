package com.here.Runner;

import org.junit.runner.RunWith;		
import cucumber.api.CucumberOptions;		
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;		

@RunWith(Cucumber.class)				
@CucumberOptions(features="classpath:Features/LinkValidation.feature",
				glue = {"classpath:com.here.StepDefinations",
						"classpath:com.here.settings"})	


public class LinkValidationRunner extends AbstractTestNGCucumberTests			
{		

};