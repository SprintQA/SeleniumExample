package com.sprintqa.class51.hwsolution;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Set your Url to: https://www.facebook.com/ Populate the Month, Day, and Year
 * drop down list values with your Date Of Birth.
 * 
 * @author mpmeloche
 *
 */
public class PopulateFacebookDOBFieldsTest {
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
	 * Use all three Select methods: selectByIndex, selectByValue, and
	 * selectByVisibleText
	 * 
	 * After you have populated your Date Of Birth, loop through each drop down list
	 * and verify the Month, Day, and Year values match what you entered.
	 * 
	 * Make sure you use the Select methods: getOptions & getAllSelectedOptions
	 * 
	 * Write these values to the System console.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void populateFacebookDOBFieldsTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.facebook.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Search the web page for the check all button using the locator id.
		WebElement month = webDriver.findElement(By.name("birthday_month"));
		WebElement day = webDriver.findElement(By.name("birthday_day"));
		WebElement year = webDriver.findElement(By.name("birthday_year"));

		// DOB values
		int monthIndexValue = 11;
		String dayValue = "30";
		String yearValue = "1950";

		// Convert WebElements to Select Objects to interact with Selects
		Select monthSelect = new Select(month);
		Select daySelect = new Select(day);
		Select yearSelect = new Select(year);

		// Select dropdown values
		monthSelect.selectByIndex(monthIndexValue);
		daySelect.selectByValue(dayValue);
		yearSelect.selectByVisibleText(yearValue);

		Thread.sleep(3000);

		assertTrue(isOptionInAllSelectedOptions(monthSelect, monthIndexValue + ""));
		assertTrue(isOptionInAllSelectedOptions(daySelect, dayValue));
		assertTrue(isOptionInAllSelectedOptions(yearSelect, yearValue));

		assertTrue(isOptionSelected(monthSelect, monthIndexValue + ""));
		assertTrue(isOptionSelected(daySelect, dayValue));
		assertTrue(isOptionSelected(yearSelect, yearValue));
	}

	boolean isOptionInAllSelectedOptions(Select select, String value) {
		// Loop through selected options
		for (WebElement webElement : select.getAllSelectedOptions()) {
			// If option is selected return true
			if (webElement.getAttribute("value").equals(value))
				return true;
		}
		// If we make it through the loop our option was not selected.
		return false;
	}

	boolean isOptionSelected(Select select, String value) {
		// Loop through selected options
		for (WebElement webElement : select.getOptions()) {
			// If option is selected return true
			if (webElement.isSelected() && webElement.getAttribute("value").equals(value))
				return true;
		}
		// If we make it through the loop our option was not selected.
		return false;
	}

}
