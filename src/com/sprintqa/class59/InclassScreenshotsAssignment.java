package com.sprintqa.class59;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utils.CommonMethods;

class InclassScreenshotsAssignment extends CommonMethods{

	@Test
	void test() throws InterruptedException {
		TakesScreenshot ts = (TakesScreenshot) webDriver;

		File screenShot = ts.getScreenshotAs(OutputType.FILE);
		File outputFile = new File("screenshots/OnPageLoad.png");

		try {
			InputStream inputStream = new FileInputStream(screenShot);
			Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			inputStream.close();

			// Populate input fields
			
			sendText(getWebElementById("txtUsername"),"Admin");
			sendText(getWebElementById("txtPassword"),"admin123");

			screenShot = ts.getScreenshotAs(OutputType.FILE);
			outputFile = new File("screenshots/BeforeLogin.png");

			inputStream = new FileInputStream(screenShot);
			Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			inputStream.close();

			getWebElementById("frmLogin").submit();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Thread.sleep(3000);

	}

}
