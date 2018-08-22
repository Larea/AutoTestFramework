package main.java.com.rh.AutoTestFramework.utils;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
 
public class browser {
	private static browser library = new browser();
	public static browser getInstance() {
		return library;
	}
	public static WebDriver driver = getDrive();
	public static WebDriver getDrive() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\larea\\Downloads\\chromedriver_win32\\chromedriver.exe");
//		profile.setPreference("browser.download.manager.showWhenStarting", false);//是否显示下载进度框
//		profile.setPreference("browser.offline-apps.notify", false);//网站保存离线数据时不通知我
//		profile.setPreference("browser.helperApps.alwaysAsk.force", false);//应用程序设置不询问
//		profile.setPreference("browser.download.folderList", 0);//设置下载地址0是桌面；1是“我的下载”；2是自定义
//		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, application/vnd.ms-excel, text/csv, application/zip, application/msword");
//		profile.setPreference("dom.webnotifications.enabled", false);//允许通知			
		WebDriver driver = new ChromeDriver();//启动谷歌浏览器
		driver.manage().window().maximize();//设置窗口大小
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);//设置页面加载超时
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//设置查询组件等待时间
		driver.get(testURL);
		return driver;
	}

	public final static String LINE = "\r\n";
	public final static String testURL = "http://127.0.0.1:8080/CTP/";
	public final static String smile = "^_^";
	public final static String sad = "*o*";
	public final static String userPath = "C:\\Users\\larea\\Desktop\\";
	//截图命名为当前时间保存桌面
	public void takeScreenshotByNow() throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String file = userPath +getNow() + ".png";
		FileUtils.copyFile(srcFile,new File(file));
	}
	//获取当前时间
	public String getNow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");//设置日期格式
		String nowTime = df.format(new Date());
		return nowTime;
	}
	//截图重命名保存至桌面
	public void takeScreenshotByName(String name) throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String file = userPath + name + ".png";
		FileUtils.copyFile(srcFile,new File(file));
	}
	//通过id获取元素并点击
	public void findElementByIdAndClick(String id) {
		driver.findElement(By.id(id)).click();
	}
	public void findElementByNameAndClick(String name) {
		findElementByName(name).click();
	}
	//根据文本获取元素并点击
	public void findElementByLinkTextAndClick(String text) {
		driver.findElement(By.linkText(text)).click();
	}
	//根据文本模糊查找并点击
	public void findElementByPartiaLinkTextAndClick(String text) {
		driver.findElement(By.partialLinkText(text)).click();
	}
	//根据文本和标签获取元素
	public WebElement findElementByTextAndTag(String tag, String text){
		List<WebElement> elements = findElementsByXpath(tag);
		WebElement element = null;
		for(int i=0;i<elements.size();i++){
			if(text.equals(elements.get(i).getAttribute("textContent")) || text.equals(elements.get(i).getAttribute("text"))){
				element = elements.get(i);
			}
		}
		return element;
	}
	//根据xpath获取元素
	public WebElement findElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	public WebElement findElementByTag(String tag) {
		return driver.findElement(By.tagName(tag));
	}
	//根据id获取元素
	public WebElement findElementById(String id) {
		return driver.findElement(By.id(id));
	}
	//根据id获取元素清除文本写入文本
	public void findElementByIdAndClearSendkeys(String id1 , String id2, String text) {
		driver.findElement(By.id(id1)).clear();
		driver.findElement(By.id(id2)).sendKeys(text);
	}
	public void findElementByIdAndClearSendkeys(String id, String text) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(text);
	}
	public void findElementByIdAndClearSendkeys(String id, int num) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(num+"");
	}
	public void findElementByNameAndClearSendkeys(String name, String text) {
		findElementByName(name).clear();
		findElementByName(name).sendKeys(text);
	}
	public void findElementByNameAndClearSendkeys(String name, int num) {
		findElementByName(name).clear();
		findElementByName(name).sendKeys(num+"");
	}
	//通过xpath获取元素点击
	public void findElementByXpathAndClick(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	//通过class获取元素并点击
	public void findElementByClassNameAndClick(String name) {
		driver.findElement(By.className(name)).click();
	}
	
	public WebElement findElementByClassName(String name){
		return driver.findElement(By.className(name));
	}
	//获取一组元素
	public List<WebElement> findElementsByClassName(String className) {
		return driver.findElements(By.className(className));
	}
	//根据text获取一组页面元素
	public List<WebElement> findElementsByText(String text) {
		return driver.findElements(By.linkText(text));
	}
	public List<WebElement> findElementsByPartialText(String text) {
		return driver.findElements(By.partialLinkText(text));
	}
	//根据id获取一组页面元素
	public List<WebElement> findElementsById(String id) {
		return driver.findElements(By.id(id));
	}
	//根据tagName获取一组页面元素
	public List<WebElement> findElementsByTag(String tag) {
		return driver.findElements(By.tagName(tag));
	}
	public WebElement findElementByText(String text){
		return driver.findElement(By.linkText(text));
	}
	public WebElement findElementByPartialLinkText(String text){
		return driver.findElement(By.partialLinkText(text));
	}
	public WebElement findElementByName(String name) {
		return driver.findElement(By.name(name));
	}
	//输出cookies信息
	public void outputCookie() {
		Set<Cookie> cookie = driver.manage().getCookies();
		System.out.println(cookie);
//		Cookie abc = new Cookie("", "");
//		driver.manage().addCookie(abc);
	}
	public void addCookie(Map<String, String> args) {
		Set<String> keys = args.keySet();
		for(String key : keys){
			driver.manage().addCookie(new Cookie(key, args.get(key)));
		}
	}

	//判断元素是否存在
	public boolean exists(By selector) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//设置查询组件等待时间
		try {
			driver.findElement(selector);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//设置查询组件等待时间
			return true;
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//设置查询组件等待时间
			return false;
		}
	}
	
	//通过xpath判断元素是否存在
	public boolean assertIsExistsByXpath(String xpath){
		if(exists(By.xpath(xpath))){
			//System.out.println(xpath + "is exists!");
			return true;
		}
		else {
			//System.out.println(xpath + "is not exists！");
			return false;
		}
	}
	
	//通过id判断元素是否存在
	public boolean assertIsExistsById(String Id){
		if(exists(By.xpath(Id))){
			//System.out.println(Id + "is exists!");
			return true;
		}
		else {
			//System.out.println(Id + "is not exists！");
			return false;
		}
	}
	
	//通过className判断元素是否存在
	public boolean assertIsExistsByclass(String className){
		if(exists(By.className(className))){
			//System.out.println(className + "is exists!");
			return true;
		}
		else {
			//System.out.println(className + "is not exists！");
			return false;
		}
	}
	
	//判断元素是否可见
	public boolean isDisplayByXpath(String xpath){
		return driver.findElement(By.xpath(xpath)).isDisplayed();
	}
	
	//通过js点击
	public void clickByJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		//第二种点击方法
