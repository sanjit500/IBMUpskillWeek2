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
import com.training.pom.AddNewFeatureTC2POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: To verify whether application allows admin to add new Feature while adding new property*/


public class AddNewFeatureTC2 
{

	private WebDriver driver;
	private String baseUrl;
	private AddNewFeatureTC2POM AddNewFeatureTC2POM;
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
		AddNewFeatureTC2POM = new AddNewFeatureTC2POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		AddNewFeatureTC2POM.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		AddNewFeatureTC2POM.sendUserName("admin");
		AddNewFeatureTC2POM.sendPassword("admin@123");
		AddNewFeatureTC2POM.clickLoginBtn();
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
		AddNewFeatureTC2POM.clickPropertiesBtn(); 
		screenShot.captureScreenShot("PropertiesPageScreenshot");
		Thread.sleep(3000);
		
		//2. Click on Add New button
		AddNewFeatureTC2POM.clickAddNewBtn();
		
		//Test Case: Properties page should be displayed
		String PageTitle = driver.getTitle();
		System.out.println("Properties page is being displayed, Page Title: " +PageTitle);
		
		//3. Click on Add new Feature link in Feature section
		AddNewFeatureTC2POM.clickFeatureLink();
		
		//4. Enter valid details in Textbox & capture the entered data
		AddNewFeatureTC2POM.enterFeature("Superb");
		WebElement Featuretext = driver.findElement(By.id("newproperty_feature"));
	    String EnteredFeature = Featuretext.getAttribute("value");
	    System.out.println("Entered data in Feature Text is: " +EnteredFeature);
		Thread.sleep(1000);
		
		//5. Select valid details in Parent Feature list box
		AddNewFeatureTC2POM.clickParentFeature();
		Select  dropdown = new Select(driver.findElement(By.id("newproperty_feature_parent")));
		dropdown.selectByVisibleText("interior");
		Thread.sleep(3000);
		
				  //selected value should get displayed in Parent Feature list box
				  Select sel = new Select(driver.findElement(By.id("newproperty_feature_parent")));
				  WebElement strCurrentValue = sel.getFirstSelectedOption();
				  String expectedval = ("interior"); 
				  String actualval = strCurrentValue.getText();

				  if (actualval.equals(expectedval))
		            {
		     		System.out.println("Entered data in Parent Feature Text is: " +actualval);
		            }
		            else 
		            {
		         	System.out.println("Selected Value is mismatching:  " +actualval);
		            }
		
		//6. Click on Add New Feature button and Submit the details
		AddNewFeatureTC2POM.clickAddNewFeature();

		//7. Click on Refresh button from keyboard
		driver.get("http://realestate.hommelle.com/wp-admin/post-new.php?post_type=property");
 		driver.findElement(By.name("publish")).sendKeys(Keys.F5);
		Thread.sleep(4000);
		
		//After the Page has been refreshed above & added Feature checkbox should get displayed
		String selectbox = driver.findElement(By.xpath("//*[@id=\"property_feature-527\"]")).getText();
		System.out.println("Newly Added Feature Checkbox is: " +selectbox);
		Thread.sleep(4000);
		
		
		//8. Enter valid credentials in Enter Title Here textbox
		AddNewFeatureTC2POM.enterTitle("prestige");
		
		//Entered data in Name Title textbox should get displayed
		WebElement text = driver.findElement(By.xpath("//*[@id=\"title\"]"));
	    String EnteredTitle = text.getAttribute("value");
		System.out.println("Entered data in Title is: " +EnteredTitle); 
		
		//9. Enter valid credentials in textbox
		AddNewFeatureTC2POM.enterTextBox("home town");
		
		//Entered data in textbox should get displayed
		WebElement text1 = driver.findElement(By.id("content"));
	    String EnteredTextBox = text1.getAttribute("value");
		System.out.println("Entered data in textbox is: " +EnteredTextBox); 
		
		//10. Click on checkbox beside newly created feature
		AddNewFeatureTC2POM.clickFeatureBox();
		Thread.sleep(3000);
		screenShot.captureScreenShot("CheckBox Selected Evidence");
		Thread.sleep(3000);
		
		//11. Click on Publish button
		AddNewFeatureTC2POM.clickPublishBtn();
		//String DisplMsg is being Asserted at the end.
		String DisplMsg = ("Post published. View post"); 
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		
		//Post published. View post message should get displayed and asserted
		String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		System.out.println("Message appears after Property Published: " +PublishMsg); 
		assertEquals(PublishMsg, DisplMsg);
		
	}
}

