package com.sprintqa.class53;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class AlertBoxesExample {
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
		System.setProperty("webdriver.chrome.driver", getChromeDriverBinaryPath());

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
	void alertBoxesTest() throws InterruptedException {
		// Set your starting web page.
		String url = "http://uitestpractice.com/Students/Switchto";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Use the WebDriver to search the web page using the "id" locator.
		WebElement alertButton = webDriver.findElement(By.id("alert"));

		// Click the alertButton to trigger the Alert box.
		alertButton.click();

		// Wait for alert to display
		Thread.sleep(1000);

		// Using WebDriver to interact with the Alert pop-up.
		//
		// The simplest of these is referred to as an alert, which shows a custom
		// message, and a single button which dismisses the alert, labeled in most
		// browsers as OK. It can also be dismissed in most browsers by pressing the
		// close button, but this will always do the same thing as the OK button.
		//

		// 1. Use switchTo() method returns a TargetLocator Class.
		TargetLocator targetLocator = webDriver.switchTo();

		// 2. Use the TargetLocator alert() method to return a Alert Class.
		Alert alert = targetLocator.alert();

		// 3. Use the Alert accept() method to click the confirmation
		// button and close the pop-up
		System.out.println(alert.getText());
		alert.accept();

		// Close alert
		Thread.sleep(1000);

		// 4. Return control to the main window
		webDriver.switchTo().defaultContent();

		// 5. Verify the pop-up was clicked.
		// Use the WebDriver to search the web page using the "id" locator.
		WebElement demoDiv = webDriver.findElement(By.id("demo"));

		// This is the text that should be displayed in the Div "demo" if the action is
		// successful.
		String expectedDisplayedActionText = "You have clicked on ok button in alert window";

		// Store the actual text being displayed in Div "demo"
		String actualDisplayedActionText = demoDiv.getText();

		// Validate text is displayed
		if (expectedDisplayedActionText.equals(actualDisplayedActionText)) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

		// Load page Element
		WebElement confirmButton = webDriver.findElement(By.id("confirm"));

		// Click confirm button
		confirmButton.click();

		// Wait for page to load
		Thread.sleep(1000);

		// Switch to alert
		targetLocator = webDriver.switchTo();
		alert = targetLocator.alert();

		// Accept alert
		alert.accept();

		// return to mai\n window
		webDriver.switchTo().defaultContent();

		// Wait for main window to laod
		Thread.sleep(1000);

		// Get Demo element
		demoDiv = webDriver.findElement(By.id("demo"));

		// Set String variables
		String expectedConfirmationText = "You pressed Ok in confirm window";
		String actualConfirmationText = demoDiv.getText();

		// Confirm expected values
		if (expectedConfirmationText.equals(actualConfirmationText)) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

		// Get Prompt button
		WebElement promptButton = webDriver.findElement(By.id("prompt"));

		// Display prompt box
		promptButton.click();

		// Wait for prompt to display
		Thread.sleep(1000);

		// Use switchTo() method returns a TargetLocator Class.
		targetLocator = webDriver.switchTo();

		// Use the TargetLocator alert() method to return a Alert Class.
		alert = targetLocator.alert();

		// use sendkeys() to send text to input
		alert.sendKeys("Michael");

		// Use the Alert accept() method to click the confirmation
		alert.accept();

		// Wait for page to laod
		Thread.sleep(1000);

		// Switch back to defult window
		webDriver.switchTo().defaultContent();

		// Refresh demo div element
		demoDiv = webDriver.findElement(By.id("demo"));

		// Wait for window to refresh
		Thread.sleep(1000);

		// Set expected string value
		String expectedPromptText = "Hello Michael! How are you today?";
		String actualPromptText = demoDiv.getText();

		// validate displayed text
		if (expectedPromptText.equals(actualPromptText)) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}
	}

	/**
	 * The System class maintains a Properties object that describes the
	 * configuration of the current working environment. System properties include
	 * information about the current user, the current version of the Java runtime,
	 * and the character used to separate components of a file path name.
	 * 
	 * We can use that here to determine which ChromeDriver binary to load.
	 * 
	 * @see https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
	 * @return
	 */
	private String getChromeDriverBinaryPath() {
		// The key "user.dir" returns the Users working directory.
		String userWorkingDirectory = System.getProperty("user.dir");

		// The key "os.name" returns the Users working directory.
		String os = System.getProperty("os.name");

		// local var reference to store os binary path
		String chromeBinaryPath = "";
		System.out.println(os.substring(0, 3));
		// We only need the 1st 3 characters from the os.name to determine our OS.
		// Other wise you will get all flavors of Windows (7, 8, 10), etc.
		switch (os.substring(0, 3).toLowerCase()) {

		// If the OS starts with "win" for windows use the windows binary.
		case "win":
			chromeBinaryPath = "/webdrivers/win/chromedriver.exe";
			break;

		// If the OS starts with "mac" for MacIntosh use the mac binary.
		case "mac":
			chromeBinaryPath = "/webdrivers/mac/chromedriver";
			break;

		// If the OS starts with "lin" for MacIntosh use the lin binary.
		case "lin":
			chromeBinaryPath = "/webdrivers/lin/chromedriver";
		}

		// combine the user working path with the binary path.
		return userWorkingDirectory + chromeBinaryPath;
	}

}
