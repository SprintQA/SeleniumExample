package com.sprintqa.class55.hwsolution;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class DynamicTablesTest {
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
	 * HW Assignment Using Dynamic Tables:
	 * 
	 * 1. Locate the Locator for user name input and enter "Admin" text
	 * 
	 * 2. Locate the Locator for the password input and enter "admin123"
	 * 
	 * 3. Locate the locator for the login button and click it.
	 * 
	 * 4. Wait for login to complete and page to load
	 * 
	 * 5. Click the "User Management" option on the menu bar.
	 * 
	 * 6. Wait for page to load
	 * 
	 * 7. In the "System Users" section Select "ESS" from the "User Role" drop down.
	 * 
	 * 8. In the "System Users" section click the "Search" button
	 * 
	 * 9. Wait for page to load
	 * 
	 * 10. Locate all the row data in the table and write our the number of rows to
	 * the System.out console.
	 * 
	 * 11. Locate the column data in the table and write our the number of columns
	 * to the System.out console.
	 * 
	 * 12. Write out all the row data to the System.out console
	 * 
	 * 13. Click the check box for each row in the table
	 * 
	 * 14 In the "System Users" section Select "Admin" from the "User Role" drop
	 * down.
	 * 
	 * 15. In the "System Users" section click the "Search" button
	 * 
	 * 16. Wait for page to load.
	 * 
	 * 17. Locate all the row data in the table and write our the number of rows to
	 * the System.out console.
	 * 
	 * 18. Locate the column data in the table and write our the number of columns
	 * to the System.out console.
	 * 
	 * 19. Write out all the row data to the System.out console
	 * 
	 * 20. If the row has a check box click it
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void inClassDynamicTablesTest() throws InterruptedException {
		// Set your starting web page.
		String url = "https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// 1. Locate the Locator for the user name input and enter "Admin"
		webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

		// 2. Locate the Locator for the password input and enter "admin123"
		webDriver.findElement(By.id("txtPassword")).sendKeys("admin123");

		// 3. Locate the Locator for the form and submit it.
		webDriver.findElement(By.id("frmLogin")).submit();

		// 4. Pause for 1 sec to let the page load.
		Thread.sleep(1000);

		// 5. Click the "User Management" option on the menu bar.
		webDriver.findElement(By.id("menu_admin_viewAdminModule"));

		// 6. Wait for page to load
		Thread.sleep(1000);

		// 7. In the "System Users" section Select "ESS" from the "User Role" drop down.
		WebElement userRoleDropDown = webDriver.findElement(By.id("searchSystemUser_userType"));
		Select userRoleSelect = new Select(userRoleDropDown);
		userRoleSelect.selectByVisibleText("ESS");

		// 8. In the "System Users" section click the "Search" button
		webDriver.findElement(By.id("searchBtn")).click();

		// 9. Wait for page to load
		Thread.sleep(1000);

		// 10. Locate all the row data in the table and write our the number of rows to
		// the System.out console.
		List<WebElement> rows = webDriver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
		System.out.println("Number of rows =" + (rows.size() - 1));

		// 11. Locate the column data in the table and write our the number of columns
		// to the System.out console.
		List<WebElement> cols = webDriver.findElements(By.xpath("//table[@id='resultTable']/thead/tr/th"));
		System.out.println("Number of cols=" + cols.size());

		// 12. Write out all the row data to the System.out console
		String rowData = "";
		// 13. Click the check box for each row in the table
		for (int i = 2; i <= rows.size(); i++) {
			// Get the text of the current row and store it in our string row data
			rowData = webDriver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]"))
					.getText();

			// Write the current Row Data to the System.out console.
			System.out.println(rowData);
			webDriver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td/input")).click();
		}

		// 14 In the "System Users" section Select "Admin" from the "User Role" drop
		// down.
		userRoleDropDown = webDriver.findElement(By.id("searchSystemUser_userType"));
		userRoleSelect = new Select(userRoleDropDown);
		userRoleSelect.selectByVisibleText("Admin");
		
		// 15. In the "System Users" section click the "Search" button
		webDriver.findElement(By.id("searchBtn")).click();
		
		// 16. Wait for page to load.
		Thread.sleep(1000);
		
		// 17. Locate all the row data in the table and write our the number of rows to
		// the System.out console.
		rows = webDriver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
		System.out.println("Number of rows =" + (rows.size() - 1));

		// 18. Locate the column data in the table and write our the number of columns
		// to the System.out console.
		cols = webDriver.findElements(By.xpath("//table[@id='resultTable']/thead/tr/th"));
		System.out.println("Number of cols=" + cols.size());

		// 19. Write out all the row data to the System.out console
		// 20. If the row has a check box click it
		for (int i = 2; i <= rows.size(); i++) {
			// Get the text of the current row and store it in our string row data
			rowData = webDriver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]"))
					.getText();

			// Write the current Row Data to the System.out console.
			System.out.println(rowData);
			webDriver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td/input")).click();
		}
	
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
