package com.shopease.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopease.utils.ConfigReader;
import com.shopease.utils.WaitUtils;


public class SeleniumPage
{
	 private WebDriver driver;
	 private WaitUtils waitUtils;
	 
	 @FindBy(css = "h1")
	 private WebElement mainHeading;

    public SeleniumPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        
        PageFactory.initElements(driver, this);
    }
    
    
    public void openPage()
	{
		driver.get(ConfigReader.getProperty("baseUrl"));
	}
    public String getMainHeading()
    {
    	       waitUtils.waitForVisibility(org.openqa.selenium.By.cssSelector("h1"));

        return mainHeading.getText();
    }
    public String getPageTitle()
    {
    		return driver.getTitle();
    }
	
	
}
