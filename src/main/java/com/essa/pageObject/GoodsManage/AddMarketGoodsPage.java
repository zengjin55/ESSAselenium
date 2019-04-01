package com.essa.pageObject.GoodsManage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.framework.Tools;

/**
 * @author Administrator
 *添加市场商品页
 */
public class AddMarketGoodsPage extends BasePage {
	public AddMarketGoodsPage(WebDriver driver) {
		super(driver);
	}
	public static String skuNameNo = null;
	/*
	 * 页面元素定位
	 */
	/*
	 * 元素定位
	 */
	//审核人
	@FindBy(xpath="//*[@ng-model='buyerInfo.auditId']")
	WebElement auditor;
	
	//选择供应商
	@FindBy (xpath="//*[@ng-click='supplier()']")
	WebElement selectSupplier;
	
	//供应商查询方式选择
	@FindBy (xpath="//*[@ng-click='showSelect()']")
	WebElement showSelect;
	
	//供应商名称查询
	@FindBy (xpath="//*[contains(@ng-click,'keywordTypeChecked(item)') and @value='name']/..")
	WebElement queryByName;
	
	//查询输入框
	@FindBy (xpath="//*[@ng-keyup='keyupChecked()']")
	WebElement queryKeyword;
	
	//供应商查询按钮
	@FindBy (xpath="//*[@ng-click='search()']")
	WebElement search;
	
	//查询的第一个结果
	@FindBy (xpath="//*[@id='spTableParams']/tbody/tr[1]")
	WebElement firstRestult;
	
	//供应商选择【确定】
	@FindBy (xpath="//*[text()='确定']")
	WebElement sureBotton;
	
	//品牌名称选框
	@FindBy (xpath="//*[text()='品牌名称']/../div[2]/div/div")
	WebElement brandName;
	
	//品牌选项-第一个品牌
	@FindBy (xpath="//*[@class='msgLayer']/li[1]")
	WebElement firstBrand;
	
	//品牌【确定】
	//与供应商选择的确定按钮元素一样，可直接使用
	
	//选择类目
	@FindBy(xpath="//*[text()='主类目']/../../div[1]/div")
	WebElement selectCategory;
	
	//玩具类目一级类目
	@FindBy (xpath="//*[@id='undefined_1_switch']")
	WebElement oneLevel;
	
	//动植物二级类目
	@FindBy (xpath="//*[@id='undefined_7_switch']")
	WebElement twoLevel;
	
	//飞行鸟类三级类目
	@FindBy (xpath="//*[text()='飞行鸟类']")
	WebElement threeLevel;
	
	//[主类目]选择
	@FindBy (xpath="//*[contains(@class,'btn btn-success')]")
	WebElement mainCategory;
	
	
	/*
	 * 商品属性
	 */
	//商品属性-功能:第二个-遥控
	@FindBy (xpath="//*[text()='功能']/../div[2]/div[2]")
	WebElement secondFunction;
	
	//商品属性-特殊功能:第二个-声控
	@FindBy (xpath="//*[text()='特殊功能']/../div[2]/div[2]")
	WebElement secondSpcialFunction;
	
	//商品属性-外观造型:第一个-仿真
	@FindBy (xpath="//*[text()='外观造型']/../div[2]/div[1]")
	WebElement skin;
	
	//商品属性-是否带人
	@FindBy (xpath="//*[text()='是否带人']/../div[2]/div[1]")
	WebElement withPerson;
	
	//商品属性-适用年龄:第三个-6-18个月
	@FindBy (xpath="//*[text()='适用年龄']/../div[2]/div[3]")
	WebElement forAge;
	
	/*
	 * 商品信息
	 */
	
	//品名选择
	@FindBy (xpath="//*[text()='品名']/../div[2]/div")
	WebElement selectLanguage;
	
	//品名语言-英语
	@FindBy (xpath="//*[@class='msg2']/ul[1]/li[2]")
	WebElement english;
	
	//品名输入框
	@FindBy (xpath="//*[text()='品名']/../div[3]/div/input")
	WebElement goodsName;
	
	//工厂货号输入框
	@FindBy (xpath="//*[@ng-model='model.factoryNo']")
	WebElement factoryNo;
	
	//包装语言
	@FindBy (xpath = "//*[@dic-list='包装语言']/div[1]")
	WebElement packageLanguage;
	
