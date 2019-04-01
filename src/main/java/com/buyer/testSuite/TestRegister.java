package com.buyer.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buyer.pageObject.IndexPage;
import com.buyer.pageObject.RegisterPage;
import com.essa.pageObject.BaseTest;

/**
 * @author Administrator
 *测试用例：采购商注册信息填写
 */
public class TestRegister extends BaseTest{
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		initBuyer();
	}
	
	/**
	 * 注册buyer
	 */
	@Test(description="填写注册采购商信息，注册采购商")
	public void register() {
		this.driver = getDriver();
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.toRegisterPage();
		RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
		registerPage.register();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(indexPage.isSucceed(), true, "采购商注册失败了！");
		softAssert.assertAll();
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
