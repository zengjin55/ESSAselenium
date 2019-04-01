package com.essa.testSuite;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.StorageManage.LoadContainerHandlePage;
import com.essa.pageObject.StorageManage.LoadContainerManagePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLoadContainerTask extends BaseTest {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        initsetUp();
        loginValid("chenyijie");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    /**
     * 装柜任务处理
     */
    @Test(description="装柜任务处理")
    public void LoadContainerTask() {
        this.driver = getDriver();
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.toLoadContainer();
        LoadContainerManagePage loadContainerManagePage = PageFactory.initElements(driver,LoadContainerManagePage.class);
        loadContainerManagePage.toLoadContainerDetail();
        LoadContainerHandlePage loadContainerHandlePage = PageFactory.initElements(driver,LoadContainerHandlePage.class);
        loadContainerHandlePage.loadTaskHandle();
        homePage.logout();
    }
}
