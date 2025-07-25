package com.salmon.test.framework.helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.logging.Level;

public class WebDriverHelper {

    private static WebDriver REAL_DRIVER = null;
    private static String BROWSER;
    private static String PLATFORM;
    private static String DRIVER_PATH;
    private static String DRIVER_ROOT_DIR;
    private static String FILE_SEPARATOR;
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverHelper.class);
    private static final Dimension BROWSER_WINDOW_SIZE = new Dimension(1280, 1024);
    private static final Thread CLOSE_THREAD = new Thread(() -> {
        if (REAL_DRIVER != null) {
            REAL_DRIVER.quit();
        }
    });

    static {
        FILE_SEPARATOR = System.getProperty("file.separator");
        PLATFORM = LoadProperties.getRunProps().getProperty("platform");
        BROWSER = LoadProperties.getRunProps().getProperty("browser");
        DRIVER_ROOT_DIR = LoadProperties.getRunProps().getProperty("driver.root.dir");

        if (!DRIVER_ROOT_DIR.equals("DEFAULT_PATH")) {
            System.setProperty("webdriver.chrome.driver", getDriverPath());
            System.setProperty("webdriver.ie.driver", getDriverPath());
        }

        try {
            if (BROWSER.equalsIgnoreCase("chrome")) {
                REAL_DRIVER = startChromeDriver();
            } else if (BROWSER.equalsIgnoreCase("firefox")) {
                REAL_DRIVER = startFireFoxDriver();
            } else if (BROWSER.equalsIgnoreCase("iexplore")) {
                REAL_DRIVER = startIEDriver();
            } else {
                throw new IllegalArgumentException("Browser " + BROWSER + " or Platform " + PLATFORM + " type not supported");
            }
        } catch (IllegalStateException e) {
            LOG.error("FIX path for driver.root.dir in pom.xml " + DRIVER_ROOT_DIR
                    + " Browser parameter " + BROWSER + " Platform parameter " + PLATFORM
                    + " type not supported");
        }

        if (REAL_DRIVER != null) {
            REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
        }
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public static String getDriverPath() {
        if (BROWSER.equals("chrome") && PLATFORM.contains("win")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "chromedriver.exe";
        } else if (BROWSER.equals("chrome") && PLATFORM.contains("linux")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "chromedriver";
        } else if (BROWSER.equals("iexplore") && PLATFORM.contains("win")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "iedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "IEDriverServer.exe";
        }
        return DRIVER_PATH;
    }

    private static WebDriver startIEDriver() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        // Set IE options as needed
        return new InternetExplorerDriver(ieOptions);
    }

    private static WebDriver startFireFoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // Set Firefox options as needed
        return new FirefoxDriver(firefoxOptions);
    }

    private static WebDriver startChromeDriver() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        chromeOptions.setCapability("goog:loggingPrefs", logs);
        chromeOptions.setCapability("chrome.verbose", false);

        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }
}
