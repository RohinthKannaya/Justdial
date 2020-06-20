package POM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverSetup.DriverSetUp;

public class POM2 extends DriverSetUp{
	Properties prop =new Properties();
	
	public void navigatetoUrl() throws FileNotFoundException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.get(prop.getProperty("url"));
	}
	public void removePopups() throws InterruptedException {
		Actions actions=new Actions(driver);
		actions.sendKeys(Keys.ESCAPE).build().perform();
	}
	public void deletecookies() {
		driver.manage(). deleteAllCookies();
	}
	public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
                try {
                    Thread.sleep(3000);
                    WebDriverWait wait = new WebDriverWait(driver, 30);
                    wait.until(expectation);
                } catch (Throwable error) {
                   System.out.println("Timeout waiting for Page Load Request to complete.");
                }
    }
	public void sendText(WebElement element,String text) {
		element.sendKeys(text);
	}
	public WebElement findElement(String xPath) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(xPath)))));
		return (driver.findElement(By.xpath(xPath)));
	}
	public void clickElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",element);
	}
	public String getElementText(WebElement element) {
		return element.getText();
	}
	public void scrolldown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
	}
	public void quitBrowser() {
		driver.quit();
	}
	public void clearBox(WebElement element) {
		element.clear();
	}
}
