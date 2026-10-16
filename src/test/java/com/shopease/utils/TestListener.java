
package com.shopease.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentReportManager.getInstance();

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getTestClass().getName()
                + " - " + result.getMethod().getMethodName();

        ExtentTest extentTest = extent.createTest(testName);

        extentTest.info("Test execution started");
        extentTest.info("Thread: " + Thread.currentThread().getName());
        extentTest.info("Parameters: "
                + java.util.Arrays.deepToString(result.getParameters()));

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        test.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            extentTest.fail("Test failed");
            extentTest.fail(result.getThrowable());
        }

        test.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            extentTest.skip("Test skipped");

            if (result.getThrowable() != null) {
                extentTest.skip(result.getThrowable());
            
            }
           }
        test.remove();
  
        }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
