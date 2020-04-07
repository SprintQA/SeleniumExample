package com.sprintqa.class50;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Example using Selenium WebDriver to interact and test radio buttons.
 * <p>
 * Use this site @see <a href=
 * "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html">https://www.seleniumeasy.com/test/basic-radiobutton-demo.html</a>
 * for testing.
 */
class UsingRadioButtonsWithSeleniumExample {
	WebDriver webDriver;
	String url = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";

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
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/webdrivers/mac/chromedriver");

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
	 * Radio button example with 2 radio buttons. These are commonly used for:
	 * Answers: Yes/No Answers: true/false Gender Questions: Male/Female
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void clickMaleRadioButtonValidateItIsSelectedTest() throws InterruptedException {
		// Validation flag
		boolean isSelected = false;

		// Define String variables
		String selectedGender = "";
		String expectedClickedCheckValueButtonText = "";

		// Open up your Chrome browser to the starting web page.
		webDriver.get(url);

		// Maximize the Chrome browser to fill the screen.
		webDriver.manage().window().maximize();

		// Look up page elements using WebDriver findElement() method.
		WebElement maleRadio = webDriver.findElement(By.xpath("//input[@name='optradio'][@value='Male']"));
		WebElement femaleRadio = webDriver.findElement(By.xpath("//input[@name='optradio'][@value='Female']"));
		WebElement checkValueButton = webDriver.findElement(By.id("buttoncheck"));
		WebElement clickedCheckValueButtonTextP = webDriver.findElement(By.cssSelector("p.radiobutton"));

		List<WebElement> genderRadioButtons = webDriver.findElements(By.name("optradio"));

		// Click each radio button
		maleRadio.click();
		femaleRadio.click();

		// Check that a radio button is selected and get its value.
		for (WebElement radioButton : genderRadioButtons) {
			if (radioButton.isSelected()) {
				isSelected = true;
				selectedGender = radioButton.getAttribute("value");
				expectedClickedCheckValueButtonText = "Radio button '" + selectedGender + "' is checked";
			}
		}

		// If not selected fail test
		if (!isSelected)
			fail("No radio button selected.");

		// Click check value button
		checkValueButton.click();

		// Verify expected text is displayed
		assertEquals(expectedClickedCheckValueButtonText, clickedCheckValueButtonTextP.getText());

		Thread.sleep(5000);
	}

}
