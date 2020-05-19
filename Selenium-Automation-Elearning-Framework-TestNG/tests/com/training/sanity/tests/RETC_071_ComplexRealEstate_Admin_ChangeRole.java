//Complex Test Cases
//2.RETC_071 - To Verify whether application allows admin to change the role of registered user in Users module

package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.dataproviders.RolesDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.RealEstateAllUsersPOM;
import com.training.pom.RealEstateDashBoardPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_071_ComplexRealEstate_Admin_ChangeRole {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
	private RealEstateDashBoardPOM REDashBoardPOM;
	private RealEstateAllUsersPOM REAllUsersPOM;
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
		screenShot.captureScreenShot("RETC_071_RealEstateLoginDashboard");
		System.out.println("Admin can login into application,Dashboard seen");
	}

	@Test(priority = 1, dataProvider = "excel-ChangeRole", dataProviderClass = RolesDataProviders.class)
	public void AdminChangingUserRole(String sUserName, String sRole) throws InterruptedException {
		System.out.println(
				"RETC_071 - To Verify whether application allows admin to change the role of registered user in Users module");

		Actions act = new Actions(driver);
		act.moveToElement(REDashBoardPOM.Users).build().perform();
		screenShot.captureScreenShot("RETC_071_RealEstateSubMenuUnderUsers");
		boolean viewSubMenuUsers = REDashBoardPOM.SubMenuUsers.isDisplayed();
		boolean viewAllUsers = REDashBoardPOM.SubMenuUsersAllUsers.isDisplayed();
		boolean viewAddNew = REDashBoardPOM.SubMenuUsersAddNew.isDisplayed();
		boolean viewYourProfile = REDashBoardPOM.SubMenuUsersYourProfile.isDisplayed();
		Assert.assertTrue(viewSubMenuUsers);
		Assert.assertTrue(viewAllUsers);
		Assert.assertTrue(viewAddNew);
		Assert.assertTrue(viewYourProfile);
		REDashBoardPOM.clickonUsers();
		REDashBoardPOM.clickOnAllUsers();
		Thread.sleep(2000);
		REAllUsersPOM.SearchUser(sUserName);
		String UserRoleBefore = REAllUsersPOM.UserRole.getText();
		System.out.println("User Role Before change :" + UserRoleBefore);
		screenShot.captureScreenShot("RETC_071_RealEstateInitialRoleOftheUser");
		REAllUsersPOM.SelectCheckBox();
		REAllUsersPOM.SelectChangeRoleToDropDown(sRole);
		REAllUsersPOM.ChangeRole();
		String ExpectedMessage = "Changed roles.";
		String ActualMessage = REAllUsersPOM.CheckRoleChangeMessage();
		screenShot.captureScreenShot("RETC_071_RealEstateRoleChangedMsg");
		Assert.assertEquals(ActualMessage, ExpectedMessage, "Expected and Actual Role change messages match");
		Thread.sleep(2000);
		REAllUsersPOM.SearchUser(sUserName);
		String UserRoleAfter = REAllUsersPOM.UserRole.getText();
		System.out.println("User Role After change :" + UserRoleAfter);
		screenShot.captureScreenShot("RETC_071_RealEstateChangedRoleOftheUser");
		Thread.sleep(2000);

	}

}
