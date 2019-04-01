package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *审核市场商品操作页，点击审核的那个页面
 */
public class AuditMarketOpreatePage extends BasePage {
	public AuditMarketOpreatePage(WebDriver driver) {
		super(driver);
	}
	/*元素定位*/
	
	//审核通过
	@FindBy(xpath="//*[text()='审核通过']")
	WebElement pass;
	
	/*页面方法*/
	/**
	 * 审核通过
	 * @return
	 */
	public AuditMarketGoodsPage auditPass() {
		forceWait(2000);
		moveHeightScroll("100");
		click(pass);
		return new AuditMarketGoodsPage(driver);
	}
}
