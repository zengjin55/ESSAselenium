package com.buyer.pageObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.framework.Tools;

/**
 * 采购商注册页
 * @author Administrator
 *
 */
public class RegisterPage extends BasePage {
	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	//检查点-是否进入页面
	@FindBy (xpath ="//*[contains(text(),' Own essa.cn account')]")
	WebElement checkPoint;
	
	//email
	@FindBy (xpath ="//*[@id='email']")
	WebElement email;
	
	//password
	@FindBy (xpath ="//*[@id='password']")
	WebElement password;
	
	//confirmPassword
	@FindBy (xpath ="//*[@id='confirmPassword']")
	WebElement confirmPassword;
	
	//币种 currency
	@FindBy (xpath ="//*[@id='currPlaceholder']")
	WebElement currPlaceholder;
	
	//CNY 
	@FindBy (xpath="//*[text()='CNY']")
	WebElement CNY;
	
	//companyName
	@FindBy (xpath="//*[@id='companyName']")
	WebElement companyName;
	
	//空白的地方-用于关闭选项框
	@FindBy (xpath ="//*[@class='register-right']")
	WebElement blank;
	
	//区域continent
	@FindBy (xpath ="//*[@id='continentPlaceholder']")
	WebElement continentPlaceholder;
	
	//欧洲europe
	@FindBy (xpath="//*[text()='Europe']")
	WebElement Europe;
	
	//country
	@FindBy (xpath ="//*[@id='countryPlaceholder']")
	WebElement country;
	
	//俄罗斯Russian
	@FindBy (xpath ="//*[text()='Russian federation']")
	WebElement Russian;
	
	//contactName
	@FindBy (xpath ="//*[@id='contactName']")
	WebElement contactName;
	
	//phone
	@FindBy (xpath ="//*[@id='phone']")
	WebElement phone;
	
	//purchaseQuantity
	@FindBy (xpath ="//*[@id='purchaseQuantity']")
	WebElement purchaseQuantity;
	
	//quantity
	@FindBy (xpath ="//*[contains(text(),'1 000 000 - 2 500 000 USD')]")
	WebElement quantity;
	
	//singleQuantity
	@FindBy (xpath ="//*[@id='singleQuantity']")
	WebElement singleQuantity;
	
	//single
	@FindBy (xpath ="//*[text()='30-49 ctn']")
	WebElement single;
	
	//select
	@FindBy (xpath ="//*[text()='Select']")
	WebElement select;
	
	//toys1
	@FindBy (xpath ="//*[@title='Animals&plants']")
	WebElement toys1;
	
	//toys2
	@FindBy (xpath ="//*[text()='Dolls and accessories']")
	WebElement toys2;
	
	//toys3
	@FindBy (xpath ="//*[text()='Bricks']")
	WebElement toys3;
	
	//referralCode
	@FindBy (xpath ="//*[@id='referralCode']")
	WebElement referralCode;
	
	//checkBox
	@FindBy (xpath ="//*[@id='protocol']")
	WebElement checkbox;
	
	//Register
	@FindBy (xpath ="//*[@id='submit']")
	WebElement register;
	
	/*
	 * 页面方法
	 */
	/**
	 *  注册
	 * @return IndexPage
	 */
	public IndexPage register() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		String num = Tools.getTime();
		sendKeys(email, Model.getEmail());
		sendKeys(password, "essa123");
		sendKeys(confirmPassword, "essa123");
		click(currPlaceholder);
		click(CNY);
		sendKeys(companyName, "Auto"+num);
		click(continentPlaceholder);
		click(Europe);
		click(Russian);
		sendKeys(contactName, "Tester"+num);
		sendKeys(phone, format.format(new Date()));
		forceWait(500);
		click(purchaseQuantity);
		click(quantity);
		forceWait(500);
		click(singleQuantity);
		click(single);
		moveHeightScroll("100");
		jsExecutorClick(select);
		click(toys1);
		click(toys2);
		click(toys3);
		click(blank);
		sendKeys(referralCode, Model.getInvateCode());
		jsExecutorClick(checkbox);
		click(register);
		forceWait(3000);
		return new IndexPage(driver);
	}
}
