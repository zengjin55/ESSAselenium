package com.essa.testSuite;

/**
 * Created by Administrator on 2018/10/26 0026.
 */

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.essa.pageObject.StorageManage.CabineTaskManagemenPage;
import com.essa.pageObject.StorageManage.HandlingLoadingOrders;

import java.io.IOException;

/**
* @author lana
* 测试用例：装柜确认操作
**/
public class TestConfirmCabinet extends BaseTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException {
        initsetUp();
        loginValid("chenyijie");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /**
     * 查到存在有装柜任务,进入处理页面
     */
    @Test(description="根据PO单号，确认装柜")
    public void ConfirmCabinet(){
        driver = getDriver();
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.toCabineTaskManagemenPage();
        CabineTaskManagemenPage searchCabineTaskManagemenPage = PageFactory.initElements(driver, CabineTaskManagemenPage.class);
        searchCabineTaskManagemenPage.setWareHouse();
        HandlingLoadingOrders handlingLoadingOrders = PageFactory.initElements(driver, HandlingLoadingOrders.class);
        handlingLoadingOrders.setHandlingCabinet();
    }


}

