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
 * XPath Review There are multiple ways we can select an Element using XPath.
 * Using the Chrome Web Browser go to the url
 * https://www.seleniumeasy.com/test/basic-first-form-demo.html
 */
public class SeleniumXpathReviewExample {
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
	 * Working XPath Example:
	 * 
	 * XPath Format: //tag_name[attribute='value'] = //input[@id='sum1']
	 * 
	 * Chrome Console Lookup results 1 of 1 and if we hover of the result in the
	 * console the field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.xpath) Results: Returns the WebElement
	 * for the input field the From field "Enter a".
	 */
	@Test
	void xpathTagNameAttributeIdLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
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
			testResults.add("Test Passed: xpathTagNameAttributeIdLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameAttributeIdLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Not Working XPath Example:
	 * 
	 * XPath Format: //tag_name[attribute='value'] = //input[@class='form-control']
	 * 
	 * Chrome Console Lookup 1 of 3 and if we hover of the result in the console the
	 * field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.xpath) Results: Because the Element we
	 * are looking for is the 2nd Element in the list of results this XPath returns
	 * the WebElement for the input field for "Enter message" not "Enter a".
	 */
	@Test
	void xpathTagNameAttributeClassLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.xpath("//input[@class='form-control']"));
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

		// This test will fail because input value for 1 is entered into the wrong input
		// box.
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathTagNameAttributeClassLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameAttributeClassLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Working XPath Example:
	 * 
	 * XPath Format: //tag[starts-with(attribute,'value')] =
	 * //input[starts-with(@placeholder,'Enter')]
	 * 
	 * Chrome Console Lookup results 1 of 2 and if we hover of the result in the
	 * console the field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.xpath) Results: Because the Element we
	 * are looking for is the 1st Element in the list of results this XPath returns
	 * the WebElement for the input field "Enter a".
	 */
	@Test
	void xpathTagStartsWithAttributePlaceHolderLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.xpath("//input[starts-with(@placeholder,'Enter')]"));
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
			testResults.add("Test Passed: xpathTagStartsWithAttributePlaceHolderLookupTest.");
		else
			testResults.add("Test Failed: xpathTagStartsWithAttributePlaceHolderLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Not Working XPath Example:
	 * 
	 * XPath Format: //*[attribute='value'] = //*[@class='form-control']
	 * 
	 * Chrome Console Lookup results 1 of 3 and if we hover of the result in the
	 * console the field we are looking for is NOT highlighted.
	 * 
	 * Using WebDriver method findElement(By.xpath) Results: Because the Element we
	 * are looking for is the 2nd Element in the list of results this XPath returns
	 * the WebElement for the input field for "Enter message" not "Enter a".
	 */
	@Test
	void xpathWildCardWithAttributeClassLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.xpath("//*[@class='form-control']"));
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

		// This test will fail because 1 is entered into the wrong input box.
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathWildCardWithAttributeClassLookupTest.");
		else
			testResults.add("Test Failed: xpathWildCardWithAttributeClassLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Not Working XPath Example:
	 * 
	 * XPath Format: //*[attribute='value'][text()='value'] =
	 * //*[@type='button'][text()='Show Message']
	 * 
	 * Chrome Console Lookup results 1 of 1 and if we hover of the result in the
	 * console the field we are looking for is NOT highlighted.
	 * 
	 * Using WebDriver method findElement(By.xpath) Results: This XPath will return
	 * us the button "Show Messages". However, the question asked us to find the
	 * button "Get Total".
	 */
	@Test
	void xpathWildCardWithAttributeTypeAndTextLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		totalButton = webDriver.findElement(By.xpath("//*[@type='button'][text()='Show Message']"));

		// Input "1" into A
		enterAInputBox.sendKeys("1");

		// Input "2" into A
		enterBInputBox.sendKeys("2");

		// Click the total button
		totalButton.click();

		// The page refreshed so we need to get this element after the button is
		// clicked.
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		// This test will fail because show message is clicked so 1 + 2 is not summed
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathWildCardWithAttributeTypeAndTextLookupTest.");
		else
			testResults.add("Test Failed: xpathWildCardWithAttributeTypeAndTextLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Working XPath Example:
	 * 
	 * XPath Format: //tag_name[contains(attribute, 'value')] =
	 * //button[contains(@onclick, 'return total()')]
	 * 
	 * Chrome Console Lookup results 1 of 1 and if we hover of the result in the
	 * console the field we are looking for is highlighted.
	 * 
	 * Using WebDriver method findElement(By.xpath) Results: Returns the WebElement
	 * for the From Button "Get Total".
	 */
	@Test
	void xpathTagNameContainsTextAttributeOnClickLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		totalButton = webDriver.findElement(By.xpath("//button[contains(@onclick, 'return total()')]"));

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
			testResults.add("Test Passed: xpathTagNameContainsTextAttributeOnClickLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameContainsTextAttributeOnClickLookupTest.");

		// Same as above just, simpler to use assert
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * Not Working XPath Example:
	 * 
	 * XPath Format: //tag_name[starts-with(attribute,'value')] =
	 * //button[starts-with(@class,'btn')]
	 * 
	 * Chrome Console Lookup results 1 of 2 and if we hover over the 1st result in
	 * the console the field we are looking for is not highlighted.
	 * 
	 * Using WebDriver method findElement(By.xpath) Results: Because the Element we
	 * are looking for is the 2nd Element in the list of results this XPath returns
	 * the WebElement for the button "Show Messages".
	 */
	@Test
	void xpathTagNameStartsWtihTextAttributeOnClassLookupTest() throws InterruptedException {
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		totalButton = webDriver.findElement(By.xpath("//button[starts-with(@class,'btn')]"));

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
			testResults.add("Test Passed: xpathTagNameStartsWtihTextAttributeOnClassLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameStartsWtihTextAttributeOnClassLookupTest.");

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
