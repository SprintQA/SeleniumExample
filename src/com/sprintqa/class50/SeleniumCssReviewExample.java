package com.sprintqa.class50;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * CSS Review There are multiple ways we can select an Element using CSS
 * Selectors. Using the Chrome Web Browser go to the url
 * https://www.seleniumeasy.com/test/basic-first-form-demo.html
 */

public class SeleniumCssReviewExample {
	WebDriver webDriver;

	// Define Class variables for Reusable WebElements
	WebElement enterAInputBox;
	WebElement enterBInputBox;
	WebElement totalButton;
	WebElement displayValueSpan;

	// Starting URL for each test
	String url = "https://www.seleniumeasy.com/test/basic-first-form-demo.html";

	// Used to store test results.
	static List<String> testResults = new ArrayList<String>();

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
	 * Working CSS Selector Example:
	 * 
	 * CSS Selector Format: tagname#ID = input#sum1
	 * 
	 * Chrome Console Lookup Results: We get the results 1 of 1 and if we hover of
	 * the result in the console the field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.cssSelector) Results: Returns the
	 * WebElement for the input field the From field "Enter a".
	 */
	@Test
	void cssSelectorTagNameAttributeIdLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.cssSelector("input#sum1"));
		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));

		// Input "1" into A
		enterAInputBox.sendKeys("1");

		// Input "2" into A
		enterBInputBox.sendKeys("2");

		// Click the total button
		totalButton.click();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		// This test will pass because input values 1 + 2 =3
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorTagNameAttributeIdLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorTagNameAttributeIdLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Working CSS Selector Example:
	 * 
	 * CSS Selector Format: #id = #sum1
	 * 
	 * Chrome Console Lookup Results: We get the results 1 of 1 and if we hover of
	 * the result in the console the field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.cssSelector) Returns the WebElement for
	 * the input field the From field "Enter a".
	 */
	@Test
	void cssSelectorIdLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1"));
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));

		// Input "1" into A
		enterAInputBox.sendKeys("1");

		// Input "2" into A
		enterBInputBox.sendKeys("2");

		// Click the total button
		totalButton.click();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		// This test will pass because input values 1 + 2 =3
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorIdLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorIdLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Working CSS Selector Example:
	 * 
	 * CSS Selector Format: #id[attribute] = #sum1[placeholder]
	 * 
	 * Chrome Console Lookup Results results 1 of 1 and if we hover of the result in
	 * the console the field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.cssSelector) Results: Returns the
	 * WebElement for the input field the From field "Enter a".
	 */
	@Test
	void cssSelectorIdAttributePlaceHolderLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1[placeholder]"));
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));

		// Input "1" into A
		enterAInputBox.sendKeys("1");

		// Input "2" into A
		enterBInputBox.sendKeys("2");

		// Click the total button
		totalButton.click();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		// This test will pass because input values 1 + 2 =3
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorIdAttributePlaceHolderLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorIdAttributePlaceHolderLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Not Working CSS Selector Example:
	 * 
	 * CSS Selector Format: tag.class = button.btn
	 * 
	 * Chrome Console Lookup results 1 of 2 and if we hover over the 1st result in
	 * the console the field we are looking for is NOT highlighted.
	 * 
	 * Using WebDriver method findElement(By.cssSelector) Results: Because the
	 * Element we are looking for is the 2nd Element in the list of results this
	 * XPath returns the WebElement for the button "Show Messages".
	 */
	@Test
	void cssSelectorTagNameClassLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1"));
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		totalButton = webDriver.findElement(By.cssSelector("button.btn"));

		// Input "1" into A
		enterAInputBox.sendKeys("1");

		// Input "2" into A
		enterBInputBox.sendKeys("2");

		// Click the total button
		totalButton.click();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		// This test will fail because input values blank + 2 = NaN
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorTagNameClassLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorTagNameClassLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Working CSS Selector Example:
	 * 
	 * CSS Selector Format: Tag[attribute*='value'] = button[onclick*='total']
	 * 
	 * Chrome Console Lookup results 1 of 1 and if we hover of the result in the
	 * console the field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.cssSelector) Results: Returns the
	 * WebElement for the From Button "Get Total".
	 */
	@Test
	void cssSelectorTagNAmeAttributeOnClickLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1"));
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		totalButton = webDriver.findElement(By.cssSelector("button[onclick*='total']"));

		// Input "1" into A
		enterAInputBox.sendKeys("1");

		// Input "2" into A
		enterBInputBox.sendKeys("2");

		// Click the total button
		totalButton.click();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		// This test will pass because input values 1 + 2 =3
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorTagNAmeAttributeOnClickLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorTagNAmeAttributeOnClickLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * After all tests run report test results to console.
	 */
	@AfterAll
	public static void cleanUp() {
		// Output test results to console at the end of the test.
		System.out.println("Test Results:");
		for (String testRresult : testResults)
			System.out.println(testRresult);

	}

}
