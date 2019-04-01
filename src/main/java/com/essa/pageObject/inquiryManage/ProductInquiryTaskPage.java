package com.essa.pageObject.inquiryManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator 成品询价任务列表
 */
public class ProductInquiryTaskPage extends BasePage {
	public ProductInquiryTaskPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * 元素定位
	 */
	// 高级查询
	@FindBy(xpath = "//*[contains(text(),'高级查询')]")
	WebElement advancedQuery;

	// 商品编码查询
	@FindBy(xpath = "//*[text()='商品编码']/../div/input")
	WebElement skuNoQuery;

	// 根据PO查询
	@FindBy(xpath = "//*[text()='来源单号']/../div[1]/input")
	WebElement POQuery;

	// 高级查询-查询按钮
	@FindBy(xpath = "//*[@name='advSearch']/div[12]/button[1]")
	WebElement search;

	// 列表第一行数据
	@FindBy(xpath = "//*[@ng-table='tableParams1']/tbody/tr[1]")
	WebElement firstRow;

	// 询价反馈
	@FindBy(xpath = "//*[contains(text(),'询价反馈')]")
	WebElement feedBack;

	// 暂无数据--用于检查是否已经成功
	@FindBy(xpath = "//*[text()='暂无数据']")
	WebElement isSucceed;

	/*
	 * 页面方法
	 */
	/**
	 * 查出要成品询价的商品，进入成品询价反馈页面
	 * 
	 * @return ProductInquiryFeedback
	 */
	public ProductInquiryFeedbackPage toFeedback() {
//		mywait(firstRow);
		dynamicWait(By.xpath("//*[@ng-table='tableParams1']/tbody/tr[1]"));
		click(advancedQuery);
		sendKeys(skuNoQuery, Model.getSkuNo());
		click(search);
		forceWait(1000);
		mywait(firstRow);
		click(firstRow);
		click(feedBack);
		return new ProductInquiryFeedbackPage(driver);
	}

	/**
	 * 判断页面是否查询不到该商品
	 * 
	 * @return boolean
	 */
	public boolean isSucceed() {
		click(advancedQuery);
		sendKeys(skuNoQuery, Model.getSkuNo());
		click(search);
		forceWait(1000);
		return isVisibility(By.xpath("//*[text()='暂无数据']"));
	}

	/**
	 * 判断根据po单查询，是否全部询价完成
	 * 
	 * @return boolean
	 */
	public boolean isPOSucceed() {
		forceWait(1000);
		return isVisibility(By.xpath("//*[@ng-table='tableParams1']/tbody/tr[1]"));
	}

	/**
	 * 根据PO单号查询询价
	 * 
	 * @return 询价反馈页面
	 */
	public ProductInquiryFeedbackPage POtoFeedback() {
		forceWait(1000);
		dynamicWait(By.xpath("//*[@ng-table='tableParams1']/tbody/tr[1]"));
		click(advancedQuery);
		sendKeys(POQuery, Model.getPoNum());
		click(search);
		dynamicWait(By.xpath("//*[@id='mask' and @style='display: none;']"));
		click(firstRow);
		click(feedBack);
		return new ProductInquiryFeedbackPage(driver);
	}

	/**
	 * 如果列表中还存在待询价的单，循环方法，继续询价
	 * 
	 * @return
	 */
	public ProductInquiryFeedbackPage POcircle() {
		click(firstRow);
		click(feedBack);
		return new ProductInquiryFeedbackPage(driver);
	}
}
