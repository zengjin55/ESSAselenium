package com.essa.pageObject;  
  
import com.essa.pageObject.DocumentaryManage.BillingCenterPage;
import com.essa.pageObject.DocumentaryManage.ReceiptCorePage;
import com.essa.pageObject.GoodsManage.*;
import com.essa.pageObject.LogisticShipp.LogisticsShippPage;
import org.openqa.selenium.By;
import com.essa.pageObject.SupplierManage.SupplierOperationsTrackPage;
import com.essa.pageObject.productDev.AddMatterialPage;
import com.essa.pageObject.productDev.AddProjectPage;
import com.essa.pageObject.productDev.ProjectListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;  
import org.openqa.selenium.support.FindBy;
import com.essa.framework.BasePage;
import com.essa.framework.Model;
import com.essa.pageObject.DocumentaryManage.PODocumentaryListPage;
import com.essa.pageObject.StorageManage.GoodsReceiveTaskPage;
import com.essa.pageObject.StorageManage.NoticeReceivePage;
import com.essa.pageObject.buyPlaneManage.SkuCategoryManagerCongfigPage;
import com.essa.pageObject.buyerManage.InvateCodePage;
import com.essa.pageObject.inquiryManage.ProductInquiryTaskPage;
import com.essa.pageObject.marketingManage.GroupControlPage;
import com.essa.pageObject.marketingManage.GroupSettingPage;
import com.essa.pageObject.needDealt.NeedDealtApplyPage;
import com.essa.pageObject.StorageManage.CabineTaskManagemenPage;

/**
 * @author Administrator
 *bpms后台首页
 */
public class HomePage extends BasePage{  
    public HomePage(WebDriver driver) {  
        super(driver);  
    }  
    
    /*
     * 元素定位
     */
    
    //退出
    @FindBy (xpath="//*[text()='退出']")
    WebElement logout;
    
    //左上角图标--用于回到首页
    @FindBy (xpath="//*[@class='logo-text']")
    WebElement essaIcon;
    
    //供应商管理
    @FindBy (xpath="//*[text()='供应商管理']")
    WebElement supplier;
    
    //平台运营跟进管理
    @FindBy (xpath="//*/a[contains(text(),'平台运营跟进管理')]")
    WebElement operationsTrack;
    
    //供应商查询
    @FindBy (xpath="//*[text()='供应商查询']")
    WebElement searchSuppliers;
    
    //商品管理
    @FindBy (xpath="//*[text()='商品管理']")
    WebElement goodsManage;

    @FindBy (xpath = "//*[text()='开发商品发布审核']")
    WebElement DevGoodAudit;//开发商品发布审核
    
    //商品库
    @FindBy (xpath="//*[text()='商品库']")
    WebElement goodBank;
    
    //原厂商品发布
    @FindBy (xpath="//*[text()='原厂商品发布']")
    WebElement addOriginalGoods;
    
    //市场商品发布
    @FindBy (xpath="//*[text()='市场商品发布']")
    WebElement marketGoodsRelese;
    
    //商品发布管理-子元素
    @FindBy(xpath="//*[@name='child.text' and text()='商品发布管理']")
    WebElement goodsPublish;
    
    //原厂商品发布审核
    @FindBy(xpath="//*[text()='原厂商品发布审核']")
    WebElement auditOriginal;
    
    //市场商品发布审核
    @FindBy(xpath="//*[text()='市场商品发布审核']")
    WebElement auditMarket;
    
    //采购计划管理
    @FindBy(xpath="//*[text()='采购计划管理']")
    WebElement buyerPlaneManage;
    
    //商品类目经理分配配置
    @FindBy(xpath="//*[text()='商品类目经理分配配置']")
    WebElement skuManagerConfig;
    
    //营销管理
    @FindBy (xpath="//*[text()='营销管理']")
    WebElement marketingManage;
    
    //团购设置
    @FindBy (xpath="//*[text()='团购设置']/..")
    WebElement groupSetting;
    
    //团购控制
    @FindBy (xpath="//*[text()='团购控制']/..")
    WebElement groupControl;
    
    //采购商管理
    @FindBy (xpath="//*[text()='采购商管理']")
    WebElement buyerManage;
    
    //生成邀请码
    @FindBy (xpath="//*[text()='生成邀请码']"	)
    WebElement inviteCode;
    
