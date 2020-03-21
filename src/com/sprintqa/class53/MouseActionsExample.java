package com.sprintqa.class53;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

class MouseActionsExample {
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
		String url = "http://uitestpractice.com/Students/Actions";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

// Using Actions to interact with Drag and Drop
//
// Drag and drop is a very common feature. It is when you "grab" an object and
// drag it to a different location.
//

		// 1. Identify the element to drag from
		WebElement dragItem = webDriver.findElement(By.id("draggable"));
		
		// 2. Identify the element to drag to
		WebElement dropItem = webDriver.findElement(By.id("droppable"));

		// 3. Declare an Actions variable and instantiate it by
		// passing the webDriver to the Actions(WebDriver) constructor.
		// This will allow are actions variable to interact with the 
		// webpage.
		Actions act = new Actions(webDriver);
		
		// 4. Call the dragAndDrop() method, passing in the item
		// to drag and where to drop the item. Because this
		// is more than one action you have to call the build()
		// method to prepare the action so it can call the 
		// perform() method.
		act.dragAndDrop(dragItem, dropItem).build().perform();
		
		Thread.sleep(3000);

// Using Actions to control a web pages mouse interactions
//
// The Action class is user-facing API for emulating complex user gestures.
// Use this class rather than using the Keyboard or Mouse directly.
//

		// 5. Identify the the blue box
		WebElement blueBoxdiv = webDriver.findElement(By.id("div2"));
		
		// 6. Use the moveToElement to move the mouse over the blue box
		// this will trigger the onMouseOver Event changing the box to
		// Green. Because this is a single action we do not need to
		// call the build() method.
		act.moveToElement(blueBoxdiv).perform();
		Thread.sleep(3000);
		

		// 7. Identify the the double click button
		WebElement dblClickButton = webDriver.findElement(By.name("dblClick"));
		
		// 8. Now we want use the moveToElement() to move the mouse
		// to the dblClickButton and perform two clicks.
		// Because we are doing multiple actions we also need
		// to call the build() method to prepare the action.
		// So we can call the perform() to run the action command.
		act.moveToElement(dblClickButton).click().click().build().perform();
		Thread.sleep(3000);

		//9. switch to alert and click
		webDriver.switchTo().alert().accept();
		
		//10.switch back to main window
		webDriver.switchTo().defaultContent();
		
		Thread.sleep(3000);

		// 11. Identify the tag for the numbered boxes
		WebElement selectableButtonList = webDriver.findElement(By.id("selectable"));
		
		// 12. Because the numbered boxes are a list we need to get list items
		// Since this is not a Select we cannot use the Select class.
		// Instead we have to do an additional findElements() call on
		// the <ul> tag "selectable" for <li> tags. This will return
		// a List of line Item Webelements.
		List<WebElement> numberListItems = selectableButtonList.findElements(By.tagName("li"));
		
		// 13. Dynamically build our click actions on the list items.
		// We want to click the list item then wait 1 second
		// before clicking the next list item.
		for (WebElement numberListItem : numberListItems) {
			act.click(numberListItem).pause(1000);
		}
		
		// 14. Now that we have our actions we need to call the build()
		// method to prepare the commands to be run by the perform()
		// method.
		act.build().perform();
		
		
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
