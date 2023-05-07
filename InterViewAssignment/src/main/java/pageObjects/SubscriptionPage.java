package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubscriptionPage {
	WebDriver driver;
//selectors
	@FindBy(id = "translation-btn")
	WebElement translateBtnEnglish;

	@FindBy(id = "country-name")
	WebElement selectedcountry;

	@FindBy(css = "#arrow")
	WebElement countrySelector;

	@FindBy(css = ".plan-title")
	List<WebElement> planesNames;
	
	@FindBy (css = "#country-selct a")
	List<WebElement> countrieslist;

	@FindBy(xpath = "(//div[contains(text(),'From')])[1]")
	WebElement mainTrailcurrency;
	
	@FindBy(css = ".price")
	List<WebElement> pricesList;
	
	@FindBy(id = "currency-lite")
	WebElement litePrice;
	
	@FindBy(id = "currency-classic")
	WebElement classicPrice;
	
	@FindBy(id = "currency-premium")
	WebElement premiumPrice;

//Initializing the Page Objects:
	public SubscriptionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//actions
	public void changeToEnglish() {
		translateBtnEnglish.click();
	}

	public String getSelectedCountry() {
		return selectedcountry.getText();
	}

	public void selectCountry(String country) {
		countrySelector.click();
		driver.findElement(By.id(country)).click();
	}

	public List<String> getPlanesNames() {
		List<String> planNames = new ArrayList<String>();
		for (WebElement plan : planesNames) {
			planNames.add(plan.getText());
		}
		return planNames;
	}
	
	public List<String> getCountriesList() {
		List<String> countries = new ArrayList<String>();
		for (WebElement country : countrieslist) {
			countries.add(country.getAttribute("id"));
		}
		return countries;
	}

	public String getMaintrailcurrency() {
		return mainTrailcurrency.getText();
	}
	
	public List<String> getPriceList(){
		List<String> prices = new ArrayList<String>();
		for (WebElement price : pricesList ) {
			prices.add(price.getText());
		}
		return prices;
	}
	
	public String getLitePrice() {
		return litePrice.getText();
	}
	
	public String getClassicPrice() {
		return classicPrice.getText();
	}
	
	public String getPremiumPrice() {
		return premiumPrice.getText();
	}
}
