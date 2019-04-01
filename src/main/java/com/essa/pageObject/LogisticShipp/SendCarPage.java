package com.essa.pageObject.LogisticShipp;

import com.essa.framework.BasePage;
import com.essa.framework.Model;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//确认发车页面
public class SendCarPage extends BasePage {
    public SendCarPage(WebDriver driver) {
        super(driver);
    }

    //订车看板
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/ul/li[4]")
    WebElement BookingCarBoard;

    //待发车看板
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/ul/li[2]")
    WebElement WaitForBookingCarBoard;

    //查询
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/form/div/div/input")
    WebElement QueryWaitForBooking;

    //查询按钮
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/form/div/div/span/button")
    WebElement QueryWaitForBookingCarBtn;

    //查询结果
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div/div/div/div/div/div/table/tbody/tr")
    WebElement FristWaitForBookingCarDate;

    //发车确认
    @FindBy(xpath = "//*[text()='发车确认']")
    WebElement ConfirmBookingCar;

    //柜号
    @FindBy(xpath = "//*[@name='containerNo']")
    WebElement SetContainerNo;

    //封条号
    @FindBy(xpath = "//*[@name='sealNo']")
    WebElement SetSealNo;

    //确认发车
    @FindBy(xpath = "//*[@data-bb-handler='submit']")
    WebElement SubmitBtn;

    public SendCarPage toSendCarPage() {
        forceWait(1000);
        click(BookingCarBoard);
        mywait(WaitForBookingCarBoard);
        click(WaitForBookingCarBoard);
        forceWait(500);
        QueryWaitForBooking.sendKeys(Model.getPoNum());
        click(QueryWaitForBookingCarBtn);
        forceWait(1000);
        click(FristWaitForBookingCarDate);
        click(ConfirmBookingCar);
        mywait(SetContainerNo);
        SetContainerNo.sendKeys("TestContainerNo");
        SetSealNo.sendKeys("TestSealNo");
        click(SubmitBtn);
        return new SendCarPage(driver);
    }
}
