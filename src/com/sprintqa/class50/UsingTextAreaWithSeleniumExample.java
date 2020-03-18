package com.sprintqa.class50;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Example using Selenium WebDriver to interact and test TextAreas.
 * <p>
 * Use this site @see <a href="https://www.seleniumeasy.com/test/input-form-demo.html">https://www.seleniumeasy.com/test/input-form-demo.html</a> for testing.
 */
class UsingTextAreaWithSeleniumExample {
	WebDriver webDriver;
	String url = "https://www.seleniumeasy.com/test/input-form-demo.html";

	/**
	 * Remember to configure your System path so the application
	 * can find your ChromeDriver binary files. 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"/Users/mpmeloche/Development/eclipse/workspace/SeleniumExamples/webdrivers/chromedriver");
		webDriver = new ChromeDriver();
	}
	
	/**
	 * Enter in text into a TextArea on a form.
	 * 
	 * @throws InterruptedException 
	 */
	@Test
	void singleCheckBoxTest() throws InterruptedException {
		// Declare the WebElements we will be using
		WebElement projectDescriptionTextArea;
		
		//We are using the newline tag "\n" to add text to multiple line in the text area.
		String inputTextAreaText = "line1\nline2";
		String actualTextAreaText = "";
		
		webDriver.get(url);
		webDriver.manage().window().maximize();
		
		projectDescriptionTextArea = webDriver.findElement(By.name("comment"));
		projectDescriptionTextArea.sendKeys("line1\nline2");
		
		// Why wont this work? 
		actualTextAreaText = projectDescriptionTextArea.getText();
		
		// But this will?
		actualTextAreaText = projectDescriptionTextArea.getAttribute("value");
		assertEquals(inputTextAreaText, actualTextAreaText);
		
		Thread.sleep(5000);
	}
	
	/**
	 * Make sure when your done running your tests that you close the window/tab
	 * and then exit out of the browser window.
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		webDriver.close();
		webDriver.quit();
	}

}
