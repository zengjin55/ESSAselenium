package com.essa.testSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.buyPlaneManage.SkuCategoryManagerCongfigPage;
import com.essa.pageObject.marketingManage.GroupControlPage;
import com.essa.pageObject.marketingManage.GroupSettingPage;

/**
 * @author Administrator
 *测试用例：发布团购
 */
public class TestGroupPurchase extends BaseTest {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		initsetUp();
		loginValid("linrong");
	}

	/**
	 * 配置商品类目经理
	 */
	@Test(description="配置商品类目经理")
	public void SkuCategoryManagerCongfig() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toSkuCategoryManagerCongfig();
		SkuCategoryManagerCongfigPage skuCategoryManagerCongfigPage = PageFactory.initElements(driver,
				SkuCategoryManagerCongfigPage.class);
		skuCategoryManagerCongfigPage.distributeManager();
	}

	/**
	 * 发布团购商品
	 */
	@Test(description="发布团购")
	public void publishGroupPurchase() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toGroupSettingPage();
		GroupSettingPage groupSettingPage = PageFactory.initElements(driver, GroupSettingPage.class);
		groupSettingPage.publishGroup();
	}

	/**
	 * 进入团购控制，查找是否有发布成功，并进行断言
	 */
	@Test(description="检查团购是否发布成功")
	public void isPublish() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toGroupControlPage();
		GroupControlPage groupControlPage = PageFactory.initElements(driver, GroupControlPage.class);
		boolean actual = groupControlPage.isSuccess();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actual, true, "发布团购失败了！");
		softAssert.assertAll();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
