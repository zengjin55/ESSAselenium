package com.buyer.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * 购物车页
 * @author Administrator
 *
 */
public class ShoppingCartPage extends BasePage {
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	//关键字查询框
	@FindBy (xpath ="//*[@ng-model='keyword']")
	WebElement keyword;
	
	//查询按钮
	@FindBy (xpath ="//*[contains(@ng-click,'search()')]")
	WebElement searchButton;
	
	//搜索结果的一个sku对应的复选框
	@FindBy (xpath ="//*[contains(@class,'products-list ng-scope')]/div[2]/div[2]/span[1]/input")
	WebElement checkbox;
	
	//可拼柜
	@FindBy (xpath="//*[text()='Consolidation allowed']")
	WebElement consolidationAllowed;
	
	//开始拼柜
	@FindBy (xpath ="//button[contains(text(),' Start consolidation')]")
	WebElement startConslidation;
	
	//确认采购按钮
	@FindBy (xpath = "//*[@class='products-list ng-scope']/div[2]/div/div[1]/div/span[5]/div/button")
	WebElement confirmOrder;
	
	/*
	 * 页面方法
	 */
	/**
	 * 断言sku是否已经加入购物车
	 * @return true：已加入 false：未加入
	 */
	public boolean isSucceed() {
		mywait(keyword);
		sendKeys(keyword, Model.getSkuNo());
		click(searchButton);
		mywait(checkbox);
		forceWait(2000);
		return isVisibility(By.xpath("//*[contains(text(),'"+Model.getSkuNo()+"')]"));
	}
	/**
	 * 开始拼柜
	 * @return ConslidationPage
	 */
	public ConslidationPage startConslidation() {
		mywait(keyword);
		click(consolidationAllowed);
		mywait(startConslidation);
		click(startConslidation);
		return new ConslidationPage(driver);
	}
	
	/**
	 * 确认采购
	 */
	public void confirmOrder() {
		dynamicLoad(By.xpath("//*[@class='products-list ng-scope']/div[2]/div/div[1]/div/span[5]/div/button"));
		click(confirmOrder);
	}
}
