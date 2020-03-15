package com.sprintqa.class49;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class sauceDemoExample {
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
	void test() {
		webDriver.get("https://www.saucedemo.com/");
		webDriver.manage().window().setSize(new Dimension(1280, 877));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement userNameElement = webDriver.findElement(By.id("user-name"));
		userNameElement.click();
		userNameElement.sendKeys("standard_user");
		
		WebElement passwordElement = webDriver.findElement(By.xpath("//input[@id='password']"));
		passwordElement.sendKeys("secret_sauce");
		
		WebElement loginButton = webDriver.findElement(By.cssSelector("input.btn_action"));
		loginButton.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// fail("Not yet implemented");
	}

}
