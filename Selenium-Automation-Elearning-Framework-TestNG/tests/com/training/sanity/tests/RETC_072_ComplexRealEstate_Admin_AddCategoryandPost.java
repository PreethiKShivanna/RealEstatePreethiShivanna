//Complex Test Cases
//3.RETC_072 -To verify whether application allows admin to add new category while adding new post & same getting displayed on home screen for user

package com.training.sanity.tests;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.RealEstateAddNewPOM;
import com.training.pom.RealEstateAllUsersPOM;
import com.training.pom.RealEstateDashBoardPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_072_ComplexRealEstate_Admin_AddCategoryandPost {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
	private RealEstateDashBoardPOM REDashBoardPOM;
	private RealEstateAllUsersPOM REAllUsersPOM;
	private RealEstateAddNewPOM REAddNewPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		REloginPOM = new RealEstateLoginPOM(driver);
		REDashBoardPOM = new RealEstateDashBoardPOM(driver);
		REAllUsersPOM = new RealEstateAllUsersPOM(driver);
		REAddNewPOM = new RealEstateAddNewPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
		Thread.sleep(3000);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(priority = 0)
	public void LogintoRealEstateApp() {
		System.out.println("Verify whether application allows registered admin to login into application");
		REloginPOM.clickonLoginRegisterLink();
		REloginPOM.enterUserName("PreethiKS");
		REloginPOM.enterPassword("IBMManipal1234");
		REloginPOM.ClickSignInBtn();
		String ExpectedTitle = "Dashboard";
		String ActualTitle = REDashBoardPOM.getHeading();
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		screenShot.captureScreenShot("RETC_072_RealEstateLoginDashboard");
		System.out.println("Admin can login into application,Dashboard seen");
	}

	@Test(priority = 1)
	public void AdminChangingUserRole() throws InterruptedException {
		System.out.println(
				"RETC_072 -To verify whether application allows admin to add new category while adding new post & same getting displayed on home screen for user");

		Actions act = new Actions(driver);
		act.moveToElement(REDashBoardPOM.Posts).build().perform();
		screenShot.captureScreenShot("RETC_072_RealEstateSubMenuUnderUsers");
		Thread.sleep(3000);
		boolean viewSubMenuPosts = REDashBoardPOM.SubMenuPosts.isDisplayed();
		boolean viewAllPosts = REDashBoardPOM.SubMenuAllPosts.isDisplayed();
		boolean viewAddNew = REDashBoardPOM.SubMenuAddNew.isDisplayed();
		boolean viewCategories = REDashBoardPOM.SubMenuCategories.isDisplayed();
		boolean viewTags = REDashBoardPOM.SubMenuTags.isDisplayed();
		Assert.assertTrue(viewSubMenuPosts);
		Assert.assertTrue(viewAllPosts);
		Assert.assertTrue(viewAddNew);
		Assert.assertTrue(viewCategories);
		Assert.assertTrue(viewTags);
		REDashBoardPOM.clickonPosts();
		REDashBoardPOM.clickonAddNew();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(REAddNewPOM.AddNewPostHeading));
		boolean viewAddNewPost = REAddNewPOM.AddNewPostHeading.isDisplayed();
		Assert.assertTrue(viewAddNewPost);
		Thread.sleep(3000);
		REAddNewPOM.ClickOnAddNewCategory();
		REAddNewPOM.AddCategoryName("Plots");
		String AddedCaregory = REAddNewPOM.AddNewCategoryTextBox.getAttribute("value");
		Thread.sleep(3000);
		REAddNewPOM.selectFromParentCategoryDropDwn();
		REAddNewPOM.clickOnAddNewCategoryBtn();
		driver.navigate().refresh();
		screenShot.captureScreenShot("RETC_072_AddedSubCategoryDisplayed");
		System.out.println("RETC_072_AddedSubCategoryDisplayed");
		String AddedSubCategory = REAddNewPOM.AddedSubCategory.getText();
		Assert.assertEquals(AddedSubCategory, AddedCaregory);
		REAddNewPOM.EnterPostTitle("Poorvankara Builders");
		String EnteredPropertyPost = REAddNewPOM.TitleTextBox.getAttribute("value");
		Thread.sleep(5000);
		driver.switchTo().frame("content_ifr");
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(REAddNewPOM.TextArea));
		REAddNewPOM.EnterPostBody("New Launch in Home");
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		REAddNewPOM.AddedSubCategory.click();
		Thread.sleep(1000);
		REAddNewPOM.ClickPublishBtn();
		Thread.sleep(2000);
		screenShot.captureScreenShot("RETC_072_RealEstatePostPublished");
		Boolean ViewPostsVisible = REAddNewPOM.ViewPostLink.isDisplayed();
		Assert.assertTrue(ViewPostsVisible);
		act.moveToElement(REloginPOM.LoggedInUser).build().perform();
		Thread.sleep(1000);
		REloginPOM.LogOut();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1800)");
		REloginPOM.ClickOnRealEstateIcon();
		REloginPOM.searchProperty(EnteredPropertyPost);
		Thread.sleep(4000);
		String SearchedProperty = REloginPOM.SearchedProperty.getText();
		Assert.assertEquals(SearchedProperty, EnteredPropertyPost);

	}

}
