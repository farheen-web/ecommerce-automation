package com.shopease.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataProviderTest
{
	@DataProvider(name="seatchData")
	public Object[][] provideSerachData()
	{
		return new Object[][]
			{
			{"Selenium", true},
			{"Playwright", true},
			{"", false}
			};
	}



@Test(dataProvider = "seatchData")
public void validateSearchInput(String searchText, boolean expectedValid)
	{
		boolean actualValid = searchText != null && !searchText.isBlank();
		System.out.println("Input: [" +searchText + "], Expected Valid: " + expectedValid + ", Actual Valid: " + actualValid);
		
		Assert.assertEquals(actualValid, expectedValid);
	}
}