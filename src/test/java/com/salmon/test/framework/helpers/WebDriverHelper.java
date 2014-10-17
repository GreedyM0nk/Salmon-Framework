package com.salmon.test.framework.helpers;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.logging.Level;


public class WebDriverHelper extends EventFiringWebDriver {

    private static WebDriver REAL_DRIVER = null;
    private static String BROWSER;
    private static final Dimension BROWSER_WINDOW_SIZE = new Dimension(1280, 1024);
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };

    public WebDriverHelper() {
        super(REAL_DRIVER);
    }


    static {
        LoadProperties.loadRunConfigProps();
        BROWSER = LoadProperties.getRunProps().getProperty("browser");
        if (BROWSER.equalsIgnoreCase("chrome")) {
            REAL_DRIVER = startChromeDriver();
        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            startFireFoxDriver();
        } else {
            throw new IllegalArgumentException("Browser type not supported: " + BROWSER);
        }

        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }


    private static void startFireFoxDriver() {
        REAL_DRIVER = new FirefoxDriver();
    }

    protected static WebDriver startChromeDriver() {
        DesiredCapabilities capabilities = getDesiredCapabilities();
        REAL_DRIVER = new ChromeDriver(ChromeDriverService.createDefaultService(), capabilities);
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
        return REAL_DRIVER;
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        System.setProperty("webdriver.chrome.driver", "tools/chromedriver/linux64/chromedriver");
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        capabilities.setCapability("chrome.verbose", false);

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }


    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }


    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }





}
