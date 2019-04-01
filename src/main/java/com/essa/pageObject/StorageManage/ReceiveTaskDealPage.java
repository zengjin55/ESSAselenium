package com.essa.pageObject.StorageManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * PO收货任务处理页面
 * @author Administrator
 *
 */
public class ReceiveTaskDealPage extends BasePage {

	public ReceiveTaskDealPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (xpath = "//*[text()='全部完成收货']")
	WebElement allReceive;//全部收货完成
	
	@FindBy (xpath = "//*[text()='确定']")
	WebElement confirm;//确定
	
	/*
	 * 页面方法
	 */
	/**
	 * 全部收货完成
	 * @return 收货任务管理页
	 */
	public GoodsReceiveTaskPage allReceive() {
		forceWait(1000);
		dynamicLoad();
		moveHeightScroll("0");
		click(allReceive);
		forceWait(500);
		click(confirm);
		forceWait(1000);
		click(confirm);
		dynamicLoad();
		return new GoodsReceiveTaskPage(driver);
	}
}
