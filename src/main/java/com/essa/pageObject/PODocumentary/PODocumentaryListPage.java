package com.essa.pageObject.PODocumentary;

import com.essa.framework.Model;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *PO跟单任务列表页面
 */
public class PODocumentaryListPage extends BasePage {

	public PODocumentaryListPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * 元素定位
	 */
	@FindBy (xpath = "//*[text()='跟单管理']")
	WebElement DocumentaryManage;//跟单管理

	@FindBy (xpath ="//*[text()='PO跟单']")
	WebElement PODocumentary;//PO跟单
	
	@FindBy (xpath = "//*[@placeholder='请输入PO单号、客户编号等关键字查询']")
	WebElement OrderNo;//输入PO单号

	@FindBy (xpath = "//*[@ng-click='search()']")
	WebElement searchPO;//搜索

	@FindBy (xpath = "//*[contains(text(),'查看详情')]")
	WebElement detail;//查看详情

    /**
     * 搜索PO单查看详情
     */

	public PODocumentaryListPage toPoDocumentaryListPage(){
	    click(DocumentaryManage);
	    click(PODocumentary);
	    sendKeys(OrderNo,Model.getPoNum());
	    forceWait(2000);
		click(searchPO);
		forceWait(2000);
	    click(detail);
	    forceWait(7000);
    return new PODocumentaryListPage(driver);
    }
}
