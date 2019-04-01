package com.essa.pageObject.marketingManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *团购控制
 */
public class GroupControlPage extends BasePage {
	public GroupControlPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 页面元素定位
	 */
	
	//关键字查询输入
	@FindBy (xpath = "//*[@ng-model='searchKeyword']")
	WebElement searchKeyword;
	
	//查询按钮
	@FindBy (xpath = "//*[@ng-click='gotoSearch()']")
	WebElement search;
	
	/*
	 * 页面方法
	 */
	/**
	 * 判断团购的商品是否发布成功
	 * @return boolean
	 */
	public boolean isSuccess() {
		mywait(searchKeyword);
		sendKeys(searchKeyword, Model.getSkuNo());
		click(search);
		forceWait(1500);
		return isVisibility(By.xpath("//*[contains(text(),'"+Model.getSkuNo()+"')]"));
	}
}
