package com.sprintqa.class50;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Example using Selenium WebDriver to click links. Use this site
 * https://www.seleniumeasy.com/ for testing.
 */
class UsingLinksWithSeleniumExample {
	WebDriver webDriver;
	WebDriver nextPage;

	// Set test String values
	String homeUrl = "https://www.seleniumeasy.com/";
	String homePageTitle = "Learn Selenium with Best Practices and Examples | Selenium Easy";

	String tutorialUrl = "https://www.seleniumeasy.com/selenium-tutorials";
	String nextPageTitle = "Selenium Tutorials | Selenium Easy";

	String pageTitle;
	String currentUrl;

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
	 * Test clicking a Hyperlink.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void clickHyperlinkTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(homeUrl);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		WebElement seleniumTestTutorialsLink = webDriver
				.findElement(By.xpath("//a[@title='selenium tutorials'][text()='Selenium Tutorials']"));

		// Click the link
		seleniumTestTutorialsLink.click();

		// Why should we put a pause here.
		// So we can wait for the page to load.
		Thread.sleep(1000);

		// Get current page information
		pageTitle = webDriver.getTitle();
		currentUrl = webDriver.getCurrentUrl();

		// Verify we are on the next page
		assertEquals(nextPageTitle, pageTitle);
		assertEquals(tutorialUrl, currentUrl);
	}

	/**
	 * Test clicking on n image with a Hyperlink.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void clickAnImageWithAHyperlinkTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(tutorialUrl);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		WebElement seleniumEasyPageLogoImg = webDriver.findElement(By.xpath("//img[@alt='Home ']"));

		// Click link
		seleniumEasyPageLogoImg.click();

		// Why should we put a pause here.
		// So we can wait for the page to load.
		Thread.sleep(1000);

		// Get current page information
		pageTitle = webDriver.getTitle();
		currentUrl = webDriver.getCurrentUrl();

		// Verify we are on the next page
		assertEquals(pageTitle, homePageTitle);
		assertEquals(homeUrl, currentUrl);
	}

}
