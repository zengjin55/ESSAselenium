package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *审核原厂商品列表页
 */
public class AuditOriginalGoodsPage extends BasePage {
	public AuditOriginalGoodsPage(WebDriver driver) {  
        super(driver);  
    }  
	
	/*
	 * 元素定位
	 */
	
	//第一个复选框，同时也是检查点
	@FindBy(xpath="//*[@ng-table='tableParams']/tbody[1]/tr[1]/td[1]/input")
	WebElement checkBox;
	
	/**
	 * 根据商品名称获得其复选框
	 * @return WebElement
	 */
	private WebElement getCheckBox() {
		String no = AddOriginalGoodsPage.getSkuNameNo();
		return driver.findElement(By.xpath("//*[contains(@class,'col-sm-') and contains(text(),'"+no+"')]/../../../../td[1]/input"));
	}
	
	//审核
	@FindBy(xpath="//*[text()='审核']")
	WebElement audit;
	
	//批量通过并上架
	@FindBy(xpath="//*[text()='批量通过并上架']")
	WebElement batchAudit;
	
	/*
	 * 页面方法
	 */
	
	/**
	 * 选择商品，并进入审核页面
	 * @return AuditOriginalOpreatePage
	 */
	public AuditOriginalOpreatePage toAuditOriginalSku() {
//		forceWait(2000);
//		mywait(checkBox);
		dynamicWait(By.xpath("//*[@ng-table='tableParams']/tbody[1]/tr[1]/td[1]/input"));
		click(getCheckBox());
		click(audit);
		return new AuditOriginalOpreatePage(driver);
	}
	
	/**
	 * 用于断言列表是否有要找的商品
	 * @return boolean
	 */
	public boolean isSucceed() {
//		mywait(checkBox);
//		forceWait(2000);
		dynamicWait(By.xpath("//*[@ng-table='tableParams']/tbody[1]/tr[1]/td[1]/input"));
		String SkuNameNo = AddOriginalGoodsPage.getSkuNameNo();
		boolean b = isVisibility(By.xpath("//*[contains(text(),'"+SkuNameNo+"')]"));
		return b;
	}
	
}
