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
 * Example using Selenium WebDriver to click links.
 * <p>
 * Use this site @see <a href="https://www.seleniumeasy.com/">https://www.seleniumeasy.com/</a> for testing.
 */
class UsingLinksWithSeleniumExample {
	WebDriver webDriver;
	WebDriver nextPage;
	
	String homeUrl = "https://www.seleniumeasy.com/";
	String homePageTitle = "Learn Selenium with Best Practices and Examples | Selenium Easy";
	
	String tutorialUrl = "https://www.seleniumeasy.com/selenium-tutorials";
	String nextPageTitle = "Selenium Tutorials | Selenium Easy";

	String pageTitle;
	String currentUrl;
	
	/**
	 * Remember to configure your System path so the application
	 * can find your ChromeDriver binary files. 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"/Users/mpmeloche/Development/eclipse/workspace/SeleniumExamples/webdrivers/chromedriver");
		webDriver = new ChromeDriver();
	}
	
	/**
	 * Test clicking a Hyperlink.
	 * 
	 * @throws InterruptedException 
	 */
	@Test
	void clickHyperlinkTest() throws InterruptedException {
		// Declare the WebElements we will be using
		WebElement seleniumTestTutorialsLink;
				
		webDriver.get(homeUrl);
		webDriver.manage().window().maximize();

		// Why wont this work... //a[@title='selenium tutorials']
		seleniumTestTutorialsLink = webDriver.findElement(By.xpath("//a[@title='selenium tutorials'][text()='Selenium Tutorials']"));
		seleniumTestTutorialsLink.click();

		// Why should we put a pause here.
		Thread.sleep(1000);

		pageTitle = webDriver.getTitle();
		assertEquals(nextPageTitle, pageTitle);
		
		currentUrl = webDriver.getCurrentUrl();
		assertEquals(tutorialUrl, currentUrl);
		
		Thread.sleep(5000);
	}

	/**
	 * Test clicking on n image with a Hyperlink.
	 * 
	 * @throws InterruptedException 
	 */
//	@Test
//	void clickAnImageWithAHyperlinkTest() throws InterruptedException {
//		// Declare the WebElements we will be using
//		WebElement seleniumEasyPageLogoImg;
//				
//		webDriver.get(tutorialUrl);
//		webDriver.manage().window().maximize();
//
//		seleniumEasyPageLogoImg = webDriver.findElement(By.xpath("//img[@alt='Home ']"));
//		seleniumEasyPageLogoImg.click();
//
//		// Why should we put a pause here.
//		Thread.sleep(1000);
//
//		pageTitle = webDriver.getTitle();
//		assertEquals(pageTitle, homePageTitle);
//		
//		currentUrl = webDriver.getCurrentUrl();
//		assertEquals(homeUrl, currentUrl);
//		
//		Thread.sleep(5000);
//	}	
	
	/**
	 * Make sure when your done running your tests that you close the window/tab
	 * and then exit out of the browser window.
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		webDriver.close();
		webDriver.quit();
	}

}
