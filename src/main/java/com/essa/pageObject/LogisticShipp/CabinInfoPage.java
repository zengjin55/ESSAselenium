package com.essa.pageObject.LogisticShipp;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.framework.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//船务看板-订舱-待订舱-订舱录入界面
public class CabinInfoPage extends BasePage {
    public CabinInfoPage(WebDriver driver) {
        super(driver);
    }

//    public String FilePath = "C:\\Users\\hanlei\\Desktop\\1.png";

    //订舱看板
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/ul/li[3]")
    WebElement BookingCabin;

    //记录
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/table/tbody/tr[1]/td/table/tbody/tr[1]")
    WebElement FirstDate;

    //输入框
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/form[1]/div/div/input")
    WebElement SetQuery;

    //查询按钮
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/form[1]/div/div/span/button")
    WebElement QueryBtn;

    //选择订舱条目
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[1]/input")
    WebElement SelectFirstDate;

    //订舱按钮
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/div[1]/button[1]")
    WebElement BookingCabinBtn;

    //订舱S/O号
    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div/form/div/div[1]/div/div[2]/div/div/input")
    WebElement SetCabinSONo;

    //最后装柜日期
    @FindBy(xpath = "//*[@id='lastLoadTime']")
    WebElement LastLoadTimeSet;

    //选择文件
    @FindBy(xpath = "//*[text()='点击选择文件...']")
    WebElement FileUp;

    //确定
    @FindBy(xpath = "//button[@data-bb-handler='submit' and @type='button']")
    WebElement CommitBtn;

    //窗体
    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div")
    WebElement Body;

    //预计到港时间
    @FindBy (xpath = "//*[@id='expectArrivalTime']")
    WebElement expectArrivalTime;


    public CabinInfoPage toCabinInfoPage() {
        forceWait(1000);
        click(BookingCabin);
        mywait(FirstDate);
        SetQuery.sendKeys(Model.getPoNum());
        click(QueryBtn);
        forceWait(1000);
        click(SelectFirstDate);
        click(BookingCabinBtn);
        mywait(SetCabinSONo);
        SetCabinSONo.sendKeys("TestCabinNo");
        jsExecutorRemoveAttribute(LastLoadTimeSet, "readonly");
        click(LastLoadTimeSet);
        paste(Tools.getFormatTime());//用键盘粘贴的方式填入时间
        click(Body);
        actionClick(FileUp);
        uploadFile(Model.getPicPath());
        dynamicLoad(By.xpath("//img[@ng-src='/img/nopic.jpg']"));//直到默认的图片找不到时，执行下一步
        click(CommitBtn);
        dynamicLoad();
        return new CabinInfoPage(driver);
    }
}
