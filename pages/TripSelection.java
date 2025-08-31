package org.example.pages;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TripSelection extends BasePOM {
	Properties properties;
	public WebDriver driver;
	public TripSelection(WebDriver driver,Properties properties) {
		 super(driver);
		 this.properties=properties;

	}
	
	//locating Elements
	//opening alert
	@FindBy(xpath="//span[@class='commonModal__close']")
	WebElement closeALERT;
	//train icon
	@FindBy(xpath="//span[text()='Trains']/parent::a[contains(@href,'railways')]")
	WebElement trainIcon;
	//from place
	@FindBy(css= "#fromCity")
	WebElement fromCity;
	//field in from search
	@FindBy(xpath="//*[@id='fromCity']/ancestor::div[contains(@class,'selectRailCity')]//div[@role='combobox']//input[@type='text']")
	WebElement fieldFrom;
	//list of from places
	@FindBy(xpath="//*[@id='fromCity']/ancestor::div[contains(@class,'selectRailCity')]//ul/li")
	List<WebElement> fromSearch;
	//field in To search
	@FindBy(xpath="//*[@id='toCity']/ancestor::div[contains(@class,'selectRailCity')]//div[@role='combobox']//input[@type='text']")
	WebElement fieldTo;
	//list of To places
	@FindBy(xpath="//*[@id='toCity']/ancestor::div[contains(@class,'selectRailCity')]//ul/li")
	List<WebElement> toSearch;
	//field in To search
	@FindBy(xpath="(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[1]")
	WebElement firstMonth;
	// list of dates in firstMonth
	@FindBy(xpath="(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[1]//following-sibling::div[@class='DayPicker-Body']/div/div")
	List<WebElement> firstMonthDates;
	//field in To search
	@FindBy(xpath="(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[2]")
	WebElement secMonth;
	@FindBy(xpath="(//div[@class='DayPicker-Months']//div[@class='DayPicker-Caption'])[2]//following-sibling::div[@class='DayPicker-Body']/div/div")
	List<WebElement> secMonthDates;
	//field in To search
	@FindBy(xpath="//span[@aria-label='Next Month']")
	WebElement nextMonth;
	
	@FindBy(xpath="//li[text()='Sleeper Class']")
	WebElement busClass;
	
	@FindBy(xpath="//a[text()='Search']")
	WebElement searchButton;

	public void fromPlace() throws InterruptedException {
		//closing the ALERT of the opening page
		click(closeALERT);
		//clicking the train icon
		click(trainIcon);
		//Click on the from place
		click(fromCity);
		//entering the from place input
		sendKeys(fieldFrom,properties.getProperty("fromPlace"));
		//taking list of places
		Thread.sleep(2000);
		for(WebElement from: fromSearch) {
			System.out.println(from.getText());
			if (from.getText().contains(properties.getProperty("fromPlaceFullName"))) {
				from.click();
				break;
			}
		}	
	}
	//to search details
	public void toPlace() throws InterruptedException {
		sendKeys(fieldTo, properties.getProperty("toPlace"));
		//taking list of places
		Thread.sleep(2000);
		for(WebElement to: toSearch) {
			System.out.println(to.getText());
			if (to.getText().contains(properties.getProperty("toPlaceFullName"))) {
				to.click();
				break;
			}
		}	
	}
	//date picker
	public void calender() {
		while (true){
			if (firstMonth.getText().equals(properties.getProperty("month"))) {
//				List<WebElement> firstMonthDates= firstMonth.findElements(By.xpath(".//following-sibling::div[@class='DayPicker-Body']/div/div"));
				for(WebElement date: firstMonthDates) {
					if(date.getText().contains(properties.getProperty("date"))) {
						date.click();
						break;
					}
				}	break;
			}else if (secMonth.getText().equals(properties.getProperty("month"))) {
//				List<WebElement> secMonthDates= secMonth.findElements(By.xpath(".//following-sibling::div[@class='DayPicker-Body']/div/div"));
					for(WebElement date: secMonthDates) {
						if(date.getText().contains(properties.getProperty("date"))) {
							date.click();
							break;
				}	
			}	break;
			}
			else {
				nextMonth.click();
			}
		}
	}
	public void classSelection() {
		//selecting the bus class
		click(busClass);
		//Clicking on the search Button
		click(searchButton);
	}
	

	
//class ending
}
