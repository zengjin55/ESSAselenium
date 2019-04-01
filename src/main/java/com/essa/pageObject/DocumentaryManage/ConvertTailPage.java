package com.essa.pageObject.DocumentaryManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * 转在途尾货页面
 * @author Administrator
 *
 */
public class ConvertTailPage extends BasePage {

	public ConvertTailPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	@FindBy (xpath = "//*[@name='tailDeliveryDay']")
	WebElement tailDeliveryDay;//尾货货期
	
	@FindBy (xpath ="//*[@name='moveOutQuantity']")
	WebElement moveOutQuantity;//转出箱数
	
	@FindBy (xpath ="//button[text()='保存']")
	WebElement save;//保存
	
	@FindBy (xpath ="//button[text()='确定']")
	WebElement confirm;//保存
	
	/*
	 * 页面方法
	 */
	/**
	 * 对sku转在途尾货
	 * @return PO单看板页面
	 */
	public POBoardPage convertTail() {
		dynamicWait(By.xpath("//*[@name='tailDeliveryDay']"));
		sendKeys(tailDeliveryDay, "7");
		sendKeys(moveOutQuantity, "2");
		click(save);
		dynamicWait(By.xpath("//button[text()='确定']"));
		click(confirm);
		return new POBoardPage(driver);
	}
}
