package POM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Excelutils.excelUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class POM3 extends POM2{
	POM2 pom2 = new POM2();
	Properties prop = new Properties();
	excelUtils excel =new excelUtils();
	String[] input = null;
	public void selectLocation() throws InterruptedException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.input=excel.readExcel(0);
		pom2.removePopups();
		WebElement locationbutton =pom2.findElement(prop.getProperty("locationbutton_xPath"));
		pom2.clickElement(locationbutton);
		WebElement locationbox=pom2.findElement(prop.getProperty("locationbox_xPath"));
		pom2.clearBox(locationbox);
		pom2.sendText(locationbox,input[1]);
		WebElement locationdropdown=pom2.findElement(("//a[@id=\"undefined~undefined~0~~~undefined~~~~undefined~undefined~undefined~undefined~undefined~undefined~~~~~"+input[1]+"~~~~~~Car Washing Services~0\"]"));
		pom2.deletecookies();
		pom2.clickElement(locationdropdown);
		pom2.waitForPageLoaded();
	}
	public void clickPopularity() {
		WebElement popularity=pom2.findElement(prop.getProperty("popularity_xPath"));
		pom2.clickElement(popularity);
		pom2.waitForPageLoaded();
	}
	public void scrollDown() throws InterruptedException {
		pom2.removePopups();
		pom2.scrolldown();
		pom2.waitForPageLoaded();
		pom2.removePopups();
	}
	public void getCenterNames() throws IOException, TesseractException{
		LinkedList<String> list =new LinkedList<String>();
		List<WebElement> centers = driver.findElements(By.xpath(prop.getProperty("names_xpath")));
		List<WebElement> phonenumbers = driver.findElements(By.xpath(prop.getProperty("phonenumbers_xpath")));
		List<WebElement> ratings = driver.findElements(By.xpath(prop.getProperty("ratings_xpath")));
		WebDriverWait wait = new WebDriverWait(driver,10);
		List<WebElement> votes = driver.findElements(By.xpath(prop.getProperty("votes_xpath")));
		int[] rating = new int[ratings.size()];
		int[] vote = new int[votes.size()];
		ITesseract image = new Tesseract();
		image.setDatapath(".\\tessdata");
		for(int i=0;i<ratings.size();i++) {
			rating[i]=Integer.parseInt(ratings.get(i).getText().replaceAll(prop.getProperty("Specialformat"),"").trim());
			vote[i]=Integer.parseInt(votes.get(i).getText().replaceAll("Votes","").trim());
			if((rating[i]>40)&&(vote[i]>20))
			{
				wait.until(ExpectedConditions.visibilityOf(phonenumbers.get(i)));
				File scrFile = ((TakesScreenshot) phonenumbers.get(i)).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(".\\phonenumberScreenshots\\phonenumber"+i+".png"));
				String phonenumber=image.doOCR(new File(".\\phonenumberScreenshots\\phonenumber"+i+".png")).trim();
				list.add(centers.get(i).getText().trim()+"  "+phonenumber);
				new File(".\\phonenumberScreenshots\\phonenumber"+i+".png").delete();
			}
			else {
				continue;
			}
		}
		excel.writeExcel(list);
	}
	public void clickJdicon() throws InterruptedException {
		WebElement homebutton1=pom2.findElement(prop.getProperty("homebutton1_xPath"));
		pom2.clickElement(homebutton1);
		pom2.waitForPageLoaded();
		Thread.sleep(2000);
	}
}