    //询价管理
    @FindBy (xpath ="//span[text()='询价管理']")
    WebElement inquiryManage;
    
    //成品询价任务列表
    @FindBy (xpath = "//a[contains(text(),'成品询价任务列表')]")
    WebElement productInquiryTask;
    
    //产品开发
    @FindBy (xpath ="//*[text()='产品开发']")
    WebElement productDev;
    
    //项目立项书新增
    @FindBy (xpath ="//*[text()='项目立项书新增']")
    WebElement addProject;
    
    //项目立项书查询
    @FindBy (xpath ="//*[text()='项目立项书查询']")
    WebElement quiryProject;
    
    //待办中的"采购计划审核流程"
    @FindBy (xpath = "//*[@title='采购计划审核流程']")
    WebElement purchasePlaneProcess;
    
    //待办中的“船务确认备选船期流程”
    @FindBy (xpath = "//*[text()='船务确认备选船期流程']")
    WebElement confirmDailingDate;

    //第一条待办
    @FindBy (xpath = "//*[@ng-table='tableParams']/tbody/tr[1]")
    WebElement firstNeedDealt;
    
    //待办搜索文本框
    @FindBy (xpath ="//*[@name='searchForm']/div/input")
    WebElement searchText;
    
    //待办搜索按钮
    @FindBy (xpath = "//*[@name='searchForm']/div/span/button")
    WebElement search;
    
    //跟单管理
    @FindBy (xpath = "//*[text()='跟单管理']")
    WebElement DocumentaryManage;
    
    //PO跟单
    @FindBy (xpath ="//*[text()='PO跟单']")
    WebElement PODocumentary;

    //前台发单中心
    @FindBy (xpath ="//*[text()='前台发单中心']")
    WebElement Receipt_core ;
    
    //仓储管理
    @FindBy (xpath = "//span[contains(text(),'仓储管理')]")
    WebElement StorageManage;

    //收货通知
    @FindBy (xpath = "//a[@name='child.text'][contains(text(),'收货通知')]")
    WebElement NoticeReceive;

    //收货任务管理
    @FindBy (xpath = "//a[contains(text(),'收货任务管理')]")
    WebElement goodsReceiveTask;


    //物料新增
    @FindBy (xpath = "//*[text()='物料新增']")
    WebElement addMaterial;

    //装柜任务管理
    @FindBy (xpath = "//a[@name='child.text'][contains(text(),'装柜任务管理')]")
    WebElement loadContainerTask;

    //前台发单中心
    @FindBy (xpath = "//*[text()='前台发单中心']")
    WebElement billingCenter;


    //物流船务
    @FindBy (xpath = "//*[text()='物流船务']")
    WebElement LogisticsskippBoard;

    //船务看板
    @FindBy (xpath = "//*[text()='船务看板']")
    WebElement skippBoard;

    //装柜通知（lana）
    @FindBy (xpath = "//a[@name='child.text'][contains(text(),'装柜通知')]")
    WebElement CabinetsNotice;

    //装柜务务管理（lana）
    @FindBy (xpath = "//a[contains(text(),'装柜任务管理')]")
    WebElement   CabineTaskManagemen;




    /*
     * 方法
     */
    
    /**
     * 进入运营跟进管理页面
     * @return SupplierOperationsTrackPage
     */
    public SupplierOperationsTrackPage goToSupplierOperationsTrack() {
    	
    	//点击 供应商管理
    	click(supplier);
    	
    	//点击 平台运营跟进管理
    	click(operationsTrack);
    	
    	//此时，系统会加载一个平台运营跟进管理页面，故在此初始化该页面并将driver传递过去
    	return new SupplierOperationsTrackPage(driver);
    	
    }
    
    /**
     * 进入原厂商品发布
     * @return AddOriginalGoodsPage
     */
    public AddOriginalGoodsPage tOriginalGoodsPage() {
    	click(goodsManage);
    	isElementExist(addOriginalGoods);
    	click(addOriginalGoods);
    	return new AddOriginalGoodsPage(driver);
    }
    
    /**
     * 进入商品发布管理
     * @return GoodsRelesePage
     */
    public GoodsRelesePage tGoodsRelesePage() {
        getHome();
    	click(goodsManage);
    	click(goodsPublish);
    	return new GoodsRelesePage(driver);
    }
    
