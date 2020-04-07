package com.sprintqa.class50.hwsolution;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Populate the form fields on
 * https://www.seleniumeasy.com/test/input-form-demo.html
 * 
 * @author mpmeloche
 *
 */
public class InputFormDemoTest {
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
	 * Populate all form fields
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test
	void inputFormDemoTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.seleniumeasy.com/test/input-form-demo.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().fullscreen();

		// Look up page elements using WebDriver findElement() method.
		WebElement firstNameInput = webDriver.findElement(By.name("first_name"));
		WebElement lastNameInput = webDriver.findElement(By.name("last_name"));
		WebElement emailInput = webDriver.findElement(By.name("email"));
		WebElement phoneInput = webDriver.findElement(By.name("phone"));
		WebElement addressInput = webDriver.findElement(By.name("address"));
		WebElement cityInput = webDriver.findElement(By.name("city"));
		WebElement zipInput = webDriver.findElement(By.name("zip"));
		WebElement websiteInput = webDriver.findElement(By.name("website"));
		WebElement hostingRadioYes = webDriver.findElement(By.xpath("//input[@name='hosting'][@value='yes']"));
		WebElement hostingRadioNo = webDriver.findElement(By.xpath("//input[@name='hosting'][@value='no']"));
		WebElement commentTextArea = webDriver.findElement(By.name("comment"));

		// Populate input fields
		firstNameInput.sendKeys("John");
		lastNameInput.sendKeys("Smith");
		emailInput.sendKeys("support@sprintqa.com");
		phoneInput.sendKeys("6155555555");
		addressInput.sendKeys("123 main st.");
		cityInput.sendKeys("Nashville");
		zipInput.sendKeys("12345");
		websiteInput.sendKeys("sprintqa.com");
		hostingRadioNo.click();
		commentTextArea.sendKeys("SprintQA\nBootCamp\nTest");

		// Validate fields are populated
		assertEquals(firstNameInput.getAttribute("value"), "John");
		assertEquals(lastNameInput.getAttribute("value"), "Smith");
		assertEquals(emailInput.getAttribute("value"), "support@sprintqa.com");
		assertEquals(phoneInput.getAttribute("value"), "6155555555");
		assertEquals(addressInput.getAttribute("value"), "123 main st.");
		assertEquals(cityInput.getAttribute("value"), "Nashville");
		assertEquals(zipInput.getAttribute("value"), "12345");
		assertEquals(websiteInput.getAttribute("value"), "sprintqa.com");
		assertTrue(hostingRadioNo.isSelected());
		assertEquals(commentTextArea.getAttribute("value"), "SprintQA\nBootCamp\nTest");

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
