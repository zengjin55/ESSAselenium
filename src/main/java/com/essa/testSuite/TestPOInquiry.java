package com.essa.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.inquiryManage.ProductInquiryFeedbackPage;
import com.essa.pageObject.inquiryManage.ProductInquiryTaskPage;

public class TestPOInquiry extends BaseTest {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		initsetUp();
		loginValid("chenhong");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	
	/**
	 * po询价
	 */
	@Test(description="根据PO单号，做询价操作")
	public void POInquiry() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.getHome();
		homePage.toProductInquiryTask();
		ProductInquiryTaskPage productInquiryTask = PageFactory.initElements(driver, ProductInquiryTaskPage.class);
		ProductInquiryFeedbackPage productInquiryFeedbackPage = PageFactory.initElements(driver,
				ProductInquiryFeedbackPage.class);
		productInquiryTask.POtoFeedback();
		productInquiryFeedbackPage.POsubmit();
		
		//如果列表还有未询价，继续执行
		while (productInquiryTask.isPOSucceed()) {
			productInquiryTask.POcircle();
			productInquiryFeedbackPage.POsubmit();
		}
	}
}
