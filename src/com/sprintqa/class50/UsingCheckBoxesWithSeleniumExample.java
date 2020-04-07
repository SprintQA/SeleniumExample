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
 * Example using Selenium WebDriver to interact and test check boxes.
 * 
 * Use this site https://www.seleniumeasy.com/test/basic-checkbox-demo.html for
 * testing.
 */
class UsingCheckBoxesWithSeleniumExample {
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
	 * Single check box example.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void singleCheckBoxTest() throws InterruptedException {
		// Declare the WebElements we will be using
		WebElement txtAgeDiv;
		WebElement ageSelectedCheckbox;

		// Set your starting web page.
		String url = "https://www.seleniumeasy.com/test/basic-checkbox-demo.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		/*
		 * We can look this Element up a number of different ways:
		 * 
		 * ID: isAgeSelected
		 * 
		 * XPath: //input[@id='isAgeSelected'] or
		 * //input[@type='checkbox'][@id='isAgeSelected']
		 * 
		 * CSS Selector: input#isAgeSelected or #isAgeSelected
		 */
		ageSelectedCheckbox = webDriver.findElement(By.id("isAgeSelected"));

		/*
		 * Other ways of looking up AgeSelected Checkbox:
		 * 
		 * webDriver.findElement(By.xpath("//input[@id='isAgeSelected']"));
		 * 
		 * webDriver.findElement(By.xpath(
		 * "//input[@type='checkbox'][@id='isAgeSelected']"));
		 * 
		 * webDriver.findElement(By.cssSelector("input#isAgeSelected"));
		 * 
		 * webDriver.findElement(By.cssSelector("#isAgeSelected"));
		 */

		txtAgeDiv = webDriver.findElement(By.id("txtAge"));

		// If checkbox is not checked then the display message should be blank.
		if (!ageSelectedCheckbox.isSelected()) {
			assertEquals("", txtAgeDiv.getText());
		}

		// Click afe Selected
		ageSelectedCheckbox.click();

		// If checkbox is not checked then the display message should be "Success -
		// Check box is checked".
		if (ageSelectedCheckbox.isSelected()) {
			assertEquals("Success - Check box is checked", txtAgeDiv.getText());
		}

	}

}
