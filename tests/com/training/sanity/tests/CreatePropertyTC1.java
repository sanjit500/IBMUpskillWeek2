package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CreatePropertyTC1POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: To verify whether application allows admin to create property details based by adding feature & regions*/


public class CreatePropertyTC1 
{

	private WebDriver driver;
	private String baseUrl;
	private CreatePropertyTC1POM CreatePropertyTC1POM;
	private static Properties properties;
	private ScreenShot screenShot;


	@BeforeClass
	public static void setUpBeforeClass() throws IOException 
	{
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception 
	{
		//Before method executes the basic operations like opening Link & Logging in..
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		CreatePropertyTC1POM = new CreatePropertyTC1POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		CreatePropertyTC1POM.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		CreatePropertyTC1POM.sendUserName("admin");
		CreatePropertyTC1POM.sendPassword("admin@123");
		CreatePropertyTC1POM.clickLoginBtn();
	}
	
	@AfterMethod
	public void tearDown() throws Exception 
	{
		Thread.sleep(1000);
		driver.quit();
		
	}
	@Test
	public void ClicknAddNewProperty() throws InterruptedException 
	{
		//1. Click on Properties tab
		CreatePropertyTC1POM.clickPropertiesBtn(); 
		screenShot.captureScreenShot("PropertiesPageScreenshot");
		Thread.sleep(3000);
		
		//2. Click on Add New button
		CreatePropertyTC1POM.clickAddNewBtn();
		
		//Properties page should be displayed
		String PageTitle = driver.getTitle();
		System.out.println("Properties page is being displayed, Page Title: " +PageTitle);
		
		//3. Enter valid credentials in Enter Title Here textbox
		CreatePropertyTC1POM.enterTitle("a");
		
		//Capture Entered data in Name Title textbox & should get displayed
		WebElement text = driver.findElement(By.xpath("//*[@id=\"title\"]"));
	    String EnteredTitle = text.getAttribute("value");
		System.out.println("Entered data in Title is: " +EnteredTitle); 
		
		//4. Enter valid credentials in textbox
		CreatePropertyTC1POM.enterTextBox("aa");
		
		//Capture Entered data in textbox & should get displayed
		WebElement text1 = driver.findElement(By.id("content"));
	    String EnteredTextBox = text1.getAttribute("value");
		System.out.println("Entered data in textbox is: " +EnteredTextBox); 
		
		//5. Click on checkbox beside added Feature of Features section
		CreatePropertyTC1POM.clickFeatureBox();
		
		//6. Click on checkbox beside added Region of Regions section
		CreatePropertyTC1POM.clickRegionBox();
		screenShot.captureScreenShot("CheckBox Selected Evidence");
		Thread.sleep(3000);
		
		//7. Click on Publish button
		CreatePropertyTC1POM.clickPublishBtn();
		
		//String DisplMsg(Post Publish) is being Asserted at the end.
		String DisplMsg = ("Post published. View post"); 
		
		//To capture Post Publish message
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		
		//Post published. View post message should get displayed
		System.out.println("Message appears after Property Published: " +PublishMsg); 
		
		
		assertEquals(PublishMsg, DisplMsg);
		
	}
}


