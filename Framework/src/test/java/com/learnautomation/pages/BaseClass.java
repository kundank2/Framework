package com.learnautomation.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setupSuite() {
		Reporter.log("Setting up reports and test is getting ready", true);
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		ExtentSparkReporter extent = new ExtentSparkReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Setting Done reports and test can be started", true);

	}

	@BeforeClass
	public void setup() {
		Reporter.log("Trying to start browser and getting application ready", true);

		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getQAUrl());
	
		Reporter.log("Browser and Application is up and running", true);

	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		Reporter.log("Test is about to end", true);

		if (result.getStatus() == ITestResult.FAILURE) {
			//System.out.println("www");
			logger.fail("Test Failed ",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreeshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			//System.out.println("www7");
			logger.pass("Test Passed ",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreeshot(driver)).build());

		}
		report.flush();
		Reporter.log("Test Completed >>> Reports Generated", true);
	}
}
