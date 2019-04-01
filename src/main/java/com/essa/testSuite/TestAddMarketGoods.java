package com.essa.testSuite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.essa.framework.Model;
import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.GoodsManage.AddMarketGoodsPage;
import com.essa.pageObject.GoodsManage.AuditMarketGoodsPage;
import com.essa.pageObject.GoodsManage.AuditMarketOpreatePage;
import com.essa.pageObject.GoodsManage.MarketGoodsRelesePage;

/**
 * @author Administrator
 *测试用例：添加市场商品
 */
public class TestAddMarketGoods extends BaseTest {

	WebDriver driver;
	
	@BeforeClass
	public void setUp() throws IOException {
		initsetUp();
		loginValid("maomeixiang");
	}
	/**
	 * 进入市场商品发布页，选择对应的采购商
	 */
	@Test(description="进入市场商品发布列表，选择采购商")
	public void toMarketGoodsRelesePage() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toMarketGoodsRelesePage();
		MarketGoodsRelesePage marketGoodsRelesePage = PageFactory.initElements(driver, MarketGoodsRelesePage.class);
		marketGoodsRelesePage.toAddMarketGoodsPage();
	}
	/**
	 * 市场商品建档
	 */
	@Test(description="市场商品建档")
	public void addMarketGoods() {
		this.driver = getDriver();
		AddMarketGoodsPage addMarketGoodsPage = PageFactory.initElements(driver, AddMarketGoodsPage.class);
		addMarketGoodsPage.addMarketGoods();
		
		//断言是否建档成功，如果model中的skuNo不为null则成功
		SoftAssert softAssert = new SoftAssert();
		boolean actual = !(Model.getSkuNo().isEmpty());
		softAssert.assertEquals(actual, true, "市场商品建档失败");
		softAssert.assertAll();
		
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.getHome();
		homePage.logout();
	}
	/**
	 * 对市场商品进行审核
	 */
	@Test(description="市场商品审核")
	public void auditMarketGoods() {
		loginValid("xingchangyong");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toAuditMarketGoodsPage();
		AuditMarketGoodsPage auditMarketGoodsPage = PageFactory.initElements(driver, AuditMarketGoodsPage.class);
		auditMarketGoodsPage.auditMarketGoods();
		AuditMarketOpreatePage auditMarketOpreatePage = PageFactory.initElements(driver, AuditMarketOpreatePage.class);
		auditMarketOpreatePage.auditPass();
		//断言是否审核成功
		boolean actual = auditMarketGoodsPage.isSucceed();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actual, false, "市场商品审核失败！");
		softAssert.assertAll();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
