package com.essa.testSuite;

import com.essa.framework.LinkSQL;
import com.essa.pageObject.BaseTest;
import com.essa.pageObject.DocumentaryManage.ReceiptCorePage;
import com.essa.pageObject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

// 跟单管理

public class TestReceiptCore extends BaseTest {
    WebDriver driver ;

    @BeforeClass
    public void  setUp()  {
        initsetUp();
        loginValid("zhidanbu");
    }

    @AfterClass
    public  void  tearDown() {driver.quit();}

    /*
    * PO发单接单
    * */
    @Test( )
    public  void  receiptcore ()  {
        this.driver = getDriver();
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.toReceiptCore();
        ReceiptCorePage receiptCorePage = PageFactory.initElements(driver,ReceiptCorePage.class);
//        String PO = LinkSQL.SQLQuery();

        receiptCorePage.BillOrder();

        SoftAssert softAssert = new SoftAssert();
        boolean SucceedElement = receiptCorePage.isSucceed();
//        boolean FailElement  = receiptCorePage.FailBilling();
//        boolean FailElement1  = receiptCorePage.FailOrder();
        System.out.println(SucceedElement);

        softAssert.assertEquals(SucceedElement,true,"发单接单失败");
        softAssert.assertAll();


    }

}
