package com.sprintqa.class59;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utils.CommonMethods;

class InclassScreenshotsAssignment extends CommonMethods {

	@Test
	void inclassScreenshotsTest() throws InterruptedException {
		// Instantiate it by casting WebDriver to TakeScreenshot
		TakesScreenshot ts = (TakesScreenshot) webDriver;

		// Set file input/output screenshot file type and location
		File screenShot = ts.getScreenshotAs(OutputType.FILE);
		File outputFile = new File("screenshots/OnPageLoad.png");

		try {
			// Establish an input stream to read screenshot into memory
			InputStream inputStream = new FileInputStream(screenShot);

			// Use copy method to take input stream and write it to a
			// file on the local machine.
			Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			// Close input stream when done saving file.
			inputStream.close();

			// Populate input fields
			sendText(getWebElementById("txtUsername"), "Admin");
			sendText(getWebElementById("txtPassword"), "admin123");

			// Set file input/output screenshot file type and location
			screenShot = ts.getScreenshotAs(OutputType.FILE);
			outputFile = new File("screenshots/BeforeLogin.png");

			// Establish an input stream to read screenshot into memory
			inputStream = new FileInputStream(screenShot);

			// Use copy method to take input stream and write it to a
			// file on the local machine.
			Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			// Close input stream when done saving file.
			inputStream.close();

			// Click Login button
			getWebElementById("frmLogin").submit();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
