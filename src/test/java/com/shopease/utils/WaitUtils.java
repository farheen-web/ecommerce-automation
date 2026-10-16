package com.shopease.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils
{
	//private WebDriver driver;
	private WebDriverWait wait;
	
	public WaitUtils(WebDriver driver)
	{
		//this.driver=driver;
		int timeout = Integer.parseInt(ConfigReader.getProperty("explicitWait"));
		
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	}
	public WebElement waitForVisibility(By locator) {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }
	public WebElement waitForClickability(By locator) {
        return wait.until(
            ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public boolean waitForTitle(String title) {
        return wait.until(
            ExpectedConditions.titleContains(title)
        );
    }
}
