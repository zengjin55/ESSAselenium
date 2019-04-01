package com.essa.pageObject.LogisticShipp;


import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.pageObject.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//船务看板-通知确认船务资料界面
public class LogisticsShippPage extends BasePage {
    public LogisticsShippPage(WebDriver driver) {
        super(driver);
    }

    // 通知确认船务资料看板
    @FindBy(xpath = "//a[text()='通知确认船务资料']")
    WebElement NoticeBoard;

    //等待数据结构
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/table/tbody/tr[1]")
    WebElement resultDate;

    //订单编号输入框
    @FindBy (xpath = "//input[@type='text' and @ng-model ='query.keyword']")
    WebElement setPono;

    //高级查询按钮
    @FindBy (xpath = "//i[@class='iconfont icon-search']")
    WebElement querySubBtn;

    //查询结果
    @FindBy (xpath = "//input[@type ='checkbox' and @checklist-value='item']")
    WebElement selectedpo;

    //点击通知确认船务资料
    @FindBy (xpath = "//button[text()='通知确认船务资料']")
    WebElement skippSubBtn;

    //点击确认按钮
    @FindBy (xpath = "//html/body/div[5]/div/div/div[2]/div/div/button[1]")
    WebElement submitSubBtn;

    //点击退出登录
    @FindBy (xpath="//*[text()='退出']")
    WebElement logout;

    //搜索并通知业务补充船务资料
    public HomePage isSucceed() {
        forceWait(1000);
        click(NoticeBoard);
        dynamicWait(By.xpath("//tbody/tr[1]/td[1]/input[1]"));//列表加载
//        setPono.sendKeys("D180411T0278");
        sendKeys(setPono, Model.getPoNum());
        click(querySubBtn);
        mywait(selectedpo);
        click(selectedpo);
        mywait(selectedpo);
        click(skippSubBtn);
        mywait(submitSubBtn);
        click(submitSubBtn);
        String saleman = DBSqlSearch("select a.account_name from pri_user a INNER JOIN\n" +
                "sale_po b on a.id=b.salesman_id\n" +
                "where b.`code` = '"+ Model.getPoNum()+"';","account_name");//根据PO单号查出对应的业务员
        Model.setSalesman(saleman);//将查询出的业务员存储在model中
        return new HomePage(driver);
    }
}
