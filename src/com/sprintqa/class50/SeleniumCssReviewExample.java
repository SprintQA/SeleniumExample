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
 * CSS Review There are multiple ways we can select an Element using CSS Selectors.	
 * <p>
 * Using the Chrome Web Browser go to the url @see <a href="https://www.seleniumeasy.com/test/basic-first-form-demo.html">https://www.seleniumeasy.com/test/basic-first-form-demo.html</a>
 * <p>
 * Using the console verify the following Use Cases:
 * <p>
 * <ul>
 * <li>Will these CSS Selectors locate the input box for "Enter a"?
 * <ol>
 * <li>input#sum1 see the {@link #cssSelectorTagNameAttributeIdLookupTest() cssSelectorTagNameAttributeIdLookupTest} method for solution.
 * <li>#sum1 see the {@link #cssSelectorIdLookupTest() cssSelectorIdLookupTest} method for solution.
 * <li>#sum1[placeholder] see the {@link #cssSelectorIdAttributePlaceHolderLookupTest() cssSelectorIdAttributePlaceHolderLookupTest} method for solution.
 * </ol> 
 * <p>
 * <li>Will these CSS Selectors locate the button "Get Total" 
 * <ol>
 * <li>button.btn see the {@link #cssSelectorTagNameClassLookupTest() cssSelectorTagNameClassLookupTest} method for solution.
 * <li>button[onclick*='total'] see the {@link #cssSelectorTagNAmeAttributeOnClickLookupTest() cssSelectorTagNAmeAttributeOnClickLookupTest} method for solution.
 * <ol>
 */

public class SeleniumCssReviewExample {
	WebDriver webDriver;

	WebElement enterAInputBox;
	WebElement enterBInputBox;
	WebElement totalButton;
	WebElement displayValueSpan;
	
	// Used to store test results.
	static List<String> testResults = new ArrayList<String>();

	//Set the pause time in milliseconds for Thead.sleep
	long pause = 5000l;
	
	/**
	 * Sets the class path to the ChromeDriver binary file. And set the WebDriver to the appropriate browser type. In this case Chrome.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"/Users/mpmeloche/Development/eclipse/workspace/SeleniumExamples/webdrivers/chromedriver");
		webDriver = new ChromeDriver();
	}

	/**
	 * <b>Pass</b>
	 * <p>
	 * <ul>
	 * <li>CSS Selector Format: tagname#ID
	 * <li>CSS Selector Result: input#sum1
	 * </ul>	
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 1 and if we hover of the result in the console the field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.cssSelector) Results:</b>
	 * <p>
	 * Returns the WebElement for the input field the From field "Enter a".
	 * <p>
	 */
	@Test
	void cssSelectorTagNameAttributeIdLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		// We are testing this case
		enterAInputBox = webDriver.findElement(By.cssSelector("input#sum1"));
		enterAInputBox.sendKeys("1");
		
		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");
		
		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));
		totalButton.click();
		
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));
		
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorTagNameAttributeIdLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorTagNameAttributeIdLookupTest.");
		
		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}
	
	/**
	 * <b>Pass</b>
	 * <p>
	 * <ul>
	 * <li>CSS Selector Format: #id
	 * <li>CSS Selector Result: #sum1
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 1 and if we hover of the result in the console the field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.cssSelector) Results:</b>
	 * <p>
	 * Returns the WebElement for the input field the From field "Enter a".
	 */
	@Test
	void cssSelectorIdLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		// We are testing this case
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1"));
		enterAInputBox.sendKeys("1");
		
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		enterBInputBox.sendKeys("2");
		
		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));
		totalButton.click();
		
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));
		
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorIdLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorIdLookupTest.");
		
		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}
	
	/**
	 * <b>Pass</b>
	 * <p>
	 * <ul>
	 * <li>CSS Selector Format: #id[attribute]
	 * <li>CSS Selector Result: #sum1[placeholder]
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 1 and if we hover of the result in the console the field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.cssSelector) Results:</b>
	 * <p>
	 * Returns the WebElement for the input field the From field "Enter a".
	 */
	@Test
	void cssSelectorIdAttributePlaceHolderLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		// We are testing this case
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1[placeholder]"));
		enterAInputBox.sendKeys("1");
		
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		enterBInputBox.sendKeys("2");
		
		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));
		totalButton.click();
		
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));
		
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorIdAttributePlaceHolderLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorIdAttributePlaceHolderLookupTest.");
		
		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}
	
	/**
	 * <b>Fail</b>
	 * <p>
	 * <ul>
	 * <li>CSS Selector Format: tag.class
	 * <li>CSS Selector Result: button.btn
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 2 and if we hover over the 1st result in the console the field we are looking for is NOT highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.cssSelector) Results:</b>
	 * <p>
	 * Because the Element we are looking for is the 2nd Element in the list of results this XPath returns the WebElement for the button "Show Messages".
	 */
	@Test
	void cssSelectorTagNameClassLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

	
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1"));
		enterAInputBox.sendKeys("1");
		
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		enterBInputBox.sendKeys("2");
	
		// We are testing this case
		totalButton = webDriver.findElement(By.cssSelector("button.btn"));
		totalButton.click();
		
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));
		
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorTagNameClassLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorTagNameClassLookupTest.");
		
		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}
	
	/**
	 * <b>Pass</b>
	 * <p>
	 * <ul>
	 * <li>CSS Selector Format: Tag[attribute*='value']
	 * <li>CSS Selector Result: button[onclick*='total']
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 1 and if we hover of the result in the console the field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.cssSelector) Results:</b>
	 * <p>
	 * Returns the WebElement for the From Button "Get Total".
	 */
	@Test
	void cssSelectorTagNAmeAttributeOnClickLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

	
		enterAInputBox = webDriver.findElement(By.cssSelector("#sum1"));
		enterAInputBox.sendKeys("1");
		
		enterBInputBox = webDriver.findElement(By.cssSelector("#sum2"));
		enterBInputBox.sendKeys("2");
	
		// We are testing this case
		totalButton = webDriver.findElement(By.cssSelector("button[onclick*='total']"));
		totalButton.click();
		
		displayValueSpan = webDriver.findElement(By.id("displayvalue"));
		
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: cssSelectorTagNAmeAttributeOnClickLookupTest.");
		else
			testResults.add("Test Failed: cssSelectorTagNAmeAttributeOnClickLookupTest.");
		
		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}
	
	/**
	 * Closes the web browser window/tab and then exits the browser application.
	 * 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		webDriver.close();
		webDriver.quit();
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
