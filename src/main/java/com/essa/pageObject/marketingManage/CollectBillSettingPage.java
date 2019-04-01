package com.essa.pageObject.marketingManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *集单设置
 */
public class CollectBillSettingPage extends BasePage {
	public CollectBillSettingPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	
	//添加商品-查询输入框
	@FindBy (xpath = "//*[@name='keyword']")
	WebElement searchContent;
	
	//团购设置中的关键字输入框
	@FindBy (xpath="//*[@ng-model='searchKeyword']")
	WebElement content;
	
	//查询按钮
	@FindBy (xpath = "//button[@ng-click='gotoSearch()']")
	WebElement search;
	
	//添加商品
	@FindBy (xpath = "//*[text()='添加商品']")
	WebElement addSku;
	
	//loading，判断是否加载完成
	@FindBy (xpath="//*[@class='loading ng-scope']")
	WebElement loading;
	
	//查询列表中的第一个复选框
	@FindBy (xpath = "//*[@class='table marketing-collectbill-products']/tbody/tr[1]/td[1]/input")
	WebElement firstCheckBox;
	
	//MOQ设置
	@FindBy (xpath = "//*[text()='MOQ设置']")
	WebElement MOQSet;
	
	//阶梯价
	@FindBy (xpath = "//*[text()='阶梯价']")
	WebElement setPrice;
	
	//配置主推市场
	@FindBy (xpath = "//*[text()='配置主推市场']")
	WebElement mainMarket;
	
	//发布商品
	@FindBy (xpath = "//*[text()='发布商品']")
	WebElement publishSku;
	
	//添加商品-查询按钮
	@FindBy (xpath = "//*[@ng-click='search(false)']")
	WebElement addSkuSearch;
	
	//添加商品-展开商品信息
	@FindBy (xpath = "//*[contains(@class,'fa fa-angle-double-dow')]")
	WebElement showSku;
	
	//添加商品-搜索结果复选框
	@FindBy (xpath = "//*[@ng-repeat='list in item.skus']/tbody/tr[1]/td[1]/input")
	WebElement skuCheckbox;
	
	//确定按钮
	@FindBy (xpath = "//*[contains(text(),'确定')]")
	WebElement confirm;
	
	//活动成功MOQ
	@FindBy (xpath = "//*[@ng-model='activitySuccessMoq']")
	WebElement successMoq;
	
	//活动销售MOQ
	@FindBy (xpath = "//*[@ng-model='activitySaleMoq']")
	WebElement saleMoq;
	
	//成本价格
	@FindBy (xpath = "//*[contains(text(),'成本')]")
	WebElement firstCost;
	
	//最低价
	@FindBy (xpath = "//*[@ng-model='minPrice']")
	WebElement minPrice;
	
	//最高价
	@FindBy (xpath = "//*[@ng-model='maxPrice']")
	WebElement maxPrice;
	
	//阶梯数
	@FindBy (xpath = "//*[@ng-model='ladderCount']")
	WebElement ladderCount;
	
	//生成阶梯
	@FindBy (xpath = "//*[text()='生成阶梯']")
	WebElement creatLadder;
	
	//可选区域-东欧
	@FindBy (xpath = "//*[contains(text(),'可选择的市场区域')]/../dd/span[1]/input")
	WebElement easternEurope;
	
	//可选区域-南美
	@FindBy (xpath = "//*[contains(text(),'可选择的市场区域')]/../dd/span[2]/input")
	WebElement southAmerican;
	
	//可选区域-欧美
	@FindBy (xpath = "//*[contains(text(),'可选择的市场区域')]/../dd/span[3]/input")
	WebElement europeAndAmerica;
	
	//可选区域-东南亚
	@FindBy (xpath = "//*[contains(text(),'可选择的市场区域')]/../dd/span[4]/input")
	WebElement southeastAsia;
	
	//可选区域-中东
	@FindBy (xpath = "//*[contains(text(),'可选择的市场区域')]/../dd/span[5]/input")
	WebElement middleEast;
	
	/*
	 * 页面方法
	 */
	/**
	 * 添加商品
	 */
	public void addSku() {
		mywait(addSku);
		click(addSku);
		/*while (isVisibility(By.xpath("//*[@class='loading ng-scope']"))) {
			forceWait(1000);
		};*/
		dynamicLoad(By.xpath("//*[@class='loading ng-scope']"));
		sendKeys(searchContent, Model.getSkuNo());
		click(addSkuSearch);
		forceWait(1000);
		click(showSku);
		click(skuCheckbox);
		click(confirm);
	}
	
	/**
	 * 设置MOQ
	 */
	public void setMoq() {
		forceWait(1000);
		mywait(firstCheckBox);
		sendKeys(content, Model.getSkuNo());
		click(search);
		mywait(firstCheckBox);
		click(firstCheckBox);
		click(MOQSet);
//===================================================================此处要修改		
		sendKeys(successMoq, "150");
		sendKeys(saleMoq, "15");
		click(confirm);
	}
	/**
	 * 设置阶梯价
	 */
	public void setPrice() {
		forceWait(1000);
		click(setPrice);
		mywait(creatLadder);
		int firstCost = getFirstCost();
		sendKeys(minPrice, firstCost+30+"");
		sendKeys(maxPrice, firstCost+100+"");
		sendKeys(ladderCount, "2");
		click(creatLadder);
		forceWait(1000);
		click(confirm);
	}
	/**
	 * 设置主推市场
	 */
	public void setMarket() {
		forceWait(1000);
		click(mainMarket);
		mywait(easternEurope);
		click(easternEurope);
		click(southAmerican);
		click(easternEurope);
		click(southeastAsia);
		click(middleEast);
		forceWait(500);
		click(confirm);
		forceWait(1000);
		click(confirm);
	}
	
	/**
	 * 组装以上操作
	 * 添加商品并发布团购
	 * @return GroupSettingPage
	 */
	public GroupSettingPage publishGroup() {
		addSku();
		setMoq();
		setPrice();
		setMarket();
		forceWait(1000);
		click(publishSku);
		forceWait(1000);
		click(confirm);
		return new GroupSettingPage(driver);
	}
	
	/**
	 * 获取到商品的成本价
	 * @return price
	 */
	private int getFirstCost() {
		String p = partialStr(firstCost.getText(), "成本：￥");
		int price = Integer.parseInt(p);
		return price;
	}
}
