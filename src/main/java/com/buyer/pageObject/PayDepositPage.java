package com.buyer.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * @author Administrator
 *支付定金页
 */
public class PayDepositPage extends BasePage {
	public PayDepositPage(WebDriver driver) {
		super(driver);
	}
	
	//PO信息
	@FindBy (xpath="//*[contains(text(),'PO information')]")
	WebElement POInfo;
	
	//查看资金详情---可用资金不足
	@FindBy (xpath ="//button[@class='submit-payment-deposit-button btn btn-primary']")
	WebElement FundDetail;
	
	//免定金模式--确认按钮
	@FindBy (xpath ="//button[@class='btn btn-primary queding']")
	WebElement ok;

	//其他支付模式
	@FindBy (xpath = "//button[@class='btn btn-primary btn-sm loading']")
	WebElement confirm;
	
	//po单号
	@FindBy (xpath ="//*[contains(text(),'PO number:')]/../../td[2]/span")
	WebElement poNum;

	//免定金的确认
	@FindBy (xpath = "//*[@ng-click='submit()']")
	WebElement submit;
	
	/**
	 * 查看资金详情
	 * @return AssetManagementPage
	 */
	public AssetManagementPage toAssetManagementPage() {

		click(FundDetail);
		return new AssetManagementPage(driver);
	}
	
	/**
	 * 确认PO：单柜比例
	 * @return MyOrderPage
	 */
	public MyOrderPage confirm() {
		click(ok);
		if(isVisibility(By.xpath("//button[@class='btn btn-primary btn-sm loading']"))){
			click(confirm);
		}
		return new MyOrderPage(driver);
	}


	/**
	 *确认PO操作方法
	 * @param
	 * @return
	 */
	public void operate() {
		dynamicWait(By.xpath("//*[contains(text(),'PO information')]"));
		Model.setPoNum(poNum.getText());

		if (isVisibility(By.xpath("//button[@class='submit-payment-deposit-button btn btn-primary']"))) {
			//如果是可用资金不足，跳转到资金管理
			toAssetManagementPage();
		}else if (isVisibility(By.xpath("//*[@ng-click='submit()']"))) {
			//免定金模式，确认po，跳转到我的订单列表
			click(submit);
		}else {
			//单柜比例定金
			confirm();
		}
		forceWait(2000);
	}
}
