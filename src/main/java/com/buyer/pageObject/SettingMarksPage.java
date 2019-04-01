package com.buyer.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;

/**
 * 设置唛头页
 * @author Administrator
 *
 */
public class SettingMarksPage extends BasePage {
	public SettingMarksPage(WebDriver driver) {
		super(driver);
	}
	//不应用唛头
	@FindBy (xpath ="//*[@title='No shipping marks for these products']")
	WebElement noNeedMarks;

	//第一个唛头
	@FindBy (xpath = "//ul[@class='list']/li[2]")
	WebElement firstMarks;
	
	//下一步
	@FindBy (xpath ="//*[text()='Next step']")
	WebElement next;
	
	//弹框提示-确定
	@FindBy (xpath ="//*[text()='OK']")
	WebElement ok;
	
	/**
	 * 设置唛头：不应用唛头
	 * @return SettingStickerPage
	 */
	public SettingStickerPage setMarks() {
		/*while(!(isVisibility(By.xpath("//*[@title='No shipping marks for these products']")))) {
			forceWait(1000);
		}*/
		dynamicWait(By.xpath("//*[@title='No shipping marks for these products']"));
		if(isVisibility(By.xpath("//ul[@class='list']/li[2]"))){
			click(firstMarks);
		}
		click(noNeedMarks);
		click(next);
		forceWait(500);
		click(ok);
		return new SettingStickerPage(driver);
	}
}
