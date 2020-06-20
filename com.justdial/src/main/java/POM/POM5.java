package POM;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import Excelutils.excelUtils;

public class POM5 {
	POM2 pom2 = new POM2();
	Properties prop = new Properties();
	excelUtils excel =new excelUtils();
	public void enterDetails() throws IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] input=excel.readExcel(1);
		WebElement freelistcompanyname=pom2.findElement(prop.getProperty("freelistcompanyname_xPath"));
		pom2.clearBox(freelistcompanyname);
		pom2.sendText(freelistcompanyname,input[0]);
		WebElement freelisttitle=pom2.findElement(prop.getProperty("freelisttitle_xPath"));
		pom2.clickElement(freelisttitle);
		WebElement freelisttitledropdown=pom2.findElement(prop.getProperty("freelisttitledropdown_xPath"));
		pom2.clickElement(freelisttitledropdown);
		WebElement freelistfirstname=pom2.findElement(prop.getProperty("freelistfirstname_xPath"));
		pom2.clearBox(freelistfirstname);
		pom2.sendText(freelistfirstname,input[1]);
		WebElement freelistlastname=pom2.findElement(prop.getProperty("freelistlastname_xPath"));
		pom2.clearBox(freelistlastname);
		pom2.sendText(freelistlastname,input[2]);
		WebElement freelistphonenumber=pom2.findElement(prop.getProperty("freelistphonenumber_xPath"));
		pom2.clearBox(freelistphonenumber);
		pom2.sendText(freelistphonenumber,input[3]);
		WebElement freelistlandline=pom2.findElement(prop.getProperty("freelistlandline_xPath"));
		pom2.clearBox(freelistlandline);
		pom2.sendText(freelistlandline,input[4].replaceAll(".0", ""));
	}
	public void clickSubmit() {
		WebElement freelistsubmitbutton=pom2.findElement(prop.getProperty("freelistsubmitbutton_xPath"));
		pom2.clickElement(freelistsubmitbutton);
		pom2.waitForPageLoaded();
	}
	public void getErrormessage() throws IOException {
		WebElement freelistphonenumbererrormessage=pom2.findElement(prop.getProperty("freelistphonenumbererrormessage_xPath"));
		String errormessage=pom2.getElementText(freelistphonenumbererrormessage);
		excel.writeExcel(errormessage);

	}
	public void clickJdicon() {
		WebElement homebutton=pom2.findElement(prop.getProperty("homebutton_xPath"));
		pom2.clickElement(homebutton);
		pom2.waitForPageLoaded();
	}
}
