package com.sprintqa.class58;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class InclassScreenshotsAssignment {
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
	void inclassScreenshotsTest() throws InterruptedException {
		String url = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Cast webDriver to TakeScreenShot class so we can capture
		// Screenshots of the browser
		TakesScreenshot ts = (TakesScreenshot) webDriver;

		// Set input screenshot file type and location
		File screenShot = ts.getScreenshotAs(OutputType.FILE);

		// Set output screenshot file location
		File outputFile = new File("screenshots/OnPageLoad.png");

		try {
			// Establish an input stream to read screenshot into memory
			InputStream inputStream = new FileInputStream(screenShot);

			// Use copy method to take input stream and write it to a
			// file on the local machine.
			Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			// Close input stream when done saving file.
			inputStream.close();

			// Populate input fields
			webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");
			webDriver.findElement(By.id("txtPassword")).sendKeys("admin123");

			// Capture screen shot
			screenShot = ts.getScreenshotAs(OutputType.FILE);

			// Set output screenshot file location
			outputFile = new File("screenshots/BeforeLogin.png");

			// Establish an input stream to read screenshot into memory
			inputStream = new FileInputStream(screenShot);

			// Use copy method to take input stream and write it to a
			// file on the local machine.
			Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			// Close input stream when done saving file.
			inputStream.close();

			// Find the submit button and log into the site.
			webDriver.findElement(By.id("frmLogin")).submit();

		} catch (IOException e) {
			e.printStackTrace();
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
