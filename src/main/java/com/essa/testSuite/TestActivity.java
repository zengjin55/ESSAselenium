package com.essa.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.essa.framework.Model;
import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.inquiryManage.ProductInquiryFeedbackPage;
import com.essa.pageObject.inquiryManage.ProductInquiryTaskPage;
import com.essa.pageObject.needDealt.NeedDealtApplyPage;

public class TestActivity extends BaseTest{
	public WebDriver driver;
//	private String manager;

	@BeforeClass
	public void setUp() {
		initsetUp();
//		Model.setIsactivity(1);
//		Model.setManager("linrong");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	/**
	 * 活动商品已满足，类目经理走待办
	 */
	@Test(description="采购计划单申请，送询价")
	public void TestActivityToPO() {
		this.driver = getDriver();
//		this.manager = Model.getManager();
		if (Model.getManager() == null) {
			System.out.println("获取类目经理失败了");
			driver.quit();
		}
		
		loginValid(Model.getManager());
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.applyPurchasePlane();//目前没办法查出待办流水号，只能点击第一个待办，此处后续有必要的话需要优化
		NeedDealtApplyPage needDealtApplyPage = PageFactory.initElements(driver, NeedDealtApplyPage.class);
		needDealtApplyPage.submitInquiry();
	}

	/**
	 * chenhong对成品询价，目前写死，暂不考虑义务商品
	 */
	@Test(description="成品询价")
	public void productInquiry() {
		this.driver = getDriver();
		loginValid("chenhong");//此处是汕头的商品，有可能出现义务的商品，则要用xingchangyong来操作
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
		homePage.sureLogout();
		
	}
	
	/**
	 * 类目经理对采购计划进行修订
	 */
	@Test(description="采购计划修订")
	public void planeModify() {
		this.driver = getDriver();
		loginValid(Model.getManager());
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toNeedDealtDetailPage();
		NeedDealtApplyPage needDealtApplyPage = PageFactory.initElements(driver, NeedDealtApplyPage.class);
		needDealtApplyPage.toAudit();
		homePage.logout();
	}
	
	/**
	 * 财务对采购计划进行审核
	 */
	@Test(description="财务审核采购计划")
	public void TestAuditApply() {
		this.driver = getDriver();
		loginValid("wangchaomang");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toNeedDealtDetailPage();
		NeedDealtApplyPage needDealtApplyPage = PageFactory.initElements(driver, NeedDealtApplyPage.class);
		needDealtApplyPage.audit();
	}
}
