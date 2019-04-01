package com.essa.pageObject.buyPlaneManage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *商品类目经理管理配置页
 */
public class SkuCategoryManagerCongfigPage extends BasePage {
	public SkuCategoryManagerCongfigPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 页面元素定位
	 */
	
	//查询输入框
	@FindBy (xpath="//*[@placeholder='请输入关键信息查询']")
	WebElement searchContent;
	
	//高级查询
	@FindBy (xpath="//*[contains(text(),'高级查询')]")
	WebElement advancedQuery;
	
	//sku编号
	@FindBy (xpath="//*[contains(text(),'SKU编号')]/../div/input")
	WebElement skuNoQuery;
	
	//高级查询按钮
	@FindBy (xpath="//*[text()='查询']")
	WebElement query;
	
	//查询按钮
	@FindBy (xpath="//*[@ng-click='search()']")
	WebElement search;
	
	//右下角分页的GO按钮，用作检查点，等待页面加载完成
	@FindBy (xpath="//*[text()='GO']")
	WebElement checkPoint;
	
	//列表第一条数据的复选框
	@FindBy (xpath="//*[@ng-table='tableParams']/tbody/tr[1]/td[1]/input")
	WebElement firstCheckBox;
	
	//分配类目经理
	@FindBy (xpath="//*[text()='分配类目经理']")
	WebElement distribute;
	
	//选择类目经理
	@FindBy (xpath="//*[@ng-model='mgrOpt']")
	WebElement selectManager;
	
	//确认类目经理
	@FindBy(xpath="//*[text()='确认']")
	WebElement confirm;
	
	/*
	 * 页面方法
	 */
	
	/**
	 * 商品分配类目经理
	 * @return  SkuCategoryManagerCongfigPage
	 */
	public SkuCategoryManagerCongfigPage distributeManager() {
		forceWait(1000);
		mywait(advancedQuery);
		click(advancedQuery);
		sendKeys(skuNoQuery, Model.getSkuNo());
		click(query);
		mywait(firstCheckBox);
		click(firstCheckBox);
		click(distribute);
		mywait(confirm);
		selectElement(selectManager, "林荣");
		click(confirm);
		return new SkuCategoryManagerCongfigPage(driver);
	}
}
