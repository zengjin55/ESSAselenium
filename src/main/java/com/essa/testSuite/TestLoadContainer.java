package com.essa.testSuite;

import com.essa.framework.Model;
import com.essa.pageObject.BaseTest;
import com.essa.pageObject.DocumentaryManage.BillingCenterPage;
import com.essa.pageObject.DocumentaryManage.ConvertTailPage;
import com.essa.pageObject.DocumentaryManage.POBoardPage;
import com.essa.pageObject.DocumentaryManage.PODocumentaryListPage;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.LogisticShipp.BookingCarPage;
import com.essa.pageObject.LogisticShipp.CabinInfoPage;
import com.essa.pageObject.LogisticShipp.LogisticsShippPage;
import com.essa.pageObject.LogisticShipp.SendCarPage;
import com.essa.pageObject.StorageManage.*;
import com.essa.pageObject.needDealt.LargeDcofdPage;
import com.essa.pageObject.needDealt.NeedDealtApplyPage;
import com.essa.pageObject.needDealt.RegisOptShpDatePage;
import com.essa.pageObject.needDealt.SellerFillInfoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestLoadContainer extends BaseTest {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		initsetUp();
		loginValid("zhidanbu");
//		loginValid("chenyijie");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	/**
	 * 转尾货
	 */
	@Test(description = "转在途尾货")
	public void toTail() {
		driver = getDriver();
		loginValid("chenyijie");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PODocumentaryListPage poDocumentaryListPage = PageFactory.initElements(driver, PODocumentaryListPage.class);
		POBoardPage poBoardPage = PageFactory.initElements(driver, POBoardPage.class);
		ConvertTailPage convertTailPage = PageFactory.initElements(driver, ConvertTailPage.class);
		homePage.toPoDocumentaryListPage();
		poDocumentaryListPage.todetail();
		poBoardPage.toTail();
		convertTailPage.convertTail();
	}

	/**
	 * 设置收货、装柜的仓库地址
	 */
	@Test(description = "设置收货、装柜的仓库地址")
	public void noticReceive() {
		driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		NoticeReceivePage noticeReceivePage = PageFactory.initElements(driver, NoticeReceivePage.class);
		homePage.toNoticeReceivePage();
		noticeReceivePage.setWareHouse();
	}

	/**
	 * 通知收货、装柜
	 */
	@Test(description = "通知收货、装柜")
	public void NoticeReceiveAndLoad(){
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver,HomePage.class);
		homePage.toPoDocumentaryListPage();
		PODocumentaryListPage poDocumentaryListPage = PageFactory.initElements(driver,PODocumentaryListPage.class);
		POBoardPage poBoardPage = PageFactory.initElements(driver,POBoardPage.class);
		poDocumentaryListPage.toPoFollowDetail();

		//通知收货
		poBoardPage.noticeReceive();

		//断言：通知收货是否成功
		boolean actualReceive = poBoardPage.isNoticeReceSucceed();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualReceive,true,"通知收货失败！");
		//通知装柜
		poBoardPage.noticeLoad();

		//断言：通知装柜是否成功
		boolean actualLoae = poBoardPage.isNoticeLoadSucceed();
		softAssert.assertEquals(actualLoae,true,"通知装柜失败！");
		softAssert.assertAll();
