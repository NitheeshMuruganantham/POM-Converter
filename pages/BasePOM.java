package org.example.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePOM {

	WebDriver driver;
	//constructor
	public BasePOM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	public void click(WebElement element) {
		element.click();
		
	}
	
	public void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}
	
	public void explicitWaitForElements(List<WebElement> e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wait.until(ExpectedConditions.visibilityOfAllElements(e));
	}
	
	
	
	
	
}
