package com.salmon.test.framework.helpers;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.logging.Level;


public class WebDriverHelper extends EventFiringWebDriver {

    private static RemoteWebDriver REAL_DRIVER = null;
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
        BROWSER = LoadProperties.getRunProps().getProperty("browser");
        if (BROWSER.equalsIgnoreCase("chrome")) {
            REAL_DRIVER = (RemoteWebDriver) startChromeDriver();
        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            startFireFoxDriver();
        } else if (BROWSER.equalsIgnoreCase("iexplore")) {
            startIEDriver();
        } else {
            throw new IllegalArgumentException("Browser type not supported: " + BROWSER);
        }

        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private static void startIEDriver() {
        DesiredCapabilities capabilities = getInternetExploreDesiredCapabilities();
        REAL_DRIVER = new InternetExplorerDriver(capabilities);
    }


    private static void startFireFoxDriver() {
       // DesiredCapabilities capabilities = getFireFoxDesiredCapabilities();
        REAL_DRIVER = new FirefoxDriver();
    }


    protected static WebDriver startChromeDriver() {
        DesiredCapabilities capabilities = getChromeDesiredCapabilities();
        REAL_DRIVER = new ChromeDriver(ChromeDriverService.createDefaultService(), capabilities);
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
        return REAL_DRIVER;
    }


    private static DesiredCapabilities getChromeDesiredCapabilities() {
        String platform = LoadProperties.getRunProps().getProperty("platform");
        System.setProperty("webdriver.chrome.driver", "tools/chromedriver/" + platform + "/chromedriver");
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

    private static DesiredCapabilities getFireFoxDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return capabilities;
    }

    private static DesiredCapabilities getInternetExploreDesiredCapabilities() {
        String platform = LoadProperties.getRunProps().getProperty("platform");
        System.setProperty("webdriver.ie.driver", "tools/iedriver/" + platform + "/IEDriverServer.exe");
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setVersion("9");
        return capabilities;
    }


    private static DesiredCapabilities getAppiumDesiredCapabilities() {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
        File app = new File(appDir, "ApiDemos-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.android.apis");
        capabilities.setCapability("appActivity", ".ApiDemos");
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

