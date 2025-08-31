package org.example.pages;

import java.util.List;
import java.util.Properties;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class TrainSelection_PlayWright extends BasePOM_PlayWright{

	Properties properties;
	Page page;
	private Locator trainName;
	private Locator trainLists;
	
	 public TrainSelection_PlayWright(Page page,Properties properties) {
		 
		 super(page);
		 this.page=page;
		 this.properties=properties;
		//locators
		 trainName=page.locator("//p[text()='Train Name']");
		 trainLists=page.locator("//*[@aria-label='Train Listings']/div");
		 
	 }
	 

	
	public void trainProcess() throws InterruptedException {
		//sort by train name
		trainName.click();
		Thread.sleep(5000);
		//taking train list
	    for (int i = 0; i < trainLists.count(); i++) {
	        Locator train = trainLists.nth(i);
			if(train.innerText().contains("Maq Chennai Exp")) {
				// Scroll into view and click the nested sleeper class selector
		        train.scrollIntoViewIfNeeded();
				//selecting sleeper in selected trajn
				train.locator("//div[@class='Cards_cardContainer__GnUL3 ']/div[1]").click();
				break;
			}
		}
	}
	
	
	
	

}
