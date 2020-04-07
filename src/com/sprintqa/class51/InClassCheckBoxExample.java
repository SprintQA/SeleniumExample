package com.sprintqa.class51;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassCheckBoxExample {
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
	 * Create a JUnit test to check all the check boxes. Loop through the check
	 * boxes Verify that all the check boxes isSelected. Print out the checked
	 * values to the console using System.out.println()
	 */
	@Test
	void inClassCheckBoxAssignmentTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://demoqa.com/automation-practice-form/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Use the WebDriver to search the web page using the "id" locator.
		WebElement checkBox0 = webDriver.findElement(By.id("tool-0"));

		// Click the check box
		checkBox0.click();

		// So we have a couple of option when selecting check boxes.
		// We could have used the locator id to get each individual check boxes,
		// but why create 3 lines of code to get the check boxes when we can do it in
		// one.
		// We can use the Xpath locator to get all inputs with the name attribute equal
		// to tool.
		List<WebElement> checkBoxList = webDriver.findElements(By.xpath("//input[@name='tool']"));

		// Using the for loop, we can loop through the List of check boxes
		for (WebElement checkBox : checkBoxList) {

			// Check if the option is checked.
			if (checkBox.isSelected()) {
				// Write the output of our test to the System console.
				System.out.println(checkBox.getAttribute("value"));
			}
		}

	}

}
