package com.sprintqa.class58;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class BrokenLinkExample {
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

	@Test
	void brokenLinkTest() throws InterruptedException {
		// Set your starting web page.
		String url = "http://Newtours.Demoaut.Com/";
		// String url = "https://opensource-demo.orangehrmlive.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Locate the Locator for all the a Elements on the page
		List<WebElement> links = webDriver.findElements(By.tagName("a"));

		// Define our variables
		String linkUrl = "";

		// Class URL represents a Uniform Resource Locator, a pointer to a "resource" on
		// the World Wide Web
		URL uc = null;

		// Each HttpURLConnection instance is used to make a single request but the
		// underlying network connection to the HTTP server may be transparently shared
		// by other instances.
		HttpURLConnection conn = null;

		// Loop through our links
		for (WebElement link : links) {

			// Get the href attribute value.
			linkUrl = link.getAttribute("href");

			try {
				// Creates a URL object from the String representation.
				uc = new URL(linkUrl);

				// Returns a URLConnection instance that represents a connection to the remote
				// object referred to by the URL.
				conn = (HttpURLConnection) uc.openConnection();

				// Gets the status code from an HTTP response message.
				// For example, in the case of the following status lines:
				//
				// - HTTP/1.0 200 OK
				// - HTTP/1.0 401 Unauthorized
				//
				// It will return 200 and 401 respectively. Returns -1 if no code can be
				// discerned from the response (i.e., the response is not valid
				//
				// see https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
				//
				int responce = conn.getResponseCode();

				// Verify we received HTTP/1.0 200 OK
				if (responce == 200) {
					System.out.println("Link is valid: " + link.getText());
				} else {
					System.out.print("Response Code: " + responce);
					System.out.println(" - Link is NOT valid: " + link.getText());
					System.out.println(" - " + linkUrl);
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
