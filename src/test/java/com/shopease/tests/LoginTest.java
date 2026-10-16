package com.shopease.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.shopease.base.BaseTest;
import com.shopease.pages.SeleniumPage;
import com.shopease.utils.ConfigReader;


public class LoginTest extends BaseTest
{
	@Test
	public void verifyPageTitle()
	{
		System.out.println("Test Thread: " + Thread.currentThread().getName());
		
		System.out.println(
			    "Base URL: " + ConfigReader.getProperty("baseUrl")
			);

		System.out.println(
			    "Browser: " + ConfigReader.getProperty("browser")
			);

		System.out.println(
			    "Explicit wait: " + ConfigReader.getProperty("explicitWait")
			);
			
		SeleniumPage seleniumPage = new  SeleniumPage(driver);
		
		seleniumPage.openPage();
		
		String heading = seleniumPage.getMainHeading();
		
		System.out.println("Heading: " + heading);
		
		Assert.assertTrue(
	            heading.contains("Selenium"),
	            "Main heading does not contain Selenium"
	        );
	}
	
	@Test
	public void verifyMainHeading()
	{
		System.out.println("Test Thread: " + Thread.currentThread().getName());
		
		SeleniumPage seleniumPage = new  SeleniumPage(driver);
		
		seleniumPage.openPage();
		
		String heading = seleniumPage.getMainHeading();
		
		System.out.println("Heading: " + heading);
		
		Assert.assertTrue(
	            heading.contains("Selenium"),
	            "Main heading does not contain Selenium"
	        );
	}
}
