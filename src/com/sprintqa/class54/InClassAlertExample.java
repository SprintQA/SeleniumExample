package com.sprintqa.class54;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassAlertExample {
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

	/**
	 * Go to: http://uitestpractice.com/Students/Actions
	 * 1. Use the browser to Locate the Locator you need to use to find the “Click Me!” button.
	 * 2. Click the button to display the ALERT Box
	 * 3. Next we need to switch the window focus from the main page to the ALERT Box.
	 * 4. Now that we have focus on the ALERT Box we can do the following:
	 * 	a. We can use the getText() method to get the test displayed in the ALERT BOX.
	 *  b. We can use the accept() method to click the OK button in the ALERT Box
	 * 5. Next we need to switch the window focus back to the main page.
	 * @throws InterruptedException
	 */
	@Test
	void test() throws InterruptedException {
		String url = "http://uitestpractice.com/Students/Actions";

		webDriver.get(url);

		webDriver.manage().window().maximize();

		// 1. Use the browser to Locate the Locator you need to use to find the “Click
		//    Me!” button. Then using the webDriver.findElement method get the element so
		//    we can interact with it.

		WebElement alertButton = webDriver.findElement(By.name("click"));

		// 2. Click the button to display the ALERT Box

		alertButton.click();

		// 3. Next we need to switch the window focus from the main page to the ALERT

		Alert alert = webDriver.switchTo().alert();
		Thread.sleep(3000);

		// 4. Now that we have focus on the ALERT Box we can do the following:
		//   a. We can use the getText() method to get the test displayed in the ALERT BOX.
		//   b. We can use the accept() method to click the OK button in the ALERT Box

		System.out.println(alert.getText());
		alert.accept();

		// 5. Once we have clicked the OK button, the webDriver's focus should have
		//    returned to the main window. However, sometime the webDriver's focus does not
		//    return to the main window. So, to be safe we should use the switchTo()'s
		//    defaultContent() method to ensure the webDriver's focus is on the main window
		//    before we continue.

		webDriver.switchTo().defaultContent();

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
