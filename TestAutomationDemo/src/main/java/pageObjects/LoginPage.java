package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import utils.SeleniumMethods;

public class LoginPage extends SeleniumMethods{

	@FindBy(how=How.ID,using="user-name")
	WebElement username;
	@FindBy(how=How.ID,using="password")
	WebElement password;
	@FindBy(how=How.ID,using="login-button")
	WebElement login;
	@FindBy(how=How.XPATH,using="//h3[@data-test='error']")
	WebElement lockederroemessage;
	public LoginPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}


	public HomePage login(String unameValue,String pValue){
		username.clear();
		sendKeys(username, unameValue, "username");
		password.clear();
		sendKeys(password, pValue, "username");
		clickElement(login, "login");
		return new HomePage(driver,test);
	}
	public boolean verifyPageTitle()
	{
		return driver.getTitle().equals("Swag Labs");
	}
	
	public boolean verifyLockedUser(String unameValue,String pValue){
	
		username.clear();
		sendKeys(username, unameValue, "username");
		password.clear();
		sendKeys(password, pValue, "username");
		clickElement(login, "login");
		sleep();
		PageFactory.initElements(driver, this);
		System.out.println(lockederroemessage.getText());
		return lockederroemessage.getText().equals("Epic sadface: Sorry, this user has been locked out.");
	}
}
