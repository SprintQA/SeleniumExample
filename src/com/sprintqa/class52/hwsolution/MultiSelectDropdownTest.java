package com.sprintqa.class52.hwsolution;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MultiSelectDropdownTest {
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
	 * 1) Navigate to http://uitestpractice.com/Students/Select
	 * 
	 * 2) From multi select dropdown:
	 * 
	 * print out total number of values print out each value from the dropdown check
	 * if DD has multi select option select the United States Submit code
	 * screenshot.
	 */
	@Test
	void multiSelectDropdownTest() {
		// Set your starting web page.
		String url = "http://uitestpractice.com/Students/Select";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Search the web page for the check all button using the locator id.
		WebElement countriesMultiple = webDriver.findElement(By.id("countriesMultiple"));

		// Convert so Select
		Select countriesSelect = new Select(countriesMultiple);

		// Print out size of dropdown
		System.out.println(countriesSelect.getOptions().size());

		// print out each value from the dropdown
		for (WebElement webElement : countriesSelect.getOptions()) {
			System.out.println(webElement.getText());
		}

		// Check if DD has multi select option
		assertTrue(Boolean.parseBoolean(countriesMultiple.getAttribute("multiple")));

		// Select the United States
		countriesSelect.selectByVisibleText("United states of America");

		// Verify US is selected
		for (WebElement webElement : countriesSelect.getAllSelectedOptions()) {
			if (webElement.equals("United states of America"))
				assertTrue(true);
		}

	}
}
