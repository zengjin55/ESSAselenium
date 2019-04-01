package com.essa.pageObject.productDev;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
* @Description: BOM修改页面
* @Author: ZengJin
* @CreateTime: 2018/10/29
*/
public class EditBOMPage extends BasePage {
    /**
     * 构造方法
     *
     * @param driver
     */
    public EditBOMPage(WebDriver driver) {
        super(driver);
    }
    /**
     * 元素定位
     */
    @FindBy (xpath = "//*[@ng-click='materialChoose()']")
    WebElement matChoose;//物料选取

    @FindBy (xpath = "//*[@name='keyword']")
    WebElement keyword;//关键字查询

    @FindBy (xpath = "//*[@name='keyword']/../span/button")
    WebElement search;//查询按钮

    @FindBy (xpath = "//*[@ng-click='materialSelected(item)']")
    WebElement checkbox;//复选框

    @FindBy (xpath = "//*[contains(text(),'选定')]")
    WebElement selectedConfirm;//选定

    @FindBy (xpath = "//*[text()='设置']")
    WebElement set;//集单物料：设置

    @FindBy (xpath = "//*[@ng-click='selectBulkMat(item)']")
    WebElement selectBulkMat;//待选择的物料

    @FindBy (xpath = "//*[@ng-click='save()']")
    WebElement save;//确定修改

    /**
     * 页面方法
     */
    /**
     *编辑BOM，设置集单物料等
     * @param
     * @return 项目立项书列表
     */
    public ProjectListPage editBom(){
        mywait(matChoose);
        click(matChoose);
        forceWait(500);
        sendKeys(keyword, Model.getMaterialName());
        click(search);
        forceWait(1000);
        click(checkbox);
        click(selectedConfirm);
        forceWait(500);
        click(set);
        click(selectBulkMat);
        click(selectedConfirm);
        forceWait(500);
        click(save);
        return new ProjectListPage(driver);
    }

}
