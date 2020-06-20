package POM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;

public class POM1 extends POM2{
	POM2 pom2 = new POM2();
	Properties prop = new Properties();
	public void clickCarwash() throws FileNotFoundException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pom2.deletecookies();
		WebElement carwash =pom2.findElement(prop.getProperty("carwash_xPath"));
		pom2.clickElement(carwash);
		pom2.waitForPageLoaded();
	}

}
