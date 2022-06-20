package com.learnautomation.utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.out.println("We don't support this browser.");
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	public static void quitBrowser(WebDriver driver) {

driver.quit();

	}
}
