package com.sprintqa.class52.hwsolution;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
	 * 1) Navigate to https://demoqa.com/automation-practice-form/
	 * 
	 * 2) Select sex radio button
	 * 
	 * 3) Select years of experience radio button
	 * 
	 * 4) Input date of birth input field and print out the text in console
	 * 
	 * 5) In continents drop down select any value
	 * @throws InterruptedException 
	 * 
	 */
	@Test
	void multiSelectDropdownTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://demoqa.com/automation-practice-form/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Search the web page for the check all button using the locator id.
		WebElement countriesDropDown = webDriver.findElement(By.id("continents"));
		List<WebElement> genderRadio = webDriver.findElements(By.name("sex"));
		List<WebElement> yearsRadio = webDriver.findElements(By.name("exp"));

		// Select the Male radio under Sex
		for(WebElement webelement: genderRadio) {
			if(webelement.getAttribute("value").equals("Male")) {
				webelement.click();
			}
		}

		// Select 6 years of experience
		for(WebElement webelement: yearsRadio) {
			if(webelement.getAttribute("value").equals("6")) {
				webelement.click();
			}
		}
		
		// Convert so Select
		Select countriesSelect = new Select(countriesDropDown);

		// Print out size of dropdown
		System.out.println(countriesSelect.getOptions().size());

		// print out each value from the dropdown
		for (WebElement webElement : countriesSelect.getOptions()) {
			System.out.println(webElement.getText());
		}

		// Select the United States
		countriesSelect.selectByVisibleText("North America");

		// Verify US is selected
		for (WebElement webElement : countriesSelect.getAllSelectedOptions()) {
			if (webElement.equals("United states of America"))
				assertTrue(true);
		}
		
	}
}
