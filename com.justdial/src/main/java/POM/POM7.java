package POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import Excelutils.excelUtils;

public class POM7 extends POM2{
	POM2 pom2 = new POM2();
	Properties prop = new Properties();
	excelUtils excel =new excelUtils();
	
	
	public void selectCity() throws IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] input  = excel.readExcel(0);
		WebElement City =pom2.findElement(prop.getProperty("city_xPath"));
		pom2.clearBox(City);
		pom2.sendText(City,input[0]);
		WebElement citydropdown=pom2.findElement("//a[@id="+"\'"+input[0]+"\'"+"]");
		pom2.clickElement(citydropdown);
		pom2.waitForPageLoaded();
	}
	public void clickAutocare() {
		WebElement autocare =pom2.findElement(prop.getProperty("autocare_xPath"));
		pom2.clickElement(autocare);
		pom2.waitForPageLoaded();
	}
	public void clickFreelist() {
		WebElement freelist=pom2.findElement(prop.getProperty("freelist_xPath"));
		pom2.clickElement(freelist);
		pom2.waitForPageLoaded();
	}
	public void clickFitness() {
		WebElement fitness=pom2.findElement(prop.getProperty("fitness_xPath"));
		pom2.clickElement(fitness);
		pom2.waitForPageLoaded();
	}
}
