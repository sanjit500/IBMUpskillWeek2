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
import com.training.pom.PropertyIntoTrashPOMTC5;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: To verify whether application allows admin to add property details into trash & display the same*/
public class PropertyIntoTrashTC5 
{

	private WebDriver driver;
	private String baseUrl;
	private PropertyIntoTrashPOMTC5 PropertyIntoTrashPOMTC5;
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
		PropertyIntoTrashPOMTC5 = new PropertyIntoTrashPOMTC5(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		PropertyIntoTrashPOMTC5.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		PropertyIntoTrashPOMTC5.sendUserName("admin");
		PropertyIntoTrashPOMTC5.sendPassword("admin@123");
		PropertyIntoTrashPOMTC5.clickLoginBtn();
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
		PropertyIntoTrashPOMTC5.clickPropertiesBtn(); 
		Thread.sleep(3000);
		
		//2. Click on Add New button
		PropertyIntoTrashPOMTC5.clickAddNewBtn();
		
		//Properties page should be displayed
		String PageTitle = driver.getTitle();
		System.out.println("Properties page is being displayed, Page Title: " +PageTitle);
		Thread.sleep(3000);
		
		//3. Enter valid credentials in Enter Title Here textbox
		PropertyIntoTrashPOMTC5.enterTitle("prestige");
		
		//Entered data in Name Title textbox should get displayed
		WebElement text = driver.findElement(By.xpath("//*[@id=\"title\"]"));
	    String EnteredTitle = text.getAttribute("value");
		System.out.println("Entered data in Title is: " +EnteredTitle); 
		
		//4. Enter valid credentials in textbox
		PropertyIntoTrashPOMTC5.enterTextBox("home town");
		
		//Entered data in textbox should get displayed
		WebElement text1 = driver.findElement(By.id("content"));
	    String EnteredTextBox = text1.getAttribute("value");
		System.out.println("Entered data in textbox is: " +EnteredTextBox); 
		
		//5. Click on checkbox beside added Feature of Features section
		PropertyIntoTrashPOMTC5.clickFeatureBox();
		
		//6. Click on checkbox beside added Region of Regions section
		PropertyIntoTrashPOMTC5.clickRegionBox();
		Thread.sleep(3000);
		
		//7. Click on Move to Trash link
		PropertyIntoTrashPOMTC5.clickTrashBtn();
		Thread.sleep(2000);
		
		//8. Click on Leave button : "Changes you made may not be saved" message in pop up window should get displayed and click on Leave button
		driver.switchTo().alert().accept();
		
		//Wait until next page gets loaded.
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		
		//"1 post moved to the Trash. Undo message" should get displayed
		String TrashMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		System.out.println("Message displayed after Trash button was clicked: " +TrashMsg);
		
		//9. Click on Trash icon
		PropertyIntoTrashPOMTC5.clickTrashLink();
		
		//Added property details should get displayed, Below code finds the latest trash data from table and asserts it.
		WebElement Trashname = driver.findElement(By.xpath("//tbody/tr/td"));
		String Trashname1 = Trashname.getText();
		
		if (Trashname1.equals(EnteredTitle))
		{
			System.out.println("Property has been Trashed successfully:  " +Trashname1);
		}
		
		assertEquals(Trashname1, EnteredTitle);		
	}
}

