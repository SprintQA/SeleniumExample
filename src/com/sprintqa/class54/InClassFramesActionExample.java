package com.sprintqa.class54;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

class InClassFramesActionExample {
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
	 * Go to: http://uitestpractice.com/ 1. Use the browser to Locate the Locator
	 * you need to use to find the link "uitestpractice.com" located below the
	 * IFrame on the page. 2. Click the link to change the page in IFrame window. 3.
	 * Pause for 1 second before interacting with the frame 4. Use the browser to
	 * Locate the Locator you need to use to find the iFrame. 5. Before we can
	 * interact with the iFrame we first have to switch our webDriver's focus to the
	 * iFrame. 6. Declare an Actions variable and instantiate it by passing the
	 * webDriver to the Actions(WebDriver) constructor. This will allow are actions
	 * variable to interact with the webpage. 7. Use the browser to Locate the
	 * Locator you need to use to find the “Drag me to my target” box. 8. Use the
	 * browser to Locate the Locator you need to use to find the “Drop here" box. 9.
	 * Use the actions dragAndDrop() method to drag dragItem onto dropItem. Use the
	 * build() method to compile the action. Then use the perform() method to
	 * execute the action. 10. Next we need to switch the window focus back to the
	 * main page.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void inClassFramesActionTest() throws InterruptedException {
		String url = "http://uitestpractice.com/";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// 1. Use the browser to Locate the Locator you need to use to find the link
		// "uitestpractice.com"
		// located below the IFrame on the page.
		WebElement uitestpracticeLink = webDriver.findElement(By.xpath("//a[@target='iframe_a']"));

		// 2. Click the link to change the page in IFrame window.
		uitestpracticeLink.click();
		Thread.sleep(1000);

		// 3. Use the browser to Locate the Locator you need to use to find the iFrame.
		WebElement iframe = webDriver.findElement(By.name("iframe_a"));

		// 4. Before we can interact with the iFrame we first have to switch our
		// webDriver's
		// focus to the iFrame.
		webDriver.switchTo().frame(iframe);

		// 5. Declare an Actions variable and instantiate it by passing the webDriver to
		// the
		// Actions(WebDriver) constructor. This will allow are actions variable to
		// interact
		// with the webpage.
		Actions act = new Actions(webDriver);

		// 6. Use the browser to Locate the Locator you need to use to find the “Drag me
		// to my
		// target” box.
		WebElement dragItem = webDriver.findElement(By.id("draggable"));

		// 7. Use the browser to Locate the Locator you need to use to find the “Drop
		// here" box.
		WebElement dropItem = webDriver.findElement(By.id("droppable"));

		// 8. Use the actions dragAndDrop() method to drag dragItem onto dropItem. Use
		// the build()
		// method to compile the action. Then use the perform() method to execute the
		// action.
		act.dragAndDrop(dragItem, dropItem).build().perform();

		// 9. Next we need to switch the window focus back to the main page.
		webDriver.switchTo().defaultContent();

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
