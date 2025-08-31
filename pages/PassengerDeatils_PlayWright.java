package org.example.pages;

import java.util.List;
import java.util.Properties;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PassengerDeatils_PlayWright extends BasePOM_PlayWright{
	Properties properties;
	Page page;
	private Locator addPassenger;
	private Locator passengerName;
	private Locator passengerAge;
	private Locator passengerGender;
	private Locator genderList;
	private Locator passengerADD;
	
	public PassengerDeatils_PlayWright(Page page,Properties properties) {
		 super(page);
		 this.page=page;
		 this.properties=properties;
		 //Locators
		 addPassenger=page.locator("//span[text()='Add Traveller']/parent::a");
		 passengerName=page.locator("//label[text()='Name']/following-sibling::input");
		 passengerAge=page.locator("//label[text()='Age (in years)']/following-sibling::input");
		 passengerGender=page.locator("//label[text()=' Gender ']/following-sibling::div");
		 genderList=page.locator("//label[text()=' Gender ']/following-sibling::div//ul/li/span");
		 passengerADD=page.locator("//button[text()='Add']");	 
	}


	
	//Actions
	public void passenger() {
		//Click on Add passenger
		addPassenger.click();
		//Entering the  Passenger Name
		passengerName.fill(properties.getProperty("name"));
		//Entering the Age
		passengerAge.fill(properties.getProperty("age"));
		//Selecting the gender
		passengerGender.click();
		//gender select from list
		for(int i=0;i<genderList.count();i++) {
			if(genderList.nth(i).innerText().equalsIgnoreCase("Male")){
				genderList.nth(i).click();
			}
			
		}
	
		//click on Add 
		passengerADD.click();
	}

}
