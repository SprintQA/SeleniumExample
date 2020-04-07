package com.sprintqa.class56;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassPageLoadImplicitWaitsExample {
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

		/*
		 * An implicit wait tells the WebDriver to poll the DOM for a certain amount of
		 * time when trying to find an element or elements if they are not immediately
		 * available.
		 * 
		 * The default setting is 0, meaning disabled. Once set, the implicit wait is
		 * set for the life of the session.
		 * 
		 * Once this time is set, WebDriver will wait for the element before the
		 * exception occurs.
		 */
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	void inClassPageLoadImplicitWaitsTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		/*
		 * Sets the amount of time to wait for a page load to complete before throwing
		 * an error. If the timeout is negative, page loads can be indefinite.
		 */
		webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Locate the Locator for user name input and enter "Admin" text
		webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

		// Locate the Locator for the password input and enter "admin123"
		webDriver.findElement(By.id("txtPassword")).sendKeys("admin123");

		// Locate the locator for the login button and click it.
		webDriver.findElement(By.id("frmLogin")).submit();

		// Locate the locator for the "Admin" menu button and click it.
		webDriver.findElement(By.id("menu_admin_viewAdminModule")).click();

		// Declare our Strings to store Expected String value
		String expectedText = "System Users";

		// Locate the locator for the "Admin" menu button and click it.
		String actualText = webDriver.findElement(By.cssSelector("div#systemUser-information>div.head>h1")).getText();

		if (expectedText.contentEquals(actualText)) {
			System.out.println("Test Passed.");
		} else {
			System.out.println("Test Failed.");
		}

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

		// We only need the 1st 3 characters from the os.name to determine our OS.
		System.out.println(os.substring(0, 3));

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
