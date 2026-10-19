package com.shopease.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.shopease.utils.DriverManager;

public class BaseTest
{
	protected WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		System.out.println("BROWSER env: " + System.getenv("BROWSER"));
		System.out.println("GRID_URL env: " + System.getenv("GRID_URL"));
		System.out.println("Resolved browser: "
		        + com.shopease.utils.ConfigReader.getProperty("browser"));
		System.out.println("Resolved Grid URL: "
		        + com.shopease.utils.ConfigReader.getProperty("gridUrl"));
		System.out.println("Setup thread: " + Thread.currentThread().getName());
		
		DriverManager.setDriver();
		driver = DriverManager.getDriver();
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void tearDown()
	{
		System.out.println("Teardown thread: " + Thread.currentThread().getName());
		
		DriverManager.quitDriver();
	}
}
