package com.essa.framework;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.essa.framework.BasePage;
import com.essa.framework.LogType;
import com.essa.framework.Logger;
import com.mysql.cj.jdbc.PreparedStatement;

public class BasePage {

	public static WebDriver driver;
	public static String pageTitle;
	public static String pageUrl;
	public static String OutputFileName = getDateTimeByFormat(new Date(), "yyyyMMdd_HHmmss");  
	
	/**
	 *  构造方法
	 * @param driver
	 */
	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
	}

	/**
	 *  在文本框内输入字符
	 * @param element
	 * @param text
	 */
	protected void sendKeys(WebElement element, String text) {
		try {
			mywait(element);
			if (element.isEnabled()) {
				element.clear();
				Logger.Output(LogType.LogTypeName.INFO, "清除文本框中已有字符：" + partialStr(element.toString(), "xpath:"));
				element.sendKeys(text);
				Logger.Output(LogType.LogTypeName.INFO, "输入的字符是：" + text);
			}
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, element.toString()+"元素不存在");
		}
	}

	/**
	 * 点击元素，这里指点击鼠标左键
	 * @param element
	 */
	protected void click(WebElement element) {

		try {
			mywait(element);
			if (element.isEnabled()) {
				Logger.Output(LogType.LogTypeName.INFO, "点击元素：" + partialStr(element.toString(), "xpath:"));
				element.click();
			}
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}

	}

	/**
	 * 在文本输入框执行清除操作
	 * @param element
	 */
	protected void clear(WebElement element) {
		try {
			if (element.isEnabled()) {
				element.clear();
				Logger.Output(LogType.LogTypeName.INFO, "清除输入框中字符：" + partialStr(element.toString(), "xpath:") );
			}
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}

	}

	/**
	 * 判断一个页面元素是否显示在当前页面
	 * @param element
	 */
	protected void verifyElementIsPresent(WebElement element) {

		try {
			if (element.isDisplayed()) {
				Logger.Output(LogType.LogTypeName.INFO, "元素存在：" + partialStr(element.toString(), "xpath:").trim());

			}
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}
	}

	/**
	 * 获取页面的标题
	 * @return
	 */
	protected String getCurrentPageTitle() {

		pageTitle = driver.getTitle();
		Logger.Output(LogType.LogTypeName.INFO, "当前页面的标题为：" + pageTitle);
		return pageTitle;
	}

	/**
	 * 获取页面的url
	 * @return
	 */
	public static String getCurrentPageUrl() {
		pageUrl = driver.getCurrentUrl();
		Logger.Output(LogType.LogTypeName.INFO, "当前页面的URL为：" + pageUrl);
		return pageUrl;
	}

	/**
	 * 处理多窗口之间切换
	 */
	protected void switchWindow() {

		String currentWindow = driver.getWindowHandle();// 获取当前窗口句柄
		Set<String> handles = driver.getWindowHandles();// 获取所有窗口句柄
		Logger.Output(LogType.LogTypeName.INFO, "当前窗口数量： " + handles.size());
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			if (currentWindow == it.next()) {
				driver.close();
				continue;
			}
			try {
				// driver.close();// 关闭旧窗口
				WebDriver window = driver.switchTo().window(it.next());// 切换到新窗口
				Logger.Output(LogType.LogTypeName.INFO, "新窗口的标题为：" + window.getTitle());
			} catch (Exception e) {
				Logger.Output(LogType.LogTypeName.ERROR, "无法切换到新打开窗口" + e.getMessage());

			}
			// driver.close();//关闭当前焦点所在的窗口
		}
		// driver.switchTo().window(currentWindow);//回到原来页面
	}
	
	/**
	 * 处理多窗口之间切换
	 * 将会关闭当前的窗口
	 */
	protected void switchMoreWindow() {

		String handle = driver.getWindowHandle();
		for(String temhandle : driver.getWindowHandles()) {
			if (!temhandle.equals(handle)) {
				driver.close();
				driver.switchTo().window(temhandle);
			}
		}
	}
	/**
	 * 浏览器弹框操作，true确认弹框，false取消弹框
	 * @param isAccept
	 */
	protected void alert(boolean isAccept) {
		Alert alert = driver.switchTo().alert();
		if (isAccept) {
			Logger.Output(LogType.LogTypeName.INFO, "提示框内容为：" + alert.getText());
			alert.accept();
			Logger.Output(LogType.LogTypeName.INFO, "确认弹框");
		} else {
			Logger.Output(LogType.LogTypeName.INFO, "提示框内容为：" + alert.getText());
			alert.dismiss();
			Logger.Output(LogType.LogTypeName.INFO, "取消弹框");
		}
	}
	
	/**
	 * 下拉框选择选项
	 * 元素必须可以使用select，input和button使用会报错
	 * @param element
	 * @param optionText
	 */
	protected void selectElement(WebElement element, String optionText) {
		Select select = new Select(element);
		select.selectByVisibleText(optionText);
		Logger.Output(LogType.LogTypeName.INFO, "选择选项：" + optionText);
	}

	/**
	 *  下拉框选择选项，通过选项中的value来定位
	 * @param element
	 * @param value
	 */
	protected void selectElement(WebElement element, int value) {
		Select select = new Select(element);
		select.selectByIndex(value);
		Logger.Output(LogType.LogTypeName.INFO, "选择选项：" + value);
	}
	/**
	 * 判断元素在页面中是否存在
	 * @param element
	 * @return boolean
	 */
	protected boolean isElementExist(WebElement element) {
		try {
			Boolean bool = element.isDisplayed();
			
			Logger.Output(LogType.LogTypeName.INFO, "检查元素是否存在：" +partialStr(element.toString(), "xpath:")+":"+ bool);
			return bool;
		} catch (NoSuchElementException e) {
			takeScreenShot();
			Logger.Output(LogType.LogTypeName.ERROR, "无法确认当前元素是否存在：" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 元素在页面上是否可见，不建议用此方法，有时会报错，找不出原因
	 * @param element
	 * @return boolean
	 */
	protected boolean isVisibility(WebElement element) {
		try {
			if(ExpectedConditions.visibilityOf(element) != null) {
				Logger.Output(LogType.LogTypeName.INFO, "元素在页面上可见");
				return true;
			}
		} catch (NoSuchElementException e) {
			Logger.Output(LogType.LogTypeName.ERROR, "无法页面上是否有此元素");
			return false;
		}
		Logger.Output(LogType.LogTypeName.INFO, "元素在页面不可见");
		return false;
	}
	
	/**
	 * 元素在页面上是否可见
	 * @param
	 * @return
	 */
	protected boolean isVisibility(By by) {
		try {
			Logger.Output(LogType.LogTypeName.INFO, "检查元素在页面上是否可见");
			if(ExpectedConditions.visibilityOf(driver.findElement(by)) != null) {
				Logger.Output(LogType.LogTypeName.INFO, "元素可见:"+by.toString());
				return true;
			}
		} catch (NoSuchElementException e) {
		}
		Logger.Output(LogType.LogTypeName.INFO, "元素不可见："+by.toString());
		return false;
	}
	

	/**
	 * 获取元素的文本值
	 * @param element
	 */
	protected String getText(WebElement element) {

		try {
			if (element.isEnabled()) {
				Logger.Output(LogType.LogTypeName.INFO, "获取当前元素的文本值：" + element.getText());
				return element.getText();
			}
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}
		return null;
	}

	/**
	 * js的点击操作
	 * @param element
	 */
	protected void jsExecutorClick(WebElement element) {
		try {
			mywait(element);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", element);
			Logger.Output(LogType.LogTypeName.INFO, "调用JavaScript点击元素：" + partialStr(element.toString(), "xpath:"));
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}

	}
	
	/**
	 * js的删除操作
	 * @param webElement
	 * @param attribute
	 */
	protected void jsExecutorRemoveAttribute(WebElement webElement, String attribute) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "');", webElement);
			Logger.Output(LogType.LogTypeName.INFO, "调用JavaScript删除元素属性：" + attribute);
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}

	}

	/**
	 * 获取js返回的值
	 * @param webElement
	 * @return
	 */
	protected String jsExecutorGetAttributeValue(WebElement webElement) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			Logger.Output(LogType.LogTypeName.INFO, "调用JavaScript返回元素属性值");
			return (String) jsExecutor.executeScript("return arguments[0].id;", webElement);
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
			return null;
		}
	}

	/**
	 * 读取excel中的数据
	 * @param filepath excel的路径地址
	 * @param filename excel的文件名
	 * @param SheetName excel的worksheet名
	 * @return
	 * @throws Exception
	 */
	public static Object[][] readExcel(String filepath, String filename, String SheetName){
		try {
			File file = new File(filepath + "\\" + filename);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook Workbook = null;
			// 获取文件扩展名
			String fileExtensionName = filename.substring(filename.indexOf("."));
			Logger.Output(LogType.LogTypeName.INFO, "获取所要读取的文件");
			// 判断是.xlsx还是.xls的文件并进行实例化
			if (fileExtensionName.equals(".xlsx")) {
				Workbook = new XSSFWorkbook(inputStream);
				Logger.Output(LogType.LogTypeName.INFO, "文件为：.xlsx格式");
			} else if (fileExtensionName.equals(".xls")) {
				Workbook = new HSSFWorkbook(inputStream);
				Logger.Output(LogType.LogTypeName.INFO, "文件为：.xls格式");
			}
			// 通过sheetName生成Sheet对象
			Sheet Sheet = Workbook.getSheet(SheetName);
			int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
			List<Object[]> records = new ArrayList<Object[]>();
			for (int i = 0; i < rowCount + 1; i++) {
				Row row = Sheet.getRow(i);
				String fields[] = new String[row.getLastCellNum()];
				for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
					}
					// 判断数据的类型
					switch (row.getCell(j).getCellType()) {
					case Cell.CELL_TYPE_NUMERIC: // 数字
						fields[j] = String.valueOf(row.getCell(j).getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING: // 字符串
						fields[j] = String.valueOf(row.getCell(j).getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN: // Boolean
						fields[j] = String.valueOf(row.getCell(j).getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA: // 公式
						fields[j] = String.valueOf(row.getCell(j).getCellFormula());
						break;
					case Cell.CELL_TYPE_BLANK: // 空值
						fields[j] = "";
						break;
					case Cell.CELL_TYPE_ERROR: // 故障
						fields[j] = "非法字符";
						break;
					default:
						fields[j] = "未知类型";
						break;
					}
				}
				records.add(fields);
			}
			Object[][] results = new Object[records.size()][];
			for (int i = 0; i < records.size(); i++) {
				results[i] = records.get(i);
			}
			Logger.Output(LogType.LogTypeName.INFO, "读取文件成功");
			return results;
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}
		return null;
	}

	/**
	 * 上传文件
	 * @param filePath
	 * @throws Exception
	 */
	protected void uploadFile(String filePath){
		try {
			Logger.Output(LogType.LogTypeName.INFO, "开始上传文件");
			StringSelection sel = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
			// 新建一个Robot类的对象
			Robot robot = new Robot();
			Thread.sleep(2000);

			// 按下回车
			robot.keyPress(KeyEvent.VK_ENTER);

			// 释放回车
			robot.keyRelease(KeyEvent.VK_ENTER);

			// 按下 CTRL+V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			// 释放 CTRL+V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);

			// 点击回车 Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			Logger.Output(LogType.LogTypeName.INFO, "上传文件成功");
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}
	}

	/**
	 * 字符串切片
	 * @param element 需要被操作的元素
	 * @param begin 从这个字符开始切
	 * @param end 到这个字符结尾
	 * 例子：某个元素的文本值为：广州市天河区猎德，只要“天河区”
	 * 第二个参数：天, 第三个参数：区
	 * @return
	 */
	protected String partialStr(WebElement element, String begin, String end) {
		String result_string = element.getText();
		Logger.Output(LogType.LogTypeName.INFO, "获取所需切片的字符串");
		// 根据词切片，取第二片字符串
		String st1 = result_string.split(begin)[1];
		Logger.Output(LogType.LogTypeName.INFO, "切除" + begin + "之前的字符串");
		// 再切一次结尾，得到我们想要的结果
		String search_need = st1.split(end)[0];
		Logger.Output(LogType.LogTypeName.INFO, "切除" + end + "之后的字符串");
		Logger.Output(LogType.LogTypeName.INFO, "返回切片后的字符串");
		return search_need;
	}

	/**
	 * 字符串切片
	 * @param element 需要被操作的元素
	 * @param begin 从这个字符开始切
	 * @param end 到这个字符结尾
	 * @return
	 */
	protected String partiaStr(WebElement element,int begin,int end){
		String Str = element.getText();
		System.out.print("获取所需切片的字符串：");
		// 对字符串切片，保留begin之后，end之前的字符串
		System.out.println(Str.subSequence(begin,end));
		// 把切片值作为结果并返回
		String search_need = (String) Str.subSequence(begin,end);
		return search_need;
	}

	/**
	 * 复写切片，仅在本页面作为截断日志文本后面带的一堆字符串
	 * @param string
	 * @param begin
	 * @return
	 */
	protected String partialStr(String string,String begin) {
		String st1 = string.split(begin)[1];
		return st1;
	}

	/**要的元素是否存在，最多5秒
	 * @param element
	 */
	protected void mywait(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, 7);
//		Logger.Output(LogType.LogTypeName.INFO, "等待元素在页面上加载可见，最多5秒");
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**要的元素是否存在，传递元素表达式，最多5秒
	 * @param
	 */
	protected void mywait(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 7);
//		Logger.Output(LogType.LogTypeName.INFO, "等待元素在页面上加载可见，最多5秒");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
	}
	
	/**
	 * 设立检查点，判断页面是否是我们要的
	 * @param checkPoint
	 * @param element
	 * @return
	 */
	protected boolean isThisPage(String checkPoint,WebElement element) {
		boolean bool1=checkPoint.equals(element.getText());
		Logger.Output(LogType.LogTypeName.INFO, "判断检查点是否存在:"+bool1);
		return bool1;
	}

	/**
	 * 截图当前页面
	 */
	protected void takeScreenShot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			// 拷贝截图文件到我们项目./Screenshots
			FileUtils.copyFile(src, new File(".\\Log\\Screenshots\\"+OutputFileName+"截图.png"));
			Logger.Output(LogType.LogTypeName.INFO, "截图当前页面成功！");
		}

		catch (IOException e) {
			System.out.println(e.getMessage());
			Logger.Output(LogType.LogTypeName.ERROR, "截图当前页面失败！");
		}

	}
	
	/**
	 * 上下移动滚动条，这里使用js操作
	 * @param percent 0：最下方  100：最上方
	 */
	protected void moveHeightScroll(String percent) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scrollBy(0, 0-document.body.scrollHeight *"+percent+"/100)");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		Logger.Output(LogType.LogTypeName.INFO, "上下拖动滚动条");
	}

	/**
	 * 左右移动滚动条
	 * @param percent 0：最左  100：最右
	 */
	protected void moveWidthScroll(String percent) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scrollBy(0, 0-document.body.scrollWidth *"+percent+"/100)");
		Logger.Output(LogType.LogTypeName.INFO, "左右拖动滚动条");
	}
	
	/**
	 * 鼠标点击
	 * @param element
	 */
	protected void actionClick(WebElement element) {
		try {
			Actions action = new Actions(driver);
			Logger.Output(LogType.LogTypeName.INFO, "鼠标事件点击元素");
			action.click(element).perform();
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, "鼠标事件点击元素失败！");
		}
	}
	
	/**
	 * 鼠标双击
	 * @param element
	 */
	protected void actionDoubleClick(WebElement element) {
		try {
			Actions action = new Actions(driver);
			Logger.Output(LogType.LogTypeName.INFO, "鼠标双击元素");
			action.doubleClick(element).perform();
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, "鼠标双击元素失败！");
		}
	}
	
	
	/**
	 * 模拟鼠标事件拖动元素
	 * @param element 需要拖动的元素
	 * @param horizontal 水平方向：正数向右，负数向左
	 * @param vertical 垂直方向：正数向上，负数向下
	 */
	protected void jsExecutorDragAndDrop(WebElement element,int horizontal,int vertical) {
		try {
			Actions action = new Actions(driver); 
			action.dragAndDropBy(element, horizontal, vertical).perform();
			Logger.Output(LogType.LogTypeName.INFO, "使用鼠标拖动，将元素水平拖动"+horizontal+"	垂直拖动"+vertical);
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, e.getMessage() + ".");
		}
	}
	
	/**
	 * 移动鼠标到指定元素
	 * @param element
	 */
	public void moveMouse(WebElement element) {
		try {
			Actions action = new Actions(driver);
			Logger.Output(LogType.LogTypeName.INFO, "移动鼠标到指定元素上");
			action.moveToElement(element).perform();
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, "移动鼠标失败了~");
		}
	}
	
	/**
	 * 键盘回车
	 * @param element
	 */
	protected void enter(WebElement element) {
		try {
			Logger.Output(LogType.LogTypeName.INFO, "对元素进行键盘回车");
			element.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, "键盘回车失败！");
		}
	}
	
	/**
	 * 强行等待，有时候页面加载需要时间，检查点检测不出使用
	 * @param msec
	 */
	protected void forceWait(int msec) {
		try {
			Logger.Output(LogType.LogTypeName.INFO, "强行等待："+msec/1000+"秒");
			Thread.sleep(msec);
		} catch (Exception e) {
			Logger.Output(LogType.LogTypeName.ERROR, "强行等待失败");
		}
	}
	/**
	 * 动态等待，如果元素不存在，等待一秒直到元素出现，最长7秒
	 * @param by
	 */
	protected void dynamicWait(By by) {
		for(int count=0;count<14&&!(isVisibility(by));count++) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				Logger.Output(LogType.LogTypeName.ERROR, "强行等待失败");
			}
		}
	}
