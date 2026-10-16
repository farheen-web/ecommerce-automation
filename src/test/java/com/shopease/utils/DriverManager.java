package com.shopease.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager
{
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void setDriver()
	{
		String browser = ConfigReader
                .getProperty("browser")
                .toLowerCase();
		switch (browser) {

        case "chrome":
            driver.set(new ChromeDriver());
            break;
            
        case "firefox":
            driver.set(new FirefoxDriver());
            break;

        default:
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
                    + ". Supported browsers: chrome, firefox");
		}
	}
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	public static void quitDriver()
	{
		if (driver.get() != null)
		{
			driver.get().quit();
			driver.remove();
		}
	}
	

}
