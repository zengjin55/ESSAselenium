package com.essa.pageObject.StorageManage;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
* @Description: 装柜任务管理列表
* @Author: ZengJin
* @CreateTime: 2018/11/1
*/
public class LoadContainerManagePage extends BasePage {

    public LoadContainerManagePage(WebDriver driver) {
        super(driver);
    }

    /**
     * 元素定位
     */

    // 关键字搜索输入框
    @FindBy(xpath = "//input[@placeholder='请输入PO单号或采购商编号,多个请用空格隔开']")
    WebElement inputKeySearch;

    // 放大镜搜索按钮
    @FindBy(xpath = "//*[@class='iconfont icon-search']")
    WebElement keySearch;

    // 选中查询的第一条数据
    @FindBy (xpath = "//td[@title='待处理']")
    WebElement firstPO;

    // 处理
    @FindBy(xpath = "//*[text()='处理']")
    WebElement handle;

    /**
     * 页面方法
     *
     * @return
     */
    public LoadContainerManagePage toLoadContainerDetail() {
        forceWait(1000);
        dynamicLoad(By.xpath("//div[@style='display: none;' and @id='mask']"));
        sendKeys(inputKeySearch, Model.getPoNum());
//        forceWait(5000);
        click(keySearch);
//        forceWait(2000);
        dynamicLoad();
        click(firstPO);
//        forceWait(2000);
        click(handle);
        return new LoadContainerManagePage(driver);
    }
}
