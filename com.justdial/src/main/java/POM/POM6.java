package POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Excelutils.excelUtils;

public class POM6 extends POM2{
	POM2 pom2 = new POM2();
	Properties prop = new Properties();
	excelUtils excel =new excelUtils();
	public void getGymsubmenu() throws IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LinkedList<String> list =new LinkedList<String>();
		List<WebElement> gymsubtitles = driver.findElements(By.xpath(prop.getProperty("gymsubtitles_xPath")));
		for(int i=0;i<gymsubtitles.size();i++) {
			list.add(gymsubtitles.get(i).getText());
		}
		excel.gymExcel(list);
	}
	public void clickJdicon() {
		WebElement homebutton1=pom2.findElement(prop.getProperty("homebutton1_xPath"));
		pom2.clickElement(homebutton1);
		pom2.waitForPageLoaded();
	}
}