	//自有版权
	@FindBy (xpath="//*[text()='自有版权']")
	WebElement ownCopyright;
	
	//第三方版权
	@FindBy (xpath="//*[text()='第三方版权']")
	WebElement thirdCopyright;
	
	//无授权
	@FindBy (xpath="//*[text()='无版权']")
	WebElement noCopyright;
	
	//A货
	@FindBy (xpath="//*[text()='A货']")
	WebElement AGoods;
	
	//新品保护期 -第三个：7天
	@FindBy (xpath="//*[@dic-list='新品保护期']/div[3]")
	WebElement sevenDays;
	
	/*
	 * 商品规格
	 */
	
	//计量单位-第一个:只
	@FindBy (xpath="//*[@dic-list='计量单位']/div[1]")
	WebElement countUnit;
	
	//外箱
	@FindBy (xpath="//*[text()='外箱']")
	WebElement outBox;
	
	//遥控模式
	@FindBy (xpath="//*[@placeholder='请选择遥控模式']")
	WebElement remoteMode;
	
	//遥控模式：无线电
	@FindBy (xpath="//*[@placeholder='请选择遥控模式']/../../div[3]/div/span[1]")
	WebElement remoteMode2;
	
	//通道
	@FindBy (xpath="//*[@placeholder='请选择通道']")
	WebElement thoroughfare;
	
	//通道:2通
	@FindBy (xpath="//*[@placeholder='请选择通道']/../../div[3]/div/span[1]")
	WebElement thoroughfare2;
	
	//常规功能
	@FindBy (xpath="//*[@placeholder='请选择常规功能']")
	WebElement normalFunction;
	
	//常规功能：灯光
	@FindBy (xpath="//*[@placeholder='请选择常规功能']/../../div[3]/div/span[2]")
	WebElement normalFunction2;
	
	//是否包电
	@FindBy (xpath="//*[@placeholder='请选择是否包电']")
	WebElement isBattery;
	
	//是否包电:包电
	@FindBy (xpath="//*[@placeholder='请选择是否包电']/../../div[3]/div/span[2]")
	WebElement isBattery2;
	
	//是否可充电
	@FindBy (xpath="//*[@placeholder='请选择是否可充电']")
	WebElement isCharge;
	
	//是否可充电：可充电
	@FindBy (xpath="//*[@placeholder='请选择是否可充电']/../../div[3]/div/span[1]")
	WebElement isCharge2;
	
	//配件
	@FindBy (xpath="//*[@placeholder='请选择配件']")
	WebElement parts;
	
	//配件:无
	@FindBy (xpath="//*[@placeholder='请选择配件']/../../div[3]/div/span[1]")
	WebElement parts2;
	
	//包装图案
	@FindBy (xpath="//*[@placeholder='请选择包装图案']")
	WebElement pattern;
	
	//包装图案：无
	@FindBy (xpath="//*[@placeholder='请选择包装图案']/../../div[3]/div/span[1]")
	WebElement pattern2;
	
	//单品或套装
	@FindBy (xpath="//*[@placeholder='请选择单品或套装']")
	WebElement suit;
	
	//单品或套装：单品
	@FindBy (xpath="//*[@placeholder='请选择单品或套装']/../../div[3]/div/span[1]")
	WebElement suit2;
	
	//局部工艺
	@FindBy (xpath="//*[@placeholder='请选择局部工艺']")
	WebElement localProcess;
	
	//局部工艺：无
	@FindBy (xpath="//*[@placeholder='请选择局部工艺']/../../div[3]/div/span[1]")
	WebElement localProcess2;
	
	//颜色
	@FindBy (xpath="//*[@placeholder='请选择颜色']")
	WebElement color;
	
	//颜色
	@FindBy (xpath="//*[@placeholder='请选择颜色']/../../div[3]/div/span[1]")
	WebElement color2;
	
	//装量
	@FindBy (xpath="//*[@placeholder='请选择装量']")
	WebElement count;
	
	//装量：1
	@FindBy (xpath="//*[@placeholder='请选择装量']/../../div[3]/div/span[2]")
	WebElement count2;
	
	//包装类型
	@FindBy (xpath="//*[@placeholder='请选择包装类型']")
	WebElement packingType;
	
