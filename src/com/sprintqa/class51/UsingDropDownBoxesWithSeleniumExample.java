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

class UsingDropDownBoxesWithSeleniumExample {
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
	 * Create a JUnit test to test the facebook.com MONTH drop down list form field.
	 * Loop through the drop down list values and verify that the default month is march.
	 * Then change it to Aug.
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
		WebElement monthElement = webDriver.findElement(By.id("month"));
		
		// In order to interact with the drop down list you need to
		// declare a selenium.support.ui.Select class to interact with the select
		// element.
		Select monthSelect = new Select(monthElement);
		
		// Here are 3 different ways to update a select drop down list:
		// monthSelect.selectByIndex(12);
		// monthSelect.selectByValue("6");
		monthSelect.selectByVisibleText("Aug");
		
		// In the Select class we can use the getOptions
		// to get the List of options in the select.
		List<WebElement> monthOptions = monthSelect.getOptions();
		
		// Using the for loop, we can loop through the List of Options
		for(WebElement option:monthOptions) {

			// Check if the option is checked.
			if(option.isSelected())

				// Write the output of our test to the System console.
				System.out.println(option.getAttribute("value"));	
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
