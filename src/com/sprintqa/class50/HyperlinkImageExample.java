package com.sprintqa.class50;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class HyperlinkImageExample {
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
		String url = "https://www.seleniumeasy.com/selenium-tutorials";
		webDriver.get(url);
		webDriver.manage().window().maximize();
		
		WebElement hyperlink =  webDriver.findElement(By.xpath("//a[@title='Home']"));
		hyperlink.click();
		
		Thread.sleep(5000);
	}

}
