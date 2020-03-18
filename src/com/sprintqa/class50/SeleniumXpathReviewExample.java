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
 * <p>
 * Using the Chrome Web Browser go to the url @see <a href=
 * "https://www.seleniumeasy.com/test/basic-first-form-demo.html">https://www.seleniumeasy.com/test/basic-first-form-demo.html</a>
 * <p>
 * <b>Using the console verify the following Use Cases:</b>
 * <p>
 * <ul>
 * <li>Will these XPath locate the input box for "Enter a"?
 * <ol>
 * <li>//input[@id='sum1'] see the {@link #xpathTagNameAttributeIdLookupTest()
 * xpathTagNameAttributeIdLookupTest} method for solution.
 * <li>//input[@class='form-control'] see the
 * {@link #xpathTagNameAttributeClassLookupTest()
 * xpathTagNameAttributeClassLookupTest} method for solution.
 * <li>//input[starts-with(@placeholder,'Enter')] see the
 * {@link #xpathTagStartsWithAttributePlaceHolderLookupTest()
 * xpathTagStartsWithAttributePlaceHolderLookupTest} method for solution.
 * <li>//*[@class='form-control'] see the
 * {@link #xpathWildCardWithAttributeClassLookupTest()
 * xpathWildCardWithAttributeClassLookupTest} method for solution.
 * </ol>
 * <p>
 * <li>Will these XPath locate the button "Get Total"
 * <ol>
 * <li>//*[@type='button'][text()='Show Message'] see the
 * {@link #xpathWildCardWithAttributeTypeAndTextLookupTest()
 * xpathWildCardWithAttributeTypeAndTextLookupTest} method for solution.
 * <li>//button[contains(@onclick, 'return total()')] see the
 * {@link #xpathTagNameContainsTextAttributeOnClickLookupTest()
 * xpathTagNameContainsTextAttributeOnClickLookupTest} method for solution.
 * <li>//button[starts-with(@class,'btn')] see the
 * {@link #xpathTagNameStartsWtihTextAttributeOnClassLookupTest()
 * xpathTagNameStartsWtihTextAttributeOnClassLookupTest} method for solution.
 * </ol>
 * </ul>
 */
public class SeleniumXpathReviewExample {
	WebDriver webDriver;

	WebElement enterAInputBox;
	WebElement enterBInputBox;
	WebElement totalButton;
	WebElement displayValueSpan;

	// Used to store test results.
	static List<String> testResults = new ArrayList<String>();

	// Set the pause time in milliseconds for Thead.sleep
	long pause = 5000l;

