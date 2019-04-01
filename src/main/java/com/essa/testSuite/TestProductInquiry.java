package com.essa.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.inquiryManage.ProductInquiryFeedbackPage;
import com.essa.pageObject.inquiryManage.ProductInquiryTaskPage;

/**
 * @author Administrator 测试用例：成品询价
 */
public class TestProductInquiry extends BaseTest {

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
	 * 成品询价
	 */
	@Test(description = "成品询价流程")
	public void productInquiry() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toProductInquiryTask();
		ProductInquiryTaskPage productInquiryTask = PageFactory.initElements(driver, ProductInquiryTaskPage.class);
		productInquiryTask.toFeedback();
		ProductInquiryFeedbackPage productInquiryFeedback = PageFactory.initElements(driver,
				ProductInquiryFeedbackPage.class);
		productInquiryFeedback.submit();
		// 断言
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInquiryTask.isSucceed(), true, "成品询价失败！");
		softAssert.assertAll();
	}
}
