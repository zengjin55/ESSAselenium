package com.essa.pageObject.DocumentaryManage;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.pageObject.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

/**
* @Description: 发单中心页面
* @Author: ZengJin
* @CreateTime: 2018/10/31
*/
public class BillingCenterPage extends BasePage {
    /**
     * 构造方法
     *
     * @param driver
     */
    public BillingCenterPage(WebDriver driver) {
        super(driver);
    }
    /**
     * 元素定位
     */
    @FindBy (xpath = "//*[@ng-model='query.params.keyword']")
    WebElement keyword;//关键字搜索框

    @FindBy (xpath = "//*[text()='高级查询']")
    WebElement advancedSearch;//高级查询

    @FindBy (xpath = "//*[@ng-model='query.params.poCode']")
    WebElement PONum;//根据PO单号查询

    @FindBy (xpath = "//*[@ng-click='richSearch()']")
    WebElement search;//高级查询：查询按钮

    @FindBy (xpath = "//*[text()='开始发单']")
    WebElement send;//开始发单按钮

    @FindBy (xpath = "//*[text()='登记接单']")
    WebElement accept;//登记接单按钮

    @FindBy (xpath = "//tbody//tr[1]/td[1]")
    WebElement firstFo;//第一个FO单编号

    @FindBy (xpath = "//tbody//tr[1]/td[3]")
    WebElement status;//第一个FO单发单状态

    @FindBy (xpath = "//*[@ng-model='scanValue']")
    WebElement FOKeyword;//弹框里的FO单号查询输入框

    @FindBy (xpath = "//*[@ng-click='clickBtn()']")
    WebElement FOSearch;//FO查询搜索框

    @FindBy (xpath = "//*[@ng-click='sendBill()']")
    WebElement sendBill;//开始发单按钮

    @FindBy (xpath = "//*[@ng-click='acceptBill()']")
    WebElement acceptBill;//接单完成

    @FindBy (xpath = "//div[@class='left ng-scope']//span[2]")
    WebElement total;//一共有多少个FO单

    @FindBy (xpath = "//button[contains(text(),'50')]")
    WebElement show50;//分页展示50个

    /**
     * 页面方法
     */
    /**
     *单个Fo单的发单方法
     * @param
     * @return homePage
     */
    public HomePage Billing(){
        forceWait(2000);
        sendKeys(keyword,"程序仍在进行，等待页面加载完成中……");
        forceWait(4000);
        dynamicLoad(By.xpath("//*[text()='对不起！没有查询到相关数据。']"));
        clear(keyword);
        click(advancedSearch);
        sendKeys(PONum,Model.getPoNum());
        click(search);
        dynamicLoad();
        String FO = firstFo.getText();
        click(send);
        sendKeys(FOKeyword,FO);
        click(FOSearch);
        forceWait(500);
        click(sendBill);
        forceWait(500);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(status.getText(),"发单中","发单失败！");
        click(accept);
        sendKeys(FOKeyword,FO);
        click(FOSearch);
        forceWait(500);
        click(acceptBill);
        forceWait(500);
        softAssert.assertEquals(status.getText(),"已接单","接单失败！");
        softAssert.assertAll();
        return new HomePage(driver);
    }
    /**
     *多个FO单时的发单
     * @param
     * @return
     */
    public HomePage MoreBilling(){
        forceWait(2000);
        sendKeys(keyword,"程序仍在进行，等待页面加载完成中……");
        forceWait(4000);
        dynamicLoad(By.xpath("//*[text()='对不起！没有查询到相关数据。']"));
        clear(keyword);
        forceWait(1000);
        click(advancedSearch);
        sendKeys(PONum,Model.getPoNum());
        click(search);
        dynamicLoad();
        int totalNum = Integer.parseInt(total.getText());//根据分页获取FO单总数
        if (totalNum>10){
            click(show50);
            forceWait(1000);
        }
        for(int i=1;i<=totalNum;i++){
            WebElement FOs = driver.findElement(By.xpath("//tbody//tr["+i+"]/td[1]"));//获取Fo单号
            WebElement FoStatus = driver.findElement(By.xpath("//tbody//tr["+i+"]/td[3]"));//当前FO单状态
            String FO = FOs.getText();
            switch (FoStatus.getText()){
                case "制单中" :
                    click(send);
                    sendKeys(FOKeyword,FO);
                    click(FOSearch);
                    forceWait(500);
                    click(sendBill);
                    forceWait(500);
                    click(accept);
                    sendKeys(FOKeyword,FO);
                    click(FOSearch);
                    forceWait(500);
                    click(acceptBill);
                    forceWait(500);
                    break;
                case "发单中" :
                    click(accept);
                    sendKeys(FOKeyword,FO);
                    click(FOSearch);
                    forceWait(500);
                    click(acceptBill);
                    forceWait(500);
                    break;
                default :
                    break;
            }
        }
        return new HomePage(driver);
    }
}
