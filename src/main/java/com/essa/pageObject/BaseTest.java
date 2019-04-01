package com.essa.pageObject;

import com.buyer.pageObject.BuyerLoginPage;
import com.buyer.pageObject.IndexPage;
import com.essa.framework.BrowserEngine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Administrator
 * 测试用例的基类，主要是有登录和调用浏览器的方法
 */
public class BaseTest {
    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * bpms调用浏览器，打开要测试的网页
     */
    public void initsetUp() {
        BrowserEngine browserEngine = new BrowserEngine();
//		try {
        browserEngine.initConfigData();
		/*} catch (IOException e) {
			e.printStackTrace();
		}*/
        driver = browserEngine.getBrowser();
    }

    /**
     * buyer调用浏览器，将访问buyer的地址
     */
    public void initBuyer() {
        BrowserEngine browserEngine = new BrowserEngine();
//		try {
        browserEngine.initConfigData();
	/*	} catch (IOException e) {
			e.printStackTrace();
		}*/
        driver = browserEngine.buyerGetBrowser();
    }

    /**
     * bpms初始化登录页面，登录
     *
     * @param account
     */
    public void loginValid(String account) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(account, "essa123");
    }

    /**
     * buyer初始化登录
     *
     * @param account
     */
    public void loginBuyerValid(String account, String password) {
        IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
        indexPage.toLoginPage();
        BuyerLoginPage loginPage = PageFactory.initElements(driver, BuyerLoginPage.class);
        loginPage.login(account, password);
    }
}

