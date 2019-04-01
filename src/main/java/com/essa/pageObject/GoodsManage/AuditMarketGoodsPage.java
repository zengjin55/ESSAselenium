package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *审核市场商品列表页
 */
public class AuditMarketGoodsPage extends BasePage {
	public AuditMarketGoodsPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	
	/*搜索控件*/
	//采购商编号
	@FindBy (xpath="//*[@placeholder='请输入采购商编号']")
	WebElement buyerNo;
	
	//sku编号
	@FindBy (xpath="//*[contains(@placeholder,'请输入多个商品编号')]")
	WebElement skuNo;
	
	//查询
	@FindBy (xpath="//*[text()='查询']")
	WebElement search;
	
	//搜索结果-第一个复选框
	@FindBy (xpath="//*[@ng-table='tableParams']/tbody[1]/tr[1]/td[1]/input")
	WebElement firstCheckBox;
	
	//审核按钮
	@FindBy (xpath="//*[text()='审核']")
	WebElement audit;
	
	/*
	 * 页面方法
	 */
	/**
	 * 选择要审核的商品，并点击审核按钮
	 * @return AuditMarketOpreatePage
	 */
	public AuditMarketOpreatePage auditMarketGoods() {
		forceWait(500);
		sendKeys(skuNo, Model.getSkuNo());
		click(search);
		forceWait(1000);
		mywait(firstCheckBox);
		click(firstCheckBox);
		click(audit);
		return new AuditMarketOpreatePage(driver);
	}
	
	/**
	 * 如果审核成功，返回false，列表中已经不存在该待审核sku
	 * @return boolean
	 */
	public boolean isSucceed() {
		forceWait(1500);
		return isVisibility(By.xpath("//*[contains(text(),'"+Model.getSkuNo()+"')]"));
	}
}
