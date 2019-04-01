package com.essa.pageObject.productDev;

import com.essa.framework.Model;
import com.essa.framework.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

import java.util.Date;

/**
 * @author Administrator
 *新增项目立项书
 */
public class AddProjectPage extends BasePage {
	public AddProjectPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	//改版类型
	@FindBy (xpath ="//*[@dic-list='改版类型']")
	WebElement revisonType;
	
	//项目名称
	@FindBy (xpath ="//*[@ng-model='model.name']")
	WebElement projectName;
	
	//项目负责人
	@FindBy (xpath ="//*[@ng-model='model.leaderId']/input")
	WebElement projectLeader;
	
	//项目负责人-林荣
	@FindBy (xpath ="//*[@id='role-user-select-box']/ul/li[3]/span")
	WebElement linrong;
	
	//开发程度
	@FindBy (xpath ="//*[@dic-list='开发程度']")
	WebElement devLevel;
	
	//开发系列
	@FindBy (xpath ="//*[@ng-model='model.serialId']")
	WebElement devSerial;
	
	//团队成员-新增
	@FindBy (xpath ="//button[text()='新增']")
	WebElement addMember;
	
	//任务处理人 -第一个
	@FindBy (xpath ="//*[@class='col-md-14']/table/tbody/tr[1]/td[2]/div/input")
	WebElement Transactor1;
	
	//处理人-linrong
	@FindBy (xpath ="//tbody//tr[1]//td[2]//div[1]//div[1]//ul[1]//li[3]")
	WebElement linrong1;
	
	//任务处理人 -第二个
	@FindBy (xpath ="//*[@class='col-md-14']/table/tbody/tr[2]/td[2]/div/input")
	WebElement Transactor2;

	//处理人-第二个
	@FindBy (xpath ="//tbody//tr[2]//td[2]//div[1]//div[1]//ul[1]//li[3]")
	WebElement linrong2;
	
	//成员角色 -第一个
	@FindBy (xpath ="//*[@class='col-md-14']/table/tbody/tr[1]/td[3]/select")
	WebElement role1;
	
	//成员角色 -第二个
	@FindBy (xpath ="//*[@class='col-md-14']/table/tbody/tr[2]/td[3]/select")
	WebElement role2;
	
	//选择改版商品信息
	@FindBy (xpath ="//*[text()='选择']")
	WebElement selectGoods;

	@FindBy (xpath = "//*[@name='keyword']")
	WebElement searchContent;

	//添加商品-查询按钮
	@FindBy (xpath = "//*[@ng-click='search(false)']")
	WebElement addSkuSearch;

	//添加商品-展开商品信息
	@FindBy (xpath = "//*[contains(@class,'fa fa-angle-double-dow')]")
	WebElement showSku;

	//添加商品-搜索结果复选框
	@FindBy (xpath = "//*[@ng-repeat='list in item.skus']/tbody")
	WebElement skuCheckbox;

	//确定按钮
	@FindBy (xpath = "//*[contains(text(),'确定')]")
	WebElement confirm;

	//保存
	@FindBy (xpath ="//*[contains(text(),'保存')]")
	WebElement save;

	//新增光身物料
	@FindBy (xpath = "//*[@ng-click='relateAdd()']")
	WebElement addBodyMat;

	/*
	页面方法
	 */
	/*
	* @Description:新增项目立项书
	* @param: []
	* @return: 新增光身物料页面
	* @Author: ZengJin
	* @CreateTime: 2018/10/29
	*/
	public AddMatterialPage addProject(){
		mywait(revisonType);
		selectElement(revisonType,"自主改版");
		Model.setProjectName("自主改版"+ Tools.getTime());
		sendKeys(projectName,Model.getProjectName());
		click(projectLeader);
		click(linrong);
		selectElement(devLevel,"小改");
		selectElement(devSerial,"男孩系列");
		click(addMember);
		click(Transactor1);
		forceWait(500);
		click(linrong1);
		selectElement(role1,"项目经理");
		click(Transactor2);
		forceWait(500);
		click(linrong2);
		selectElement(role2,"翻译人员");
		click(selectGoods);
		dynamicLoad(By.xpath("//*[@class='loading ng-scope']"));
		String no = DBSqlSearch("SELECT no FROM prd_goods_sku WHERE src=1 ORDER BY create_time DESC LIMIT 1","no");
		sendKeys(searchContent,no);
		click(addSkuSearch);
		forceWait(1000);
		click(showSku);
		click(skuCheckbox);
		click(confirm);
		forceWait(3000);
		click(save);
		forceWait(1000);
		click(addBodyMat);
		return new AddMatterialPage(driver);
	}
}
