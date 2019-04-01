package com.essa.pageObject.DocumentaryManage;

import com.essa.framework.Model;
import com.essa.framework.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.essa.framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;
/**
* @Description: Po看板页面
* @Author: ZengJin
* @CreateTime: 2018/11/1
*/
public class POBoardPage extends BasePage {

	public POBoardPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	@FindBy (xpath = "//*[text()='订单任务看板']")
	WebElement taskBoard;//订单任务看板

	@FindBy (xpath = "//*[@ng-model='keyword']")
	WebElement keyword;//关键字查询输入框

	@FindBy (xpath ="//*[@ng-model='keyword']/../span/button")
	WebElement search;//查询按钮

	@FindBy (xpath = "//*[@id='detailView']/div/div[3]/div[1]/div/div/table/tbody/tr[1]/td/div[2]/div[1]/input")
	WebElement firstCheckbox;//第一个复选框

	@FindBy (xpath ="//button[contains(text(),'转在途尾货')]")
	WebElement toTail;//转在途尾货按钮

	// 通知收货
	@FindBy (xpath = "//button[contains(text(),'通知收货')]")
	WebElement noticeReceive;

	//收货日期
	@FindBy (xpath = "//*[contains(text(),'收货日期')]")  ///label[@class='control-label col-sm-8']
			WebElement reveiveDate;

	// 收货日期输入框
	@FindBy (xpath = " //div[@class='col-sm-16']//input[@type='text']")
	WebElement inputReceiveDate;

	// 确定
	@FindBy (xpath = "//button[contains(text(),'确定')]")
	WebElement submit;

	// 通知装柜
	@FindBy(xpath = "//button[contains(text(),'通知装柜')]")
	WebElement noticeLoad;

	// 装柜日期空白处
	@FindBy(xpath = " //label[@class='control-label col-md-8']")
	WebElement loadDate;

	// 装柜日期输入框
	@FindBy (xpath = "//input[@name='newDate']")
	WebElement inutLoadDate;


	/*
	 * 页面方法
	 */
	/**
	 * 转在途尾货
	 * @return 转在途尾货页面
	 */
	public ConvertTailPage toTail() {
		dynamicLoad(By.xpath("//*[style='display: block;']"));//等待页面加载完成
		click(taskBoard);
		dynamicLoad(By.xpath("//*[style='display: block;']"));
		sendKeys(keyword, Model.getSkuNo());
		click(search);
		dynamicLoad(By.xpath("//*[style='display: block;']"));
		click(firstCheckbox);
		click(toTail);
		return new ConvertTailPage(driver);
	}

	/**
	 * PO通知收货和通知装柜
	 */
	// 通知收货
	public POBoardPage noticeReceive(){
		click(noticeReceive);
		jsExecutorRemoveAttribute(inputReceiveDate,"readonly");
		sendKeys(inputReceiveDate, Tools.getToday());
		click(reveiveDate);
		click(submit);
		return new POBoardPage(driver);
	}

	public boolean isNoticeReceSucceed(){
		forceWait(2000);
		return isVisibility(By.xpath("//*[contains(text(),'收货中')]"));
	}

	//通知装柜
	public POBoardPage noticeLoad(){
		click(noticeLoad);
		jsExecutorRemoveAttribute(inutLoadDate,"readonly");
		sendKeys(inutLoadDate,Tools.getToday());
		click(loadDate);
		forceWait(1000);
		click(submit);
//		forceWait(2000);
		return new POBoardPage(driver);
	}

	public boolean isNoticeLoadSucceed(){
		forceWait(2000);
		return isVisibility(By.xpath("//*[contains(text(),'装柜中')]"));
	}


}
