package com.learnautomation.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement loginPageButton;
	@FindBy(xpath = "//input[@placeholder='E-mail address']")
	WebElement username;
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	@FindBy(xpath = "//div[@class='ui fluid large blue submit button']")
	WebElement loginButton;
	

	public void loginToCRM(String usernameApplication, String passwordApplication) {
		loginPageButton.click();
		username.sendKeys(usernameApplication);
		password.sendKeys(passwordApplication);
		loginButton.click();
		
	}
}
