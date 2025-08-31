package org.example.pages;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassengerDeatils extends BasePOM {
	Properties properties;
	WebDriver driver;
	public PassengerDeatils(WebDriver driver,Properties properties) {
		 super(driver);
		 this.properties=properties;

	}
	
	
	
	//locators
	//Click on Add passenger
	@FindBy(xpath="//span[text()='Add Traveller']/parent::a")
	WebElement addPassenger;
	//passenger Name
	@FindBy(xpath="//label[text()='Name']/following-sibling::input")
	WebElement passengerName;
	//passenger Age
	@FindBy(xpath="//label[text()='Age (in years)']/following-sibling::input")
	WebElement passengerAge;
	//passenger gender
	@FindBy(xpath="//label[text()=' Gender ']/following-sibling::div")
	WebElement passengerGender;
	//gender list
	@FindBy(xpath="//label[text()=' Gender ']/following-sibling::div//ul/li/span")
	List<WebElement> genderList;
	//passenger details add
	@FindBy(xpath="//button[text()='Add']")
	WebElement passengerADD;
	
	//Actions
	public void passenger() {
		//Click on Add passenger
		click(addPassenger);
		//Entering the  Passenger Name
		sendKeys(passengerName, properties.getProperty("name"));
		//Entering the Age
		sendKeys(passengerAge, properties.getProperty("age"));
		//Selecting the gender
		click(passengerGender);
		//gender select from list
		for(WebElement gender: genderList) {
			if(gender.getText().equalsIgnoreCase("Male")){
				gender.click();
			}
		}
		//click on Add 
		click(passengerADD);
	}

}
