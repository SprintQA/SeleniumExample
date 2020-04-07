package com.sprintqa.class51;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class UsingMultipleCheckBoxesWithSeleniumExample {
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
	 * Create a JUnit test to check all the check boxes.
	 */
	@Test
	void multipleCheckBoxesTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.seleniumeasy.com/test/basic-checkbox-demo.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Search the web page for the check all button using the locator id.
		WebElement checkBoxButton = webDriver.findElement(By.id("check1"));

		// Click the check all button to populate all the check boxes.
		checkBoxButton.click();

		// So in this example the check boxes do not have an id but they do share the
		// same
		// CSS so we can use the css selector tag.class.
		List<WebElement> checkBoxList = webDriver.findElements(By.cssSelector("input.cb1-element"));

		// Local variable to set which check box to click.
		int option = 1;

		// Loop though list of check boxes
		for (WebElement checkBox : checkBoxList) {

			// On the second loop through click the check box
			if (option == 2)
				checkBox.click();

			// Check to see if the check box is selected
			if (checkBox.isSelected()) {

				// Write the output of our test to the System console.
				System.out.println("Checkbox Option " + option + " isSelected()");
			}

			// Increment count
			option++;
		}

	}

}
