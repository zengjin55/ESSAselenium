package com.essa.testSuite;


import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.needDealt.NeedDealtApplyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 测试用例：登记备选船期
 */
public class TestShippingMark extends BaseTest{

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        initsetUp();
        loginValid("wangmiaodan");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    /**
     * 登记备选船期
     */
    @Test(description = "登记备选船期待办")
    public void ShippingMarkWait(){
        this.driver = getDriver();
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.toShippingMarkWait();
        NeedDealtApplyPage needDealtApplyPage = PageFactory.initElements(driver,NeedDealtApplyPage.class);
        needDealtApplyPage.toClaimShippingMark();
        homePage.logout();
    }
}
