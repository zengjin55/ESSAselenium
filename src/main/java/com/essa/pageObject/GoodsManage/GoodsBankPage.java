package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *商品库页
 */
public class GoodsBankPage extends BasePage {
	public GoodsBankPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 页面元素定位
	 */
	
	//商品名称
	private  WebElement getSkuName() {
		String no = AddOriginalGoodsPage.skuNameNo;
		return driver.findElement(By.xpath("//*[contains(text(),'"+no+"')]"));
	}
	
	//商品编号
	private WebElement getSkuNo() {
		String no = AddOriginalGoodsPage.skuNameNo;
		return driver.findElement(By.xpath("//*[contains(text(),'"+no+"')]/../li[3]"));
	}
	
	/*
	 * 页面方法
	 */
	
	/**
	 * 获取商品编号
	 * @return No
	 */
	public String getNo() {
		dynamicWait(By.xpath("//*[@id='undercarriageGoods']/tbody[1]/tr[1]/td[1]/input"));
		String skuNo = getSkuNo().getText();
		Model.setSkuNo(skuNo);
		return skuNo;
	}
}