    /**
     * 进入原厂商品发布审核
     * @return AuditOriginalGoodsPage
     */
    public AuditOriginalGoodsPage toAuditOriginalGoodsPage() {
    	mywait(logout);
    	click(goodsManage);
    	click(auditOriginal);
    	return new AuditOriginalGoodsPage(driver);
    }
    
    /**
     * 进入商品库
     * @return GoodsBankPage
     */
    public GoodsBankPage toGoodsBankPage() {
    	click(goodsManage);
    	click(goodBank);
    	return new GoodsBankPage(driver);
    }
    
    /**
     * 进入市场商品发布
     * @return MarketGoodsRelesePage
     */
    public MarketGoodsRelesePage toMarketGoodsRelesePage() {
    	click(goodsManage);
    	click(marketGoodsRelese);
    	return new MarketGoodsRelesePage(driver);
    }
    /**
     * 进入市场商品发布审核 
     * @return AuditMarketGoodsPage
     */
    public AuditMarketGoodsPage toAuditMarketGoodsPage() {
    	click(goodsManage);
    	click(auditMarket);
    	return new AuditMarketGoodsPage(driver);
    }
    /**
     * 进入商品类目经理分配配置
     * @return SkuCategoryManagerCongfigPage
     */
    public SkuCategoryManagerCongfigPage toSkuCategoryManagerCongfig() {
    	mywait(logout);
    	click(buyerPlaneManage);
    	click(skuManagerConfig);
    	return new SkuCategoryManagerCongfigPage(driver);
    }
    /**
     * 进入团购设置
     * @return GroupSettingPage
     */
    public GroupSettingPage toGroupSettingPage() {
    	getHome();
    	click(marketingManage);
    	click(groupSetting);
    	return new GroupSettingPage(driver);
    }
    /**
     * 进入团购控制
     * @return GroupControlPage
     */
    public GroupControlPage toGroupControlPage() {
//    	getHome();
    	jsExecutorClick(essaIcon);
    	click(marketingManage);
    	click(groupControl);
    	return new GroupControlPage(driver);
    }
    /**
     * 进入生成邀请码
     * @return InvateCodePage
     */
    public InvateCodePage toInvateCodePage() {
    	click(buyerManage);
    	moveHeightScroll("100");
    	click(inviteCode);
    	return new InvateCodePage(driver);
    }
    /**
     * 进入成品询价任务列表
     * @return ProductInquiryTask
     */
    public ProductInquiryTaskPage toProductInquiryTask() {
    	click(inquiryManage);
    	click(productInquiryTask);
    	return new ProductInquiryTaskPage(driver);
    }
    
    
    /**
     * 判断是否进入后台首页
     * @return boolean
     */
    public boolean isSucceed() {
    	return isThisPage("退出", logout);
    }
    
    /**
     * 判断是否选中“供应商管理”
     * @return
     */
    public boolean isSearchSuppliers() {
    	return isElementExist(searchSuppliers);
    }
    
    /**
     *点击essa图标， 回到bpms后台首页
     */
    public void getHome() {
    	forceWait(500);
    	jsExecutorClick(essaIcon);
    	forceWait(1000);
    }
    /**
     * 退出登录
     */
    public void logout() {
    	click(logout);
    }
    
    /**
     * 点击退出，有弹框确认是否离开时，用这个
     */
    public void sureLogout() {
    	click(logout);
    	alert(true);
    	forceWait(1000);
    }
    
    /**
     * 团购活动达成，类目经理采购计划单申请
     * @return 询价待办申请页面
     */
    public NeedDealtApplyPage applyPurchasePlane() {
    	mywait(firstNeedDealt);
    	click(purchasePlaneProcess);
    	forceWait(500);
    	click(firstNeedDealt);
    	switchWindow();
    	return new NeedDealtApplyPage(driver);
    }
    
    /**
     * 待办详情页
     * @return 进入待办详情页
     */
    public NeedDealtApplyPage toNeedDealtDetailPage() {
    	mywait(firstNeedDealt);
    	sendKeys(searchText, Model.getSerialNum());
    	click(search);
    	forceWait(1000);
    	mywait(firstNeedDealt);
    	click(firstNeedDealt);
//    	switchWindow();
    	switchMoreWindow();
    	return new NeedDealtApplyPage(driver);
    }
    
    /**
     * 进入PO跟单列表页
     * @return PO跟单列表页
     */
    public PODocumentaryListPage toPoDocumentaryListPage() {
        getHome();
    	click(DocumentaryManage);
    	click(PODocumentary);
    	return new PODocumentaryListPage(driver);
    }

