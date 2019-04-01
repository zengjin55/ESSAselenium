package com.essa.pageObject.DocumentaryManage;

import com.essa.framework.Model;
import org.openqa.selenium.By;
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
	@FindBy (xpath = "//*[contains(text(),'高级查询')]")
	WebElement advancedQuery;//高级查询

	@FindBy (xpath = "//*[text()='PO单号:']/../div[1]/input")
	WebElement OrderNo;//PO单号

	@FindBy (xpath = "//*[text()='查询']")
	WebElement search;//查询按钮

	@FindBy (xpath = "//*[@id='listView']/div/table/tbody/tr[1]/td/div/div[1]/div/ul/li[1]/a")
	WebElement detail;//搜索结果中的一个查看详情

	// 关键字查询
	@FindBy (xpath = "//*[contains(@placeholder,'请输入PO单号、客户编号等关键字查询')]")
	WebElement keySearch;

	// 放大镜查询按钮
	@FindBy (xpath = "//*[contains(@ng-click,'search()')]")
	WebElement magnifierSearch;

	// 查看详情
	@FindBy (xpath = "//*[contains(text(),'查看详情')]")
	WebElement followDetail;

	/*
	 * 页面方法
	 */
	public POBoardPage todetail() {
		mywait(detail);
		click(advancedQuery);
		sendKeys(OrderNo, Model.getPoNum());
		click(search);
		dynamicLoad(By.xpath("//*[style='display: block;']"));
		click(detail);
		return new POBoardPage(driver);
	}

	/**
	 * 进入PO跟单详情
	 * @return
	 */
	public PODocumentaryListPage toPoFollowDetail(){
		forceWait(1000);
		sendKeys(keySearch, Model.getPoNum());
		click(magnifierSearch);
		dynamicLoad();
		click(followDetail);
		forceWait(3000);
		dynamicLoad();
		return new PODocumentaryListPage(driver);
	}
}
