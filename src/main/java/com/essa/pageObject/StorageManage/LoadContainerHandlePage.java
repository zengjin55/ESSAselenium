package com.essa.pageObject.StorageManage;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.framework.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;


/**
 * 装柜任务处理
 */
public class LoadContainerHandlePage extends BasePage {

    public LoadContainerHandlePage(WebDriver driver){
        super(driver);
    }

    /**
     * 元素定位
     */
    // 实装柜日期
    @FindBy (xpath = "//input[@id='loadCompleteDate']")
    WebElement actLoadTime;

    // 可装柜箱数
    @FindBy (xpath = "//tr[@class='ng-scope']//td[6]")
    WebElement mayLoadQuantity;

    // 实装箱数
    @FindBy (xpath = "//input[@id='actLoadBoxQuantity']")
    WebElement actLoadQuantity;

    // 点击选择文件
    @FindBy (xpath ="//div[@id='rt_rt_1cqvbp0jn14kfb41keb187l1u8oh']//label" )
    WebElement actPicture;

    // 提交
    @FindBy (xpath = "//*[text()='提交']")
    WebElement submit;

    /**
     * 页面方法
     */
    public LoadContainerHandlePage loadTaskHandle(){
        forceWait(1000);
        jsExecutorRemoveAttribute(actLoadTime,"readonly");
//        String date = getDateTimeByFormat(new Date(), "MM/dd/yyyy");
        sendKeys(actLoadTime, Tools.getToday());
        // 获取‘可装柜数量’字符串中的数量
        String qua = partiaStr(mayLoadQuantity,0,1);
        // 对切片结果进行int转换
        int i = Integer.valueOf(qua).intValue();
        // 形成现存尾货‘2’
        int tail = i-2;
        // 对计算结果转换成string,并赋值给“实装柜数量”
        String quantity = String.valueOf(tail);
        sendKeys(actLoadQuantity,quantity);
        click(submit);
        dynamicLoad();
        return new LoadContainerHandlePage(driver);
    }
}
