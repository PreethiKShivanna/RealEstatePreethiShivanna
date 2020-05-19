package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RealEstateLoginPOM {
	WebDriver driver;

	public RealEstateLoginPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@class='avatar avatar-26 photo']")
	public WebElement LoggedInUser;

	@FindBy(xpath = "//a[contains(@class,'ab-item')][contains(text(),'Log Out')]")
	private WebElement Logout;

	@FindBy(xpath = "//a[contains(text(),'log out')]")
	private WebElement LogOutPromt;

	@FindBy(xpath = "//a[@class='sign-in']")
	private WebElement LogInRegisterLink;

	@FindBy(xpath = "//input[@id='user_login']")
	private WebElement UserName;

	@FindBy(xpath = "//input[@id='user_pass']")
	private WebElement Password;

	@FindBy(xpath = "//input[@name='login']")
	private WebElement SignInBtn;

	@FindBy(xpath = "//a[contains(text(),'Lost Your Password?')]")
	private WebElement ForgotPassword;

	@FindBy(xpath = "//div[@class='post-content']//h3[contains(text(),'Brigade Flats')]")
	public WebElement AddedPost;

	@FindBy(xpath = "//li[@id='menu-item-617']//a[contains(text(),'Blog')]")
	public WebElement Blog;

	@FindBy(xpath = "(//*[contains(text(),'Read More')])[1]")
	public WebElement ReadMoreLink;

	@FindBy(xpath = "//textarea[@id='comment']")
	public WebElement Commentbox;

	@FindBy(id = "author")
	public WebElement Name;

	@FindBy(id = "email")
	public WebElement email;

	@FindBy(xpath = "//input[@id='submit']")
	public WebElement PostCommentBtn;

	@FindBy(xpath = "//a[contains(text(),'Real Estate')]")
	private WebElement RealEstateIcon;

	@FindBy(xpath = "//input[@placeholder='Search here for Properties..']")
	private WebElement PropertySearchBox;

	@FindBy(xpath = "//div[@class='promagnifier']//div[@class='innericon']//*[local-name()='svg']")
	private WebElement PropertySearchIcon;

	@FindBy(xpath = "//h3/a[@class='asl_res_url'][contains(text(),'Poorvankara Builders')]")
	public WebElement SearchedProperty;

	@FindBy(xpath = "//a[contains(text(),'New Launch')]")
	public WebElement NewLaunch;

	@FindBy(xpath = "//div[@class='listing-item compact'][@data-title='Donec quis']")
	public WebElement Donecquis;

	@FindBy(xpath = "//input[@name='your-name']")
	public WebElement YourName;

	@FindBy(xpath = "//input[@name='your-email']")
	public WebElement YourEmail;
	
	@FindBy(xpath = "//input[@aria-invalid='true'][@name='your-email']")
	public WebElement InvalidYourEmail;

	@FindBy(xpath = "//input[@name='your-subject']")
	public WebElement Subject;

	@FindBy(xpath = "//textarea[@name='your-message']")
	public WebElement YourMessage;

	@FindBy(xpath = "//input[@type='submit' and @value='Send']")
	public WebElement SendBtn;

	@FindBy(xpath = "//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng']")
	public WebElement ErrorMsg;

	public void clickonLoginRegisterLink() {
		LogInRegisterLink.click();
	}

	public void enterUserName(String sUserName) {
		this.UserName.clear();
		this.UserName.sendKeys(sUserName);

	}

	public void enterPassword(String sPassword) {
		this.Password.clear();
		this.Password.sendKeys(sPassword);
	}

	public void ClickSignInBtn() {
		SignInBtn.click();
	}

	public void ClickBlogTab() {
		Blog.click();
	}

	public void ClickOnReadMoreLink() {
		ReadMoreLink.click();
	}

	public void EnterInComments(String sComments) {
		Commentbox.sendKeys(sComments);
	}

	public void EnterInName(String sName) {
		Name.sendKeys(sName);
	}

	public void EnterInemail(String semail) {
		email.sendKeys(semail);
	}

	public void ClickonPostComment() {
		PostCommentBtn.click();
	}

	public void LogOut() {
		Logout.click();
	}

	public void ClickOnLogOutPrompt() {
		LogOutPromt.click();
	}

	public void ClickOnRealEstateIcon() {
		RealEstateIcon.click();
	}

	public void searchProperty(String sProperty) {
		PropertySearchBox.click();
		PropertySearchBox.sendKeys(sProperty);
		PropertySearchIcon.click();
	}

	public void ClickOnNewLaunchtab() {
		NewLaunch.click();
	}

	public void clickOnDonecquisApartments() {
		Donecquis.click();
	}

	public void enterYourName(String sUserName) {
		YourName.sendKeys(sUserName);
	}

	public void enterYourEmail(String sEmailID) {
		YourEmail.sendKeys(sEmailID);
	}

	public void enterYourSubject(String sSubject) {
		Subject.sendKeys(sSubject);
	}

	public void enterYourMessage(String sMessage) {
		YourMessage.sendKeys(sMessage);
	}

	public void ClickOnSendBtn() {
		SendBtn.click();
	}

	public void GetMsg() {
		ErrorMsg.getText();
	}
}
