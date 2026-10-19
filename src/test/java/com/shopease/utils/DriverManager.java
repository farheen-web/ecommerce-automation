
package com.shopease.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void setDriver() {

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

            case "remote":
                ChromeOptions options = new ChromeOptions();

                try {
                    driver.set(new RemoteWebDriver(
                            new URL(ConfigReader.getProperty("gridUrl")),
                            options
                    ));
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException(
                            "Invalid Selenium Grid URL", e
                    );
                }
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                        + ". Supported browsers: chrome, firefox, remote"
                );
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver currentDriver = driver.get();

        if (currentDriver != null) {
            try {
                currentDriver.quit();
            } finally {
                driver.remove();
            }
        }
    }
}
