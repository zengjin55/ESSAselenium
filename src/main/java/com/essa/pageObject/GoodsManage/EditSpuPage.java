package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * @author Administrator
 *编辑spu页
 */
public class EditSpuPage extends BasePage {
	 public EditSpuPage(WebDriver driver) {  
	        super(driver);  
	    } 
	 /*
	  * 页面元素定位
	  */
	 
	 //上传图片
	 @FindBy(xpath="//*[@class='webuploader-element-invisible']/../label")
	 WebElement upDatePic;
	 
	 //保存
	 @FindBy(xpath="//*[text()='保存']")
	 WebElement save;
	 
	 /*
	  * 页面方法
	  */
	 
	 /**
	  * 编辑spu图片
	 * @return GoodsRelesePage
	 * @throws InterruptedException 
	 */
	public GoodsRelesePage editSpuPic() throws InterruptedException {
		 moveHeightScroll("30");
		 jsExecutorClick(upDatePic);
		 moveHeightScroll("0");
		 click(save);
		 return new GoodsRelesePage(driver);
	 }
}
