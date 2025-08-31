package org.example.pages;

import java.util.List;
import java.util.Properties;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TripSelection_PlayWright extends BasePOM_PlayWright {
	Properties properties;
	Page page;
	private Locator closeALERT;
	private Locator trainIcon;
	private Locator fromCity;
	private Locator fieldFrom;
	private Locator fromSearch;
	private Locator fieldTo;
	private Locator toSearch;
	private Locator firstMonth;
	private Locator firstMonthDates;
	private Locator secMonth;
	private Locator secMonthDates;
	private Locator nextMonth;
	private Locator busClass;
	private Locator searchButton;

	public TripSelection_PlayWright(Page page,Properties properties) {
		 super(page);
		 this.properties=properties;
		 this.page=page;
		//locating Elements
		//opening alert
		 closeALERT=page.locator("//span[@class='commonModal__close']");
		//train icon
		 trainIcon=page.locator("//span[text()='Trains']/parent::a[contains(@href,'railways')]");
		//from place
		 fromCity=page.locator("#fromCity");
		//field in from search
		 fieldFrom=page.locator("//*[@id='fromCity']/ancestor::div[contains(@class,'selectRailCity')]//div[@role='combobox']//input[@type='text']");
		//list of from places
		 fromSearch=page.locator("//*[@id='fromCity']/ancestor::div[contains(@class,'selectRailCity')]//ul/li");
		//field in To search
		 fieldTo=page.locator("//*[@id='toCity']/ancestor::div[contains(@class,'selectRailCity')]//div[@role='combobox']//input[@type='text']");
		//list of To places
		 toSearch=page.locator("//*[@id='toCity']/ancestor::div[contains(@class,'selectRailCity')]//ul/li");
		//field in To search
		 firstMonth=page.locator("(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[1]");
		// list of dates in firstMonth
		 firstMonthDates=page.locator("(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[1]//following-sibling::div[@class='DayPicker-Body']/div/div");
		//field in To search 
		 secMonth=page.locator("(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[2]");
		// list of dates in secMonth
		 secMonthDates=page.locator("(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[2]//following-sibling::div[@class='DayPicker-Body']/div/div");
		//field in To search
		 nextMonth=page.locator("//span[@aria-label='Next Month']");
		 busClass=page.locator("//li[text()='Sleeper Class']");
		 searchButton=page.locator("//a[text()='Search']");
	}



	public void fromPlace() throws InterruptedException {
		//closing the ALERT of the opening page
		closeALERT.click();
		//clicking the train icon
		trainIcon.click();
		//Click on the from place
		fromCity.click();
		//entering the from place input
		fieldFrom.fill(properties.getProperty("fromPlace"));
		//taking list of places
		Thread.sleep(2000);
		for (int i = 0; i < fromSearch.count(); i++) {
		    Locator from = fromSearch.nth(i);
		    String text = from.innerText().trim();
		    System.out.println(text);

		    if (text.contains(properties.getProperty("fromPlaceFullName"))) {
		        from.click();  // No need for Thread.sleep — Playwright auto-waits
		        break;
		    }
		}
	}
	//to search details
	public void toPlace() throws InterruptedException {
		fieldTo.fill(properties.getProperty("toPlace"));
		//taking list of places
		Thread.sleep(2000);
		for (int i = 0; i < toSearch.count(); i++) {
		    Locator to = toSearch.nth(i);
		    if (to.innerText().trim().contains(properties.getProperty("toPlaceFullName"))) {
		        to.click();  // Playwright will auto-wait for the element to be clickable
		        break;
		    }
		}
	}
	
	//date picker
	public void calender() {
		while (true){
			if (firstMonth.innerText().equals(properties.getProperty("month"))) {
				
				for (int i = 0; i < firstMonthDates.count(); i++) {
				    Locator date = firstMonthDates.nth(i);
				   if (date.innerText().trim().contains(properties.getProperty("date"))) {
				        date.click(); // Playwright auto-waits for element to be ready
				        break;
				    }
				}
				break;
			}else if (secMonth.innerText().equals(properties.getProperty("month"))) {
				for (int i = 0; i < secMonthDates.count(); i++) {
				    Locator date = secMonthDates.nth(i);
				   if (date.innerText().trim().contains(properties.getProperty("date"))) {
				        date.click(); // Playwright auto-waits for element to be ready
				        break;
				    }
				}		break;
			}
			else {
				nextMonth.click();
			}
		}
	}
	public void classSelection() {
		//selecting the bus class
		busClass.click();;
		//Clicking on the search Button
		searchButton.click();
	}
	
//class ending
}
