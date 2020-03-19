package com.sprintqa.class51;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class InClassDropDownExample {
	// Declare WebDriver variable as a Class variable so we can use it through out
	// the class.
	WebDriver webDriver;

	/**
	 * Remember to configure your System path so the application can find your
	 * ChromeDriver binary files.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Register the Chrome Driver Binary to the system path so WebDriver can
		// communicate with the Chrome browser
		System.setProperty("webdriver.chrome.driver",
				"/Users/mpmeloche/Development/eclipse/workspace/SeleniumExamples/webdrivers/chromedriver");

		// Declare your webDriver class variable to a ChromeDriver WebDriver to
		// communicate with Chrome.
		webDriver = new ChromeDriver();
	}

	/**
	 * Create a JUnit test to test the facebook.com day drop down list form field.
	 * Loop through the drop down list values and verify that the default day 18.
	 * Then change it to 28.
	 */
	@Test
	void usingDropDownBoxesTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.facebook.com";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Use the WebDriver to search the web page using the "id" locator.
		WebElement dayElement = webDriver.findElement(By.id("day"));

		// In order to interact with the drop down list you need to
		// declare a selenium.support.ui.Select class to interact with the select
		// element.
		Select daysSelect = new Select(dayElement);

		// In the Select class we can use the getAllSelectedOptions
		// to get a List of WebElements that are clicked.
		List<WebElement> daysOptions = daysSelect.getAllSelectedOptions();

		// Create a boolean variable to store the results of checking if todays day is
		// selected.
		boolean isTodaysDay = false;

		// Using the for loop, we can loop through the List of Options
		for (WebElement option : daysOptions) {

			// Check if the option is checked.
			if (option.isSelected())

				// Check the option attribute value of the selected option to see if it equals
				// "18".
				isTodaysDay = option.getAttribute("value").equals("18");

			// Write the output of our test to the System console.
			System.out.println("Is selected value equal to 18? " + isTodaysDay);
		}

		// Here are 3 different ways to update a select drop down list to "28".
		// Select by Index, Value, and Visible Text.
		// daysSelect.selectByIndex(28);
		// daysSelect.selectByValue("28");
		daysSelect.selectByVisibleText("28");

		// Now that we have updated the Select List to "28"
		// We need to re-pull the select options to get the updated list.
		daysOptions = daysSelect.getAllSelectedOptions();

		// Create a boolean variable to store the results of checking if the day drop
		// down list is "28".
		boolean isTheTwentyEigth = false;

		// Using the for loop, we can loop through the List of Options
		for (WebElement option : daysOptions) {

			// Check if the option is checked.
			if (option.isSelected())

				// Check the option attribute value of the selected option to see if it equals
				// "28".
				isTheTwentyEigth = option.getAttribute("value").equals("28");

			// Write the output of our test to the System console.
			System.out.println("Is selected value equal to 28? " + isTheTwentyEigth);
		}

		Thread.sleep(5000);

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

}
