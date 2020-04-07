package com.sprintqa.class58.hwsolution;

import static org.junit.Assert.fail;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

class DownlaodAFileTest {
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

		// Create a HashMap to store additional browser settings
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		// Disable the save to folder popup
		chromePrefs.put("profile.default_content_settings.popups", 0);

		// Change the default download directory
		chromePrefs.put("download.default_directory", System.getProperty("user.dir"));

		// Declare ChromeOptions to enable browser settings
		ChromeOptions options = new ChromeOptions();

		// set Experimental Option "prefs" with your HashMap values.
		options.setExperimentalOption("prefs", chromePrefs);

		// Declare your webDriver class variable to a ChromeDriver WebDriver to
		// communicate with Chrome. Pass in Chrome Options.
		webDriver = new ChromeDriver(options);
	}

	/**
	 * HW Assignment Using Download Files:
	 * 
	 * Create a Junit test to click and download the image file from the file
	 * download section.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void downlaodAFileTest() throws InterruptedException {
		// Set your starting web page.
		String url = "http://uitestpractice.com/Students/Widgets";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// We find the download link
		WebElement downloadButton = webDriver.findElement(By.partialLinkText("Download"));

		// Click the download button
		downloadButton.click();

		// When we clicked the download a file
		// images.png should be down loaded to the specified output folder.
		String expectedFileName = "images.png";

		// Create a File object with user path and expected file name.
		File targetFile = new File(System.getProperty("user.dir"), expectedFileName);

		// Wait for the file to download
		// Set wait time 30s
		// Check until condition every second
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1));

		// Use Java lambda to wait until file.exists
		// or we reach out timeout limit.
		fluentWait.until(fileExists -> targetFile.exists());

		// if file is not present – fail test
		if (!targetFile.exists()) {
			fail("File is not present");
		} else {
			// delete file to be sure that next time new file will be downloaded
			targetFile.delete();
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
