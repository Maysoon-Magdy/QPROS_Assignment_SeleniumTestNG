package TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import driver.BrowserType;
import driver.driverAction;


public class TestBase {
	
	public static Properties config = new Properties();
	public static FileInputStream fis;

	@BeforeMethod
	public void setup() throws IOException {
		if (driverAction.driver == null) {
		   fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Config.Properties");
			config.load(fis);
			if (config.getProperty("browser").contains("chrome")){
				driverAction.initBrowser(BrowserType.chrome);
			} else if(config.getProperty("browser").contains("fireFox")){
				driverAction.initBrowser(BrowserType.fireFox);
			}
		}
		driverAction.driver.get(config.getProperty("URL"));
		driverAction.	driver.manage().window().maximize();
	}

	@AfterTest
	public void tearDown() {
		driverAction.driver.quit();
	}

}