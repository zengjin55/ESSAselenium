package com.essa.testSuite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.GoodsManage.AddOriginalGoodsPage;
import com.essa.pageObject.GoodsManage.AuditOriginalGoodsPage;
import com.essa.pageObject.GoodsManage.AuditOriginalOpreatePage;
import com.essa.pageObject.GoodsManage.GoodsBankPage;
import com.essa.pageObject.GoodsManage.GoodsRelesePage;
import com.essa.pageObject.GoodsManage.UpdatePicPage;

/**
 * @author Administrator
 *测试用例：天机原厂商品
 */
public class TestAddOriginalGoods extends BaseTest {

	WebDriver driver;

	@BeforeClass
	public void setUp() throws IOException {
		initsetUp();
		loginValid("linxun");
	}

	/**
	 * 商品建档
	 * 
	 * @throws InterruptedException
	 */
	@Test(description="原厂商品建档")
	public void toAddOriginalGoodsPage() throws InterruptedException {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.tOriginalGoodsPage();
		AddOriginalGoodsPage addOriginalGoodsPage = PageFactory.initElements(driver, AddOriginalGoodsPage.class);
		addOriginalGoodsPage.addOriginalGoods();

	}

	/**
	 * 更新商品图片
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	@Test(description="更新图片")
	public void updatePic() throws Exception {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.tGoodsRelesePage();
		GoodsRelesePage goodsRelesePage = PageFactory.initElements(driver, GoodsRelesePage.class);
		
		//断言上一步商品建档是否成功
		boolean actual = goodsRelesePage.isAddOrignalSucceed();
		SoftAssert assertion = new SoftAssert();
		assertion.assertEquals(actual, true, "商品建档失败！");
		assertion.assertAll();
		
		goodsRelesePage.toUpDatePic();
		UpdatePicPage updatePicPage = PageFactory.initElements(driver, UpdatePicPage.class);
		// 传入要上传的图片的本机地址
//		updatePicPage.upDatePic("C:\\Users\\Administrator\\Pictures\\Camera Roll\\4.jpg");
		updatePicPage.upDatePic();
	}

	/**
	 * 审核原厂商品
	 */
	@Test(description="原厂商品审核")
	public void auditOriginal() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toAuditOriginalGoodsPage();
		AuditOriginalGoodsPage auditOriginalGoodsPage = PageFactory.initElements(driver, AuditOriginalGoodsPage.class);
		// 断言上一步更新图片是否成功
		boolean actual1 = auditOriginalGoodsPage.isSucceed();
		SoftAssert assertion = new SoftAssert();
		assertion.assertEquals(actual1, true, "更新图片失败！");

		auditOriginalGoodsPage.toAuditOriginalSku();
		AuditOriginalOpreatePage auditOriginalOpreatePage = PageFactory.initElements(driver,
				AuditOriginalOpreatePage.class);
		auditOriginalOpreatePage.auditPass();

		// 断言是否审核成功，如果发布审核页面没有找到刚刚的商品，说明审核成功了
		boolean actual = auditOriginalGoodsPage.isSucceed();
		assertion.assertEquals(actual, false, "审核原厂商品时失败!");
		assertion.assertAll();
	}
	
	/**
	 * 获取新增的商品编号
	 */
	@Test(description="去商品库获取添加的sku编号")
	public void getSkuNo() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toGoodsBankPage();
		GoodsBankPage goodsBankPage = PageFactory.initElements(driver, GoodsBankPage.class);
		goodsBankPage.getNo();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
