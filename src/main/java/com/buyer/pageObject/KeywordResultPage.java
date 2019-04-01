package com.buyer.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;

/**
 * 关键字查询结果页
 * @author Administrator
 *
 */
public class KeywordResultPage extends BasePage {
	public KeywordResultPage(WebDriver driver) {
		super(driver);
	}
	/*
	 * 元素定位
	 */
	//加入购物车
	@FindBy (xpath ="//*[@id='product-list']/ul[1]/div[1]/li[1]/div[1]/div[1]/div[4]/div[2]")
	WebElement addToCart;
	
	//确认添加
	@FindBy (xpath ="//*[@id='product-list']/ul[1]/div[1]/li[1]/div[2]/div[3]/div[1]")
	WebElement confirmAdd;
	
	//添加购物车时，提示加入一款成功toast
	@FindBy (xpath ="//*[@class='util-bill-pd']")
	WebElement toast;
	
	//右上角购物车
	@FindBy (xpath ="//*[@id='miniCart']")
	WebElement minicart;
	
	//订购量
	@FindBy (xpath="//*[contains(text(),'quantity')]/../span[2]/input")
	WebElement count;
	
	//活动商品-添加购物车
	@FindBy (xpath ="//*[@class='btn-box']/div[2]")
	WebElement addToCart1;
	
	//查询输入框
	@FindBy (xpath ="//*[@class='search-tx']/input")
	WebElement searchText;
	
	/*
	 * 页面方法
	 */
	/**
	 * 加入到购物车中，且会进入购物车校验是否加入成功
	 * @return ShoppingCartPage
	 */
	public ShoppingCartPage addToCart() {
		if (Model.getIsactivity() == 0) {//该sku为非活动商品
			click(addToCart);
			forceWait(1000);
			click(count);
			sendKeys(count, "50");
		}else if (Model.getIsactivity() == 1) {//该sku为活动商品
			click(addToCart1);
			forceWait(1000);
			click(count);
			sendKeys(count, "200");
		}
		
		click(confirmAdd);
		dynamicWait(By.xpath("//*[@class='util-bill-pd']"));
		forceWait(1000);
		click(minicart);
		if (Model.getIsactivity() == 1) {
			getManager();//找出对应的类目经理
		}
		return new ShoppingCartPage(driver);
	}
	/**
	 * 由于操作过快，系统会提示：有人正在操作
	 * sku加入到购物车，但不进入购物车，防止无法操作后续的拼柜
	 * 此方法与addToCart()一样，只是不进入购物车
	 */
	public void addSku() {
		if (Model.getIsactivity() == 0) {
			click(addToCart);
			forceWait(1000);
			click(count);
			sendKeys(count, "50");
		}else if (Model.getIsactivity() == 1) {
			click(addToCart1);
			forceWait(1000);
			click(count);
			sendKeys(count, "200");
		}
		click(confirmAdd);
		dynamicWait(By.xpath("//*[@class='util-bill-pd']"));
		if (Model.getIsactivity() == 1) {
			sendKeys(searchText, "查询数据库ing……程序还在进行，无聊可以先去撩撩妹子呀！");
			getManager();//找出对应的类目经理
		}
	}
	
	/**
	 * 根据sql查出商品对应类目经理,并在model类中设置其值
	 */
	public void getManager() {
		//查找商品对应的类目经理sql
		String sql = "SELECT u.account_name from prd_goods g \r\n" + 
				"LEFT JOIN prd_goods_sku s on g.id = s.goods_id\r\n" + 
				"LEFT JOIN pri_user u on u.id = g.category_manager_id\r\n" + 
				"where s.`no` in ("+Model.getSkuNo()+");";
		Model.setManager(DBSqlSearch(sql, "account_name"));
	}
}