//		((JavascriptExecutor) driver).executeScript("arguments[0].click()", question);
	}
	//通过xpath获取元素用js点击
	public void clickByJsByXpath(String xpath) {
		clickByJs(driver.findElement(By.xpath(xpath)));
	}
	public void clickByJsByText(String text) {
		clickByJs(findElementByText(text));
	}
	public void clickByJsById(String id) {
		clickByJs(findElementById(id));
	}
	public void clickByJsByClassName(String name) {
		clickByJs(findElementByClassName(name));
	}
	public void clickByJsByName(String name) {
		clickByJs(findElementByName(name));
	}
	//按物理按键
	public void pressKeyEvent(int keycode) throws AWTException {
		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_ENTER);//按下enter键
		robot.keyPress(keycode);
	}
	//通过xpath获取元素清除文本并写入
	public void findElementByXpathAndClearSendkeys(String xpath, String text) {
		findElementByXpath(xpath).clear();
		findElementByXpath(xpath).sendKeys(text);
	}
	public void findElementByXpathAndClearSendkeys(String xpath, int num) {
		findElementByXpath(xpath).clear();
		findElementByXpath(xpath).sendKeys(num+"");
	}
	//判断是否有警告框
	public boolean judgeAlert(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
			} catch (Exception e) {
				System.out.println("没有发现警告框！");
				return false;
				}
		}
	//获取文本
	public String getTextByXpath(String xpath) {
		return findElementByXpath(xpath).getText();
	}
	public String getTextByClassName(String name) {
		return findElementByClassName(name).getText();
	}
	public String getTextById(String id) {
		return findElementById(id).getText();
	}
	public String getTextByName(String name) {
		return findElementByName(name).getText();
	}
	//获取数量
