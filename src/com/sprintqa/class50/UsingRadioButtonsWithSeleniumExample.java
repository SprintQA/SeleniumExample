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
 * Use this site @see <a href="https://www.seleniumeasy.com/test/basic-radiobutton-demo.html">https://www.seleniumeasy.com/test/basic-radiobutton-demo.html</a> for testing.
 */
class UsingRadioButtonsWithSeleniumExample {
	WebDriver webDriver;
	String url = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";

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
	 * Radio button example with 2 radio buttons.
	 * These are commonly used for:
	 * 		Answers: Yes/No
	 * 		Answers: true/false
	 * 		Gender Questions: Male/Female
	 * 
	 * @throws InterruptedException 
	 */
	@Test
	void clickMaleRadioButtonValidateItIsSelectedTest() throws InterruptedException {
		// Declare the WebElements we will be using
		List<WebElement> genderRadioButtons;
		WebElement maleRadio;
		WebElement femaleRadio;
		WebElement checkValueButton;
		WebElement clickedCheckValueButtonTextP;
		
		boolean isSelected = false;
		
		String selectedGender = "";
		String expectedCLickedCheckValueButtonText = "";
		
		
		webDriver.get(url);
		webDriver.manage().window().maximize();
		
		maleRadio = webDriver.findElement(By.xpath("//input[@name='optradio'][@value='Male']"));
		maleRadio.click();
		
		femaleRadio = webDriver.findElement(By.xpath("//input[@name='optradio'][@value='Female']"));
		femaleRadio.click();
		
		// Check that a radio button is selected and get its value.
 	 	genderRadioButtons= webDriver.findElements(By.name("optradio"));
		for(WebElement radioButton:genderRadioButtons) {
			if(radioButton.isSelected()){
				isSelected = true;
				selectedGender = radioButton.getAttribute("value");
				expectedCLickedCheckValueButtonText = "Radio button '"+selectedGender+"' is checked";
			}
		}
		
		if(!isSelected)
			fail("No radio button selected.");
		
		checkValueButton = webDriver.findElement(By.id("buttoncheck"));
		checkValueButton.click();
		
		clickedCheckValueButtonTextP = webDriver.findElement(By.cssSelector("p.radiobutton"));
		assertEquals(expectedCLickedCheckValueButtonText, clickedCheckValueButtonTextP.getText());
		
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
