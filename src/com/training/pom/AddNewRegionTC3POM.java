
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewRegionTC3POM 
{
	private WebDriver driver;
	
	public AddNewRegionTC3POM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//*[@id=\"responsive\"]/li[8]/a")
	private WebElement signin;
		
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[@id=\"menu-posts-property\"]/a/div[3]")
	private WebElement properties;
	
	@FindBy(xpath="//*[@id=\"wpbody-content\"]/div[3]/a")
	private WebElement clickAddNewBtn;
	
	@FindBy(id="region-add-toggle")
	private WebElement clickAddNewRgn;
	
	@FindBy(id="newregion")
	private WebElement EnterRegion;
	
	@FindBy(id="newregion_parent")
	private WebElement ClickParentRegion;
	
	@FindBy(id="region-add-submit")
	private WebElement AddNwRegion;
	
	@FindBy(xpath="//*[@id=\"title\"]")
	private WebElement EnterTitle;
		
	@FindBy(xpath="//*[@id=\"content\"]")
	private WebElement EnterTextBox;
	
	@FindBy(id="region-534")
	private WebElement ClickCheckBox1;
	
	@FindBy(id="publish")
	private WebElement publishBtn;
		
	public void clickSignInBtn() {
		this.signin.click(); 
	}
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	public void clickPropertiesBtn() {
		this.properties.click(); 
	}
	public void clickAddNewBtn() {
		this.clickAddNewBtn.click(); 
	}
	public void clickRegionLink() {
		this.clickAddNewRgn.click();
	}
	public void enterRegion(String featureEnter) {
		this.EnterRegion.sendKeys(featureEnter); 
	}
	public void clickParentRegion() {
		this.ClickParentRegion.click();
	}
	public void clickAddNewRegion() {
		this.AddNwRegion.click();
	}
	public void enterTitle(String titleEnter) {
		this.EnterTitle.sendKeys(titleEnter); 
	}
	public void enterTextBox(String contentEnter) {
		this.EnterTextBox.sendKeys(contentEnter); 
	}
	public void clickRegionBox() {
		this.ClickCheckBox1.click();
	}
	public void clickPublishBtn() {
		this.publishBtn.click();
	}	
	
}