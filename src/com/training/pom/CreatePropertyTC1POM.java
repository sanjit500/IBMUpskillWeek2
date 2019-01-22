
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePropertyTC1POM 
{
	private WebDriver driver;
	
	public CreatePropertyTC1POM(WebDriver driver) 
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
	
	@FindBy(xpath="//*[@id=\"title\"]")
	private WebElement EnterTitle;
		
	@FindBy(xpath="//*[@id=\"content\"]")
	private WebElement EnterTextBox;
	
	@FindBy(xpath="//*[@id=\"in-property_feature-422\"]")
	private WebElement ClickCheckBox1;
	
	@FindBy(xpath="//*[@id=\"in-region-39\"]")
	private WebElement ClickCheckBox2;
	
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
	public void enterTitle(String titleEnter) {
		this.EnterTitle.sendKeys(titleEnter); 
	}
	public void enterTextBox(String contentEnter) {
		this.EnterTextBox.sendKeys(contentEnter); 
	}
	public void clickFeatureBox() {
		this.ClickCheckBox1.click();
	}
	public void clickRegionBox() {
		this.ClickCheckBox2.click(); 
	}
	public void clickPublishBtn() {
		this.publishBtn.click();
	}	
	
}