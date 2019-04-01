package com.essa.pageObject.GoodsManage;

import com.essa.framework.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *商品发布管理
 */
public class GoodsRelesePage extends BasePage {
	public GoodsRelesePage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	
	//商品发布--检查点
	@FindBy(xpath="//*[text()='商品发布']")
	WebElement checkPoint;
	
	/*
	//勾选第1个复选框--后续最好根据商品名称来定位勾选框
	@FindBy(xpath="//*[@ng-table='preUploadCtrlTableParams']/tbody[1]/tr[1]/td[1]/input")
	WebElement checkBox;
	*/
	
	/**
	 * 根据商品名称获得其复选框
	 * @return
	 */
	private WebElement getCheckBox() {
		String no = AddOriginalGoodsPage.getSkuNameNo();
		return driver.findElement(By.xpath("//*[contains(text(),'"+no+"')]/../../td[1]/input"));
	}

	@FindBy (xpath = "//*[@ng-model='query.keyword']")
	WebElement keyword;//关键字

	@FindBy (xpath = "//*[@ng-click='search()']")
	WebElement search;//查询按钮

	@FindBy (xpath = "//*[@ng-table='preUploadCtrlTableParams']/tbody[1]/tr[1]/td[1]/input")
	WebElement checkbox;//第一个复选框
	
	//图片更新
	@FindBy(xpath="//*[contains(text(),'图片更新')]")
	WebElement updatePic;
	
	//编辑
	@FindBy(xpath="//*[contains(text(),'编辑')]")
	WebElement edit;
	
	/*
	 * 页面方法
	 */
	
	/**
	 * 进入更新图片页面
	 * @return UpdatePicPage
	 */
	public UpdatePicPage toUpDatePic() {
//		forceWait(2000);
//		dynamicWait(By.xpath("//*[@ng-table='preUploadCtrlTableParams']/tbody[1]/tr[1]"));
		while ("0"==driver.findElement(By.xpath("//*[text()='条数据,共']/span[2]")).getText()){
			forceWait(1000);//如果分页数据统计为0，等待
		}
		click(getCheckBox());
		click(updatePic);
		return new UpdatePicPage(driver);
	}
	
	/**
	 * 进入编辑页面
	 * @return EditSpuPage
	 * @throws InterruptedException 
	 */
	public EditSpuPage toEditSpuPage() {
		isThisPage("商品发布", checkPoint);
		click(getCheckBox());
		click(edit);
		return new EditSpuPage(driver);
	}

	/**
	 *开发商品更新图片
	 * @param
	 * @return 更新图片详情页
	 */
	public UpdatePicPage toUpdateDevGoodPic(){
		forceWait(2000);
		dynamicWait(By.xpath("//*[@ng-table='preUploadCtrlTableParams']/tbody[1]/tr[1]/td[1]/input"));
		sendKeys(keyword,Model.getSkuNo());
		click(search);
		forceWait(1500);
		click(checkbox);
		click(updatePic);
		return new UpdatePicPage(driver);
	}
	
	/**
	 * 断言商品建档是否成功
	 * @return boolean
	 */
	public boolean isAddOrignalSucceed() {
		forceWait(1000);
		dynamicWait(By.xpath("//*[@ng-table='preUploadCtrlTableParams']/tbody[1]/tr[1]/td[1]/input"));
		String SkuNameNo = AddOriginalGoodsPage.getSkuNameNo();
		//由于@findby只能输入常量，不能输入变量，所以这里用最底层的方法来寻找元素
		return isVisibility((driver.findElement(By.xpath("//*[contains(text(),'"+SkuNameNo+"')]"))));
	}
}
