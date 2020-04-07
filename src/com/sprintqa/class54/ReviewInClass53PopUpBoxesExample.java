package com.sprintqa.class54;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class ReviewInClass53PopUpBoxesExample {
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
	void reviewInClass53PopUpBoxesTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://only-testing-blog.blogspot.com/2014/01/textbox.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Search the web page for the check all button using the locator id.
		WebElement alertButton = webDriver.findElement(By.xpath("//input[@value='Show Me Alert']"));

		// Click the button to display the alert
		alertButton.click();

		// Get the Alert pop up
		Alert alert = webDriver.switchTo().alert();

		// Write the text to the console
		System.out.println(alert.getText());

		// Click the ok button
		alert.accept();

		// Return to the calling window
		webDriver.switchTo().defaultContent();

		// Search the web page for the check all button using the locator id.
		WebElement confimButton = webDriver.findElement(By.xpath("//button[text()='Show Me Confirmation']"));

		// Click the continue button
		confimButton.click();

		// switch to the alert box
		alert = webDriver.switchTo().alert();

		// Write the alert text to the console
		System.out.println(alert.getText());

		// Click the ok popup
		alert.accept();

		// Switch back to the original window
		webDriver.switchTo().defaultContent();

		// Search the web page for the check all button using the locator id.
		WebElement demoDiv = webDriver.findElement(By.id("demo"));

		// write the displayed text to the console
		System.out.println(demoDiv.getText());

		// Click the confirm button to display confirm box
		confimButton.click();

		// Switch to alert box
		alert = webDriver.switchTo().alert();

		// Write the alert text to console
		System.out.println(alert.getText());

		// Click the close button
		alert.dismiss();

		// Switch to the main window
		webDriver.switchTo().defaultContent();

		// Get the displayed text
		demoDiv = webDriver.findElement(By.id("demo"));

		// Write the displayed text to console
		System.out.println(demoDiv.getText());

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

		// The key "user.dir" returns the Users working directory.
		String os = System.getProperty("os.name");

		// local var reference to store os binary path
		String chromeBinaryPath = "";
		System.out.println(os.substring(0, 3));
		// We only need the 1st 3 characters from the os.name to determine our OS.
		// Other wise you will get all flavors of Windows (7, 8, 10), etc.
		switch (os.substring(0, 3).toLowerCase()) {

		// If the OS starts with "win" for windows use the windows binary.
		case "win":
			chromeBinaryPath = "/webdrivers/win/chromedriver";
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
