package com.sprintqa.class53;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

class InClassMouseActionsExample {
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
		String url = "http://uitestpractice.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();
				
/**
 * Go to: http://uitestpractice.com/
 *	⁃ Perform the following Actions:
 *		⁃ Drag the “Drag me to my target” box to the “Drop here” box”.
 *		⁃ Double click the “Double Click ME!” Button.
 *	⁃ Get text from alert box and write it to the console.
 *	⁃ Click OK on alert box.
 *	⁃ Click the “Toggle icons” button.
 */
		// Declare an Actions variable and instantiate it by
		// passing the webDriver to the Actions(WebDriver) constructor.
		// This will allow are actions variable to interact with the 
		// webpage.
		Actions act = new Actions(webDriver);
		
		//Find our drag and drop elements by id
		WebElement dragItem = webDriver.findElement(By.id("draggable"));
		WebElement dropItem = webDriver.findElement(By.id("droppable"));
		
		//drags dragItem onto dropItem 
		act.dragAndDrop(dragItem, dropItem).build().perform();
		
		Thread.sleep(3000);
		
		//Find our drag and drop elements
		WebElement doubleClickButton = webDriver.findElement(By.name("dblClick"));
		
		//perform double click on button
		act.moveToElement(doubleClickButton).doubleClick().build().perform();
		
		Thread.sleep(3000);	
		//Switch to the alert box and click ok
		webDriver.switchTo().alert().accept();
		
		// Switch to the default open window
		webDriver.switchTo().defaultContent();
		
		//Locate toggle button by id
		WebElement toggleClickButton = webDriver.findElement(By.id("toggle"));

		//click toggle button
		toggleClickButton.click();
		
		// Pause the test
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
