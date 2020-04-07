package com.sprintqa.class53;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassMultipleWindowsExample {
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
	void inClassMultipleWindowsTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://omayo.blogspot.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		/**
		 * Go to: https://omayo.blogspot.com/ ⁃ Look for the “Open in New Window Link”
		 * on left. ⁃ Click the “Selenium Tutorial” link. ⁃ Switch to the new window ⁃
		 * Get the text from number 6 under “Introduction to Selenium” and write it to
		 * the console. ⁃ Close the window. ⁃ Return to original window. ⁃ Get text from
		 * “Text Area Field Two” and write it to the console.
		 */
		String originalWindow = webDriver.getWindowHandle();

		// Get Link Element
		WebElement newWindowLink = webDriver.findElement(By.xpath("//a[@target='_blank'][text()='SeleniumTutorial']"));

		// Click link to open second window
		newWindowLink.click();

		// Wait until second window is open
		while (webDriver.getWindowHandles().size() < 2) {
			Thread.sleep(1000);
		}

		// Loop through window ids
		for (String windowHandle : webDriver.getWindowHandles()) {
			// Check to see if we have located a new window
			if (!originalWindow.contentEquals(windowHandle)) {
				// Use the switchTo() method to change controls to the new window
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}

		// Get Link Text Element
		WebElement listElemnt = webDriver.findElement(By.partialLinkText("Firepath?"));
		System.out.println(listElemnt.getText());

		// Close second window
		webDriver.close();

		// Return to original window
		webDriver.switchTo().window(originalWindow);

		// Get TextAre Element
		WebElement textArea = webDriver.findElement(By.xpath("//textarea[@cols='30']"));

		// write the text to console
		System.out.println(textArea.getText());

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
