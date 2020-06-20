package POM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;

public class POM4 extends POM2{
	POM2 pom2 = new POM2();
	Properties prop = new Properties();
	public void clickGym() throws FileNotFoundException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		WebElement gym=pom2.findElement(prop.getProperty("gym_xPath"));
		pom2.clickElement(gym);
		pom2.waitForPageLoaded();
	}
}
