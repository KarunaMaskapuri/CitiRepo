package userTests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;


public class BaseTestJunit {
	static ExtentReports extentReport =null;
	ExtentTest extentTest;
	WebDriver driver;
	@Before
	public void launchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@After
	public void quitBrowser()
	{
		driver.quit();
	}
	@BeforeClass
	public static void createReports()
	{
		String reportName="./reports/ExtentReport"+System.currentTimeMillis()+".html";
		 extentReport = new ExtentReports(reportName);
		
		

	}
	@AfterClass
	public static void flushReports()
	{
		extentReport.flush();
	}
	
	public void assertIntValues(int actual,int expected,ExtentTest test){
		
	}
	
	public void assertTrue(boolean result,ExtentTest test){
		if(!result)
			
				test.log(LogStatus.FAIL,test.addScreenCapture(getScreenshotPath()));
			
			Assert.assertTrue(result);
	}
	public void assertFalse(boolean result,ExtentTest test){
		if(result)
			test.log(LogStatus.FAIL,test.addScreenCapture(getScreenshotPath()));
			Assert.assertTrue(result);
	}
	public String getScreenshotPath(){
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path="./screenshots/"+System.currentTimeMillis()+".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
