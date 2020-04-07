package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseClass {

	/**
	 * Method will accept alert
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 */
	public void acceptAlert() {
		try {
			Alert alert = webDriver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}
	
	public void click(WebElement element) {
		element.click();
	}

	/**
	 * Method will dismiss alert
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 */
	public void dismissAlert() {
		try {
			Alert alert = webDriver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}

	/**
	 * Method will get text of an alert
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 * @return String text
	 */
	public String getAlertText() {

		try {
			Alert alert = webDriver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
			return null;
		}
	}

	public WebElement getWebElement(By locator) {
		return webDriver.findElement(locator);
	}

	public WebElement getWebElementByCssSelector(String cssSelector) {
		return getWebElement(By.cssSelector(cssSelector));
	}

	public WebElement getWebElementById(String id) {
		return getWebElement(By.id(id));
	}

	public WebElement getWebElementByXpath(String xpath) {
		return getWebElement(By.xpath(xpath));
	}

	public List<WebElement> getWebElements(By locator) {
		return webDriver.findElements(locator);
	}

	public List<WebElement> getWebElementsByCssSelector(String cssSelector) {
		return getWebElements(By.cssSelector(cssSelector));
	}

	public List<WebElement> getWebElementsById(String id) {
		return getWebElements(By.id(id));
	}

	public List<WebElement> getWebElementsByXpath(String xpath) {
		return getWebElements(By.xpath(xpath));
	}

	public String getWebElementText(WebElement webElement) {
		return webElement.getText();
	}

	/**
	 * Method that will click the button using Javascript
	 * 
	 * @param WebElement element
	 */
	public void jsClick(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Method that will scroll down the page using Javascript
	 * 
	 * @param int pixels
	 */
	public void scrollDown(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	/**
	 * Method that will scroll up the page using Javascript
	 * 
	 * @param int pixels
	 */
	public void scrollUp(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("window.scrollBy(0,-" + pixels + ")");
	}

	public void selectList(WebElement element, String text) {

		List<WebElement> listLocations = element.findElements(By.tagName("li"));
		for (WebElement li : listLocations) {
			String liText = li.getAttribute("innerHTML");

			if (liText.contains(text)) {
				li.click();
				break;
			}
		}
	}

	/**
	 * @autor Syntax This method will select a specified value from a drop down by
	 *        its index
	 * @parm Select element, int index
	 */
	public void selectValueFromDD(WebElement element, int index) { // method overloading

		Select select = new Select(element);
		List<WebElement> options = select.getOptions();

		if (options.size() > index) {
			select.selectByIndex(index);
		} else {
			System.out.println("Invalid index has been passed");
		}
		select.deselectByIndex(index);

	}

	/**
	 * @author Syntax This method will select a specified value from a drop down by
	 *         visible text
	 * @parameter Select element, String text
	 */
	public void selectValueFromDD(WebElement element, String text) {

		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		boolean isSelected = false;
		for (WebElement option : options) {
			String optionText = option.getText();
			if (optionText.equals(text)) {
				select.selectByVisibleText(text);
				System.out.println("Option with text " + text + " is selected");
				isSelected = true;
				break; // once we find the value we are looking for, we stop it; break;
			}
		}
		if (!isSelected) {
			System.out.println("Option with text " + text + " is NOT available");
		}
	}

	public void sendText(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public void submitForm(WebElement webElement) {
		webElement.submit();
	}

	/**
	 * Method that will switch control to the specify frame
	 * 
	 * @param frame index
	 */
	public void switchToFrame(int index) { // method overloading
		try {
			webDriver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}

	/**
	 * Method that will switch control to the specify frame
	 * 
	 * @throws NoSuchFrameException if frame is not present
	 * @param frame id or frame name
	 */
	public void switchToFrame(String idOrName) {
		try {
			webDriver.switchTo().frame(idOrName);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}

	/**
	 * Method that will switch control to the specify frame
	 * 
	 * @param frame element
	 */
	public void switchToFrame(WebElement element) { // method overloading
		try {
			webDriver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}

	public void takeScreenShot(String fileName) {
		// Indicates a driver that can capture a screenshot and store it in different
		// ways.
		// Instantiate it by casting WebDriver to TakeScreenshot
		TakesScreenshot ts = (TakesScreenshot) webDriver;

		try {
			// Capture the screenshot and store it in the specified location.
			// In this case file
			File screenShot = ts.getScreenshotAs(OutputType.FILE);

			// Creates a FileInputStream by opening a connection to an actual file, the file
			// named by the File object file in the file system. A new FileDescriptor object
			// is created to represent this file connection.
			InputStream inputStream = new FileInputStream(screenShot);

			// Create output file setting the file directory to our project screenshots
			// folder.
			// Name the output file UsingScreenshotsWithSeleniumExample_ + our formatted
			// date/time + the post fix .png
			File outputFile = new File(System.getProperty("user.dir") + "/screenshots/" + fileName);

			// Copies all bytes from an input stream to a file. On return, the input stream
			// will be at end of stream.
			Files.copy(inputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			// Make sure to close your input stream.
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to take screenshot");
		}
	}

	public void waitForElementBeClickable(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElementBeClickable(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementBeVisible(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Method that will wait for element to be visible
	 * 
	 * @param WebElement element, int time
	 */
	public void waitForElementBeVisible(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
