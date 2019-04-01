package com.essa.testSuite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.buyerManage.InvateCodePage;

/**
 * @author Administrator
 *测试用例：生成邀请码
 */
public class TestGenrateCode extends BaseTest {

WebDriver driver;
	
	@BeforeClass
	public void setUp() throws IOException {
		initsetUp();
		loginValid("admin");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	/**
	 * 生成邀请码
	 */
	@Test(description="生成采购商注册邀请码")
	public void genrateCode() {
		this.driver = getDriver();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.toInvateCodePage();
		InvateCodePage invateCodePage = PageFactory.initElements(driver, InvateCodePage.class);
		invateCodePage.generateCode();
	}
}