	//包装类型：OPP袋
	@FindBy (xpath="//*[@placeholder='请选择包装类型']/../../div[3]/div/span[1]")
	WebElement packingType2;
	
	//包装方式
	@FindBy (xpath="//*[@placeholder='请选择包装方式']")
	WebElement packingWay;
	
	//包装方式：OPP袋
	@FindBy (xpath="//*[@placeholder='请选择包装方式']/../../div[3]/div/span[1]")
	WebElement packingWay2;
	
	//“+”sku按钮
	@FindBy (xpath="//*[@class='addSku']")
	WebElement addSku;
	
	//计数数量
	@FindBy (xpath="//*[@title='计数数量']/div/input")
	WebElement skuItemCount;
	
	//销售MOQ
	@FindBy (xpath="//*[@title='销售MOQ']/div/input")
	WebElement saleMOQ;
	
	//供货周期
	@FindBy (xpath="//*[@title='供货周期']/div/input")
	WebElement supplyTime;
	
	//供应商报价
	@FindBy (xpath="//*[@placeholder='单品价格']")
	WebElement suppliPrice;
	
	//横向滚动条1
	@FindBy (xpath="//*[@id='customScroll']")
	WebElement widthScroll1;
	
//	//横向滚动条2,滚动条与内容一起，无法拖动
//	@FindBy (xpath="//*[contains(@id,'table-single-goods-sku')]/..")
//	WebElement widthScroll2;

	//商品体积-长
	@FindBy (xpath = "//td[@title='商品尺寸(cm)']//input[@placeholder='长']")
	WebElement goodsLong;

	//商品体积-宽
	@FindBy (xpath = "//td[@title='商品尺寸(cm)']//input[@placeholder='宽']")
	WebElement goodsWidth;

	//商品体积-高
	@FindBy (xpath = "//td[@title='商品尺寸(cm)']//input[@placeholder='高']")
	WebElement goodsHigh;

	// 包装体积-长
	@FindBy (xpath = "//td[@title='包装尺寸(cm)']//input[@placeholder='长']")
	WebElement packeLong;

	// 包装体积-宽
	@FindBy (xpath = "//td[@title='包装尺寸(cm)']//input[@placeholder='宽']")
	WebElement packeWidth;

	// 包装体积-高
	@FindBy (xpath = "//td[@title='包装尺寸(cm)']//input[@placeholder='高']")
	WebElement packeHigh;
	
	//外箱体积-长
	@FindBy (xpath="//*[@title='外箱体积(cm)']/div[2]/input")
	WebElement packageLong;
	
	//外箱体积-宽
	@FindBy (xpath="//*[@title='外箱体积(cm)']/div[3]/input")
	WebElement packageWidth;
	
	//外箱体积-高
	@FindBy (xpath="//*[@title='外箱体积(cm)']/div[4]/input")
	WebElement packageHigh;
	
	//毛重
	@FindBy (xpath="//*[@title='毛重']/div/input")
	WebElement grossWeight;
	
	//净重
	@FindBy (xpath="//*[@title='净重']/div/input")
	WebElement netWeight;
	
	//上传图片
	@FindBy (xpath="//*[contains(@handler-obj,'skuPicsHandler')]")
	WebElement updatePic;
	
	//图片上传成功验证
	@FindBy (xpath="//*[contains(@ng-click,'deleteInfo')]")
	WebElement isUpudate;
	
	//提交审核
	@FindBy (xpath="//*[text()='提交审核']")
	WebElement submit;
	
	//提交审核后弹窗--确定按钮
	@FindBy(xpath="//*[text()='确定']")
	WebElement confirm;
	
	//提交审核后弹窗--商品编号
	@FindBy (xpath="//*[@ng-bind='item.no']")
	WebElement skuNo;
	
