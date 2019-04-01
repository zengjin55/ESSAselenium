package com.essa.pageObject.LogisticShipp;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.framework.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//订车页面
public class BookingCarPage extends BasePage {

    public BookingCarPage(WebDriver driver) {
        super(driver);
    }

    //订车看板
    @FindBy(xpath = "//*[text()='订车']")
    WebElement BookingCarBoard;

    //查询条件
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/form/div/div/input")
    WebElement SetQuery;

    //查询按钮
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/form/div/div/span/button/i")
    WebElement QueryBtn;

    //查询数据
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/table/tbody/tr/td[1]")
    WebElement QueryRsult;

    //订车按钮
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/div[1]/button[1]")
    WebElement BookingCarBtn;

    //订车类型
    @FindBy(xpath = "//*[text()='贷代订车']")
    WebElement BookingCarSeclType;

    //预计到车日期
    @FindBy(xpath = "//*[@name='expectTruckArrivingDate']")
    WebElement BookingCarDate;

    //确定按钮
    @FindBy(xpath = "//button[@data-bb-handler='submit']")
    WebElement SubmitBtn;

    public BookingCarPage toBookingCarPage() {
        mywait(BookingCarBoard);
        click(BookingCarBoard);
        SetQuery.sendKeys(Model.getPoNum());
        click(QueryBtn);
        forceWait(1000);
        click(QueryRsult);
        click(BookingCarBtn);
        mywait(BookingCarSeclType);
        click(BookingCarSeclType);
//        mywait(BookingCarDate);
        jsExecutorRemoveAttribute(BookingCarDate, "readonly");
//        sendKeys(BookingCarDate,);
        click(BookingCarDate);
        paste(Tools.getFormatTime());
        click(SubmitBtn);
        return new BookingCarPage(driver);
    }
}
