package DriverSetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Excelutils.excelUtils;

public class DriverSetUp extends excelUtils
{
	public static WebDriver driver;
	public WebDriver openBrowser(String browser) throws FileNotFoundException
	{
		Properties prop =new Properties();
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\POMResources\\POM.properties");
		try 
		{
			prop.load(file);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if(browser.equals("chrome")) 
		{
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			System.setProperty("webdriver.chrome.driver",prop.getProperty("chromeExe_path"));
			driver = new ChromeDriver(options);
		}
			else if(browser.equals("firefox"))
			{
				FirefoxOptions options = new FirefoxOptions();
				options.addPreference("geo.enabled", false);
				System.setProperty("webdriver.gecko.driver",prop.getProperty("firefoxExe_path")) ;
				driver = new FirefoxDriver(options);
			}
			else if(browser.equals("microsoftedge")){
				System.setProperty("webdriver.edge.driver",prop.getProperty("edgeExe_path"));
				driver = new EdgeDriver();
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
}
