package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *审核原厂商品页面
 */
public class AuditOriginalOpreatePage extends BasePage {
	public AuditOriginalOpreatePage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	
	//审核通过
	@FindBy(xpath="//*[text()='审核通过']")
	WebElement pass;
	
	//审核通过提示语
	@FindBy(xpath="//*[text()='商品审核已通过'")
	WebElement succeedMessage;

	//定价销售
	@FindBy (xpath = "//*[@ng-model='skuItem.isFixedSale']")
	WebElement isFixedSale;

	//毛利率
	@FindBy (xpath = "//*[@ng-model='skuItem.grossProfit2']")
	WebElement grossProfit;

	//工厂货号重复时，确定按钮
	@FindBy (xpath = "//*[text()='确定']")
	WebElement confirm;
	
	/*
	 * 页面方法
	 */
	
	/**
	 * 挪动滚动条至底部，点击审核通过
	 * @return AuditOriginalGoodsPage
	 */
	public AuditOriginalGoodsPage auditPass() {
		forceWait(2000);
		moveHeightScroll("0");
		click(pass);
		forceWait(500);
		if (isVisibility(By.xpath("//*[text()='确定']")))
			click(confirm);
		return new AuditOriginalGoodsPage(driver);
	}
	/**
	 *开发商品审核通过，要设置毛利率
	 * @param
	 * @return AuditOriginalGoodsPage
	 */
	public AuditOriginalGoodsPage auditDevGoodPass(){
		forceWait(2000);
		moveHeightScroll("50");
		selectElement(isFixedSale,"否");
		sendKeys(grossProfit,"5");
		moveHeightScroll("0");
		click(pass);
		forceWait(500);
		if (isVisibility(By.xpath("//*[text()='确定']")))
			click(confirm);
		return new AuditOriginalGoodsPage(driver);
	}
	
	/**
	 * 用于断言商品审核是否通过
	 * @return boolean
	 */
	public boolean isSucceed() {
		return isElementExist(succeedMessage);
	}
	
}
