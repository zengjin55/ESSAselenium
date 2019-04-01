package com.buyer.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * 采购商登录页
 * @author Administrator
 *
 */
public class BuyerLoginPage extends BasePage {
	public BuyerLoginPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 页面元素定位
	 */
	//用户名：邮箱
	@FindBy (xpath = "//*[@id='username']")
	WebElement userName;
	
	//密码
	@FindBy (xpath = "//*[@id='password']")
	WebElement passWord;
	
	//登录按钮
	@FindBy (xpath = "//*[@id='login']")
	WebElement signIn;
	
	/*
	 * 页面方法
	 */
	/**
	 * 采购商登录方法
	 * @param account 账号
	 * @param password 密码
	 * @return  IndexPage
	 */
	public IndexPage login(String account,String password) {
		sendKeys(userName, account);
		sendKeys(passWord, password);
		click(signIn);
		return new IndexPage(driver);
	}
}