	/**
	 * Remember to configure your System path so the application can find your
	 * ChromeDriver binary files.
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
	 * <li>XPath Format: //tag_name[attribute='value']
	 * <li>XPath Result: //input[@id='sum1']
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 1 and if we hover of the result in the console the
	 * field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.xpath) Results:</b>
	 * <p>
	 * Returns the WebElement for the input field the From field "Enter a".
	 */
	@Test
	void xpathTagNameAttributeIdLookupTest() throws InterruptedException {

		webDriver.manage().window().maximize();
		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		
		// We are testing this case
		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
		enterAInputBox.sendKeys("1");

		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");

		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));
		totalButton.click();

		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathTagNameAttributeIdLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameAttributeIdLookupTest.");

		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * <b>Fail</b>
	 * <p>
	 * <ul>
	 * <li>XPath Format: //tag_name[attribute='value']
	 * <li>XPath Result: //input[@class='form-control']
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 3 and if we hover of the result in the console the
	 * field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.xpath) Results:</b>
	 * <p>
	 * Because the Element we are looking for is the 2nd Element in the list of
	 * results this XPath returns the WebElement for the input field for "Enter
	 * message" not "Enter a".
	 */
	@Test
	void xpathTagNameAttributeClassLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		// We are testing this case
		enterAInputBox = webDriver.findElement(By.xpath("//input[@class='form-control']"));
		enterAInputBox.sendKeys("1");

		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");

		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));
		totalButton.click();

		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathTagNameAttributeClassLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameAttributeClassLookupTest.");

		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * <b>Pass</b>
	 * <p>
	 * <ul>
	 * <li>XPath Format: //tag[starts-with(attribute,'value')]
	 * <li>XPath Result: //input[starts-with(@placeholder,'Enter')]
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 2 and if we hover of the result in the console the
	 * field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.xpath) Results:</b>
	 * <p>
	 * Because the Element we are looking for is the 1st Element in the list of
	 * results this XPath returns the WebElement for the input field "Enter a".
	 */
	@Test
	void xpathTagStartsWithAttributePlaceHolderLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		// We are testing this case
		enterAInputBox = webDriver.findElement(By.xpath("//input[starts-with(@placeholder,'Enter')]"));
		enterAInputBox.sendKeys("1");

		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");

		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));
		totalButton.click();

		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathTagStartsWithAttributePlaceHolderLookupTest.");
		else
			testResults.add("Test Failed: xpathTagStartsWithAttributePlaceHolderLookupTest.");

		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * <b> Fail</b>
	 * <p>
	 * <ul>
	 * <li>XPath Format: //*[attribute='value']
	 * <li>XPath Result: //*[@class='form-control']
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 3 and if we hover of the result in the console the
	 * field we are looking for is NOT highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.xpath) Results:</b>
	 * <p>
	 * Because the Element we are looking for is the 2nd Element in the list of
	 * results this XPath returns the WebElement for the input field for "Enter
	 * message" not "Enter a".
	 */
	@Test
	void xpathWildCardWithAttributeClassLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		// We are testing this case
		enterAInputBox = webDriver.findElement(By.xpath("//*[@class='form-control']"));
		enterAInputBox.sendKeys("1");

		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");

		totalButton = webDriver.findElement(By.xpath("//*[@id='gettotal']/button"));
		totalButton.click();

		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathWildCardWithAttributeClassLookupTest.");
		else
			testResults.add("Test Failed: xpathWildCardWithAttributeClassLookupTest.");

		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * <b>Fail</b>
	 * <p>
	 * <ul>
	 * <li>XPath Format: //*[attribute='value'][text()='value']
	 * <li>XPath Result: //*[@type='button'][text()='Show Message']
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 1 and if we hover of the result in the console the
	 * field we are looking for is NOT highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.xpath) Results:</b>
	 * <p>
	 * This XPath will return us the button "Show Messages". However, the question
	 * asked us to find the button "Get Total".
	 */
	@Test
	void xpathWildCardWithAttributeTypeAndTextLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
		enterAInputBox.sendKeys("1");

		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");

		// We are testing this case
		totalButton = webDriver.findElement(By.xpath("//*[@type='button'][text()='Show Message']"));
		totalButton.click();

		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		Thread.sleep(pause);
		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathWildCardWithAttributeTypeAndTextLookupTest.");
		else
			testResults.add("Test Failed: xpathWildCardWithAttributeTypeAndTextLookupTest.");

		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * <b>Pass</b>
	 * <p>
	 * <ul>
	 * <li>XPath Format: //tag_name[contains(attribute, 'value')]
	 * <li>XPath Result: //button[contains(@onclick, 'return total()')]
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 1 and if we hover of the result in the console the
	 * field we are looking for is highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.xpath) Results:</b>
	 * <p>
	 * Returns the WebElement for the From Button "Get Total".
	 */
	@Test
	void xpathTagNameContainsTextAttributeOnClickLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
		enterAInputBox.sendKeys("1");

		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");

		// We are testing this case
		totalButton = webDriver.findElement(By.xpath("//button[contains(@onclick, 'return total()')]"));
		totalButton.click();

		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathTagNameContainsTextAttributeOnClickLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameContainsTextAttributeOnClickLookupTest.");

		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
	}

	/**
	 * <b>Fail</b>
	 * <p>
	 * <ul>
	 * <li>XPath Format: //tag_name[starts-with(attribute,'value')]
	 * <li>XPath Result: //button[starts-with(@class,'btn')]
	 * </ul>
	 * <p>
	 * <b>Chrome Console Lookup Results:</b>
	 * <p>
	 * We get the results 1 of 2 and if we hover over the 1st result in the console
	 * the field we are looking for is not highlighted.
	 * <p>
	 * <b>Using WebDriver method findElement(By.xpath) Results:</b>
	 * <p>
	 * Because the Element we are looking for is the 2nd Element in the list of
	 * results this XPath returns the WebElement for the button "Show Messages".
	 */
	@Test
	void xpathTagNameStartsWtihTextAttributeOnClassLookupTest() throws InterruptedException {

		webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		webDriver.manage().window().maximize();

		enterAInputBox = webDriver.findElement(By.xpath("//input[@id='sum1']"));
		enterAInputBox.sendKeys("1");

		enterBInputBox = webDriver.findElement(By.xpath("//input[@id='sum2']"));
		enterBInputBox.sendKeys("2");

		// We are testing this case
		totalButton = webDriver.findElement(By.xpath("//button[starts-with(@class,'btn')]"));
		totalButton.click();

		displayValueSpan = webDriver.findElement(By.id("displayvalue"));

		if (displayValueSpan.getText().equals("3"))
			testResults.add("Test Passed: xpathTagNameStartsWtihTextAttributeOnClassLookupTest.");
		else
			testResults.add("Test Failed: xpathTagNameStartsWtihTextAttributeOnClassLookupTest.");

		Thread.sleep(pause);
		assertEquals("3", displayValueSpan.getText());
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
