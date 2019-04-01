package com.essa.pageObject.GoodsManage;

import com.essa.framework.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *更新图片
 */
public class UpdatePicPage extends BasePage {
	public UpdatePicPage(WebDriver driver) {
		super(driver);
	}
//	public static String picPath;
	
	/*
	 * 元素定位
	 */
	
	//检查点
	@FindBy(xpath="//*[text()='SPU信息']")
	WebElement checkPoint;
	
	//spu上传图片按钮
	@FindBy(xpath="//*[@id='spuWrapper']/div[3]/div[2]")
	WebElement spuUploadPic;
	
	//spu的主图
	@FindBy(xpath="//*[@class='load-pic']")
	WebElement spuPic;
	
	//sku上传图片按钮
	@FindBy(xpath="//*[@id='skuWrapper']/div/div/div[1]/div[2]")
	WebElement skuUploadPic;
	
	//SKU图片上的X
	@FindBy(xpath="//*[contains(@ng-click,'picInfo,')]")
	WebElement skuPic;
	
	//保存按钮
	@FindBy(xpath="//*[text()='保存']")
	WebElement save;
	
	//保存成功提示
	@FindBy(xpath="保存成功")
	WebElement succeedMessage;
	
	/*
	 * 以下为页面方法
	 */
	
	/**
	 * 更新商品图片
	 * @param picPath 作为商品图片的本机地址
	 * @return GoodsRelesePage
	 */
	public GoodsRelesePage upDatePic() {
		forceWait(2000);
		dynamicWait(By.xpath("//*[text()='SPU信息']"));
		click(spuUploadPic);
//		uploadFile(picPath);
		uploadFile(Model.getPicPath());
		dynamicWait(By.xpath("//*[@class='load-pic']"));
		click(skuUploadPic);
//		uploadFile(picPath);
		uploadFile(Model.getPicPath());
		dynamicWait(By.xpath("//*[contains(@ng-click,'picInfo,')]"));
		forceWait(1000);
		click(save);
		return new GoodsRelesePage(driver);
	}
	
	/**
	 * 断言更新图片是否成功
	 * @return
	 */
	public boolean isSucceed() {
		return isElementExist(succeedMessage);
	}
	
//	/**
//	 * 通过GUI设置本机图片路径
//	 * @param picpath
//	 */
//	public static void setPicPath(String picpath) {
//		picPath = picpath;
//	}
}
