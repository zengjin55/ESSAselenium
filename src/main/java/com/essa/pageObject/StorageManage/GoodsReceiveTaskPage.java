package com.essa.pageObject.StorageManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * 收货任务管理页面
 * @author Administrator
 *
 */
public class GoodsReceiveTaskPage extends BasePage {

	public GoodsReceiveTaskPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * 元素定位
	 */
	@FindBy (xpath = "//*[@name='selectKey']")
	WebElement keyword;//关键字查询
	
	@FindBy (xpath = "//button[contains(@ng-click,'search')]")
	WebElement search;//查询按钮
	
	@FindBy (xpath = "//tbody/tr[1]")
	WebElement firstResult;//列表第一个结果
	
	@FindBy (xpath = "//button[contains(text(),'处理')]")
	WebElement deal;//处理

	@FindBy (xpath = "//tbody//tr[1]/td[8]")
	WebElement status;//第一个结果的状态
	
	/*
	 * 页面方法
	 */
	/**
	 * 选择第一个筛选结果，点击处理
	 * @return PO收货任务处理页
	 */
	public GoodsReceiveTaskPage dealReceive() {
		forceWait(1000);
		dynamicWait(By.xpath("//tbody//tr[1]"));
		sendKeys(keyword, Model.getPoNum());
		click(search);
		dynamicLoad();
		click(firstResult);
		click(deal);
		return new GoodsReceiveTaskPage(driver);
	}
	/**
	 *判断是否全部收货完成：true完成，false未完成
	 * @param
	 * @return boolean
	 */
	public boolean isFinish(){
		forceWait(500);
		boolean b = isVisibility(By.xpath("//*[text()='完成']"));
		return b;
	}
}
