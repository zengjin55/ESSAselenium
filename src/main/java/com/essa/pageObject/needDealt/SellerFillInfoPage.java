package com.essa.pageObject.needDealt;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.pageObject.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//业务员补充船务资料待办页面
public class SellerFillInfoPage extends BasePage {
    public SellerFillInfoPage(WebDriver driver) {
        super(driver);
    }

    //选择货代来源
    @FindBy(xpath = "//*[text()='ESSA指定']")
    WebElement AppointSet;

    //选择报关
    @FindBy(xpath = "//*[@id='shippingPanel1']/div[2]/div/div/label[2]")
    WebElement DeclareSet;

    //选择货代
    @FindBy(xpath = "//*[text()='请选择货代']")
    WebElement AppointCompanySet;

    //选择货代
    @FindBy(xpath = "//*[text()='中外运']")
    WebElement AppointCompanySec;

    //确定选择货代
    @FindBy(xpath = "//*[text()='确定']")
    WebElement CommitAppointCompanySec;

    //目标港口
    @FindBy(xpath = "//*[@id='shippingPanel2']/div[2]/div/div/input")
    WebElement TargetPortSet;

    //发货人公司
    @FindBy(xpath = "//*[text()='汕头伊斯卡玩具有限公司']")
    WebElement ConsignmentCompany;

    //收货公司
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[1]/ng-form/div/div/div[1]/div/div/input")
    WebElement HarvestCompany;

    //收货电话
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[1]/ng-form/div/div/div[3]/div/div/input")
    WebElement HarvestMobile;

    //收货传真
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[1]/ng-form/div/div/div[4]/div/div/input")
    WebElement HarvestFax;

    //收货邮编
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[1]/ng-form/div/div/div[5]/div/div/input")
    WebElement HarvestZipcode;

    //通知方公司
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[2]/ng-form/div/div/div[1]/div/div/input")
    WebElement SetNoticeCompany;

    //通知方电话号码
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[2]/ng-form/div/div/div[3]/div/div/input")
    WebElement SetNoticeMobile;

    //通知方邮编
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[2]/ng-form/div/div/div[5]/div/div/input")
    WebElement SetNoticeZipcode;

    //通知方传真
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[2]/ng-form/div/div/div[4]/div/div/input")
    WebElement SetNoticeFix;

    //寄单公司名称
    @FindBy(xpath = "//*[@id='shippingPanel5']/div[7]/div/div/div[1]/div/div/input")
    WebElement MailCompanyName;

    //寄单联系人名称
    @FindBy(xpath = "//*[@id='shippingPanel5']/div[7]/div/div/div[2]/div/div/input")
    WebElement MailConnecter;

    //寄单联系人名称
    @FindBy(xpath = "//*[@id='shippingPanel5']/div[7]/div/div/div[3]/div/div/input")
    WebElement MailPhone;

    //寄单邮编
    @FindBy(xpath = "//*[@id='shippingPanel5']/div[7]/div/div/div[4]/div/div/input")
    WebElement MailZipcode;

    //寄单传真
    @FindBy(xpath = "//*[@id='shippingPanel4']/div[2]/ng-form/div/div/div[4]/div/div/input")
    WebElement MailFax;

    //寄单邮编
    @FindBy(xpath = "//*[text()='确认']")
    WebElement SubmitBtn;

    //流水号
    @FindBy (xpath = "//*[contains(text(),'流水号：RW')]")
    WebElement serialNumber;

    /**
     *业务员填写船务资料
     * @param
     * @return
     */
    public HomePage toSellerFillInfo() {
//        mywait(AppointSet);
        dynamicWait(By.xpath("//*[contains(text(),'待办工作')]"));
        Model.setSerialNum(partialStr(serialNumber.getText(), "流水号："));//将流水号传递出去
        click(AppointSet);
        click(DeclareSet);
        click(AppointCompanySet);
        forceWait(1000);
        click(AppointCompanySec);
        click(CommitAppointCompanySec);
        mywait(TargetPortSet);
        TargetPortSet.sendKeys("TestPort");
        click(ConsignmentCompany);
        HarvestCompany.sendKeys("TestCompany");
        HarvestMobile.sendKeys("15888889999");
        HarvestFax.sendKeys("TestFax@test.com");
        HarvestZipcode.sendKeys("000000");

        SetNoticeCompany.sendKeys("TestNoticeCompany");
        SetNoticeMobile.sendKeys("15888889999");
        SetNoticeFix.sendKeys("TestNoticeFix@test.com");
        SetNoticeZipcode.sendKeys("000000");

        MailCompanyName.sendKeys("TestMailCompanyName");
        MailConnecter.sendKeys("TestMailConnecter");
        MailPhone.sendKeys("TestMailPhone");
        MailZipcode.sendKeys("TestMailZipCode");
        MailFax.sendKeys("TestMailFax");
        click(SubmitBtn);
        dynamicLoad();
        return new HomePage(driver);
    }
}
