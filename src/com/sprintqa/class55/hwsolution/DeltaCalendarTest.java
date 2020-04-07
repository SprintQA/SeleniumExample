package com.sprintqa.class55.hwsolution;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class DeltaCalendarTest {
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

	/**
	 * HW Assignment Using Calendars:
	 * 
	 * 1. Locate the Locator for Depart and click on it
	 * 
	 * 2. Locate the Locator for Depart Month datepicker and write the Month/year
	 * displayed on the calendar
	 * 
	 * 3. Locate the Locator for Return Month datepicker and write the Month/year
	 * displayed on the calendar
	 * 
	 * 4. Locate the Locator for datepicker next calendar page button and click it.
	 * 
	 * 5. Locate the Locator for the new Depart Month datepicker and write the
	 * Month/year displayed on the calendar
	 * 
	 * 6. Locate the Locator for new Return Month datepicker and write the
	 * Month/year displayed on the calendar
	 * 
	 * NOTE:  Dep/Ret month can vary based on starting month.
	 * 
	 * 7. Locate the Locator for the Depart date Jan 26 and click it
	 * 
	 * 8. Locate the Locator for the Return date Feb 26 and click it
	 * 
	 * 9. Locate the Locator for the Calendar Done button and click it
	 * 
	 * 10. Locate the Locator for the updated Depart Date and write its text the the
	 * System.out console.
	 * 
	 * 11. Locate the Locator for the updated Return Date and write its text the the
	 * System.out console.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void usingOrbitzJQueryDatePickerTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://www.delta.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		Thread.sleep(1000);
		
		// 1. Locate the Locator for Depart and click on it
		WebElement depart = webDriver.findElement(By.id("calDepartLabelCont"));
		depart.click();

		// 2. Locate the Locator for Depart Month datepicker and write the Month/year
		// displayed on the calendar
		WebElement departMonth = webDriver.findElement(By.cssSelector("span.dl-datepicker-month-0"));
		WebElement departYear = webDriver.findElement(By.cssSelector("span.dl-datepicker-year-0"));
		System.out.println("Init Depart Month: " + departMonth.getText());
		System.out.println("Init Depart Year: " + departYear.getText());

		// 3. Locate the Locator for Return Month datepicker and write the Month/year
		// displayed on the calendar
		WebElement returnMonth = webDriver.findElement(By.cssSelector("span.dl-datepicker-month-1"));
		WebElement returnYear = webDriver.findElement(By.cssSelector("span.dl-datepicker-year-1"));
		System.out.println("Init Return Month: " + returnMonth.getText());
		System.out.println("Init Return Year: " + returnYear.getText());

		// 4. Locate the Locator for datepicker next calendar page button and click it.
		WebElement nextMonthBtn = webDriver.findElement(By.xpath("//span[@class='monthSelector'][text()='Next']"));
		nextMonthBtn.click();

		// 5. Locate the Locator for the new Depart Month datepicker and write the
		// Month/year displayed on the calendar
		departMonth = webDriver.findElement(By.cssSelector("span.dl-datepicker-month-0"));
		departYear = webDriver.findElement(By.cssSelector("span.dl-datepicker-year-0"));
		System.out.println("New Depart Month: " + departMonth.getText());
		System.out.println("New Depart Year: " + departYear.getText());

		// 6. Locate the Locator for new Return Month datepicker and write the
		// Month/year displayed on the calendar
		returnMonth = webDriver.findElement(By.cssSelector("span.dl-datepicker-month-1"));
		returnYear = webDriver.findElement(By.cssSelector("span.dl-datepicker-year-1"));
		System.out.println("New Return Month: " + returnMonth.getText());
		System.out.println("New Return Year: " + returnYear.getText());

		// 7. Locate the Locator for the Depart date April 26 and click it
		// Locate the left calendars Date Picker Calendar Month Caption and store it in
		// a By variable.

		// Check the String variable calendarDisplayedMonth to see if it is currently
		while (!departMonth.getText().equals("January")) {
			nextMonthBtn.click();
			departMonth = webDriver.findElement(By.cssSelector("span.dl-datepicker-month-0"));
		}

		List<WebElement> departDays = webDriver
				.findElements(By.xpath("//div[@class='dl-datepicker-group dl-datepicker-group-0']//td"));

		// Loop through depart days until we find 26
		for (WebElement webElement : departDays) {
			if (webElement.getText().equals("26")) {
				webElement.click();
				break;
			}
		}

		// 8. Locate the Locator for the Return date May 26 and click it
		List<WebElement> returnDays = webDriver
				.findElements(By.xpath("//div[@class='dl-datepicker-group dl-datepicker-group-1']//td"));

		// Loop through depart days until we find 26
		for (WebElement webElement : returnDays) {
			if (webElement.getText().equals("26")) {
				webElement.click();
				break;
			}
		}

		// 9. Locate the Locator for the Calendar Done button and click it
		webDriver.findElement(By.cssSelector("button.donebutton")).click();

		// 10. Locate the Locator for the updated Depart Date and write its text the the
		// System.out console.
		System.out.println("Actual Depart Date: "
				+ webDriver.findElement(By.cssSelector("span.calenderDepartSpan")).getText());

		// 11. Locate the Locator for the updated Return Date and write its text the the
		// System.out console.
		System.out.println("Actual Depart Date: "
				+ webDriver.findElement(By.cssSelector("span.calenderReturnSpan")).getText());
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
