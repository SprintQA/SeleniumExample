package com.sprintqa.class49;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Example using Xpath and CSS Selectors to locate WebElements to log into
 * SauceDemo
 * 
 * @author mpmeloche
 *
 */
class sauceDemoExample {
	WebDriver webDriver;

	/**
	 * Remember to configure your System path so the application can find your
	 * ChromeDriver binary files.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() {
		// Setup the System path to the Selenium Chrome Binary file
		// Use System.getProperty("user.dir") to get the system path for the project.
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/webdrivers/mac/chromedriver");

		// Instantiate WebDriver to use ChromeDriver.
		webDriver = new ChromeDriver();
	}

	/**
	 * Make sure when your done running your tests that you close the window/tab and
	 * then exit out of the browser window.
	 * 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		webDriver.close();
		webDriver.quit();
	}

	/**
	 * Test login functionality on saucedemo.com
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void test() throws InterruptedException {
		// using WebDriver open browser to saucedemo.com
		webDriver.get("https://www.saucedemo.com/");

		// Using the WebDriver to change the browser dimensions
		// manage() - Returns: An option interface
		// window() - Returns: the interface for managing the current window.
		// setSize() - Set the size of the current window. This will change the outer
		// window dimension, not just the view port, synonymous to window.resizeTo() in
		// JS.
		webDriver.manage().window().setSize(new Dimension(1280, 877));

		// One Second Pause For Display Purposes Only
		Thread.sleep(1000);

		// Look up page elements using WebDriver findElement() method.
		// Use By class methods to specify the locator type.
		WebElement userNameElement = webDriver.findElement(By.id("user-name"));
		WebElement passwordElement = webDriver.findElement(By.xpath("//input[@id='password']"));
		WebElement loginButton = webDriver.findElement(By.cssSelector("input.btn_action"));

		// Click the user name field to place mouse focus on element.
		userNameElement.click();

		// Populate user name with "standard_user"
		userNameElement.sendKeys("standard_user");

		// Populate user password with "secret_sauce"
		passwordElement.sendKeys("secret_sauce");

		// Click the login button to login
		loginButton.click();

		// One Second Pause For Display Purposes Only
		Thread.sleep(1000);
	}

}
