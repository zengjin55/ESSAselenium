package com.essa.pageObject.needDealt;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.framework.Tools;
import com.essa.pageObject.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//认领登记备选船期待办页面
public class RegisOptShpDatePage extends BasePage {
    public RegisOptShpDatePage (WebDriver driver) {
        super(driver);
    }

    // 认领登记备选船期任务
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div[4]/button")
    WebElement ClaimTask;

    // 添加船务信息
    @FindBy(xpath = "//*[@id='wf_content']/div/div/button[1]")
    WebElement AddShippInfo;

    // 添加船公司
    @FindBy(xpath = "//*[@id='wf_content']/form/table/tbody/tr/td/ng-form/div/div[1]/input")
    WebElement AddShippCompanyInfo;

    // 设置开船日期
    @FindBy(xpath = "//*[@id='deliveryDate0']")
    WebElement SetdeliveryDate0;

    // 设置到岗日期
    @FindBy(xpath = "//*[@id='estimatedArrivalDate0']")
    WebElement SetEstimatedArrivalDate0;

    // 提交
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div[4]/span[1]/button[2]")
    WebElement SubmitBtn;

    //流水号
    @FindBy (xpath = "//*[contains(text(),'流水号：RW')]")
    WebElement serialNumber;

    /**
     *登记船期
     * @param
     * @return HomePage
     */
    public HomePage toRegisOptShpDatePage(){
        dynamicWait(By.xpath("//*[contains(text(),'待办工作')]"));
        if (isVisibility(By.xpath("//*[@id='tableViewList']/div/div/div/div[4]/button"))){
            click(ClaimTask);//如果需要认领，则认领
            forceWait(4000);
        }
        mywait(AddShippInfo);
        Model.setSerialNum(partialStr(serialNumber.getText(), "流水号："));
        click(AddShippInfo);
        mywait(AddShippCompanyInfo);
        AddShippCompanyInfo.sendKeys("TestShippCompanyInfo");
        jsExecutorRemoveAttribute(SetdeliveryDate0, "readonly");
        sendKeys(SetdeliveryDate0, Tools.moreDays(20));
        jsExecutorRemoveAttribute(SetEstimatedArrivalDate0, "readonly");
        sendKeys(SetEstimatedArrivalDate0,Tools.moreDays(20));
        click(SubmitBtn);
        dynamicLoad();
        return new HomePage(driver);
    }
}
