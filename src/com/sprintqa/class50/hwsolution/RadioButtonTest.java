package com.sprintqa.class50.hwsolution;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Populate the Group Radio Buttons Demo form on
 * https://www.seleniumeasy.com/test/basic-radiobutton-demo.html. Assert that
 * the populated values get displayed.
 * 
 * @author mpmeloche
 *
 */
public class RadioButtonTest {
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
	 * Populate the Group Radio Buttons Demo form on
	 * https://www.seleniumeasy.com/test/basic-radiobutton-demo.html. Assert that
	 * the populated values get displayed.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	void radioButtonTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().fullscreen();

		// Look up page elements using WebDriver findElement() method.
		WebElement maleRadio = webDriver.findElement(By.xpath("//input[@name='gender'][@value='Male']"));
		WebElement femaleRadio = webDriver.findElement(By.xpath("//input[@name='gender'][@value='Female']"));
		WebElement firstAgeGroup = webDriver.findElement(By.xpath("//input[@value='0 - 5'][@name='ageGroup']"));
		WebElement secondAgeGroup = webDriver.findElement(By.xpath("//input[@value='5 - 15'][@name='ageGroup']"));
		WebElement thirdAgeGroup = webDriver.findElement(By.xpath("//input[@value='15 - 50'][@name='ageGroup']"));
		WebElement getValuesButton = webDriver.findElement(By.xpath("//button[contains(text(),'Get values')]"));
		WebElement displayedText = webDriver.findElement(By.cssSelector("p.groupradiobutton"));

		// Click the radio buttons Male & 0-5
		maleRadio.click();
		firstAgeGroup.click();

		// Click get Value button
		getValuesButton.click();

		// Verify the radio buttons are clicked
		assertTrue(displayedText.getText().contains(maleRadio.getAttribute("value")));
		assertTrue(displayedText.getText().contains(firstAgeGroup.getAttribute("value")));
	}
}
