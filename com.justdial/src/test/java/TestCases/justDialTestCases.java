package TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import DriverSetup.DriverSetUp;
import POM.POM1;
import POM.POM2;
import POM.POM3;
import POM.POM4;
import POM.POM5;
import POM.POM6;
import POM.POM7;
import net.sourceforge.tess4j.TesseractException;
public class justDialTestCases extends POM2
{
	Properties prop = new Properties();
	DriverSetUp driversetup = new DriverSetUp();
	POM2 pom2 = new POM2();
	POM7 pom7 = new POM7();
	POM1 pom1 = new POM1();
	POM3 pom3= new POM3();
	POM5 pom5 = new POM5();
	POM4 pom4 = new POM4();
	POM6 pom6 = new POM6();
	ExtentReports report;
	ExtentTest test;
	
	@BeforeSuite
	public void loadfiles() throws FileNotFoundException {
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\TestCaseResources\\justDialTestCase.properties");
		try 
		{
			prop.load(file);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(".\\reports\\ExtentReport.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
	}
	
	@AfterSuite
	public void flushReport() {
		report.flush();
	}
	
	@BeforeMethod
	@Parameters({"browser"})
	public void launchBrowserUrl(String browser) throws IOException
	{
	    driversetup.openBrowser(browser);
	    pom2.navigatetoUrl();
	    pom2.waitForPageLoaded();
	    pom7.selectCity();
	}

	
	@AfterMethod 
	public void closeBrowser() {
		  pom2.quitBrowser(); 
	}
	 
	
	@Test(priority=0,enabled=true)
	public void carWashTestCase() throws InterruptedException, IOException, TesseractException 
	{
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitleone"));
		pom7.clickAutocare();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitletwo"));
        pom1.clickCarwash();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitlethree"));
		pom3.selectLocation();
		pom3.clickPopularity();
		pom3.scrollDown();
		pom3.getCenterNames();
		pom3.clickJdicon();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitleone"));
		test=report.createTest("Test Name : carWashTestCase");
        if(driver.getTitle().equals(prop.getProperty("expectedTitleone"))){
        	test.log(Status.PASS,"Testcase -> carWashTestCase is passed.");
        }
        else {
        	test.log(Status.FAIL,"Testcase -> carWashTestCase is failed.");
        }
	}
	@Test(priority=1,enabled=true)
	public void freelistTestCase() throws IOException {
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitleone"));
		pom7.clickFreelist();
		pom5.enterDetails();
		pom5.clickSubmit();
		pom5.getErrormessage();
		pom5.clickJdicon();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitleone"));
		test=report.createTest("Test Name : freelistTestCase");
		if(driver.getTitle().equals(prop.getProperty("expectedTitleone"))){
        	test.log(Status.PASS,"Testcase -> freelistTestCase is passed.");
        }
        else {
        	test.log(Status.FAIL,"Testcase -> freelistTestCase is failed.");
        }
	}
	@Test(priority=2,enabled=true)
	public void gymTestCase() throws IOException {
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitleone"));
		pom7.clickFitness();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitlefive"));
		pom4.clickGym();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitlesix"));
		pom6.getGymsubmenu();
		pom6.clickJdicon();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("expectedTitleone"));
		test=report.createTest("Test Name : gymTestCase");
		if(driver.getTitle().equals(prop.getProperty("expectedTitleone"))){
        	test.log(Status.PASS,"Testcase -> gymTestCase is passed.");
        }
        else {
        	test.log(Status.FAIL,"Testcase -> gymTestCase is failed.");
        }
	}
}