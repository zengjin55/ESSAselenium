package com.essa.pageObject.needDealt;

import com.essa.framework.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.pageObject.HomePage;

import java.util.Date;

/**
 * 当团购活动成功时，类目经理采购计划单申请页面
 * @author Administrator
 *
 */
public class NeedDealtApplyPage extends BasePage {

	public NeedDealtApplyPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * 元素定位
	 */
	
	//提交询价
	@FindBy (xpath ="//*[text()='提交询价']")
	WebElement submitInquiry;
	
	//待办工作--检查点
	@FindBy (xpath = "//*[contains(text(),'待办工作')]")
	WebElement checkpoint;
	
	//流水号
	@FindBy (xpath = "//*[contains(text(),'流水号：RW')]")
	WebElement serialNumber;
	
	//确认
	@FindBy (xpath = "//*[text()='确认']")
	WebElement confirm;
	
	//送审核
	@FindBy (xpath = "//*[text()='提交审核']")
	WebElement toAudit;
	
	//认领
	@FindBy (xpath = "//*[text()='认领']")
	WebElement claim;
	
	//同意
	@FindBy (xpath ="//*[text()='同意']")
	WebElement agree;

	//添加
	@FindBy(xpath = "//*[text()='添加']" )
	WebElement add;

	//船公司
	@FindBy(xpath = "//input[@name='shipperCompany']")
	WebElement inputShipperCompany;

	//开船日期
	@FindBy(xpath = "//input[@id='deliveryDate0']")
	WebElement inputShipperStartDate;

	//预计到港日期
	@FindBy(xpath = "//input[@id='estimatedArrivalDate0']")
	WebElement inputShipperArriveDate;

	//提交
	@FindBy(xpath = "//*[text()='提交']")
	WebElement submit;

	//暂存
	@FindBy(xpath = "//*[text()='暂存']")
	WebElement save;


	/*
	 * 页面方法
	 */
	
	/**
	 * 提交询价 
	 * @return bmps首页
	 */
	public HomePage submitInquiry() {
		mywait(checkpoint);
		forceWait(1000);
		Model.setSerialNum(partialStr(serialNumber.getText(), "流水号："));
		moveHeightScroll("100");
		click(submitInquiry);
		forceWait(500);
		click(confirm);
		dynamicLoad(By.xpath("//*[style='display: block;']"));
		return new HomePage(driver);
	}
	
	/**
	 * 类目经理修订，送审核
	 * @return bpms首页
	 */
	public HomePage toAudit() {
		mywait(checkpoint);
		forceWait(1000);
		moveHeightScroll("100");
		click(toAudit);
		forceWait(500);
		click(confirm);
		dynamicLoad(By.xpath("//*[style='display: block;']"));
		return new HomePage(driver);
	}
	
	/**
	 * 财务审核计划申请
	 * @return
	 */
	public HomePage audit() {
		mywait(checkpoint);
		forceWait(1000);
		moveHeightScroll("100");
		click(claim);
		mywait(checkpoint);
		moveHeightScroll("100");
		click(agree);
		click(confirm);
		dynamicLoad(By.xpath("//*[style='display: block;']"));
		return new HomePage(driver);
	}

	public HomePage confirmSailingDate() {
		mywait(checkpoint);
		forceWait(1000);
		Model.setSerialNum(partialStr(serialNumber.getText(), "流水号："));
		click(confirm);
		return new HomePage(driver);
	}

	/**
	 * 认领船务登记船期待办
	 * @return 首页
	 */
	public HomePage toClaimShippingMark(){

		dynamicWait(By.xpath("//*[contains(text(),'待办工作')]"));
		Model.setSerialNum(partialStr(serialNumber.getText(), "流水号："));
		if(isVisibility(By.xpath("//*[text()='认领']"))){
			moveHeightScroll("0");
			click(claim);
		}
		forceWait(3000);
		mywait(checkpoint);
		click(add);
		sendKeys(inputShipperCompany,"essa");
		//设置开船日期
		jsExecutorRemoveAttribute(inputShipperStartDate,"readonly");
		inputShipperStartDate.clear();
//		String date = getDateTimeByFormat(new Date(),"MM/dd/yyyy");
		sendKeys(inputShipperStartDate, Tools.getNextMonth());
		//设置预计到港日期
		jsExecutorRemoveAttribute(inputShipperArriveDate,"readonly");
		inputShipperArriveDate.clear();
		sendKeys(inputShipperArriveDate,Tools.getNextMonth());
		//提交
		click(submit);
		dynamicLoad();
		return new HomePage(driver);
	}


}
