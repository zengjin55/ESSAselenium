package com.essa.pageObject.productDev;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.pageObject.GoodsManage.AddOriginalGoodsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
* @Description: 项目立项书列表页面
* @Author: ZengJin
* @CreateTime: 2018/10/29
*/
public class ProjectListPage extends BasePage {
    /**
     * 构造方法
     *
     * @param driver
     */
    public ProjectListPage(WebDriver driver) {
        super(driver);
    }

    /**
    元素定位
     */
    @FindBy (xpath = "//table[@class='tree-grid table table-hover table-even-striped no-border border-default text-center table-fixed ng-scope ng-table']//tbody//tr[1]")
    WebElement firstResult;//第一个查询结果

    @FindBy (xpath = "//*[@name='keyWord']")
    WebElement keyword;//关键字查询

    @FindBy (xpath = "//*[@name='keyWord']/../span/button")
    WebElement search;//查询按钮

    @FindBy (xpath = "//*[@ng-click='updateBom()']")
    WebElement updateBom;//BOM更新

    @FindBy (xpath = "//*[text()='确定']")
    WebElement confirm;//确定按钮

    @FindBy (xpath = "//*[@ng-click='singleGoodsPublic()']")
    WebElement DevGoodPublic;//开发单品发布

    @FindBy (xpath = "//*[contains(text(),'不选择SPU')]")
    WebElement NotChooseSpu;//不选择SPU

    @FindBy (xpath = "//*[@data-ng-bind='item.publishSkuCode']")
    WebElement DevGoodNo;//开发商品编号

    /**
     * 页面方法
     */
    /**
     *BOM更新方法
     * @param
     * @return 编辑BOM页面
     */
    public EditBOMPage updateBom(){
        mywait(firstResult);
        sendKeys(keyword,Model.getProjectName());
        click(search);
        dynamicLoad();
        click(firstResult);
        click(updateBom);
        return new EditBOMPage(driver);
    }

   /**
    *开发单品发布，由于这里只是点击一下保存，故共用新增原厂商品的一个保存方法
    * @param
    * @return 确认开发商品档案页面
    */
    public AddOriginalGoodsPage publicDevGood(){
        click(confirm);
        sendKeys(keyword,Model.getProjectName());
        dynamicWait(By.xpath("//table[@class='tree-grid table table-hover table-even-striped no-border border-default text-center table-fixed ng-scope ng-table']//tbody//tr[1]"));
        click(search);
        dynamicLoad();
        click(firstResult);
        click(DevGoodPublic);
        forceWait(1000);
        click(NotChooseSpu);
        return new AddOriginalGoodsPage(driver);
    }
    /**
     *获取到开发商品的编号，并且传递到model的skuNo字段中
     * @param
     * @return
     */
    public void getDevGoodNo(){
        sendKeys(keyword,Model.getProjectName());
        click(search);
        dynamicLoad();
        Model.setSkuNo(getText(DevGoodNo));
    }
}
