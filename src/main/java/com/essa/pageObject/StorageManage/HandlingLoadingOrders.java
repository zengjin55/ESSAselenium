package com.essa.pageObject.StorageManage;

import com.essa.framework.BasePage;
import com.essa.framework.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 装柜任务处理页面
 * Created by Administrator on 2018/10/30 0030.
 */
public class HandlingLoadingOrders extends BasePage {
    public HandlingLoadingOrders(WebDriver driver) {
        super(driver);
    }

    WebDriver webDriver;
    /*
	 * 元素定位
	 */

    //实际装柜日期
    @FindBy(xpath = "//input[@id='loadCompleteDate']")
    WebElement loadCompleteDate;

    //获取装柜商品列表
    @FindBy(xpath = "//tbody[2]/tr[1]/td[6]")
    WebElement firstTr;//定位第一行

    //输入实装箱数
    @FindBy(xpath = "//input[@id='actLoadBoxQuantity']")
    WebElement realNumber;

    //提交
    @FindBy(xpath = "//*[text()='提交']")
    WebElement submit;

    //可装柜箱数
    @FindBy (xpath = "//tr[@class='ng-scope']//td[6]/div")
    WebElement canLoadNum;

    /**
     * 设置装柜处理参数
     */
    public HandlingLoadingOrders setHandlingCabinet() {
        forceWait(1000);
        //设置实际装柜日期
        jsExecutorRemoveAttribute(loadCompleteDate, "readonly");
        click(loadCompleteDate);
        paste(Tools.getFormatTime());
        String context = canLoadNum.getText();
        String nums = context.substring(0,2);
        int num = Integer.parseInt(nums)-8;
        forceWait(1000);
        sendKeys(realNumber,num+"");
        click(submit);
        dynamicLoad();
        return new HandlingLoadingOrders(driver);
    }


}
