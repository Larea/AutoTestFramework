package main.java.com.rh.AutoTestFramework.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import main.java.com.rh.AutoTestFramework.utils.browser;
import main.java.com.rh.AutoTestFramework.utils.XMLReader;


public class executor {
	
	public static Logger loggers = Logger.getLogger(executor.class);
	
	//单击元素
	public static void click(browser page, String elementPath){
		//page.findElementByXpathAndClick(elementPath);
		page.clickByJsByXpath(elementPath);
	}
	
	//双击元素
	public static void doubleClick(browser page, String elementPath){
		page.findElementByXpathAndDoubleClick(elementPath);
	}
	
	//点击并选择选项
	public static void clickAndSelect(browser page, String selectPath, String selectValue){
		page.findElementByXpathAndClick(selectPath);
		page.waitForEnabledAndClick(page.findElementByTextAndTag("//div", selectValue));
		
	}
	//根据text获取元素
	public static WebElement findElementByText(browser page, String text){
		List<WebElement> titleElements = page.findElementsByXpath("//td");
		WebElement element = null;
		for(int i=0;i<titleElements.size();i++){
			if(text.equals(titleElements.get(i).getAttribute("textContent"))){
				element = titleElements.get(i);
			}
		}
		return element;
	}
	//获取文本
	public static String getText(browser page, String xpath){
		String tempString = page.getTextByXpath(xpath);
		if(tempString.equals(null) || tempString.equals("")){
			tempString = page.findElementByXpath(xpath).getAttribute("textContent");
			if(tempString.equals(null) || tempString.equals("")){
				loggers.error(" this text is null!");
				return tempString;
			}
		}
		return tempString;		
	}
	
	//验证文本框
	public static void assertTextContentEqual(browser page, String xpath, String value){
		if(value.equals(getText(page, xpath))){
			loggers.info(" the textcontent is equal !");
		}
		else{
			loggers.error(" ERROR! the textcontent is not equal !");
		}
	}
	//选择菜单
	public static void clickMenu(browser page, String firstLink, String secondLink){
		page.findElementByPartiaLinkTextAndClick(firstLink);
		page.waitForEnabledAndClick(page.findElementByTextAndTag("//div[@class='menu-text']", secondLink));
		switchToActiveFrame(page);

	}
	//切换iframe
	public static void switchToActiveFrame(browser page){
		List<WebElement> frameList = page.findElementsByXpath("//iframe");
		for(int i=0;i<frameList.size();i++){
			if(frameList.get(i).findElement(By.xpath("../..")).isDisplayed()){
				page.switchToIframe(frameList.get(i));
				break;
			}
			else if(i == frameList.size()){
				loggers.error(" can not to find the iframe! ");
			}
		}
	}
	//回主文本框
	public static void switchBack(browser page){
		page.switchBack();
	}
	
	//向输入框输入值
	public static void inputString(browser page, String elementPath, String value){
		page.findElementByXpathAndClearSendkeys(elementPath, value);
	}
	
//	//向输入框输入数字
//	public void inputNum(browser page, String elementPath, int value){
//		page.findElementByXpathAndClearSendkeys(elementPath, value);
//	}
	
	//验证存在
	public static void assertExists(browser page, String elementPath){
		if(page.assertIsExistsByXpath(elementPath)){
			loggers.info(elementPath + "is exists ! ");
		}
		else{
			loggers.error(elementPath + "is not exists ! ");
		}
	}
	
	//验证不存在
	public static void assertNotExists(browser page, String elementPath){
		if(page.assertIsExistsByXpath(elementPath)){
			loggers.error(elementPath + "is exists ! ");
		}
		else{
			loggers.info(elementPath + "is not exists ! ");
		}
	}
	

}
