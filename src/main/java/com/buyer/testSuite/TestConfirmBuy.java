package com.buyer.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.buyer.pageObject.IndexPage;
import com.buyer.pageObject.ShoppingCartPage;
import com.essa.framework.Model;
import com.essa.pageObject.BaseTest;

public class TestConfirmBuy extends BaseTest {

	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		initBuyer();
		loginBuyerValid(Model.getBuyerAccount(),Model.getBuyerPassword());
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	/**
	 * 登录采购商账号，确认订单
	 */
	@Test(description="采购商确认采购")
	public void confirmBuy() {
		this.driver = getDriver();
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.toShoppingCartPage();
		ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
		shoppingCartPage.confirmOrder();
	}
}
