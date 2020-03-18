package com.sprintqa.class50;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class TextAreExample {
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
		String url = "https://www.seleniumeasy.com/test/input-form-demo.html";
		webDriver.get(url);
		webDriver.manage().window().maximize();
	
		WebElement projectDescriptionTextArea = webDriver.findElement(By.xpath("//textarea[@name='comment']"));
		projectDescriptionTextArea.sendKeys("test1\ntest2");
		
		String projectDescriptionValue = projectDescriptionTextArea.getAttribute("value");
		
		if("test1\ntest2".equals(projectDescriptionValue)){
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}
		
		Thread.sleep(5000);
	}
	
	
	

}
