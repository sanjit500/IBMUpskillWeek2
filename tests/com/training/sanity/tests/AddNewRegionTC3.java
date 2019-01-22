package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddNewRegionTC3POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: To verify whether application allows admin to add new Region while adding new property*/
public class AddNewRegionTC3 
{

	private WebDriver driver;
	private String baseUrl;
	private AddNewRegionTC3POM AddNewRegionTC3POM;
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
		AddNewRegionTC3POM = new AddNewRegionTC3POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		AddNewRegionTC3POM.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		AddNewRegionTC3POM.sendUserName("admin");
		AddNewRegionTC3POM.sendPassword("admin@123");
		AddNewRegionTC3POM.clickLoginBtn();
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
		AddNewRegionTC3POM.clickPropertiesBtn(); 
		screenShot.captureScreenShot("PropertiesPageScreenshot");
		Thread.sleep(3000);
		
		//2. Click on Add New button
		AddNewRegionTC3POM.clickAddNewBtn();
		
		//Properties page should be displayed
		String PageTitle = driver.getTitle();
		System.out.println("Properties page is being displayed, Page Title: " +PageTitle);
		
		//3. Click on Add new Region link in Feature section
		AddNewRegionTC3POM.clickRegionLink();
		
		//4. Enter valid details in Textbox & capture the same
		AddNewRegionTC3POM.enterRegion("Electronic City");
		WebElement Regiontext = driver.findElement(By.id("newregion"));
	    String EnteredRegion = Regiontext.getAttribute("value");
	    System.out.println("Entered data in Region Text is: " +EnteredRegion);
		Thread.sleep(1000);
		
		//5. Click & Select valid details in Parent Region list box
		AddNewRegionTC3POM.clickParentRegion();
		Select  dropdown = new Select(driver.findElement(By.id("newregion_parent")));
		dropdown.selectByVisibleText("   West Bangalore");
		Thread.sleep(3000);
		
			      //selected value should get displayed in Parent Region list box
				  Select sel = new Select(driver.findElement(By.id("newregion_parent")));
				  WebElement strCurrentValue = sel.getFirstSelectedOption();
				  String expectedval = ("   West Bangalore"); 
				  String actualval = strCurrentValue.getText();

				  if (actualval.equals(expectedval))
		            {
		     		System.out.println("Entered data in Parent Region Text is: " +actualval);
		            }
		            else 
		            {
		         	System.out.println("Selected Value is mismatching:  " +actualval);
		            }
		
		//6. Click on Add New Region button
		AddNewRegionTC3POM.clickAddNewRegion();

		//7. Click on Refresh button from keyboard
		driver.get("http://realestate.hommelle.com/wp-admin/post-new.php?post_type=property");
 		driver.findElement(By.name("publish")).sendKeys(Keys.F5);
		Thread.sleep(4000);
		
		//After the Page has been refreshed above & added region checkbox should get displayed
		String selectbox = driver.findElement(By.xpath("//*[@id=\"region-534\"]")).getText();
		System.out.println("Newly Added Region Checkbox is: " +selectbox);
		Thread.sleep(4000);
		
		//8. Enter valid credentials in Enter Title Here textbox
		AddNewRegionTC3POM.enterTitle("prestige");
		
		//Entered data in Name Title textbox should get displayed
		WebElement text = driver.findElement(By.xpath("//*[@id=\"title\"]"));
	    String EnteredTitle = text.getAttribute("value");
		System.out.println("Entered data in Title is: " +EnteredTitle); 
		
		//9. Enter valid credentials in textbox
		AddNewRegionTC3POM.enterTextBox("home town");
		
		//Entered data in textbox should get displayed
		WebElement text1 = driver.findElement(By.id("content"));
	    String EnteredTextBox = text1.getAttribute("value");
		System.out.println("Entered data in textbox is: " +EnteredTextBox); 
		

		//10. Click on checkbox beside newly created region
		AddNewRegionTC3POM.clickRegionBox();
		Thread.sleep(3000);
		screenShot.captureScreenShot("CheckBox Selected Evidence");
		Thread.sleep(3000);
		
		//11. Click on Publish button
		AddNewRegionTC3POM.clickPublishBtn();
		
		//String DisplMsg is being Asserted at the end.
		String DisplMsg = ("Post published. View post"); 
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		
		//Post published. View post message should get displayed
		String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		System.out.println("Message appears after Property Published: " +PublishMsg); 
		assertEquals(PublishMsg, DisplMsg);
		
	}
}

