package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *市场商品发布页
 */
public class MarketGoodsRelesePage extends BasePage {

	public MarketGoodsRelesePage (WebDriver driver) {
		super(driver);
	}
	/*
	 * 页面元素定位
	 */
	
	//采购商编号
	@FindBy (xpath="//*[@placeholder='请输入采购商编号']")
	WebElement buyerNo;
	
	//查询按钮
	@FindBy (xpath="//*[text()='查询']")
	WebElement search;
	
	//市场商品发布--列表第一个
	@FindBy (xpath="//*[@id='tableViewList']/div/div/div/div[2]/table/tbody/tr[1]/td[9]/button")
	WebElement goodsRelese1;
	
	/*
	 * 页面方法
	 */
	/**
	 * 选择采购商，点击市场发布，进入市场商品建档页面
	 * @return AddMarketGoodsPage
	 */
	public AddMarketGoodsPage toAddMarketGoodsPage() {
		mywait(goodsRelese1);
		sendKeys(buyerNo, Model.getBuyerNo());
		click(search);
		forceWait(1000);
		mywait(goodsRelese1);
		click(goodsRelese1);
		forceWait(1000);
		switchWindow();
		return new AddMarketGoodsPage(driver);
	}
	
}
