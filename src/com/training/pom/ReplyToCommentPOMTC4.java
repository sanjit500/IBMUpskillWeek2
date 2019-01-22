
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReplyToCommentPOMTC4 
{
	private WebDriver driver;
	
	public ReplyToCommentPOMTC4(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//*[@id=\"menu-item-617\"]/a")
	private WebElement blog;
	
	@FindBy(id="comment")
	private WebElement EnterComment;
		
	@FindBy(id="submit")
	private WebElement ClickSubmit;
	
	@FindBy(id="author")
	private WebElement EnterName;
	
	@FindBy(id="email")
	private WebElement EnterEmail;
	
	@FindBy(xpath="//*[@id=\"responsive\"]/li[8]/a")
	private WebElement signin;
		
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn;
	
	@FindBy(xpath="//*[@id=\"menu-comments\"]/a/div[3]")
	private WebElement Clickcomments;
	
	@FindBy(linkText="Reply")
	private WebElement Reply;
	
	@FindBy(id="replycontent")
	private WebElement ReplyComment;
	
	@FindBy(id="replybtn")
	private WebElement ClickFinalBtn;
	
	public void clickBlogBtn() {
		this.blog.click(); 
	}
	public void enterComment(String featureEnter) {
		this.EnterComment.sendKeys(featureEnter); 
	}
	public void enterName(String featureEnter) {
		this.EnterName.sendKeys(featureEnter); 
	}
	public void enterEmail(String featureEnter) {
		this.EnterEmail.sendKeys(featureEnter); 
	}
	public void clickPostComment() {
		this.ClickSubmit.click();
	}
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
	public void clickCommentsbtnt() {
		this.Clickcomments.click();
	}
	public void clickreplyBtn() {
		this.Reply.click(); 
	}
	public void replyComment(String featureEnter) {
		this.ReplyComment.sendKeys(featureEnter); 
	}
	public void clickFinalReplyBtn() {
		this.ClickFinalBtn.click(); 
	}
	
}