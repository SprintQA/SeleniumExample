package com.sprintqa.class53;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InClassPopUpBoxesExample {
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
		// Set your starting web page.
		String url = "https://only-testing-blog.blogspot.com/2014/01/textbox.html";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		/**
		 * Go to: https://only-testing-blog.blogspot.com/2014/01/textbox.html ⁃ Click
		 * “Show Me Alert” Button. ⁃ Write alert text to console. ⁃ Click “Show me
		 * Confirmation” Button. ⁃ Write alert text to console. ⁃ Click the buttons in
		 * the confirm box. ⁃ Accept - Write Div tag “demo” text to console. - Repeat
		 * for cancel ⁃ Cancel ⁃ Write Div tag “demo” text to console.
		 */
		//Look up alert button
		WebElement alertButton = webDriver.findElement(By.xpath("//input[@value='Show Me Alert']"));
		
		// Click alert button
		alertButton.click();

		// Switch to alert box
		Alert alert = webDriver.switchTo().alert();
		
		//write alert box text to console
		System.out.println(alert.getText());
		Thread.sleep(3000);

		// Use the Alert accept() method to click the OK 
		// button and close the pop-up
		alert.accept();

		// Return control to the main window
		webDriver.switchTo().defaultContent();

		// Look up configm button by xpath
		WebElement confimButton = webDriver.findElement(By.xpath("//button[text()='Show Me Confirmation']"));
		
		// Click the confirm button to display pop-up
		confimButton.click();
		
		// Change focus to the pop-up box
		alert = webDriver.switchTo().alert();
		
		// Write alert text to console
		System.out.println(alert.getText());
		Thread.sleep(3000);

		// Use the Alert accept() method to click the OK 
		// button and close the pop-up		
		alert.accept();

		// Return control to the main window
		webDriver.switchTo().defaultContent();

		// Verify the pop-up was clicked.
		// Use the WebDriver to search the web page using the "id" locator.
		WebElement demoDiv = webDriver.findElement(By.id("demo"));
		
		// Write demo div text to console
		System.out.println(demoDiv.getText());
		Thread.sleep(3000);

		// Click the confirm button to pop-up confirm box
		confimButton.click();
		
		// Click OK Button in confirm box
		alert = webDriver.switchTo().alert();
		
		// Write alert text to console
		System.out.println(alert.getText());
		Thread.sleep(3000);

		// Click the cancel button
		alert.dismiss();

		// Return control to the main window
		webDriver.switchTo().defaultContent();

		// Verify the pop-up was clicked.
		// Use the WebDriver to search the web page using the "id" locator.
		demoDiv = webDriver.findElement(By.id("demo"));
		
		// write demo div to the console
		System.out.println(demoDiv.getText());

		// Pause the test
		Thread.sleep(5000);
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
