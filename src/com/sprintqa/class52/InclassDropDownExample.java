package com.sprintqa.class52;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class InclassDropDownExample {
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
	void test() throws InterruptedException {
		// Set your starting web page.
		String url = "http://uitestpractice.com/Students/Select";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().fullscreen();

		// identify DD with <select> and assign
		WebElement countriesSingle = webDriver.findElement(By.id("countriesSingle"));

		// Pass to the Select class Constructor
		Select countriesList = new Select(countriesSingle);

		// find how many options is available getOptions(); (return collection of
		// elements)
		List<WebElement> allOptions = countriesList.getOptions();
		System.out.println("The total # of options in countriesSingle DD is " + allOptions.size());

		// Select USA
		countriesList.selectByIndex(1);
		countriesList.selectByValue("usa");

		// If Option is selected write to console
		String optionText = "";
		for (WebElement option : allOptions) {
			optionText = option.getText();
			if (option.isSelected())
				System.out.println(optionText);
		}

	}

}
