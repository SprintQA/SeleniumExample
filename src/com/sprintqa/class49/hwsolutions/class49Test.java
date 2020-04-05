package com.sprintqa.class49.hwsolutions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 1. Using the Chrome console get the Xpath for every element on the page Goto
 * https://www.saucedemo.com/. (10 at a minimum).
 * 
 * 2. Capture all the WebElements from step 1 using the
 * findElement(By.xpath("xpath syntax")). We just want to make sure the program
 * can run and capture all the xpath elements.
 * 
 * @author mpmeloche
 *
 */
class class49Test {

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
	 * Capture all the WebElements from using the findElement(By.xpath("xpath
	 * syntax")). We just want to make sure the program can run and capture all the
	 * xpath elements.
	 */
	@Test
	void test() {
		// Look up page elements using WebDriver findElement() method.
		// Use By.xpath methods to specify the Xpath location.
		WebElement userName = webDriver.findElement(By.xpath("//*[@id='user-name']"));
		WebElement password = webDriver.findElement(By.xpath("//*[@id='password']"));
		WebElement form = webDriver.findElement(By.xpath("//form"));
		WebElement swagLabsLogo = webDriver.findElement(By.xpath("//div[@class='login_logo']"));
		WebElement robotImage = webDriver.findElement(By.xpath("//div[@class='bot_column']"));
		WebElement userNameHeader = webDriver.findElement(By.xpath("//div[@id='login_credentials']/h4"));
		WebElement passwordHeader = webDriver.findElement(By.xpath("//div[@class='login_password']/h4"));
		WebElement standardUserTxt = webDriver.findElement(By.xpath("//div[@id='login_credentials']/text()[2]"));
		WebElement lockedOutUserTxt = webDriver.findElement(By.xpath("//div[@id='login_credentials']/text()[3]"));
		WebElement problemUserTxt = webDriver.findElement(By.xpath("//div[@id='login_credentials']/text()[4]"));
		WebElement performanceGlitchUSerTxt = webDriver
				.findElement(By.xpath("//div[@id='login_credentials']/text()[5]"));
		WebElement secretSauceTxt = webDriver.findElement(By.xpath("//div[@class='login_password']/text()[2]"));

	}

}
