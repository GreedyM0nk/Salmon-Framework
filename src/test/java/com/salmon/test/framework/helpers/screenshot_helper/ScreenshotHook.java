package com.salmon.test.framework.helpers.screenshot_helper;

import com.salmon.test.framework.PageObject;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ScreenshotHook extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotHook.class);

    @After
    public void embedScreenshot(Scenario scenario) {
        try {
            Map<String, Object> screenShots = ScreenshotHelper.getScreenShotsForCurrentTest();
            for (Map.Entry<String, Object> screenShot : screenShots.entrySet()) {
                scenario.attach(((byte[]) screenShot.getValue()), "image/png", screenShot.getKey());
            }

            ScreenshotHelper.tidyUpAfterTestRun();

            if (scenario.isFailed()) {
                scenario.attach(getWebDriver().getCurrentUrl().getBytes(), "text/plain", "Current URL");
                byte[] screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenShot, "image/png", "Failure Screenshot");
            }

        } catch (WebDriverException | ClassCastException wde) {
            LOG.error("Screenshot capture failed: {}", wde.getMessage());
        } finally {
            getWebDriver().switchTo().defaultContent();
        }
    }
}
