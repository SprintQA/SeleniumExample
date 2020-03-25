package com.sprintqa.class55;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassDynamicTablesExample {
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
	}

	@Test
	void test() throws InterruptedException {
		String url = "http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx";

		webDriver.get(url);

		webDriver.manage().window().maximize();

		// Locate the Locator for the user name input and enter "Tester"
		webDriver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");

		// Locate the Locator for the password input and enter "test"
		webDriver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		
		// Locate the Locator for the login button and click it.
		webDriver.findElement(By.id("ctl00_MainContent_login_button")).click();

		// Pause for 1 sec to let the page load.
		Thread.sleep(1000);
		
		// Locate the Locator to get the table body row data
		List<WebElement> rows = webDriver.findElements(By.xpath("//table[contains(@id, 'orderGrid')]/tbody/tr"));

		// Write the number of rows to the System.out console.
		System.out.println("Number of rows =" + (rows.size() - 1));

		// Locate the Locator to get the table header column data
		List<WebElement> cols = webDriver.findElements(By.xpath("//table[contains(@id, 'orderGrid')]/tbody/tr[1]/th"));

		// Write the number of columns to the System.out console.	
		System.out.println("Number of cols=" + cols.size());
		
		// Create a String with the text "US"	
		String expectedValue = "US";
		
		// Create a String placeholder to store our row data as we loop through the table rows.
		String rowData ="";
		
		// Loop through rows
		for (int i = 2; i <= rows.size(); i++) {
			
			// Get the text of the current row and store it in our string row data
			rowData = webDriver.findElement(By.xpath("//table[contains(@id, 'orderGrid')]/tbody/tr[" + i + "]"))
					.getText();
			
			// Write the current Row Data to the System.out console.
			System.out.println(rowData);
			
			// Now check to see if the current row data contains the text in our String variable expected Text			
			if (rowData.contains(expectedValue)) {

				// if it does click on the cell
				webDriver.findElement(By.xpath("//table[contains(@id, 'orderGrid')]/tbody/tr[" + i + "]/td[1]/input")).click();

				//Pause to show click
				Thread.sleep(1000);
			}
		}
		
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
