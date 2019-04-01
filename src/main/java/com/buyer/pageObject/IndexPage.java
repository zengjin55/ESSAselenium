package com.buyer.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * 采购商首页
 * @author Administrator
 *
 */
public class IndexPage extends BasePage {
	public IndexPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	//登录-sign in
	@FindBy (xpath ="//*[@id='SignIn']/a")
	WebElement signIn;
	
	//注册-register
	@FindBy (xpath ="//*[@class='u-login']/a")
	WebElement register;
	
	//关键字输入框
	@FindBy (xpath ="//*[@class='search-tx']/input")
	WebElement searchText;
	
	//查询按钮
	@FindBy (xpath ="//*[@class='btn btn-primary search-btn']")
	WebElement searchButton;
	
	//绑定手机弹窗-关闭按钮
	@FindBy (xpath ="//*[contains(@class,'layui-layer-close1')]")
	WebElement closed;
	
	//右上角购物车
	@FindBy (xpath ="//*[@id='miniCart']")
	WebElement minicart;
	
	/*
	 * 页面方法
	 */
	/**
	 * 进入登录页面
	 * @return BuyerLoginPage
	 */ 
	public BuyerLoginPage toLoginPage() {
		click(signIn);
		return new BuyerLoginPage(driver);
	}
	
	/**
	 * 进入注册页面
	 * @return RegisterPage
	 */
	public RegisterPage toRegisterPage() {
		click(register);
		return new RegisterPage(driver);
	}
	
	/**
	 * 进入购物车
	 * @return ShoppingCartPage
	 */
	public ShoppingCartPage toShoppingCartPage() {
		if (isVisibility(By.xpath("//*[@id='bind-phone']"))) {
			forceWait(1000);
			click(closed);
		}
		click(minicart);
		return new ShoppingCartPage(driver);
	}
	/**
	 * 关键字搜索商品
	 * @return KeywordResultPage
	 */
	public KeywordResultPage keywordSearch() {
		//判断是否绑定手机
		if (isVisibility(By.xpath("//*[@id='bind-phone']"))) {
			click(closed);
		}
		//如果是非活动
//		if (Model.getIsactivity()==0 &&(Model.getSkuNo().equals("选填，勿填活动商品")||Model.getSkuNo().equals(""))) {
		if (Model.getIsactivity()==0 &&(("选填，勿填活动商品").equals(Model.getSkuNo())||("").equals(Model.getSkuNo()))) {
			//判断是否自定义输入商品编号
			String sql = "SELECT s.id,s.`no` from prd_category c LEFT JOIN prd_goods g on c.id = g.category_id LEFT JOIN prd_goods_sku s on s.goods_id = g.id where find_in_set(5,REPLACE(c.path,'.',',')) AND s.`no` > 200120000\n" +
					"AND s.id NOT IN (SELECT g.sku_id FROM sale_group_buy g) AND s.id NOT IN (SELECT t.sku_id FROM sale_shopping_cart t) AND s.src = 1 AND s.`status` = 2 AND s.is_stop_product = 0 AND s.is_delete = 0 \n" +
					"AND g.is_category_error = 0 AND G.is_attribute_error = 0 AND G.is_option_error = 0 AND G.is_spec_error = 0 limit 1;";
			Model.setSkuNo(DBSqlSearch(sql, "no"));
		}else if (Model.getIsactivity()==1 &&(("选填，勿填非活动商品").equals(Model.getSkuNo())||("").equals(Model.getSkuNo()))) {
			//活动商品	
			String sql = "SELECT s.id,s.`no` from prd_category c LEFT JOIN prd_goods g on c.id = g.category_id LEFT JOIN prd_goods_sku s on s.goods_id = g.id LEFT JOIN sale_group_buy a on a.sku_id = s.id \n" +
					"where s.`no` > 200000000 AND s.id NOT IN (SELECT t.sku_id FROM sale_shopping_cart t) AND a.`status` = 20 and s.src = 1 AND g.is_category_error = 0 AND G.is_attribute_error = 0 AND G.is_option_error = 0 AND G.is_spec_error = 0 limit 1;";
			Model.setSkuNo(DBSqlSearch(sql, "no"));
		}
//		System.out.println(Model.getSkuNo());
		sendKeys(searchText, Model.getSkuNo());
		click(searchButton);
		return new KeywordResultPage(driver);
	}
	
	/**
	 * 断言是否注册成功，根据是否有绑定手机号的弹窗来判断
	 * @return boolean
	 */
	public boolean isSucceed() {
		return isVisibility(By.xpath("//*[contains(@class,'layui-layer-close1')]"));
	}
}
