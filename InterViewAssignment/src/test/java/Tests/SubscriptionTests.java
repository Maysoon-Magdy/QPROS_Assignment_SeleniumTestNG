package Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.testng.Assert;
import TestBase.TestBase;
import pageObjects.SubscriptionPage;
import Utilities.ReadProperity;
import driver.driverAction;

public class SubscriptionTests extends TestBase {
	SubscriptionPage subscribtionPage;
	ReadProperity currencyListFile = new ReadProperity();
	Properties prop;

	@Test
	public void ValidateDefaultCountry() {
		subscribtionPage = new SubscriptionPage(driverAction.driver);
		subscribtionPage.changeToEnglish();
		AssertJUnit.assertEquals("Egypt", subscribtionPage.getSelectedCountry());
	}

	@Test
	public void ValidatePackagesTypesinCountries() {
		subscribtionPage = new SubscriptionPage(driverAction.driver);
		subscribtionPage.changeToEnglish();
		List<String> countries = subscribtionPage.getCountriesList();
		for (String country : countries) {
			subscribtionPage.selectCountry(country);
			List<String> actualPlans = subscribtionPage.getPlanesNames();
			List<String> expectedPlans = Arrays.asList("LITE", "CLASSIC", "PREMIUM");
			AssertJUnit.assertEquals(actualPlans, expectedPlans);
		}
	}

	@Test
	public void ValidateMainTrailCurrencyPerCountry() {
		subscribtionPage = new SubscriptionPage(driverAction.driver);
		subscribtionPage.changeToEnglish();
		List<String> countries = Arrays.asList("eg", "ae", "jo");
		for (String country : countries) {
			subscribtionPage.selectCountry(country);
			switch (country) {
			case "eg":
				Assert.assertTrue(subscribtionPage.getMaintrailcurrency().contains("Egyptian pound"));
				break;
			case "ae":
				Assert.assertTrue(subscribtionPage.getMaintrailcurrency().contains("AED"));
				break;
			case "jo":
				Assert.assertTrue(subscribtionPage.getMaintrailcurrency().contains("JOD"));
				break;
			}
		}
	}

	@Test
	public void ValidatePackagesCurrencies() throws IOException {
		subscribtionPage = new SubscriptionPage(driverAction.driver);
		subscribtionPage.changeToEnglish();
		List<String> prices = subscribtionPage.getPriceList();
		List<String> countries = subscribtionPage.getCountriesList();
		prop = currencyListFile.readPropertyFile("/src/main/java/Utilities/CurrencyPropertyFile.Properties");
		for (String country : countries) {
			subscribtionPage.selectCountry(country);
			String currency = prop.getProperty(country);
			for (int i = 0; i < prices.size(); i++) {
				Assert.assertTrue(prices.get(i).contains(currency)); // this is fail as currency in all plans in USD
			}
		}
	}

	@Test
	public void ValidateLitePackagesPricesPerCountry() throws IOException {
		subscribtionPage = new SubscriptionPage(driverAction.driver);
		subscribtionPage.changeToEnglish();
		List<String> countries = subscribtionPage.getCountriesList();
		prop = currencyListFile.readPropertyFile("/src/main/java/Utilities/LitePlanPrices.Properties");
		for (int i = 0; i < countries.size(); i++) {
			subscribtionPage.selectCountry(countries.get(i));
			String expectedPrice = prop.getProperty(countries.get(i));
			String actualLitePrice = subscribtionPage.getLitePrice();
			Assert.assertTrue(actualLitePrice.contains(expectedPrice));
		}
	}
	
	@Test
	public void ValidateClassicPackagesPricesPerCountry() throws IOException {
		subscribtionPage = new SubscriptionPage(driverAction.driver);
		subscribtionPage.changeToEnglish();
		List<String> countries = subscribtionPage.getCountriesList();
		prop = currencyListFile.readPropertyFile("/src/main/java/Utilities/ClassicPlanPrices.Properties");
		for (int i = 0; i < countries.size(); i++) {
			subscribtionPage.selectCountry(countries.get(i));
			String expectedPrice = prop.getProperty(countries.get(i));
			String actualClassicPrice = subscribtionPage.getClassicPrice();
			Assert.assertTrue(actualClassicPrice.contains(expectedPrice));
		}
	}

	@Test
	public void ValidatePremiumPackagesPricesPerCountry() throws IOException {
		subscribtionPage = new SubscriptionPage(driverAction.driver);
		subscribtionPage.changeToEnglish();
		List<String> countries = subscribtionPage.getCountriesList();
		prop = currencyListFile.readPropertyFile("/src/main/java/Utilities/PremiumPlanPrices.Properties");
		for (int i = 0; i < countries.size(); i++) {
				subscribtionPage.selectCountry(countries.get(i));
				String expectedPrice = prop.getProperty(countries.get(i));
				String actualPremiumPrice = subscribtionPage.getPremiumPrice();
				Assert.assertTrue(actualPremiumPrice.contains(expectedPrice));
		}
	}

}