//	/**
//	 * bpms动态等待，指的是我们后台系统那个加载的圈圈
//	 */
//	protected void dynamicWait() {
//		dynamicWait(By.xpath("//*[@name='tailDeliveryDay']"));
//	}
	
	/**
	 * 与上面的方法相反，如果元素存在，等待直到元素不存在，最长7秒
	 * @param by
	 */
	protected void dynamicLoad(By by) {
		for(int count=0;count<14&&isVisibility(by);count++) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				Logger.Output(LogType.LogTypeName.ERROR, "强行等待失败");
			}
		}
	}
	/**
	 * 我们bpms的加载等待的那个圈圈，这个加载圈消失意味着加载完成
	 * @param
	 * @return
	 */
	protected void dynamicLoad(){
		dynamicLoad(By.xpath("//*[style='display: block;']"));
	}
	/**
	 * 连接数据库查询数据
	 * @param sql 查询sql
	 * @param field 需要的字段，这个方法只支持一个字段查询
	 * @return 返回字段值
	 */
	protected String DBSqlSearch(String sql,String field) {
		try {
			Connection conn = DButil.getCon();
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getString(field);
			}
			DButil.close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *粘贴方法
	 * @param content 要粘贴的文本
	 * @return
	 */
	protected void paste(String content){
		StringSelection stringSelection = new StringSelection(content);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**
	 * 获取当前系统时间，得到格式化时间字符串 
	 * @param date
	 * @param format
	 * @return
	 */
	protected static String getDateTimeByFormat(Date date, String format) {  
  
        SimpleDateFormat df = new SimpleDateFormat(format);  
  
        return df.format(date);  
  
    }
}