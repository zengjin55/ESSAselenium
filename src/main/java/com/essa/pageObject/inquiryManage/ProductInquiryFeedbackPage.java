package com.essa.pageObject.inquiryManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator 成品询价反馈页
 */
public class ProductInquiryFeedbackPage extends BasePage {
	public ProductInquiryFeedbackPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * 元素定位
	 */
	// 查询类型
	@FindBy(xpath = "//*[@ng-change='changeSearchType()']")
	WebElement searchType;

	// 查询条件输入框
	@FindBy(xpath = "//*[@placeholder='请输入查询条件']")
	WebElement searchText;

	// 查询按钮
	@FindBy(xpath = "//button[@ng-click='getFeedbackDetailBySearchInfo()']")
	WebElement search;

	// 单箱金额
	@FindBy(xpath = "//*[contains(text(),'单箱金额')]")
	WebElement singlePrice;

	// 待反馈任务
	@FindBy(xpath = "//*[contains(text(),'待反馈任务')]")
	WebElement waitFeedback;

	// 提交审核
	@FindBy(xpath = "//*[@ng-click='submitFeedback(1)']")
	WebElement submit;

	/*
	 * 页面方法
	 */
	/**
	 * 提交询价任务
	 * 
	 * @return ProductInquiryTask
	 */
	public ProductInquiryTaskPage submit() {
		// 根据单箱价格是否为空来判断页面是否加载成功
		for (int count = 0;"单箱金额：".equals(getText(singlePrice))&& count < 7;count++) {
			forceWait(1000);
		}
		selectElement(searchType, "商品编号");
		sendKeys(searchText, Model.getSkuNo());
		// 根据sku编号来判断是否加载出想要的sku信息
		click(search);
		forceWait(1000);
		dynamicWait(By.xpath("//*[contains(text(),'" + Model.getSkuNo() + "')]"));
		click(waitFeedback);
		// 判断是否加载待反馈视图
		dynamicWait(By.xpath("//*[contains(text(),'询价要求完成时间')]"));
		moveHeightScroll("0");
		click(submit);
		return new ProductInquiryTaskPage(driver);
	}

	/**
	 * 根据PO单，询价
	 * @return
	 */
	public ProductInquiryTaskPage POsubmit() {
		// 根据单箱价格是否为空来判断页面是否加载成功
		for (int count=0;"单箱金额：".equals(getText(singlePrice))&&count<7;count++) {
			forceWait(1000);
		}
		selectElement(searchType, "来源PO单/成品采购单号");
		sendKeys(searchText, Model.getPoNum());
		click(search);
		dynamicLoad(By.xpath("//*[@id='mask' and @style='display: none;']"));
		click(waitFeedback);
		forceWait(500);
		dynamicWait(By.xpath("//*[contains(text(),'询价要求完成时间')]"));
		moveHeightScroll("0");
		toSubmit();
		return new ProductInquiryTaskPage(driver);
	}
	
	/**
	 * 如果【提交审核】在页面中存在，则一直点击，最多等7秒
	 */
	private void toSubmit() {
		for (int count =0;isVisibility(By.xpath("//*[@ng-click='submitFeedback(1)']"))&&count<7;count++) {
			click(submit);
			forceWait(1000);
		}
	}
}
