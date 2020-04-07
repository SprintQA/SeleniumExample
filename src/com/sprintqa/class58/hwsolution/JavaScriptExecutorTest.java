package com.sprintqa.class58.hwsolution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class JavaScriptExecutorTest {
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
		System.setProperty("webdriver.chrome.driver", getChromeDriverBinaryPath());

		// Declare your webDriver class variable to a ChromeDriver WebDriver to
		// communicate with Chrome.
		webDriver = new ChromeDriver();
	}

	/**
	 * HW Assignment Using JavaScriptExecutor:
	 * 
	 * 1. Using the JavaScriptExecutor scroll to the bottom of the page.
	 * 
	 * 2. Click the "Run Effect" Button
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void javaScriptExecutorTest() throws InterruptedException {
		// Set your starting web page.
		String url = "http://uitestpractice.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Locate continents dropdown box
		WebElement runEffectBtn = webDriver.findElement(By.id("button"));

		/*
		 * JavaScriptExecutor is an Interface that helps to execute JavaScript through
		 * Selenium WebDriver. JavaScriptExecutor provides two methods "executescript" &
		 * "executeAsyncScript" to run javascript on the selected window or current
		 * page.
		 */
		// Instantiate a JavascriptExecutor object and pass it the reference to
		// webDriver.
		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		/*
		 * window.scrollBy(X,Y)
		 * 
		 * X = Horizontal scrolling Y = Vertical Scrolling
		 * 
		 * Positive numbers equate to: X = Scroll right Y = Scroll Down
		 * 
		 * Negative numbers equate to: X = Scroll Left Y = Scroll Up
		 *
		 */
		// Vertical scroll down by 1000 pixels
		js.executeScript("window.scrollBy(0,1000)");

		runEffectBtn.click();

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
