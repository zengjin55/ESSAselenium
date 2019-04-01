package com.essa.testSuite;

import com.essa.pageObject.BaseTest;
import com.essa.pageObject.GoodsManage.*;
import com.essa.pageObject.HomePage;
import com.essa.pageObject.productDev.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
* @Description: 新增开发商品测试用例
* @Author: ZengJin
* @CreateTime: 2018/10/29
*/
public class TestAddDevGood extends BaseTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        initsetUp();
        loginValid("linrong");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    /**
     *新增开发商品
     * @param
     * @return void
     */
    @Test
    public void addDevGood(){
        this.driver = getDriver();
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        AddProjectPage addProjectPage = PageFactory.initElements(driver,AddProjectPage.class);
        AddMatterialPage addMatterialPage = PageFactory.initElements(driver,AddMatterialPage.class);
        MaterialListPage materialListPage = PageFactory.initElements(driver,MaterialListPage.class);
        ProjectListPage projectListPage = PageFactory.initElements(driver,ProjectListPage.class);
        EditBOMPage editBOMPage = PageFactory.initElements(driver,EditBOMPage.class);
        AddOriginalGoodsPage addOriginalGoodsPage = PageFactory.initElements(driver,AddOriginalGoodsPage.class);
        homePage.toAddProject();
        addProjectPage.addProject();//新增立项书
        addMatterialPage.addBodyMat();//新增光身物料
        homePage.toMaterialPage();
        addMatterialPage.addMaterial();//新增物料
        materialListPage.setSupplierCatConfig();//设置核心供应商
        homePage.toProjectListPage();
        projectListPage.updateBom();//更新BOM
        editBOMPage.editBom();//编辑BOM，设置集单物料
        projectListPage.publicDevGood();//发布开发单品
        addOriginalGoodsPage.addDevGood();//确认发布开发商品
        projectListPage.getDevGoodNo();//将新增的skuNo传递到model
    }
    /**
     *审核开发商品
     * @param
     * @return
     */
    @Test
    public void publicDevGood(){
        this.driver = getDriver();
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        GoodsRelesePage goodsRelesePage = PageFactory.initElements(driver,GoodsRelesePage.class);
        UpdatePicPage updatePicPage = PageFactory.initElements(driver,UpdatePicPage.class);
        AuditDevGoodsPage auditDevGoodsPage = PageFactory.initElements(driver,AuditDevGoodsPage.class);
        AuditOriginalOpreatePage auditOriginalOpreatePage = PageFactory.initElements(driver,AuditOriginalOpreatePage.class);
        homePage.tGoodsRelesePage();
        goodsRelesePage.toUpdateDevGoodPic();//更新开发商品图片
        updatePicPage.upDatePic();
        homePage.toAuditDevgoodsPage();//进入开发商品发布审核
        auditDevGoodsPage.auditDevGood();
        auditOriginalOpreatePage.auditDevGoodPass();//审核通过
    }
}
