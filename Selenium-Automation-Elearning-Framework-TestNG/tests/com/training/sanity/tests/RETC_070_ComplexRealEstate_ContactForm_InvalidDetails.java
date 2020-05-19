//Complex Test Cases
//1.RETC_070 - To Verify whether application highlights required fields upon entering invalid details in  send the query in Contact Form Page

package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.dataproviders.EnquiryDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_070_ComplexRealEstate_ContactForm_InvalidDetails {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
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

	@Test(dataProvider = "excel-ForEnquiries", dataProviderClass = EnquiryDataProviders.class)
	public void AdminChangingUserRole(String sUserName, String sEmailID, String sSubject, String sMessage)
			throws InterruptedException {
		System.out.println(
				"RETC_070 - To Verify whether application highlights required fields upon entering invalid details in  send the query in Contact Form Page");
		REloginPOM.ClickOnNewLaunchtab();
		REloginPOM.clickOnDonecquisApartments();
		REloginPOM.enterYourName(sUserName);
		REloginPOM.enterYourEmail(sEmailID);
		String YourEmailContent = REloginPOM.YourEmail.getAttribute("value");
		REloginPOM.enterYourSubject(sSubject);
		REloginPOM.enterYourMessage(sMessage);
		REloginPOM.ClickOnSendBtn();
		Thread.sleep(2000);
		
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		boolean result = YourEmailContent.matches(regex);
		if (result) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(700,200)");
			screenShot.captureScreenShot("RETC_070_RealEstateErrorMsg");
			System.out.println("Given email-id : " + YourEmailContent + " is valid");
			String MsgExpected = "There was an error trying to send your message. Please try again later.";
			Thread.sleep(2000);
			String MsgActual = REloginPOM.ErrorMsg.getText();
			Assert.assertEquals(MsgActual, MsgExpected);

		} else if (REloginPOM.InvalidYourEmail != null) {
			System.out.println("Given email-id : " + YourEmailContent + " is not valid,Field is highlighted");
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(500,-80)");
			Thread.sleep(2000);
    		screenShot.captureScreenShot("RETC_070_RealEstateDataProviderInValidData");
		}
	}

}
