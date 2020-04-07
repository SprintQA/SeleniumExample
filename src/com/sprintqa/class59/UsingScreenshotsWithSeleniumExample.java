package com.sprintqa.class59;

import org.junit.jupiter.api.Test;

import utils.CommonMethods;
import utils.ConfigsReader;

class UsingScreenshotsWithSeleniumExample extends CommonMethods {

	/**
	 * Use common Methods class to log into Orange HRM Live Take screen shots of
	 * each page.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	void usingScreenshotsTest() throws InterruptedException {

		takeScreenShot("screenshots/orangehrmlive_Login_page.png");
		sendText(getWebElementById("txtUsername"), ConfigsReader.getProperty("username"));
		sendText(getWebElementById("txtPassword"), ConfigsReader.getProperty("password"));

		takeScreenShot("screenshots/orangehrmlive_PopulatedLogin_page.png");
		submitForm(getWebElementById("frmLogin"));

		takeScreenShot("screenshots/orangehrmlive_Main_page.png");
		Thread.sleep(3000);
	}

}
