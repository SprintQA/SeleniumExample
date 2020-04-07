package com.sprintqa.class58;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class UsingJavaScriptExecutorSeleniumExample {
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
	void usingJavaScriptExecutorTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://demoqa.com/automation-practice-form/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Locate continents dropdown box
		WebElement continentDropDown = webDriver.findElement(By.id("continents"));

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

		// Vertical scroll up by 400 pixels
		js.executeScript("window.scrollBy(0, -400)");

		// takes the continentDropDown element in as arguments[0] and scrolls it into
		// view
		js.executeScript("arguments[0].scrollIntoView(true);", continentDropDown);

		WebElement chbox = webDriver.findElement(By.cssSelector("input[value='QTP']"));

		// takes the chbox element in as arguments[0] and click the check box
		js.executeScript("arguments[0].click();", chbox);

		// To generate Alert window using JavascriptExecutor. Display the alert message
		// alert('text'); Will display a browser alert box.
		js.executeScript("alert('You Clicked QTP');");

		// Switch to alert box and cick accept to close it
		webDriver.switchTo().alert().accept();

		// Convert Continent DropDown to Select Object
		// So we can navigate the drop down option.
		Select dropDown = new Select(continentDropDown);

		// Select The option Australia
		dropDown.selectByVisibleText("Australia");

		// Refresh browser
		js.executeScript("history.go(0)");

		// Get InnerText of a Webpage
		System.out.println(js.executeScript("return document.documentElement.innerText;").toString());

		// Get Title of a WebPage
		System.out.println(js.executeScript("return document.title;").toString());

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