    /**
     * 进入收货通知页面
     * @return 收货通知页面
     */
    public NoticeReceivePage toNoticeReceivePage() {
    	click(StorageManage);
    	click(NoticeReceive);
    	return new NoticeReceivePage(driver);
    }

    /**
     * 进入收货任务管理
     * @return 收货任务管理页面
     */
    public GoodsReceiveTaskPage toGoodsReceiveTaskPage() {
        getHome();
    	click(StorageManage);
    	click(goodsReceiveTask);
    	return new GoodsReceiveTaskPage(driver);
    }
    /**
     * 大跟单进入第一个需要确认备选船期的待办页面
     * @return 确认船期页面
     */
    public NeedDealtApplyPage toSailingDateConfirmPage() {
    	click(confirmDailingDate);
    	forceWait(1000);
    	click(firstNeedDealt);
    	switchMoreWindow();
    	return new NeedDealtApplyPage(driver);
    }

    /**
     * 进入装柜任务处理管理页面
     * @return 返回装柜任务处理管理页面
     */
    public NeedDealtApplyPage toLoadContainer(){
        getHome();
        click(StorageManage);
        click(loadContainerTask);
        dynamicWait(By.xpath("//div[@class='loading ng-scope']"));
//        forceWait(3000);
        return new NeedDealtApplyPage(driver);
    }

    /**
     * 登记备选船期待办
     * @return 进入登记船务船期待办详情页面
     */
    public NeedDealtApplyPage toShippingMarkWait(){
        mywait(firstNeedDealt);
        sendKeys(searchText,Model.getSerialNum());
        click(search);
        forceWait(1000);
        mywait(firstNeedDealt);
        click(firstNeedDealt);
        switchMoreWindow();
        return new NeedDealtApplyPage(driver);
    }

    /*
     * 进入前台发单中心页面
     * */
    public ReceiptCorePage toReceiptCore() {
        click(DocumentaryManage);
        click(Receipt_core);
        return new ReceiptCorePage (driver);
    }
    /**
     *进入开发商品发布审核
     * @param
     * @return 开发商品发布审核
     */
    public AuditDevGoodsPage toAuditDevgoodsPage(){
        click(goodsManage);
        click(DevGoodAudit);
        return new AuditDevGoodsPage(driver);
    }
    /**
     *进入项目立项书新增页面
     * @param
     * @return 项目立项书新增
     */
    public AddProjectPage toAddProject(){
        click(productDev);
        click(addProject);
        return new AddProjectPage(driver);
    }
    /**
     *进入物料新增页面
     * @param
     * @return 物料新增页面
     */
    public AddMatterialPage toMaterialPage(){
        click(productDev);
        click(addMaterial);
        return new AddMatterialPage(driver);
    }
    /**
     *进入项目立项书列表
     * @param
     * @return 项目立项书查询
     */
    public ProjectListPage toProjectListPage(){
        getHome();
        click(productDev);
        click(quiryProject);
        return new ProjectListPage(driver);
    }
    /**
     *进入前台发单中心
     * @param
     * @return 前台发单中心页面
     */
    public BillingCenterPage toBillingCenterPage() {
        click(DocumentaryManage);
        click(billingCenter);
        return new BillingCenterPage(driver);
    }

    /**
     *进入船务看板界面
     */
    public LogisticsShippPage toLogisticsShippPage(){
//        mywait(LogisticsskippBoard);
        click(LogisticsskippBoard);
//        forceWait(1000);
        click(skippBoard);
//        jsExecutorClick(skippBoard);
        return new LogisticsShippPage(driver);
    }
    /**
     *业务员进入待办详情页，填写船务资料
     * @param
     * @return
     */
    public NeedDealtApplyPage toWriteShipping() {
        click(firstNeedDealt);
        switchMoreWindow();
        return new NeedDealtApplyPage(driver);
    }

    /**
     * 进入装柜任务管理
     * @return装柜任务管理
     */
    public CabineTaskManagemenPage toCabineTaskManagemenPage() {
        click(StorageManage);
        click(CabineTaskManagemen);
        return new  CabineTaskManagemenPage(driver);
    }
    /**
     *对浏览器弹框处理
     * @param
     * @return
     */
    public void dealAlert(boolean isAccept){
        alert(isAccept);
    }

}