//		homePage.logout();
	}

	/**
	 * 收货任务管理，处理
	 */
	@Test (description = "收货任务处理")
	public void dealWithDelivery() {
		driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		GoodsReceiveTaskPage goodsReceiveTaskPage = PageFactory.initElements(driver, GoodsReceiveTaskPage.class);
		ReceiveTaskDealPage receiveTaskDealPage = PageFactory.initElements(driver, ReceiveTaskDealPage.class);
		homePage.toGoodsReceiveTaskPage();
		do {
			goodsReceiveTaskPage.dealReceive();
			receiveTaskDealPage.allReceive();
		}while (!(goodsReceiveTaskPage.isFinish()));

		homePage.getHome();
		homePage.dealAlert(true);//确认弹框：离开
		homePage.logout();
	}

	/**
	 * 大跟单确认船期
	 */
	@Test (description = "大跟单确认船期")
	public void confirmSailingDate() {
		driver = getDriver();
		loginValid("yuwanhang");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		NeedDealtApplyPage needDealtApplyPage = PageFactory.initElements(driver, NeedDealtApplyPage.class);
		homePage.toSailingDateConfirmPage();
		needDealtApplyPage.confirmSailingDate();
	}
	/**
	 *制单部发单
	 * @param
	 * @return
	 */
	@Test(description = "制单、发单")
	public void Billing(){
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		BillingCenterPage billingCenterPage = PageFactory.initElements(driver,BillingCenterPage.class);
		homePage.toBillingCenterPage();
		billingCenterPage.MoreBilling();
		homePage.getHome();
		homePage.logout();
	}
	@Test(description = "通知确认船务资料、订车、发车")
	public void confirmLogisticsTest() {
		this.driver = getDriver();
		loginValid("chenhong");//
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toLogisticsShippPage();

		//通知业务人员和客户，进行船务资料确认
		LogisticsShippPage logisticsShippPage = PageFactory.initElements(driver, LogisticsShippPage.class);
		logisticsShippPage.isSucceed();

		homePage.getHome();
		homePage.logout();
		loginValid(Model.getSalesman());//登录对应的业务员账号

		//待办
//        homePage.toSailingDateConfirmPage();
		homePage.toWriteShipping();
		//业务员补充船务资料
		SellerFillInfoPage sellerFillInfo = PageFactory.initElements(driver, SellerFillInfoPage.class);
		sellerFillInfo.toSellerFillInfo();

		//登记备选船期待办
//        homePage.getHome();
		homePage.logout();
		loginValid("wangmiaodan");
		homePage.toSailingDateConfirmPage();
//        homePage.toShippingMarkWait();//根据流水号进入待办
		RegisOptShpDatePage regisOptShpDatePage = PageFactory.initElements(driver,RegisOptShpDatePage.class);
		regisOptShpDatePage.toRegisOptShpDatePage();

		//大跟单确认
//        homePage.getHome();
		homePage.logout();
		loginValid("yuwanhang");
//        homePage.toSailingDateConfirmPage();
		homePage.toShippingMarkWait();//根据流水号进入待办
		LargeDcofdPage largeDcofdPage = PageFactory.initElements(driver,LargeDcofdPage.class);
		largeDcofdPage.toLargeDcofdPage();

		//订舱
//        homePage.getHome();
		homePage.logout();
		loginValid("chenhong");

		homePage.toLogisticsShippPage();
		CabinInfoPage cabinInfoPage = PageFactory.initElements(driver, CabinInfoPage.class);
		cabinInfoPage.toCabinInfoPage();

		//订车信息确认
		BookingCarPage bookingCarPage = PageFactory.initElements(driver, BookingCarPage.class);
		bookingCarPage.toBookingCarPage();

		//确认发车
		SendCarPage sendCarPage = PageFactory.initElements(driver, SendCarPage.class);
		sendCarPage.toSendCarPage();

		homePage.getHome();
		homePage.logout();
	}

	/**
	 * 查到存在有装柜任务,进入处理页面
	 */
	@Test(description="根据PO单号，确认装柜")
	public void ConfirmCabinet(){
		driver = getDriver();
		loginValid("chenyijie");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toCabineTaskManagemenPage();
		CabineTaskManagemenPage searchCabineTaskManagemenPage = PageFactory.initElements(driver, CabineTaskManagemenPage.class);
		searchCabineTaskManagemenPage.setWareHouse();
		HandlingLoadingOrders handlingLoadingOrders = PageFactory.initElements(driver, HandlingLoadingOrders.class);
		handlingLoadingOrders.setHandlingCabinet();
	}
}
