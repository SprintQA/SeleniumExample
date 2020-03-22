package com.sprintqa.class54;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassStaticTablesExample {
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

	/**
	 * 	Go to: https://the-internet.herokuapp.com/tables
	 * 1. Use the browser to Locate the Locator you need to use to find the table headers for the table in Example1. 
	 * 2. Write out the number of columns using the Collector size() method.
	 * 3. Loop through the TH tags in Row 1 and write out the table headers to the console.
	 * 4. Use the browser to Locate the Locator you need to use to find the table body rows.
	 * 5. Write out the number of rows using the Collector size() method.
	 * 6. Convert the List to an Iterator using the List iterator() method
	 * 7. Loop through the Iterator Collection using the Iterator hasNext() method
	 * 8. Using the Iterator next() method to get the next WebElement in the collection.
	 * 9. Then use the WebElement getText method to get the rowText. 
	 * 10. Finally, write the rowText to the console using System.out.
	 * @throws InterruptedException
	 */
	@Test
	void test() throws InterruptedException {
		// Set your starting web page.
		String url = "https://the-internet.herokuapp.com/tables";
		
		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		//	Use the browser to Locate the Locator you need to use to find the table headers. 
		List<WebElement> cols = webDriver.findElements(By.xpath("//*[@id='table1']/thead/tr/th"));

		// Write out the number of columns using the Collector size() method.
		System.out.println("Number of columns is=" + cols.size());
		
		// Console line spacer to make it easier to read output
		System.out.println("=======================");
		// Console text identifier for data about
		System.out.println("Printing column headers");
		// Console line spacer to make it easier to read output
		System.out.println("=======================");
		
		// Loop through the TH tags in Row 1
		for(WebElement col: cols) {
			//Write out the th cell data to the console.
			System.out.println(col.getText());
		}
		
		//getting number of rows
		List<WebElement> rows = webDriver.findElements(By.xpath("//*[@id='table1']/tbody/tr"));
		
		//Console line spacer to make it easier to read output
		System.out.println("=======================");
		//Console text identifier for data about
		System.out.println("Number of Rows is="+rows.size());
		//Console line spacer to make it easier to read output
		System.out.println("=======================");

		Iterator <WebElement> it=rows.iterator();

		//Console text identifier for data about
		System.out.println("---Printing row data---");
		System.out.println("=======================");
		
		// Iterate through Collection of rows
		String rowText="";
		while(it.hasNext()) {
			rowText=it.next().getText();
			System.out.println(rowText);
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
