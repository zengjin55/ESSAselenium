package com.essa.testSuite;

import com.essa.framework.Model;
import com.essa.pageObject.LogisticShipp.*;
import com.essa.pageObject.needDealt.LargeDcofdPage;
import com.essa.pageObject.needDealt.RegisOptShpDatePage;
import com.essa.pageObject.needDealt.SellerFillInfoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;

public class TestConfirLogistics extends BaseTest {

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

    @org.testng.annotations.Test(description = "通知确认船务资料、订车、发车")
    public void confirmLogisticsTest() {
        this.driver = getDriver();

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
    }
}
