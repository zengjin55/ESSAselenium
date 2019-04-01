package com.essa.pageObject.DocumentaryManage;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public  class ReceiptCorePage extends BasePage {

    public ReceiptCorePage(WebDriver driver) { super(driver);}

    /*
     * 页面元素定位
     * */

    @FindBy (xpath = "//*[contains(text(),'对不起！没有查询到相关数据')]")

    WebElement Wait ;//等待

    @FindBy(xpath = "//i[@class='iconfont icon-double-arrow-right']")
    //    @FindBy (xpath = "//*[contains(text(),'高级查询')]")
            WebElement advancedQuery; //高级查询

    @FindBy (xpath = "/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/form[2]/div[5]/div[1]/input[1]")
    //    @FindBy (xpath = "//input[@type='text' and @ng-model='query.params.poCode']")

            WebElement PO_numbers;  //PO单号

    @FindBy (xpath = "//*[text()='查询']")
    //    @FindBy (xpath = "//*/button[@type='submit']")

            WebElement Query ; //查询

    @FindBy (xpath = "//tbody//tr[1]")
    //    @FindBy (xpath = "//*[@id=\"frontSendListCtrlView\"]/div/div/table/tbody/tr[1]")

            WebElement list_1; //列表第一条数据

    @FindBy (xpath = "/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]")

    WebElement list; //列表

    @FindBy (xpath = "/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")

    WebElement document_code; //单据编号

    @FindBy (xpath = "//*[text()='开始发单']")
    //    @FindBy (xpath = "/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]")

            WebElement  start_billing;  // 开始发单

    @FindBy (xpath = "//button[@class='btn btn-danger ng-scope']")

    WebElement  start_billing_1 ;  //前台发单扫码平台的开始发单

    @FindBy (xpath = "//div[@class='input-group ng-isolate-scope ng-pristine ng-valid']//input[@type='text']")

    WebElement input_document_code;//单据编号输入框

    @FindBy (xpath = "//button[@type='button' and @ng-click='clickBtn()']")
    //            @FindBy (xpath = "//*[contains(@ng-click,'clickBtn')]")

            WebElement  search ;// 搜索按钮

    @FindBy (xpath = "//a[contains(text(),'扫码确认接单')]")

    WebElement Confirm_billing ; // 扫码确认接单

    @FindBy (xpath = "//*[text()='接单完成']")

    WebElement finish_billing; //接单完成

    @FindBy (xpath = "//*[contains(text(),'所选的工厂订单状态必须为“制单中”或“重新发单”')]")

    WebElement Fail_billing; //发单失败

    @FindBy (xpath = "//*[contains(text(),'所选的工厂订单状态必须为“发单中”')]")

    //*[coantains(text(),'所选的工厂订单状态必须为']
            // *[@data-notify='message']
            //*[@data-notify='message'] and text()='所选的工厂订单状态必须为“发单中”'
            //*[(text()=' 所选的工厂订单状态必须为“发单中”']

            WebElement Fail_order; //接单失败

    @FindBy (xpath = "//*[contains(text(),'操作成功')]")

    WebElement  Succeed_billing; //发单成功





    /**
     *页面方法
     * 前台发单中心页面
     */

    public  void  BillOrder() {

        dynamicWait(By.xpath("//tbody//tr[1]"));

        click(advancedQuery);
//        forceWait(1000);

        sendKeys(PO_numbers, Model.getPoNum());
//        forceWait(1000);

        click(Query);
        //        forceWait(3000);

        dynamicWait(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]"));

        //        actionDoubleClick(document_code); // 双击元素
        //        forceWait(1000);
        String Fo = document_code.getText();
//        forceWait(1000);

        click(start_billing);
        forceWait(1000);
        sendKeys(input_document_code,Fo);
//        forceWait(1000);

        click(search);
        forceWait(500);

        click(start_billing_1);
//        forceWait(1000);
        //        isVisibility(By.xpath("//*[contains(text(),'所选的工厂订单状态必须为“发单中”')]"));
        dynamicLoad();
        click(start_billing);
        forceWait(1000);

        click(Confirm_billing);
        forceWait(1000);

        sendKeys(input_document_code,Fo);
        forceWait(1000);

        click(search);
        forceWait(1000);

        click(finish_billing);
        forceWait(3000);



    }


    /*
     *用于断言发单接单是否成功
     * @return boolean
     */

    public  boolean isSucceed() {return  isElementExist(Succeed_billing);}

    /*
     *用于断言发单是否成功
     * @return boolean
     */

    public  boolean FailBilling() {return  isElementExist(Fail_billing);}

    /*
     *用于断言接单是否成功
     * @return boolean
     */


    public  boolean FailOrder() {return  isElementExist(Fail_order);}







}
