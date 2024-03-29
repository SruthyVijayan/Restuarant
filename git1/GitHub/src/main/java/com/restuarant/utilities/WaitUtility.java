package com.restuarant.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class WaitUtility {
		WebDriver driver;
		public WaitUtility(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	    public WebElement waitForElementTobeVisible(WebDriver driver, WebElement elementToBeLoaded, Duration Time) {
	        WebDriverWait wait = new WebDriverWait(driver, Time);
	        WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	        return element;
	    }

	    public WebElement waitForElemntTobeClickable(WebDriver driver, WebElement elementToBeLoaded, Duration Time) {
	        WebDriverWait wait = new WebDriverWait(driver, Time);
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementToBeLoaded));
	        return element;
	    }

	    public void MouseH(WebDriver driver, By BY) {
	        Actions actions = new Actions(driver);
	        //Retrieve WebElemnt 'slider' to perform mouse hover
	        WebElement slider = driver.findElement(BY);
	        //Move mouse to x offset 50 i.e. in horizontal direction
	        actions.moveToElement(slider, 50, 0).perform();
	        slider.click();
	    }

	    public void hitenter(WebDriver driver, WebElement el) {
	        //Retrieve WebElemnt 'slider' to perform mouse hover
	        el.sendKeys(Keys.ENTER);
	    }

	    public void sleep(long timeout) {
	        try {
	            Thread.sleep(timeout);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean waitForJSandJQueryToLoad(WebDriver driver, int duration) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	        // wait for jQuery to load
	        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                try {
	                    Long r = (Long) ((JavascriptExecutor) driver).executeScript("return $.active");
	                    return r == 0;
	                } catch (Exception e) {
	                    return true;
	                }
	            }
	        };

	        // wait for Javascript to load
	        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
	                        .toString().equals("complete");
	            }
	        };

	        return wait.until(jQueryLoad) && wait.until(jsLoad);
	    }

	    /*public WebDriver waitForElementToLoad(WebDriver driver , WebElement element)
	    {
	        WebDriverWait wait = new WebDriverWait(driver, 60);
	        wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	        return (driver);
	    }*/

	    public WebDriver waitForElementToLoad1(WebDriver driver, WebElement element, String text) {
	    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
	        return (driver);
	    }

	}



