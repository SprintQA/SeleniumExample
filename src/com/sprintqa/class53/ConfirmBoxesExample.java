package com.sprintqa.class53;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class ConfirmBoxesExample {
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

// Use the WebDriver to search the web page using the "id" locator.

		
// Click the confirm button to open up a confirm box.


		Thread.sleep(5000);

// Using WebDriver to interact with the Confirm box.
//
// A confirm box is similar to an alert, except the user can also choose to
// cancel the message.
//

// 1. Use switchTo() method returns a TargetLocator Class.

		
// 2. Use the TargetLocator alert() method to return a Alert Class.

				
// 3. Use the Alert accept() method to click the confirmation 
// button and close the pop-up

		
// 4. Switch back to main window
		
		
// 5. Verify the pop-up was clicked.
		// Use the WebDriver to search the web page using the "id" locator. 

		
		// This is the text that should be displayed in the Div "demo" if the action is successful. 

		
		// Store the actual text being displayed in Div "demo" 

		
		// Do the values match? 


		Thread.sleep(5000);

// Repeat steps 1-5 for Confirm Cancel
		// Click the confirm button to open up a confirm box.
		
		Thread.sleep(5000);
		
		// Use switchTo() method returns a TargetLocator Class.

		
		// Use the TargetLocator alert() method to return a Alert Class.
		
		// Use the Alert accept() method to click the cancel 
		// button and close the pop-up


		// Switch back to main window
		
		
		// Verify the pop-up was clicked.
		// Use the WebDriver to search the web page using the "id" locator. 

		
		// This is the text that should be displayed in the Div "demo" if the action is successful. 

		
		// Store the actual text being displayed in Div "demo" 

		
		// Do the values match? 

		
		
		// Pause the test
		Thread.sleep(5000);
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
