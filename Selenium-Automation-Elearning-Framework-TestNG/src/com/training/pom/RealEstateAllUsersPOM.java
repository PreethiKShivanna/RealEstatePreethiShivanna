package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RealEstateAllUsersPOM {
	WebDriver driver;

	public RealEstateAllUsersPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//select[@id='new_role']")
	private WebElement ChangeroletoDropDwn;

	@FindBy(xpath = "//input[@id='changeit']")
	private WebElement ChangeRoleBtn;

	@FindBy(xpath = "//input[@id='user-search-input']")
	private WebElement UserSearchBox;

	@FindBy(xpath = "//input[@id='search-submit']")
	private WebElement UserSearchBtn;

	@FindBy(xpath = "//input[starts-with(@id,'user_')] [@type='checkbox']")
	private WebElement UserCheckBox;

	@FindBy(xpath = "//td[@class='role column-role']")
	public WebElement UserRole;

	@FindBy(xpath = "//p[contains(text(),'Changed roles.')]")
	public WebElement RoleChangeMsg;

	public void SearchUser(String sUserName) throws InterruptedException {
		UserSearchBox.click();
		UserSearchBox.clear();
		UserSearchBox.sendKeys(sUserName);
		Thread.sleep(1000);
		UserSearchBtn.click();
		Thread.sleep(6000);
	}

	public void SelectCheckBox() {
		UserCheckBox.click();
	}

	public void SelectChangeRoleToDropDown(String sRole) {
		Select slc = new Select(ChangeroletoDropDwn);
		List<WebElement> Roles = driver.findElements(By.xpath("//select[@id='new_role']"));
		System.out.println("Values in the Role drop down : ");
		for (WebElement DropDownValues : Roles) {
			System.out.println(DropDownValues.getText());

		}
		slc.selectByVisibleText(sRole);
	}

	public void ChangeRole() {
		ChangeRoleBtn.click();
	}

	public String CheckRoleChangeMessage() {
		return RoleChangeMsg.getText();
	}

}
