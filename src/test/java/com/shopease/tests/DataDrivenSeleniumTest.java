
package com.shopease.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.shopease.utils.CsvDataReader;

import com.shopease.base.BaseTest;
import com.shopease.pages.SeleniumPage;

public class DataDrivenSeleniumTest extends BaseTest {

    @DataProvider(name = "headingData")
    public Object[][] provideHeadingData() {
        return CsvDataReader.readCsv("testdata/heading-data.csv");
        };
    

    @Test(dataProvider = "headingData")
    public void validatePageHeading(
            String expectedText, boolean shouldContain) {

        SeleniumPage seleniumPage = new SeleniumPage(driver);

        seleniumPage.openPage();

        String actualHeading = seleniumPage.getMainHeading();

        System.out.println(
                "Expected text: " + expectedText
                + " | Actual heading: " + actualHeading);

        if (shouldContain) {
            Assert.assertTrue(
                    actualHeading.contains(expectedText),
                    "Heading should contain: " + expectedText);
        } else {
            Assert.assertFalse(
                    actualHeading.contains(expectedText),
                    "Heading should not contain: " + expectedText);
        }
    }
}
