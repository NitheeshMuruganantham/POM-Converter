package org.example.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;





public class TrainSelection extends BasePOM{

	WebDriver driver;
	JavascriptExecutor js;
	Properties properties;
	
	 public TrainSelection(WebDriver driver,Properties properties,JavascriptExecutor js) {
		 
		 super(driver);
		 this.properties=properties;
		 this.js=js;
	 }
	 
	 
	//locators
	//sort by train name
	 @FindBy(xpath="//p[text()='Train Name']")
		WebElement trainName;
	 //list of trains
	 @FindBy(xpath="//*[@aria-label='Train Listings']/div")
	 List<WebElement> trainLists;
	
	
	public void trainProcess() {
		//sort by train name
		click(trainName);
		//taking train list
		for(WebElement train: trainLists) {
			if(train.getText().contains("Maq Chennai Exp")) {
				js.executeScript("arguments[0].scrollIntoView(true);", train);
				//selecting sleeper in selected trajn
				train.findElement(By.xpath(".//div[@class='Cards_cardContainer__GnUL3 ']/div[1]")).click();
				break;
			}
		}
	}
	
	
	
	

}
