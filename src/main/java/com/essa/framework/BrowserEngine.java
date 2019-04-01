package com.essa.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class BrowserEngine {  
    
    private static BrowserEnum browserEnum;
    private  WebDriver driver;
    private static EnvEnum env1;

    private ConfigProperties configProperties;

    public void initConfigData() {
        //有些地方是没有设置初始化环境直接调用这个方法的，所以默认是sit
        env1 = null == env1 ? EnvEnum.SIT : env1;
        configProperties = ConfigProperties.getConfig(env1);
        browserEnum = BrowserEnum.fromCode(configProperties.getBrowserName());
        Model.setEnv(env1.getCode());//我也不知道为什么执行完上面的代码后，env会变为sit
    }  
    /**
     * bpms环境初始化
     * @param environment
     * @param browser
     */
    public static void setInit(String environment,String browser) {
        browserEnum = BrowserEnum.fromCode(browser);
    	env1 = EnvEnum.fromCode(environment);
    }
      
    /**
     * bpms获取地址方法
     * @return
     */
    private WebDriver getBrowser(BrowserEnum browser,String url,int waitTime){
        System.setProperty(browser.getExeName(), SystemConstant.RESOURCE_PATH + browser.getDriver());
        //根据浏览器类型，初始化对应的驱动
        switch(browser) {
            case Firefox:{
                driver = new FirefoxDriver();
                break;
            }
            case Chrome: {
                driver = new ChromeDriver();
                break;
            }
            case IE: {
                driver = new InternetExplorerDriver();
            }
        }

        Logger.Output(LogType.LogTypeName.INFO, "正在启动" + browser.getCode() +"浏览器");
    	driver.manage().window().maximize();
        Logger.Output(LogType.LogTypeName.INFO, "窗口最大化");
        driver.get(url);
        Logger.Output(LogType.LogTypeName.INFO, "打开URL: "+ url);
        callWait(waitTime);
        return driver;  
    }  
   
    /**
     * buyer获取浏览器，并读取buyer的地址
     * @return
     */
    public WebDriver buyerGetBrowser() {
    	/*if (browserName.equals("Chrome")) {
    		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe"); //图形界面
    		driver= new ChromeDriver();  
            Logger.Output(LogType.LogTypeName.INFO, "正在启动Chrome浏览器");  
		}else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");    
            driver = new FirefoxDriver();  
            Logger.Output(LogType.LogTypeName.INFO, "正在启动FireFox浏览器"); 
		}
    	driver.manage().window().maximize();  
        Logger.Output(LogType.LogTypeName.INFO, "窗口最大化");
        driver.get(buyerURL);  
        Logger.Output(LogType.LogTypeName.INFO, "打开URL: "+ buyerURL);  
        callWait(5);  */
    	return getBrowser(browserEnum,configProperties.getBuyerURL(),5);
//        return driver;
    }

    public WebDriver getBrowser() {
        /*if(browserName.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            Logger.Output(LogType.LogTypeName.INFO, "正在启动FireFox浏览器");
        }
        else if(browserName.equals("Chrome")){
            System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");  //图形界面
            driver= new ChromeDriver();
            Logger.Output(LogType.LogTypeName.INFO, "正在启动Chrome浏览器");
        }else if(browserName.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver", ".\\src\\main\resources\\IEDriverServer.exe");
            driver= new InternetExplorerDriver();
            Logger.Output(LogType.LogTypeName.INFO, "正在启动IE浏览器");
        }
        driver.manage().window().maximize();
        Logger.Output(LogType.LogTypeName.INFO, "窗口最大化");
        driver.get(serverURL);
        Logger.Output(LogType.LogTypeName.INFO, "打开URL: "+ serverURL);
        callWait(5);*/
        return getBrowser(browserEnum,configProperties.getServerURL(),5);
//        return driver;
    }
      
      
    /**
     * 退出驱动
     * @throws InterruptedException
     */
    public void tearDown() throws InterruptedException{  
        Logger.Output(LogType.LogTypeName.INFO, "关闭浏览器");  
        driver.quit();  
        Thread.sleep(3000);  
    }  
      
    /**
     * 隐式时间等待方法 
     * @param time
     */
    public void callWait(int time){  
          
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);  
        Logger.Output(LogType.LogTypeName.INFO, "设置隐性等待"+time+" 秒");  
    }  
    /**
     *跳过安全链接 
     */
    public  void anQuan() {
    	 // 创建DesiredCapabilities类的一个对象实例  
        DesiredCapabilities cap=DesiredCapabilities.chrome();  
        // 设置变量ACCEPT_SSL_CERTS的值为True  
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        Logger.Output(LogType.LogTypeName.INFO, "设置浏览器可以打开不安全链接");
    }
}

