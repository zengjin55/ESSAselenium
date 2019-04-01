package com.essa.pageObject.StorageManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *收货通知页面
 */
public class NoticeReceivePage extends BasePage {

	public NoticeReceivePage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	
	@FindBy (xpath = "//input[@name='keyword']")
	WebElement keyword;//关键字查询
	
	@FindBy (xpath = "//button[contains(@ng-click,'search')]")
	WebElement search;//查询按钮
	
	@FindBy (xpath = "//td[@data-title=\"'PO装柜仓库'\"]//span[@class='ng-scope'][contains(text(),'设置')]")
	WebElement set;//PO装柜仓库的“设置”
	
	@FindBy (xpath = "//*[@ng-click='chooseWarehouse(warehouse)']/../li[1]")
	WebElement wareHouse;//仓库
	
	@FindBy (xpath = "//*[@ng-click='chooseZone(zone)']/../li[1]")
	WebElement zone;//区位
	
	@FindBy (xpath = "//*[@ng-click='chooseShelf(shelf)']/../li[1]")
	WebElement shelf;//货区
	
	@FindBy (xpath = "//button[contains(text(),'确定')]")
	WebElement confirm;//确定
	
	@FindBy (xpath ="//button[@data-bb-handler='confirm']")
	WebElement sysch;//是否同步提示框的确定按钮
	
	@FindBy (xpath ="//button[@data-bb-handler='ok']")
	WebElement succeed;//设置成功的确定按钮
	
	/*
	 * 页面方法
	 */
	
	/**
	 * 设置装柜仓库
	 */
	public NoticeReceivePage setWareHouse() {
		dynamicWait(By.xpath("//div[@style='display: none;' and @id='mask']"));
		sendKeys(keyword, Model.getPoNum());
		click(search);
		dynamicWait(By.xpath("//div[@style='display: none;' and @id='mask']"));
		click(set);
		forceWait(500);
		jsExecutorClick(wareHouse);
		jsExecutorClick(zone);
		jsExecutorClick(shelf);
//		click(wareHouse);
//		forceWait(1000);
//		click(zone);
//		forceWait(1000);
//		click(shelf);
//		forceWait(1000);
		click(confirm);
		forceWait(1000);
		click(sysch);
		forceWait(1000);
		isVisibility(By.xpath("//*[text()='设置成功']"));
		click(succeed);
		return new NoticeReceivePage(driver);
	}

}
