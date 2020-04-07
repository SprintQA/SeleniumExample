package com.sprintqa.class52;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class DropDownExample {
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
	void dropDownTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://demoqa.com/automation-practice-form/";
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);
		// webDriver.navigate().to(url);
		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().fullscreen();

		// identify DD with <select> and assign
		WebElement continentSelect = webDriver.findElement(By.cssSelector("select#continents"));

		// Pass to the Select class Constructor
		Select continentList = new Select(continentSelect);

		// find how many options is available getOptions(); (return collection of
		// elements)
		List<WebElement> allOptions = continentList.getOptions();
		System.out.println("The total # of options in Continent DD is " + allOptions.size());

		String optionText = "";
		for (WebElement option : allOptions) {
			optionText = option.getText();
			if (option.isSelected())
				System.out.println(optionText);
		}

		// Working with MultiSelect
		WebElement commandsDD = webDriver.findElement(By.id("selenium_commands"));
		Select select = new Select(commandsDD);
		List<WebElement> allOptions1 = select.getOptions();

		// print out total number of commands we use .size() just as List in Java
		System.out.println("The total # of commands DD is " + allOptions1.size());

		// to print each commands value we use Iterator
		Iterator<WebElement> it = allOptions1.iterator();
		while (it.hasNext()) {
			optionText = it.next().getText();
			System.out.println(optionText);
		}

		if (select.isMultiple()) { // checks if DD supports multiple options
			select.selectByVisibleText("Navigation Commands");
			select.selectByIndex(3);
		}
		Thread.sleep(5000);
		// deselect method woks from Multi Select DD only (from single it will give
		// UnsupportedOperationException
		// select.deselectByIndex(2);
		// select.deselectByValue(a);
		select.deselectByVisibleText("Navigation Commands");

		Thread.sleep(5000);
	}

}
