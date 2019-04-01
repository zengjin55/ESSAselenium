package com.essa.pageObject.productDev;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
* @Description:物料列表页面
* @Author: ZengJin
* @CreateTime: 2018/10/29
*/
public class MaterialListPage extends BasePage {
    /**
     * 构造方法
     *
     * @param driver
     */
    public MaterialListPage(WebDriver driver) {
        super(driver);
    }
    /*
    元素定位
     */
    @FindBy (xpath = "//table[@id='table-material-list']//tbody/tr[1]")
    WebElement firstResult;//列表第一个结果

    @FindBy (xpath = "//*[@name='keyWord']")
    WebElement keyword;//关键字查询

    @FindBy (xpath = "//*[@name='keyWord']/../span/button")
    WebElement search;//查询按钮

    @FindBy (xpath = "//*[contains(text(),'供应商关联类型设置')]")
    WebElement supplierCatConfig;//供应商关联类型设置

    @FindBy (xpath = "//tr[@class='ng-scope active']//select[@class='form-control ng-isolate-scope ng-pristine ng-valid']")
    WebElement AssociatioType;//关联类型

    @FindBy (xpath = "//button[contains(text(),'确定')]")
    WebElement confirm;//确定

    /*
    页面方法
     */
    /**
    * @Description:设置核心供应商
    * @return: 物料列表
    * @Author: ZengJin
    * @CreateTime: 2018/10/29
    */
    public MaterialListPage setSupplierCatConfig(){
        mywait(firstResult);
        sendKeys(keyword,Model.getMaterialName());
        click(search);
        mywait(firstResult);
        forceWait(1000);
        click(supplierCatConfig);
        forceWait(1000);
        selectElement(AssociatioType,"核心供应商");
        click(confirm);
        return new MaterialListPage(driver);
    }
}
