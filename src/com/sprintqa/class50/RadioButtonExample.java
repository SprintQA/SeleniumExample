package com.sprintqa.class50;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class RadioButtonExample {
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
	void radioButtonTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		WebElement maleRadioButton = webDriver.findElement(By.xpath("//input[@name='optradio'][@value='Male']"));
		WebElement checkGenderButton = webDriver.findElement(By.id("buttoncheck"));
		WebElement checkGenderValueP = webDriver.findElement(By.cssSelector("p.radiobutton"));

		// Click male radio
		maleRadioButton.click();

		// Click check gender button
		checkGenderButton.click();

		// Verify the text "Radio button 'Male' is checked" is displayed
		// when check gender button is clicked
		assertEquals("Radio button 'Male' is checked", checkGenderValueP.getText());

	}

}
