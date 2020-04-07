package com.sprintqa.class53;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassFramesExample {
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
	void inClassFramesTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://stqatools.com/demo/Frames.php";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		/*
		 * Go to: https://stqatools.com/demo/Frames.php ⁃ Switch to the IFrame ⁃ Click
		 * the “Basic Alert” Button ⁃ Get alert text and write it to the console. ⁃
		 * Click Ok ⁃ Switch back to main window ⁃ Get the text from “mytext” and write
		 * it to the console.
		 */
		// Find Iframe Element
		WebElement iframe = webDriver.findElement(By.tagName("iframe"));
		webDriver.switchTo().frame(iframe);

		// Find "Basic Alert" Button in Javascript Section
		WebElement alertButton = webDriver.findElement(By.id("jbalert"));

		// Click alert button to open alert box
		alertButton.click();

		// Switch to the alert box
		Alert alert = webDriver.switchTo().alert();

		// Write alert box text to console
		System.out.println(alert.getText());

		// Click OK button
		alert.accept();

		// Switch back to default window
		webDriver.switchTo().defaultContent();

		// Find the MyText div
		List<WebElement> myTextDivList = webDriver.findElements(By.id("mytext"));

		// Because id was not unique we had to pull all elements
		for (WebElement webElement : myTextDivList) {
			// write out to conole
			System.out.println(webElement.getText());
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
