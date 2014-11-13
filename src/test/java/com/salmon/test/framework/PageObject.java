package com.salmon.test.framework;

import com.salmon.test.framework.helpers.WebDriverHelper;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageObject {
    @Getter
    protected WebDriverWait wait;
    @Getter
    protected WebDriver webDriver;

    private static final long DRIVER_WAIT_TIME = 10;

    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);


    public PageObject() {
        this.webDriver = WebDriverHelper.getWebDriver();
        this.wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
    }

    /**
     * Returns the current Url from page
     **/
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    /**
    Returns the current page title from page
    */
    public String getCurrentPageTitle() {
        return getWebDriver().getTitle();
    }

    /**
    Find the dynamic element wait until its visible
    * @param by Element location found by css, xpath, id etc...
    *
    **/
    public WebElement waitForExpectedElement(final By by) {
        return wait.until(visibilityOfElementLocated(by));
    }

    /**
     Find the dynamic element wait until its visible for a specified time
     * @param by Element location found by css, xpath, id etc...
     * @param waitTimeInSeconds max time to wait until element is visible
     *
     **/
    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            LOG.info(e.getMessage());
            return null;
        } catch (TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }

    protected ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver driver) {
            	 try {
                     Thread.sleep(500);
                 } catch (InterruptedException e) {
                     LOG.error(e.getMessage());
                 }                WebElement element = getWebDriver().findElement(by);
                return element.isDisplayed() ? element : null;
            }
        };
    }

    protected boolean isElementPresent(final By by) {
        try {
            new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
            return false;
        }
        return true;
    }

    public WebElement waitForElementDisplayedAndClickable(By by) {
        waitForExpectedElement(by);
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(by));
    }

    protected boolean waitForElementToDisappear(By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebDriver getBrowserByPageTitle(String pageTitle) {
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver = webDriver.switchTo().window(windowHandle);
            if (pageTitle.equalsIgnoreCase(webDriver.getTitle())) {
                return webDriver;
            }
        }
        return null;
    }

    public void navigateToPreviousPageUsingBrowserBackButton() {
        webDriver.navigate().back();
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }

    public String getElementByTagNameWithJSExecutor(String tagName) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')").toString();
    }

    public String getElementByQueryJSExecutor(String cssSelector) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.querySelector('" + cssSelector + "')").toString();
    }

}