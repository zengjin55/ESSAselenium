package com.essa.pageObject.PODocumentary;

import com.essa.framework.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.essa.framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

/**
 * @author Administrator
 *PO详情页
 */
public class POBoardPage extends BasePage {

	public POBoardPage(WebDriver driver) { super(driver); }

    /*
     * 元素定位
     */
    @FindBy (xpath = "//*[contains(text(),'通知收货')]")
    WebElement NoticeReceipt;//通知收货

    @FindBy (xpath = "//div[@class='col-sm-16']//input[@type='text']")
    WebElement receiptDate;//选择收货日期

    @FindBy (xpath = "//input[@name='newDate']")
    WebElement loadingDate;//选择装柜日期

    @FindBy (xpath = "//*[contains(text(),'确定')]")
    WebElement submit;//提交

    @FindBy (xpath = "//div[@class='bootbox modal fade in']")
    WebElement BlankPlace;//点击空白处

    @FindBy (xpath = "//*[contains(text(),'通知装柜')]")
    WebElement NotificationLoading;//通知装柜

    //通知收货
    public POBoardPage toNoticeReceipt() {
        click(NoticeReceipt);
        jsExecutorRemoveAttribute(receiptDate,"readonly");
        String Receiptdate = getDateTimeByFormat(new Date(), "MM/dd/yyyy");
        sendKeys(receiptDate,Receiptdate);
        click(BlankPlace);
        click(submit);
        forceWait(4000);

        return new POBoardPage(driver);
    }

    /**
     * 判断通知收货是否成功
     * @return boolean
     */
    public boolean isNoticeReceipt (){
        forceWait(2000);
        return isVisibility(By.xpath("//*[contains(text(),'收货中')]"));
    }


    //通知装柜
    public POBoardPage toNotificationLoading(){
        click(NotificationLoading);
        jsExecutorRemoveAttribute(loadingDate,"readonly");
//        String Loadingdate = getDateTimeByFormat(new Date(), "MM/dd/yyyy");
        sendKeys(loadingDate, Tools.getToday());
        click(BlankPlace);
        click(submit);
        forceWait(4000);

        return new POBoardPage(driver);
    }

    /**
     * 判断通知装柜是否成功
     * @return boolean
     */
    public boolean isNotificationLoading(){
        forceWait(2000);
        return isVisibility(By.xpath("//*[contains(text(),'装柜中')]"));
    }
}