	/*
	 * 页面方法
	 */
	/**
	 * 选择供应商
	 * @param 供应商名称
	 * @throws InterruptedException 
	 */
	public void selectSupplier(){
		dynamicWait(By.xpath("//*[@ng-click='supplier()']"));
		selectElement(auditor, "邢昌勇");
		click(selectSupplier);
		click(showSelect);
		click(queryByName);
		sendKeys(queryKeyword,Model.getSupplierName());
		click(search);
		forceWait(1000);
		mywait(firstRestult);
		click(firstRestult);
		click(sureBotton);
	}
	/**
	 * 选择品牌
	 */
	public void selectBrand() {
		forceWait(1000);
		click(brandName);
		mywait(firstBrand);
		click(firstBrand);
		click(sureBotton);
	}
	/**
	 * 选择主类目
	 * 注意：这里只能用js点击事件才能展开那个“+”
	 * @throws InterruptedException 
	 */
	public void selectCategory(){
		forceWait(1000);
		mywait(selectCategory);
		click(selectCategory);
		jsExecutorClick(oneLevel);
		jsExecutorClick(twoLevel);
		jsExecutorClick(threeLevel);
		jsExecutorClick(mainCategory);
		click(sureBotton);
	}
	/**
	 * 配置商品属性
	 */
	public void goodsProperty() {
		mywait(secondFunction);
		click(secondFunction);
		click(secondSpcialFunction);
		click(skin);
		click(withPerson);
		click(forAge);
	}
	/**
	 * 配置商品信息
	 */
	public void goodsInfo() {
		skuNameNo = Tools.getCurrentTime();
		//挪动滚动条，否则查找不到元素
		moveHeightScroll("40");
		sendKeys(goodsName, "市场"+skuNameNo);
		click(selectLanguage);
		click(english);
		sendKeys(goodsName, "Market"+skuNameNo);
		sendKeys(factoryNo, Tools.getFactoryNo());
		click(packageLanguage);
		click(ownCopyright);
		click(sevenDays);
	}
	/**
	 * 选择sku属性，并提交
	 * @return 商品发布页
	 */
	public void goodsSpecifications() {
		moveHeightScroll("65");
		click(countUnit);
		click(outBox);
		click(remoteMode);
		click(remoteMode2);
		click(thoroughfare);
		click(thoroughfare2);
		click(normalFunction);
		click(normalFunction2);
		click(isBattery);
		click(isBattery2);
		click(isCharge);
		click(isCharge2);
		click(parts);
		click(parts2);
		click(pattern);
		click(pattern2);
		click(suit);
		click(suit2);
		click(localProcess);
		click(localProcess2);
		click(color);
		click(color2);
		click(count);
		click(count2);
		click(packingType);
		click(packingType2);
		click(packingWay);
		click(packingWay2);
		click(addSku);
		
		sendKeys(skuItemCount, "10");
		sendKeys(saleMOQ, "10");
		sendKeys(supplyTime, "3");
		
		//供应商报价,随机生成一个1-200的价格
		int sp = (int)(1+Math.random()*30);
		sendKeys(suppliPrice, sp+"");
		
		jsExecutorDragAndDrop(widthScroll1,2000,0);
		mywait(packageLong);

		//商品尺寸
		sendKeys(goodsLong,"10");
		sendKeys(goodsWidth,"15");
		sendKeys(goodsHigh,"20");

		//包装尺寸
		sendKeys(packeLong,"10");
		sendKeys(packeWidth,"18");
		sendKeys(packeHigh,"25");
		
		//外箱体积
		sendKeys(packageLong, "100");
		sendKeys(packageWidth, "50");
		sendKeys(packageHigh, "40");
		//毛重，净重
		sendKeys(grossWeight, "1.5");
		sendKeys(netWeight, "1.2");
	}
	
	/**
	 * 上传sku图片并提交审核，确认弹框
	 */
	public void updatePic() {
		click(updatePic);
		uploadFile(Model.getPicPath());
		/*while(!isElementExist(isUpudate)) {
			forceWait(1000);
		}*/
		dynamicWait(By.xpath("//*[contains(@ng-click,'deleteInfo')]"));
		forceWait(1000);
		click(submit);
		forceWait(1000);
		mywait(confirm);
		setSkuNo();
		click(confirm);
	}
	
	/**
	 * 市场商品建档，将上面的方法整合
	 * @return MarketGoodsRelesePage
	 */
	public MarketGoodsRelesePage addMarketGoods() {
		selectSupplier();
		selectBrand();
		selectCategory();
		goodsProperty();
		goodsInfo();
		goodsSpecifications();
		updatePic();
		return new MarketGoodsRelesePage(driver);
	}
	
	/**
	 * 将商品编号写进model
	 */
	public void setSkuNo() {
		Model.setSkuNo(skuNo.getText());
	}
}	
