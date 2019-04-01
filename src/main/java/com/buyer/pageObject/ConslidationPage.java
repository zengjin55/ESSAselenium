package com.buyer.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * 暂存柜，拼柜页面
 * @author Administrator
 *
 */
public class ConslidationPage extends BasePage {
	public ConslidationPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	//关闭提示
	@FindBy(xpath="//*[@class='close']")
	WebElement close;
	
	//选择散货
	@FindBy (xpath ="//button[text()='Bulk cargo']")
	WebElement bulkCargo;

	//选择FOB
	@FindBy (xpath = "//span[text()='FOB']")
	WebElement FOB;

	//选择整柜
	@FindBy (xpath = "//button[text()='FCL']")
	WebElement FCL;

	//选择义乌
	@FindBy (xpath = "//*[text()='Yiwu']")
	WebElement Yiwu;

	//选择40HQ
	@FindBy (xpath = "//*[text()='40HQ']/..")
	WebElement hq40;
	
	//确认
	@FindBy (xpath="//button[text()='OK']")
	WebElement ok;
	
	//查询输入框
	@FindBy (xpath="//*[@id='left-search']/div/input")
	WebElement searchText;
	
	//查询按钮
	@FindBy (xpath="//*[@id='left-search']/div/span")
	WebElement search;
	
	//左边列表第一个复选框
	@FindBy (xpath="//*[@id='left-product-list']/div[1]/div/div[2]/div/ul/li[1]/input")
	WebElement leftFirstCheckbox;
	
	//中间加入右边的箭头
	@FindBy (xpath="//*[@id='icon-pi-right-big']")
	WebElement add;
	
	//右边的第一个复选框
	@FindBy (xpath="//*[@id='right-product-list']/div/div/div[2]/div/ul/li[1]/input")
	WebElement rightFirstCheckbox;
	
	//下一步，设置贴纸
	@FindBy (xpath="//*[@id='next']")
	WebElement next;
	
	//确认弹框-可能会提示价格不一致
	@FindBy (xpath ="//a[text()='OK']")
	WebElement ok2;
	
	/**
	 *  开始拼柜
	 * @return SettingMarkerPage
	 */
	public SettingMarksPage conslidation() {
		//是否有操作提示弹框
		if(isVisibility(By.xpath("//*[@class='close']"))) {
			click(close);
		}
		forceWait(1000);
		//是否有选择货柜
		if(isVisibility(By.xpath("//button[text()='Bulk cargo']"))) {
//			click(bulkCargo);
			click(FCL);
			click(Yiwu);//2018年11月12日16:28:29 添加 By zengjin
			click(FOB);//2018年11月10日13:55:31添加By zengjin
			click(hq40);
			click(ok);
			forceWait(1000);
		}
		sendKeys(searchText, Model.getSkuNo());
		click(search);
		click(leftFirstCheckbox);
		click(add);
		//是否有加入到暂存柜
		dynamicWait(By.xpath("//*[@id='right-product-list']/div/div/div[2]/div/ul/li[1]/input"));
		forceWait(2000);
		moveHeightScroll("100");
		forceWait(1000);
		click(next);
		//如果提示价格变化，确认弹框
		if(isVisibility(By.xpath("//a[text()='OK']"))) {
			click(ok2);
			forceWait(1000);
			click(next);
		}
		return new SettingMarksPage(driver);
	}
}
