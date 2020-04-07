package com.sprintqa.class50;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class TextAreaExample {
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

	@Test
	void textAreaTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.seleniumeasy.com/test/input-form-demo.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		WebElement projectDescriptionTextArea = webDriver.findElement(By.xpath("//textarea[@name='comment']"));

		String expectedText = "test1\ntest2";

		// Populate textArea
		projectDescriptionTextArea.sendKeys(expectedText);

		// Get textArea value after inputting text
		String projectDescriptionValue = projectDescriptionTextArea.getAttribute("value");

		// Validate that the expected value matched the actual value
		if (expectedText.equals(projectDescriptionValue)) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}

		// Same as above
		assertEquals(expectedText, projectDescriptionValue);
	}

}
