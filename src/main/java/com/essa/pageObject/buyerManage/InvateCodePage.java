package com.essa.pageObject.buyerManage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *邀请码生成页面
 */
public class InvateCodePage extends BasePage {
	public InvateCodePage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	
	//第一个业务员复选框
	@FindBy(xpath ="//*[text()='选择人员']/../div/dl/dd[1]/b/input")
	WebElement firstCheckbox;
	
	//推广方式
	@FindBy (xpath="//select[contains(@ng-change,'changeChannelList')]")
	WebElement spreadForm;
	
	//推广渠道
	@FindBy (xpath="//select[@ng-model='parame.create.promoteChannelId']")
	WebElement spreadChannel;
	
	//生成
	@FindBy (xpath="//button[contains(text(),'生成')]")
	WebElement create;
	
	//确认
	@FindBy (xpath ="//*[text()='确认']")
	WebElement confirm;
	
	//关闭
	@FindBy (xpath ="//*[text()='关闭']")
	WebElement close;
	
	//生成的邀请码
	@FindBy (xpath="//tbody[@class='ng-scope']/tr[1]/td[5]")
	WebElement invateCode;
	
	/*
	 * 页面方法
	 */
	/**
	 * 生成邀请码
	 */
	public void generateCode() {
		mywait(firstCheckbox);
		click(firstCheckbox);
		selectElement(spreadForm, "搜索引擎");
		selectElement(spreadChannel, "Google");
		click(create);
		forceWait(500);
		click(confirm);
		forceWait(500);
		click(close);
		String code = invateCode.getText();
		Model.setInvateCode(code);
	}
}
