package com.sprintqa.class50;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class RadioButtonExample {
	WebDriver webDriver;
	
	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"/Users/mpmeloche/Development/eclipse/workspace/SeleniumExamples/webdrivers/chromedriver");
		webDriver = new ChromeDriver();
	}

	@AfterEach
	void tearDown() throws Exception {
		webDriver.close();
		webDriver.quit();
	}

	@Test
	void test() throws InterruptedException {
		String url = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";
		webDriver.get(url);
		webDriver.manage().window().maximize();
		
		WebElement maleRadioButton = webDriver.findElement(By.xpath("//input[@name='optradio'][@value='Male']"));
		maleRadioButton.click();
		
		WebElement checkGenderButton = webDriver.findElement(By.id("buttoncheck"));
		checkGenderButton.click();
		
		WebElement checkGenderValueP = webDriver.findElement(By.cssSelector("p.radiobutton"));
		
		if("Radio button 'Male' is checked".equals(checkGenderValueP.getText())){
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}
		
		
		Thread.sleep(5000);
		
	}

}
