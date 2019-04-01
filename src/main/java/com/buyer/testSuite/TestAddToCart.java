package com.buyer.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buyer.pageObject.IndexPage;
import com.buyer.pageObject.KeywordResultPage;
import com.buyer.pageObject.ShoppingCartPage;
import com.essa.framework.Model;
import com.essa.pageObject.BaseTest;

/**
 * @author Administrator
 *测试用例：采购商平台采购流程
 */
public class TestAddToCart extends BaseTest {
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		initBuyer();
		loginBuyerValid(Model.getBuyerAccount(),Model.getBuyerPassword());
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	/**
	 * 关键字查询，并将搜到的结果加入购物车
	 */
	@Test(description="关键字搜索，并将sku加入购物车",groups= {"first"})
	public void keywordToCart() {
		this.driver = getDriver();
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.keywordSearch();
		KeywordResultPage keywordResultPage = PageFactory.initElements(driver, KeywordResultPage.class);
		keywordResultPage.addToCart();
		//断言
		ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
		boolean actual = shoppingCartPage.isSucceed();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actual, true, "加入购物车失败！");
		softAssert.assertAll();
	}
	
	/**
	 * 关键字查询，加入购物车，但不进入购物车，防止提示有人正在操作
	 */
	@Test(description="将sku加入购物车")
	public void addSku() {
		this.driver = getDriver();
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.keywordSearch();
		KeywordResultPage keywordResultPage = PageFactory.initElements(driver, KeywordResultPage.class);
		keywordResultPage.addSku();
	}
}
