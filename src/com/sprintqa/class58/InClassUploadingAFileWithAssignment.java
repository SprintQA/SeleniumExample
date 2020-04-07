package com.sprintqa.class58;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class InClassUploadingAFileWithAssignment {
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
	void inClassUploadingAFileTest() throws InterruptedException {
		// Set your starting web page.
		String url = "http://uitestpractice.com/Students/Widgets";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Set local path to load file.
		String targetFile = System.getProperty("user.dir") + "/screenshots/placeholder.txt";

		// Find upload file button
		WebElement chooseFileButton = webDriver.findElement(By.id("image_file"));

		// Send local file path
		chooseFileButton.sendKeys(targetFile);

		// Locate upload file button
		WebElement uploadForm = webDriver.findElement(By.xpath("//input[@value='Upload']"));

		// Click upload file
		uploadForm.click();

		// Set explicit wait 30 sec
		WebDriverWait wait = new WebDriverWait(webDriver, 30);

		// Wait until contact us is loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ContactUs")));

		// Locate file uploaded window
		WebElement fileUploadedWindow = webDriver.findElement(By.cssSelector("div.ContactUs"));

		// Verify file upload window is displayed
		if (fileUploadedWindow.isDisplayed()) {
			System.out.println("File is uploaded");
		} else {
			System.out.println("File is not uploaded");
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
