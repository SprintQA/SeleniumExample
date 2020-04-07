package com.sprintqa.class48.hwsolution;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Congratulations you have been contracted by the online blog
 * https://drwhostuff.com/ to create some JUnit test to test the navigation of
 * their site and verify that their contact us page is working.
 * 
 * Steps:
 * 
 * 1. Create 1 test that navigates from the home page to the
 * https://drwhostuff.com/contact-us/ by using only the WebDriver's navigate
 * methods.
 * 
 * 2. Using WebElements sendKeys fill out the Contact Us page.
 * 
 * 3. In the subject put "SprintQA".
 * 
 * 4. In the Message include your name, class name, and brief description about
 * the test.
 * 
 * 5. Using the WebElement submit method to click the "Submit" button (Remember
 * to use the submit method of the form element in this case the form id is
 * "cp_contactformtoemail_pform_1" ) on the page to send your contact message.
 * 
 * @author mpmeloche
 *
 */
public class ContactDrWhoStuffAssignment {
	private static WebDriver webDriver;

	/**
	 * Remember to configure your System path so the application can find your
	 * ChromeDriver binary files.
	 * 
	 * @throws Exception
	 */
	@BeforeAll
	static void setUp() {
		// Setup the System path to the Selenium Chrome Binary file
		// Use System.getProperty("user.dir") to get the system path for the project.
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/webdrivers/mac/chromedriver");

		// Instantiate WebDriver to use ChromeDriver.
		webDriver = new ChromeDriver();
	}

	@Test
	void contactDrWhoStuff() throws InterruptedException {
		// Set the stating url and open the browser to the url.
		String url = "https://drwhostuff.com/";
		webDriver.get(url);

		// One Second Pause For Display Purposes Only
		Thread.sleep(1000);

		// Redirect the page to the new url
		webDriver.navigate().to("https://drwhostuff.com/contact-us/");

		// One Second Pause For Display Purposes Only
		Thread.sleep(1000);

		// Look up page elements using WebDriver findElement() method.
		// Use By class methods to specify the locator type.
		WebElement email = webDriver.findElement(By.id("email_1"));
		WebElement subject = webDriver.findElement(By.id("subject_1"));
		WebElement message = webDriver.findElement(By.id("message_1"));
		WebElement contactForm = webDriver.findElement(By.id("cp_contactformtoemail_pform_1"));

		// Populate the fields
		email.sendKeys("michael@melocheconsuling.com");
		subject.sendKeys("SprintQA Clas 48 HW Assignment");
		message.sendKeys(
				"Hello this is michael I am teaching Class 48: Selenium WebDriver. Basic HTML. Types of locators. SendKeys().");

		// One Second Pause For Display Purposes Only
		Thread.sleep(1000);

		// Submit the form
		contactForm.submit();

		// One Second Pause For Display Purposes Only
		Thread.sleep(1000);
	}

	/**
	 * Make sure when your done running your tests that you close the window/tab and
	 * then exit out of the browser window.
	 * 
	 * @throws Exception
	 */
	@AfterAll
	static void tearDown() {
		webDriver.close();
		webDriver.quit();
	}
}
