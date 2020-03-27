package com.sprintqa.class57;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class UsingAdvancedXPathExample {
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
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	void test() throws InterruptedException {
		String url = "https://www.aa.com/homePage.do";

		webDriver.get(url);

		webDriver.manage().window().maximize();
		
		// Locate the Locator for the link "originAirport" and click it		
		webDriver.findElement(By.name("originAirport")).sendKeys("DCA");

		// Locate the Locator for the link "destinationAirport" and click it	
		webDriver.findElement(By.name("destinationAirport")).sendKeys("JFK");

		webDriver.findElement(By.xpath("//input[@id='aa-leavingOn']/../following-sibling::button")).click();

		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class,'ui-corner-left')]/div"));
		while (!webElement.getText().contains("August")) {
			webDriver.findElement(By.xpath("//a[@title='Next']")).click();
			webElement = webDriver.findElement(By.xpath("//div[contains(@class,'ui-corner-left')]/div"));
		}

		List<WebElement> cells = webDriver.findElements(
				By.xpath("//div[contains(@class,'ui-corner-left')]/following-sibling::table/tbody/tr/td"));
		for (WebElement cell : cells) {
			if (cell.getText().equals("10")) {
				cell.click();
				break;
			}
		}

		webDriver.findElement(By.xpath("//input[@id='aa-returningFrom']/../following-sibling::button")).click();
		webElement = webDriver.findElement(By.xpath("//div[contains(@class,'ui-corner-right')]/div"));
		while (!webElement.getText().contains("November")) {
			webDriver.findElement(By.xpath("//a[@title='Next']")).click();
			webElement = webDriver.findElement(By.xpath("//div[contains(@class,'ui-corner-right')]/div"));
		}

		cells = webDriver.findElements(
				By.xpath("//div[contains(@class,'ui-corner-right')]/following-sibling::table/tbody/tr/td"));
		for (WebElement cell : cells) {
			if (cell.getText().equals("24")) {
				cell.click();
				break;
			}
		}
		webDriver.findElement(By.id("flightSearchForm.button.reSubmit")).click();

		// Preparing the explicit wait.
//		WebDriverWait wait = new WebDriverWait(webDriver, 40);
//		
//		wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("h1#aa-pageTitle"))));
//
//		String text = webDriver.findElement(By.id("h1#aa-pageTitle")).getText().trim();
//		Assert.assertEquals(text, "Choose flights", "Test Case Pass");
		
		
		Thread.sleep(3000);
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
