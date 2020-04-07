package com.sprintqa.class55;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class UsingOrbitzJQueryDatePickerExample {
	WebDriver webDriver;

	/**
	 * Remember to configure your System path so the application can find your
	 * ChromeDriver binary files.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() {
		// Setup the System path to the Selenium Chrome Binary file
		// Use System.getProperty("user.dir") to get the system path for the project.
		System.setProperty("webdriver.chrome.driver", getChromeDriverBinaryPath());

		// Instantiate WebDriver to use ChromeDriver.
		webDriver = new ChromeDriver();
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

	@Test
	void usingOrbitzJQueryDatePickerTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.orbitz.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Locate the check in input box and click it to bring up the DataPicker
		webDriver.findElement(By.id("hotel-checkin-hp-hotel")).click();

		// Locate the left calendars Date Picker Calendar Month Caption and store it in
		// a By variable.
		By datepickerCalMonthCaptionCssSelector = By.cssSelector("div.datepicker-cal-month > table > caption");

		// Get the displayed text from the the left calendars Date Picker Calendar Month
		// Caption and store it in a String variable calendarDisplayedMonth.
		String calendarDisplayedMonth = webDriver.findElement(datepickerCalMonthCaptionCssSelector).getText();

			// Write the String variable calendarDisplayedMonth to the System.out console.
		System.out.println(calendarDisplayedMonth);

		// Check the String variable calendarDisplayedMonth to see if it is currently
		// Jan 2021
		if (!calendarDisplayedMonth.equals("Jan 2021")) {

			// Create a do/while loop to loop until the left calendars Date Picker Calendar
			// Month Caption is Jan 2021
			do {

				// Locate the Date Picker Calendar next button and click it
				webDriver.findElement(By.cssSelector("button.next")).click();

				// Pause for 1 sec between clocks to give the calendar time to refreash
				Thread.sleep(1000);

			} while (!webDriver.findElement(datepickerCalMonthCaptionCssSelector).getText().equals("Jan 2021"));
		}

		// Click on Jan 31 2021
		webDriver.findElement(By.xpath("//button[@data-year='2021'][@data-month='0'][@data-day='31']")).click();

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
