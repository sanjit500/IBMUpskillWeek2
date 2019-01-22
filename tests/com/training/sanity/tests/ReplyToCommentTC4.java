package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.pom.ReplyToCommentPOMTC4;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: To verify whether application allows admin to reply for the comment added by user*/
public class ReplyToCommentTC4 
{

	private WebDriver driver;
	private String baseUrl;
	private ReplyToCommentPOMTC4 ReplyToCommentPOMTC4;
	private static Properties properties;


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
		//Before method executes the basic operations like opening required Link 
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		ReplyToCommentPOMTC4 = new ReplyToCommentPOMTC4(driver); 
		baseUrl = properties.getProperty("baseURL");
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menu-item-617\"]/a")));

	}
	
	@AfterMethod
	public void tearDown() throws Exception 
	{
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test (priority = 1)
	public void PostComment() throws InterruptedException 
	{
		//1. Click on Blog Tab
		ReplyToCommentPOMTC4.clickBlogBtn();
		
		//2. Click on Read More link of post added by admin
		driver.findElement(By.linkText("Read More")).click();
		
		//3. Enter valid details in Comment textbox
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comment")));
		
		//4. Enter Details and Click on Post Comment button
		ReplyToCommentPOMTC4.enterComment("good8");
		ReplyToCommentPOMTC4.enterName("Sanjit");
		ReplyToCommentPOMTC4.enterEmail("sanjit@ibm.com");
		ReplyToCommentPOMTC4.clickPostComment();
		Thread.sleep(5000);
		
		//Comment should get added and displayed on the screen
		if(driver.getPageSource().contains("good8"))
		{
		    System.out.println("Comment has been added and being displayed successfully on screen: ");
		}

		else
		{
		    System.out.println("Data issue, Please try with some other data else raise a defect");
		}
		
	}
	
	
	@Test (priority = 2)
	public void ReplytoComment() throws InterruptedException 
	{
		//5. Open admin site in new window
		ReplyToCommentPOMTC4.clickSignInBtn(); 
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		
		//6. Enter valid credential in Username textbox
		ReplyToCommentPOMTC4.sendUserName("admin");
		
		//7. Enter valid credential in Password textbox
		ReplyToCommentPOMTC4.sendPassword("admin@123");
		
		//8. Click on Sign in button
		ReplyToCommentPOMTC4.clickLoginBtn();
		
		//9. click on Comments tab
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-comments")));
		ReplyToCommentPOMTC4.clickCommentsbtnt();
		
		//comments added and numbers In Response icon of comment for the post should get displayed
		WebElement comtext = driver.findElement(By.xpath("//tbody/tr/td[3]/div/span/a/span[1]"));
		int comtext1 = Integer.parseInt(comtext.getText());
		System.out.println("Before Number in Response icon of comment is  :    " +comtext1);
		
		//10. mouse hover the comment
		WebElement celltext = driver.findElement(By.xpath("//tbody/tr/td"));
	    Actions actions1 = new Actions(driver);
		actions1.moveToElement(celltext).build().perform();
		
		//11. Click on Reply icon
		ReplyToCommentPOMTC4.clickreplyBtn();
		
		//12. enter valid details in comments textbox
		ReplyToCommentPOMTC4.replyComment("Comment added by admin1");
		
		//13. Click on Reply button
		ReplyToCommentPOMTC4.clickFinalReplyBtn();
		Thread.sleep(2000);
		
		//Refresh the page to get the updated value/number in Response icon of comment
		driver.get("http://realestate.hommelle.com/wp-admin/edit-comments.php");
 		driver.findElement(By.id("wpbody-content")).sendKeys(Keys.F5);
		Thread.sleep(2000);
		
		//Numbers In Response icon of comment for the post should get displayed and asserted as well
		WebElement comtext2 = driver.findElement(By.xpath("//tbody/tr/td[3]/div/span/a/span[1]"));
		int comtext3 = Integer.parseInt(comtext2.getText());
		System.out.println("After Number in Response icon of comment is  :    " +comtext3);
		
		//The value gets increased by 1 every time we add a comment, so the value has been decreased by 1 during assertion.
		assertEquals(comtext1, --comtext3);
	}

}

