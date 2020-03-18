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
 * Example using Selenium WebDriver to interact and test check boxes.
 * <p>
 * Use this site @see <a href="https://www.seleniumeasy.com/test/basic-checkbox-demo.html">https://www.seleniumeasy.com/test/basic-checkbox-demo.html</a> for testing.
 */
class UsingCheckBoxesWithSeleniumExample {
	WebDriver webDriver;
	String url = "https://www.seleniumeasy.com/test/basic-checkbox-demo.html";

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
	 * Single check box example.
	 * 
	 * @throws InterruptedException 
	 */
	@Test
	void singleCheckBoxTest() throws InterruptedException {
		// Declare the WebElements we will be using
		WebElement txtAgeDiv;
		WebElement ageSelectedCheckbox;
		
		webDriver.get(url);
		webDriver.manage().window().maximize();
		
		/*
		 * We can look this Element up a number of different ways:
		 * 		ID:
		 * 			isAgeSelected
		 * 		XPath:
		 * 			//input[@id='isAgeSelected']
		 * 			//input[@type='checkbox'][@id='isAgeSelected']
		 * 		CSS Selector:
		 * 			input#isAgeSelected
		 * 			#isAgeSelected
		 */
		ageSelectedCheckbox = webDriver.findElement(By.id("isAgeSelected"));
		//ageSelectedCheckbox = webDriver.findElement(By.xpath("//input[@id='isAgeSelected']"));
		//ageSelectedCheckbox = webDriver.findElement(By.xpath("//input[@type='checkbox'][@id='isAgeSelected']"));
		//ageSelectedCheckbox = webDriver.findElement(By.cssSelector("input#isAgeSelected"));
		//ageSelectedCheckbox = webDriver.findElement(By.cssSelector("#isAgeSelected"));
		txtAgeDiv = webDriver.findElement(By.id("txtAge"));
		
		// If checkbox is not checked then the display message should be blank.
		if(!ageSelectedCheckbox.isSelected()) {			
			assertEquals("", txtAgeDiv.getText());	
		}
		
		ageSelectedCheckbox.click();
		
		// If checkbox is not checked then the display message should be "Success - Check box is checked".
		if(ageSelectedCheckbox.isSelected()) {			
			assertEquals("Success - Check box is checked", txtAgeDiv.getText());	
		}
		
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