//	public int getNumByXpath(String xpath) {
//		String num = getTextByXpath(xpath);
//		return changeStringToInt(num);
//	}
//	public int getNumByClassName(String name) {
//		String num = getTextByClassName(name);
//		return changeStringToInt(num);
//	}
	//通过xpath获取classname
	public String getClassNameByXpath(String xpath) {
		return findElementByXpath(xpath).getAttribute("class");
	}
	//通过id获取classname
	public String getClassNameById(String id) {
		return findElementById(id).getAttribute("class");
		}
	//强制刷新
	public void refresh() {
		Actions ctrl = new Actions(driver);
		ctrl.keyDown(Keys.CONTROL).perform();
		try {
			pressKeyEvent(KeyEvent.VK_F5);
		} catch (AWTException e) {
			System.out.println(sad+getNow());
			e.printStackTrace();
		}
		ctrl.keyUp(Keys.CONTROL).perform();
//		driver.navigate().refresh();
	}
//	//显式等待
//	public void waitForWebElementByXpathAndClick(String xpath) {
//		new WebDriverWait(5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
//		findElementByXpathAndClick(xpath);
//	}
 
	//等待元素可用再点击
	public void waitForEnabledByXpathAndClick(String xpath){
		boolean key = true;
		while(key){
			if (findElementByXpath(xpath).isEnabled() && findElementByXpath(xpath).isDisplayed()) {
				System.out.println(123);
				clickByJsByXpath(xpath);
//				findElementByXpathAndClick(xpath);
				key = false;
			}else{
				waitTime(5);
			}
		}
	}
	public void waitForEnabledAndClick(WebElement element){
		boolean key = true;
		while(key){
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println(123);
				element.click();;
//				findElementByXpathAndClick(xpath);
				key = false;
			}else{
				waitTime(5);
			}
		}
	}
	
	//等待时间
	public void waitTime(int time) { 
		try {
             Thread.sleep(time);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
	
	//右键点击
	public void	RightClickWebElement(String id) {
		Actions actions = new Actions(driver);
		actions.contextClick(findElementById(id)).perform();;
	}
	//根据classname获取元素清除并输入内容
	public void findElementByClassnameAndClearSendkeys(String classname, String text) {
		driver.findElement(By.className(classname)).clear();
		driver.findElement(By.className(classname)).sendKeys(text);
	}
	public void findElementByClassnameAndClearSendkeys(String classname, int num) {
		driver.findElement(By.className(classname)).clear();
		driver.findElement(By.className(classname)).sendKeys(num+"");
	}
	//根据id获取下拉框，根据index选择选项
	public void findSelectByIdAndSelectByIndex(String id, int index) {
		Select select = new Select(findElementById(id));
		select.selectByIndex(index);
	}
	//根据id获取下拉框，根据value选择选项
	public void findSelectByIdAndSelectByValue(String id, String value) {
		Select select = new Select(findElementById(id));
		select.selectByValue(value);
	}
	//根据id获取下拉框，根据text选择选项
	public void findSelectByIdAndSelectByText(String id, String text) {
		Select select = new Select(findElementById(id));
		select.selectByVisibleText(text);
	}
	//根据classname获取下拉框，根据text选择选项
	public void findSelectByClassNameAndSelectByText(String name, String text) {
		Select select = new Select(findElementByClassName(name));
		select.selectByVisibleText(text);
	}
	//根据classname获取下拉框，根据Value选择选项
	public void findSelectByClassNameAndSelectByValue(String name, String value) {
		Select select = new Select(findElementByClassName(name));
		select.selectByValue(value);
		}
	//根据classname获取下拉框，根据index选择选项
	public void findSelectByClassNameAndSelectByIndex(String name, int index) {
		Select select = new Select(findElementByClassName(name));
		select.selectByIndex(index);
		}
	//根据name获取下拉框，根据text选择选项
	public void findSelectByNameAndSelectByText(String name, String text) {
		Select select = new Select(findElementByName(name));
		select.selectByVisibleText(text);
	}
	//根据name获取下拉框，根据Value选择选项
	public void findSelectByNameAndSelectByValue(String name, String value) {
		Select select = new Select(findElementByName(name));
		select.selectByValue(value);
		}
	//根据name获取下拉框，根据index选择选项
	public void findSelectByNameAndSelectByIndex(String name, int index) {
		Select select = new Select(findElementByName(name));
		select.selectByIndex(index);
		}
	//根据xpath获取下拉框，根据value选择选项
	public void findSelectByXpathAndSelectByValue(String xpath, String value){
		Select select = new Select(findElementByXpath(xpath));
		select.selectByValue(value);
	}
	//鼠标悬停
	public void moveToElementById(String id) {
		Actions actions = new Actions(driver);
		actions.moveToElement(findElementById(id));
		}
	public void moveToElementByClassName(String name) {
		Actions actions = new Actions(driver);
		actions.moveToElement(findElementByClassName(name));
	}
	//滚动到最上方
	public void scrollToTop() {
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0);");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollTo(0,0);");
	}
	//滚动到页面底部
	public void scrollToBottom(String id) {
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,10000);");
	}
	//滚动到某个元素
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	//js滚动页面内div
	public void scrollToBottomById(String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTo(0, 3000);", findElementById(id));
	}
	//使用js使元素隐藏元素显示
	public void makeDisplayById(String id) {
		JavascriptExecutor  js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById(id).style.display='block';");
	}
	public void makeElementDisplay(WebElement element) {
		JavascriptExecutor  js = (JavascriptExecutor)driver;
//		WebElement element = driver.findElement(By.xxx);
		js.executeScript("arguments[0].style=arguments[1]", element, "display: block;");
	}
	//js输入文本
	public void inputTextByJsById(String text, String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=\"2016-08-20\"",driver.findElement(By.id(id)));
	}
	//js输入文本
	public void inputTextByJs(String text, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=" + text + "\"", element);
	}
	//返回
	public void BrowserBack() {
		driver.navigate().back();
	}
	//前进
	public void BrowserForward() {
		driver.navigate().forward();
	}
	
	//切换iframe
	public void switchToIframe(WebElement seletor){
		driver.switchTo().frame(seletor);
	}
	//切换iframe
	public void switchToIframe(String xpath){
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
	}
	public void switchBack(){
		driver.switchTo().defaultContent();
	}
	
	//关闭页面
	public void closePage(){
		driver.close();
	}
	
	//关闭所有页面
	public void closeAllPage(){
		driver.quit();
	}
	public List<WebElement> findElementsByXpath(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	
	//页面跳转
	public void get(String targetURL) {
		driver.get(targetURL);
	}
	
	public void findElementByXpathAndDoubleClick(String elementPath) {
		// TODO Auto-generated method stub
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(elementPath))).doubleClick().build().perform();
	}
}