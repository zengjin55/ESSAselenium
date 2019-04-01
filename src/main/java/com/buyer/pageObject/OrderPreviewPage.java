package com.buyer.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *订单预览页
 */
public class OrderPreviewPage extends BasePage {
	public OrderPreviewPage(WebDriver driver) {
		super(driver);
	}
	
	//是否授权:同意
	@FindBy (xpath ="//*[@ng-checked='list.isAuthorExchange==1']")
	WebElement agree;
	
	//提交po
	@FindBy (xpath ="//*[@id='react-orderConfirmation-submit']")
	WebElement sendPO;
	
	//弹框：确认
	@FindBy (xpath ="//a[text()='Send PO']")
	WebElement confirm;
	
	/**
	 * 提交po
	 * @return PayDepositPage
	 */
	public PayDepositPage submit() {
//		while(!(isVisibility(By.xpath("//*[@ng-checked='list.isAuthorExchange==1']")))) {
//			forceWait(1000);
//		}
		dynamicWait(By.xpath("//*[@ng-checked='list.isAuthorExchange==1']"));
		moveHeightScroll("50");
		forceWait(500);
		jsExecutorClick(agree);
		click(sendPO);
		forceWait(1000);
		click(confirm);
		return new PayDepositPage(driver);
	}
}
