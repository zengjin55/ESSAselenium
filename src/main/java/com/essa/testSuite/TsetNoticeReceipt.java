package com.essa.testSuite;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.PODocumentary.POBoardPage;
import com.essa.pageObject.PODocumentary.PODocumentaryListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;


/**
 * @author Administrator
 *测试用例：通知收货
 */
public class TsetNoticeReceipt extends BaseTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException{
        initsetUp();
        loginValid("chenyijie");
    }

    @AfterClass
    public void tearDown() { driver.quit(); }


    /**
     * 操作
     *
     * @throws InterruptedException
     */
    @Test(description="查找PO单")
    public void TsetNoticeReceipt(){
        this.driver = getDriver();
        PODocumentaryListPage poDocumentaryListPage = PageFactory.initElements(driver,PODocumentaryListPage.class);
        POBoardPage poBoardPage = PageFactory.initElements(driver,POBoardPage.class);
        poDocumentaryListPage.toPoDocumentaryListPage();
        poBoardPage.toNoticeReceipt();
        //通知收货是否成功
        boolean actualReceipt = poBoardPage.isNoticeReceipt();
        SoftAssert ftAssert =new SoftAssert();
        ftAssert.assertEquals(actualReceipt,true,"通知收货失败");


        poBoardPage.toNotificationLoading();
        //通知装柜是否成功
        boolean actualLoading = poBoardPage.isNotificationLoading();
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(actualLoading,true,"通知装柜失败");
    }

}
