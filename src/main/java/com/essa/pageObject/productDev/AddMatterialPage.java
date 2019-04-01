package com.essa.pageObject.productDev;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.framework.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*
* @Description: 新增物料页面
* @Author: ZengJin
* @CreateTime: 2018/10/29
*/
public class AddMatterialPage extends BasePage {
    /**
     * 构造方法
     *
     * @param driver
     */
    public AddMatterialPage(WebDriver driver) {
        super(driver);
    }

    /*
    元素定位
     */
    @FindBy (xpath = "//*[text()='物料基础属性']")
    WebElement checkPoint;//检查点，物料基础属性

    @FindBy (xpath = "//*[@ng-model='item.supplierPrice']")
    WebElement supplierPrice;//光身：供应商报价

    @FindBy (xpath = "//*[@ng-model='item.bodyFclPrice']")
    WebElement bodyFclPrice;//光身：供应商整柜报价

    @FindBy (xpath = "//*[@ng-model='item.purchaseMoq']")
    WebElement purchaseMoq;//光身：采购MOQ

    @FindBy (xpath = "//*[@ng-model='item.deliverDay']")
    WebElement deliverDay;//光身：货期

    @FindBy (xpath = "//*[@ng-click='save()']")
    WebElement save;//保存

    @FindBy (xpath = "//*[@ng-model='model.matFromType' and @ng-disabled='isUpdate']")
    WebElement matFrom;//物料来源

    @FindBy (xpath = "//*[@ng-model='model.name']")
    WebElement matName;//物料名称

    @FindBy (xpath = "//*[@ng-model='model.baseUnit']")
    WebElement baseUnit;//基准单位

    @FindBy (xpath = "//*[@ng-click='openSelector()']")
    WebElement matType;//物料类型

    @FindBy (xpath = "//*[text()='纸箱']")
    WebElement carton;//物料类型：纸箱

    @FindBy (xpath = "//*[text()='海德印刷厂']/../td[3]/input")
    WebElement factoryNo;//工厂货号

    @FindBy (xpath = "//*[text()='海德印刷厂']/../td[5]/select")
    WebElement purchaseUnit;//物料采购单位

    @FindBy (xpath = "//*[text()='海德印刷厂']/../td[6]/input")
    WebElement unitExchangeRate;//换算值

    @FindBy (xpath = "//*[text()='海德印刷厂']/../td[8]/input")
    WebElement matSupplierPrice;//物料：供应商报价

    @FindBy (xpath = "//*[text()='海德印刷厂']/../td[10]/input")
    WebElement matPurchaseMoq;//物料：采购MOQ

    @FindBy (xpath = "//*[text()='海德印刷厂']/../td[12]/input")
    WebElement matDeliverDay;//物料：货期

    /*
    页面方法
     */
    /**
    * @Description: 新增光身物料方法
    * @param: []
    * @return: 项目立项书列表
    * @Author: ZengJin
    * @CreateTime: 2018/10/29
    */
    public ProjectListPage addBodyMat(){
        mywait(checkPoint);
        moveHeightScroll("100");
        sendKeys(supplierPrice,"15");
        sendKeys(bodyFclPrice,"14");
        sendKeys(purchaseMoq,"15");
        sendKeys(deliverDay,"7");
        click(save);
        return new ProjectListPage(driver);
    }
    /**
    * @Description:新增物料
    * @return: 物料列表页
    * @Author: ZengJin
    * @CreateTime: 2018/10/29
    */
    public MaterialListPage addMaterial(){
        mywait(checkPoint);
        selectElement(matFrom,"自主物料");
        Model.setMaterialName("物料" + Tools.getTime());
        sendKeys(matName,Model.getMaterialName());
        selectElement(baseUnit,"只");
        moveHeightScroll("100");
        click(matType);
        click(carton);
        forceWait(1000);
        sendKeys(factoryNo,Tools.getFactoryNo());
        selectElement(purchaseUnit,"只");
        sendKeys(unitExchangeRate,"1");
        sendKeys(matSupplierPrice,"5");
        sendKeys(matPurchaseMoq,"15");
        sendKeys(matDeliverDay,"5");
        click(save);
        return new MaterialListPage(driver);
    }
}
