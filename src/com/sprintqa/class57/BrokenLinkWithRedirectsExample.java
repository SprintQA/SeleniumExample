package com.sprintqa.class57;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class BrokenLinkWithRedirectsExample {
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
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	void test() throws InterruptedException {
		String url = "http://Newtours.Demoaut.Com/";
		// String url = "https://opensource-demo.orangehrmlive.com/";

		webDriver.get(url);

		webDriver.manage().window().maximize();

		assertTrue(isPageLinksValid(), "Some or all of the links on the page are broken");

		Thread.sleep(3000);

	}

	public boolean isPageLinksValid() {
		boolean flag = true;

		// Loops through the site links and validate if the exist.
		for (String link : getElementLinks(new String[] { "src", "href" }))
			// Bitwise AND assignment operator.
			// If any page link fails the flag will be false.
			flag &= linkExists(link);

		return flag;
	}

	public boolean linkExists(String url) {
		boolean flag = false;
		String location;
		URL base;
		URL next;
		HttpURLConnection con;
		try {
			// Sets whether HTTP redirects (requests with response code 3xx) should be
			// automatically followed by this class. True by default. Applets cannot change
			// this variable.
			HttpURLConnection.setFollowRedirects(true);

			// Returns a URLConnection instance that represents a connection to the remote
			// object referred to by the URL.
			con = (HttpURLConnection) new URL(url).openConnection();

			// Use switch/case to handle response values.
			switch (con.getResponseCode()) {

			// 200 Pass link is valid
			case HttpURLConnection.HTTP_OK:
				flag = true;
				break;

			// Follows the redirects to the end.
			case HttpURLConnection.HTTP_MOVED_PERM:
			case HttpURLConnection.HTTP_MOVED_TEMP:
				location = con.getHeaderField("Location");
				base = new URL(url);
				next = new URL(base, location); // Deal with relative URLs

				// Recursive loop - Follows the link path until we get a response code that is
				// not 301,302
				flag = linkExists(next.toExternalForm());
				break;
			default:
				flag = false;
			}

			// write out if the link is valid or not.
			if (flag) {
				System.out.println("Link is valid: " + url);
			} else {
				System.out.print("Response Code: " + con.getResponseCode());
				System.out.println(" - Link is NOT valid: " + url);
			}

			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}

	private String formatLinks(WebElement element, String attributeName) {
		if (element.getAttribute(attributeName).startsWith("http"))
			// Return url as is
			return element.getAttribute(attributeName);
		else
			// Url is a redirect on the same domain so we need to
			// prefix the link with the current url.
			return webDriver.getCurrentUrl() + "/" + element.getAttribute(attributeName);
	}

	public List<String> getElementLinks(String[] attributeNames) {

		// Create our list place holders
		List<String> links = new ArrayList<>();
		List<WebElement> pageElementList = new ArrayList<>();

		// Loop through site looking for xpath string looking for attributes
		for (String attributeName : attributeNames) {

			// Locate the page links for the specific attribute
			pageElementList = webDriver.findElements(By.xpath("//*[@" + attributeName + "]"));

			// Loop through links
			for (WebElement element : pageElementList) {

				// If link is not an #anchor tag and link not already in list add to the list
				if (!element.getAttribute(attributeName).contains("#")
						&& !links.contains(element.getAttribute(attributeName)))
					links.add(formatLinks(element, attributeName));
			}
		}
		return links;

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
