package com.essa.pageObject.needDealt;

import com.essa.framework.BasePage;
import com.essa.pageObject.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//大跟单确认备选船期待办界面
public class LargeDcofdPage extends BasePage {
    public LargeDcofdPage (WebDriver driver) {
        super(driver);
    }

    // 确认登记备选船期
    @FindBy(xpath = "//*[@id='tableViewList']/div/div/div/div[4]/span[1]/button[2]")
    WebElement ConfirmationShipDate;

    /**
     *大跟单确认船期
     * @param
     * @return
     */
    public HomePage toLargeDcofdPage(){
//        mywait(ConfirmationShipDate);
        dynamicWait(By.xpath("//*[contains(text(),'待办工作')]"));
        click(ConfirmationShipDate);
        dynamicLoad();
        return new HomePage(driver);
    }
}
