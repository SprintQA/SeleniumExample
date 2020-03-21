package com.sprintqa.class53;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class MultipleWindowsExample {
	// Declare WebDriver variable as a Class variable so we can use it through out
	// the class.
	WebDriver webDriver;

	/**
	 * Remember to configure your System path so the application can find your
	 * ChromeDriver binary files.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Set our ChromeDriver Binary Path
		System.setProperty("webdriver.chrome.driver", getChromeDeriverBinaryPath());

		// Declare your webDriver class variable to a ChromeDriver WebDriver to
		// communicate with Chrome.
		webDriver = new ChromeDriver();
	}

	@Test
	void test() throws InterruptedException {
		// Set your starting web page.
		String url = "http://uitestpractice.com/Students/Switchto";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

// Using WebDriver to interact with a window/tab.
//
// WebDriver does not make the distinction between windows and tabs. If your
// site opens a new tab or window, Selenium will let you work with it using a
// window handle. Each window has a unique identifier which remains persistent
// in a single session.
//

// 1. Start by using the getWindowHandle() method to get the window handle of
// the current window.
		String originalWindow = webDriver.getWindowHandle();
		System.out.println(originalWindow);
		
// 2. Check we don't have other windows open already
		if (webDriver.getWindowHandles().size() == 1) {
		
// 3. Use the WebDriver to search the web page using the "linkText" locator.
			WebElement openNewWindowLink = webDriver.findElement(By.linkText("Opens in a new window"));		
			
// 4. Clicking this link will open a new window/tab 
			openNewWindowLink.click();
	
			Thread.sleep(3000);


			// 5. Wait for the new window or tab
			while (webDriver.getWindowHandles().size() < 2) {
				Thread.sleep(1000);
			}

			// 6. Loop through until we find a new window handle
			for (String windowHandle : webDriver.getWindowHandles()) {
				// Check to see if we have located a new window
				if (!originalWindow.contentEquals(windowHandle)) {
					// Use the switchTo() method to change controls to the new window
					webDriver.switchTo().window(windowHandle);
					break;
				}
			}			
			
// 7. Verify we are on the new window by checking for a viable web element on
// the page.
			WebElement dragableBox = webDriver.findElement(By.id("draggable"));
			// Verify that we are on the expected page by looking for an
			// element on the page.
			if (dragableBox.isDisplayed()) {
				System.out.println("New window is displayed");
			} else {
				System.out.println("New window is NOT displayed");
			}			
			Thread.sleep(3000);
// 8. Close the tab or window
			webDriver.close();
			
// 9. Switch back to the old tab or window
			webDriver.switchTo().window(originalWindow);
			
			
// 10. Verify we are on the new window by checking for a visable web element on
// the page.
			openNewWindowLink = webDriver.findElement(By.linkText("Opens in a new window"));
			// Verify that we are on the expected page by looking for an
			// element on the page.
			if (openNewWindowLink.isDisplayed()) {
				System.out.println("Original window is displayed");
			} else {
				System.out.println("Original window is NOT displayed");
			}
			

		}
		// Pause the test
		Thread.sleep(3000);

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
	private String getChromeDeriverBinaryPath() {
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
