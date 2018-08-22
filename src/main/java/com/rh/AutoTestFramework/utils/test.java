package main.java.com.rh.AutoTestFramework.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;

public class test {
	
	public static browser page = browser.getInstance();

	public static void main(String[] args) throws InterruptedException {
		
//		System.out.println(page.findElementByXpath("//*[@id='loginName']/preceding-sibling::label").getAttribute("textContent"));
		login("admin", "123456");
		clickMenuLink("项目管理", "项目维护");
		try {
			switchToActiveFrame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.findElementByXpathAndClearSendkeys("//td[contains(text(), '开发负责人:')]/following-sibling::td/input", "test");
		System.out.println(page.findElementByXpath("//td[contains(text(), '项目阶段')]/following-sibling::td[1]/div/span/span").getAttribute("class"));
		//page.findElementByXpath("//td[contains(text(), '项目阶段')]/following-sibling::td[1]").getAttribute("class");
//		List<WebElement> elements = page.findElementsByXpath("//td");test
//		for(int i=0;i<elements.size();i++){
//			System.out.println(elements.get(i).getText());
//		}
		
		//page.findElementByXpathAndClick("//*[@id='form1']/table/tbody/tr[7]/td[2]/div/span/div/div/div[3]");
		//page.clickSeries("//*[@id='form1']/table/tbody/tr[7]/td[2]/div/span/span/span", "//*[@id='form1']/table/tbody/tr[7]/td[2]/div/span/div/div/div[3]");
		//page.clickByJsByXpath("//*[@id='form1']/table/tbody/tr[7]/td[2]/div/span/span/span");
//		List<WebElement> selects = page.findElementsByXpath("//div[@class='combobox-item']");
//		for (int i=0;i<selects.size();i++){
//			if(selects.get(i).getAttribute("textContent").equals("4-维护阶段")){
//				System.out.println(selects.get(i).getAttribute("textContent"));
//				selects.get(i).click();
//				break;
//			}
//		}
		page.waitForEnabledByXpathAndClick("//*[@id='form1']/table/tbody/tr[7]/td[2]/div/span/div/div/div[3]");
	}
	//切换iframe
	public static void switchToActiveFrame() throws InterruptedException{
		List<WebElement> frameList = page.findElementsByXpath("//iframe");
		for(int i=0;i<frameList.size();i++){
			Thread.sleep(20);
			if(frameList.get(i).findElement(By.xpath("../..")).isDisplayed()){
				page.switchToIframe(frameList.get(i));
			}
		}
	}

	
	public static void clickMenuLink(String firstLink, String secondLink){
		page.findElementByPartiaLinkTextAndClick(firstLink);
		List<WebElement> menuList = page.findElementsByXpath("//div[@class='menu-text']");
		for (int i=0;i<menuList.size();i++){
			System.out.println(menuList.get(i).getAttribute("textContent"));
			if(menuList.get(i).getAttribute("textContent").equals(secondLink)){
				menuList.get(i).click();
				break;
			}
			else if(i == menuList.size()){
				System.out.println("test failure");
			}
		}
	}
	
	//登录
		public static void login(String userName, String password){
			if(page.assertIsExistsByXpath("//div[@id='product-image-div']")){
				System.out.println(" this is login page!");
			}
			else if (page.assertIsExistsByXpath("//a[@id='btnLogout']")){
				page.findElementByXpathAndClick("//a[@id='btnLogout']");
				login(userName,password);
			}
			else{
				System.out.println("this is a wrong page");
				page.get("http://127.0.0.1:8080/CTP/");
				login(userName,password);
			}
			page.findElementByXpathAndClearSendkeys("//input[@id='loginName']", userName);
			page.findElementByXpathAndClearSendkeys("//input[@id='password']", password);
			page.findElementByIdAndClick("submit");
			if(page.assertIsExistsByXpath("//a[@id='btnLogout']")){
				System.out.println(" Login Successful! ");
			}
			else {
				System.out.println(" Login Falure! ");
			}
		}

}
