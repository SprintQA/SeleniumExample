package utils;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	public WebDriver webDriver;

	/**
	 * Remember to configure your System path so the application can find your
	 * ChromeDriver binary files.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ConfigsReader.readProperties(Constants.CONFIGFILEPATH);

//		System.setProperty("webdriver.chrome.driver",
//				"/Users/mpmeloche/Development/eclipse/workspace/SeleniumExamples/webdrivers/chromedriver");
//
//		// Declare your webDriver class variable to a ChromeDriver WebDriver to
//		// communicate with Chrome.
//		webDriver = new ChromeDriver();

		webDriver = getBrowserSpecificWebDriver(ConfigsReader.getProperty("browser"));

		// Set our timeouts
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Set window size
		webDriver.manage().window().fullscreen();

		// Set our starting url based on properties file
		webDriver.get(ConfigsReader.getProperty("url"));
		webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	/**
	 * Make sure when your done running your tests that you close the window/tab and
	 * then exit out of the browser window.
	 * 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		try {
			webDriver.close();
		} catch (Exception e) {
			System.out.println("Browser was unable to close: " + e.getMessage());
		}
		try {
			webDriver.quit();
		} catch (NoSuchSessionException e) {
			System.out.println("Browser was already exited.");
		} catch (Exception ex) {
			System.out.println("Browser was unable to Quit: " + ex.getMessage());
		}
	}

	private String getWebDriverBinaryPath() {
		String userWorkingDirectory = System.getProperty("user.dir");
		String os = System.getProperty("os.name");

		String binaryPath = "";
		System.out.println(os.substring(0, 3));

		switch (os.substring(0, 3).toLowerCase()) {
		case "win":
			binaryPath = "/webdrivers/win/";
			break;

		case "mac":
			binaryPath = "/webdrivers/mac/";
			break;

		case "lin":
			binaryPath = "/webdrivers//lin";
		}

		return userWorkingDirectory + binaryPath;
	}

	private WebDriver getBrowserSpecificWebDriver(String browser) {
		WebDriver driver = null;
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.firefox.driver", getWebDriverBinaryPath() + "geckodriver");
			driver = new FirefoxDriver();
			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver", getWebDriverBinaryPath() + "chromedriver");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", getWebDriverBinaryPath() + "msedgedriver");
			driver = new EdgeDriver();
		}

		return driver;
	}

}
