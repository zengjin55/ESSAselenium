package com.essa.pageObject.GoodsManage;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuditDevGoodsPage extends BasePage {
    /**
     * 构造方法
     *
     * @param driver
     */
    public AuditDevGoodsPage(WebDriver driver) {
        super(driver);
    }
    /**
     * 元素定位
     */
    @FindBy (xpath = "//*[@ng-model='query.skuCode']")
    WebElement skuNo;//sku编号

    @FindBy (xpath = "//*[text()='查询']")
    WebElement search;//查询按钮

    @FindBy (xpath = "//*[@ng-change='getCheckedStatus();']")
    WebElement checkbox;//复选框

    @FindBy (xpath = "//*[text()='审核']")
    WebElement audit;//审核

    /**
     * 页面方法
     */
    /**
     *跳转到审核页面，这里开发商品共用的原厂商品的审核页面
     * @param
     * @return 原厂商品审核页面
     */
    public AuditOriginalOpreatePage auditDevGood(){
//        dynamicLoad();
        dynamicWait(By.xpath("//table[@class='table border-default margin-bottom-80 ng-scope ng-table']//tbody[1]"));
        sendKeys(skuNo, Model.getSkuNo());
        click(search);
        forceWait(1000);
        click(checkbox);
        click(audit);
        return new AuditOriginalOpreatePage(driver);
    }
}
