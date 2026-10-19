
package com.shopease.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir")
                    + "/test-output/ExtentReport.html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);
	    sparkReporter.config().setOfflineMode(true);

            sparkReporter.config().setDocumentTitle(
                    "SDET Automation Report");

            sparkReporter.config().setReportName(
                    "Selenium Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "ShopEase Automation");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Browser",
                    ConfigReader.getProperty("browser"));
        }

        return extent;
    }
}
