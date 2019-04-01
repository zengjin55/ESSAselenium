package com.buyer.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.buyer.pageObject.ConslidationPage;
import com.buyer.pageObject.IndexPage;
import com.buyer.pageObject.OrderPreviewPage;
import com.buyer.pageObject.PayDepositPage;
import com.buyer.pageObject.SettingMarksPage;
import com.buyer.pageObject.SettingStickerPage;
import com.buyer.pageObject.ShoppingCartPage;
import com.essa.framework.Model;
import com.essa.pageObject.BaseTest;

/**
 * @author Administrator
 *测试用例：采购商平台采购流程
 */
public class TestSendPO extends BaseTest {
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		initBuyer();
		loginBuyerValid(Model.getBuyerAccount(),Model.getBuyerPassword());
//		loginBuyerValid("buyer51551@essa.cn","essa123");
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	/**
	 * 开始拼柜
	 */
	@Test(description="开始拼柜")
	public void conslidation() {
		this.driver = getDriver();
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.toShoppingCartPage();
		ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
		shoppingCartPage.startConslidation();
		ConslidationPage conslidationPage = PageFactory.initElements(driver, ConslidationPage.class);
		conslidationPage.conslidation();
	}
	/**
	 * 提交PO
	 */
	@Test(description="设置贴纸唛头，提交PO")
	public void sendPO() {
		this.driver = getDriver();
		SettingMarksPage settingMarksPage = PageFactory.initElements(driver, SettingMarksPage.class);
		settingMarksPage.setMarks();
		SettingStickerPage settingStickerPage = PageFactory.initElements(driver, SettingStickerPage.class);
		settingStickerPage.setSticker();
		OrderPreviewPage orderPreviewPage = PageFactory.initElements(driver, OrderPreviewPage.class);
		orderPreviewPage.submit();
		PayDepositPage payDepositPage = PageFactory.initElements(driver, PayDepositPage.class);
		payDepositPage.operate();
	}
}
