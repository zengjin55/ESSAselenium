package com.essa.pageObject.StorageManage;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by lana on 2018/10/26 0026.
 * 装柜任务管理页
 */
public class CabineTaskManagemenPage extends BasePage{

    public CabineTaskManagemenPage(WebDriver driver) {
        super(driver);
    }

    /*
	 * 元素定位
	 */

    @FindBy(xpath = "//input[@name='selectKey']")
    WebElement selectKey;//关键字查询

    @FindBy (xpath = "//button[contains(@ng-click,'search')]")
    WebElement search;//查询按钮

    @FindBy(xpath ="//button[contains(@ng-click,'goCompleteTask')]" )
    WebElement handle;//处理按钮

    @FindBy(xpath ="//tbody/tr[1]")
    WebElement firstTr;//定位第一行

    @FindBy (xpath = "//button[contains(text(),'处理')]")
    WebElement deal;//处理

    /*
    * 页面参数
     * */
//    String poNo="";

    /*
	 * 页面方法
	 */

    /**
     * 设置装柜处理查询
     */
    public CabineTaskManagemenPage setWareHouse() {
        dynamicLoad(By.xpath("//div[@style='display: none;' and @id='mask']"));
//        if(Model.getPoNum()!=""|| Model.getPoNum()!=null){
//            poNo="B180828T9165";
//            sendKeys(selectKey, poNo);
//        }else{
//        sendKeys(selectKey, Model.getPoNum());
//    }
        sendKeys(selectKey, Model.getPoNum());
        click(search);
        forceWait(1000);
        click(firstTr);
        click(deal);
        return new CabineTaskManagemenPage(driver);

    }

}
