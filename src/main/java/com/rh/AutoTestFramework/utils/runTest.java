package main.java.com.rh.AutoTestFramework.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import junit.framework.Test;
import main.java.com.rh.AutoTestFramework.utils.*;

public class runTest {
	
	public static Logger loggers = Logger.getLogger(runTest.class);
	public static String data_path = "src/main/java/com/rh/AutoTestFramework/test/data/pm01_01_modifyBaseInformation.xlsx";
	public static String test_path = "src/main/java/com/rh/AutoTestFramework/test/cases/pm01_01_modifyBaseInformation.xlsx";
	
	public static void main(String[] args){
		runATest();
	}

	public static void runATest() {
		ArrayList<ArrayList<String>> testStep = getTestCase(test_path);
		int rowCount = getDataExcelRowCounts(data_path);
		ArrayList<String> heading = getRowDataByNum(data_path, 0);
		Map<String, String> m1 = new HashMap<String, String>();
		browser page = browser.getInstance();
		for(int i=1;i<rowCount;i++){
			for(int n=0;n<heading.size();n++){
				m1.put(heading.get(n), getRowDataByNum(data_path, i).get(n));
			}
			for (int j=1;j<testStep.size();j++){
				runCommand(page, testStep.get(j), m1);
				loggers.info(testStep.get(j));
			}
		}
		page.closePage();
	}
	
	private static void runCommand(browser page, ArrayList<String> arrayList, Map<String, String> m1) {
		//runCommand下应再创建一个选择器，跟据操作类型调用不同的方法
		String elementOperation = arrayList.get(0);
		String elementName = null;
		String elementPath = null;
		if(arrayList.size() > 1){
			elementName = arrayList.get(1);
			elementPath = getElementPath(elementName);
		}
		loggers.info("元素名： " + elementName);
		loggers.info("元素路径： " + elementPath);
		loggers.info("元素操作： " + elementOperation);
		switch(elementOperation){
			case "点击菜单":
				executor.clickMenu(page, m1.get(arrayList.get(1)), m1.get(arrayList.get(2)));
				break;
			case "点击":
				executor.click(page, elementPath);
				break;
			case "输入":
				executor.inputString(page, elementPath, m1.get(arrayList.get(2)));
				break;
			case "验证存在":
				executor.assertExists(page, elementPath);
				break;
			case "验证不存在":
				executor.assertNotExists(page, elementPath);
				break;
			case "选择":
				executor.clickAndSelect(page, elementPath, m1.get(arrayList.get(2)));
				break;
			case "验证文本框":
				executor.assertTextContentEqual(page, elementPath, m1.get(arrayList.get(2)));
				break;
			case "切换回主文本框":
				executor.switchBack(page);
			default:
				loggers.error(" unknown operation ! ");
		}
	}
	
	public static String getType(Object o){
		return o.getClass().toString();
	}

	public static String getElementPath(String elementName){
		String elementPath = XMLReader.getElementPath(elementName);
		if(elementPath.equals("") || elementPath.isEmpty()){
			loggers.error("读取元素路径为空！请检查对象库！");
		}
		return elementPath;
	}
	
	public static ArrayList<ArrayList<String>> getTestCase(String excelPath){
		ArrayList<ArrayList<String>> testStep = null;
		try {
			testStep = ExcelReader.readExcel(excelPath);
		} catch (IOException e) {
			loggers.error("读取测试用例失败 ！");
			e.printStackTrace();
		}
		if(testStep.size() <= 1){
			loggers.error("测试用例为空!");
		}
		return testStep;
	}
	
	public static ArrayList<ArrayList<String>> getTestData(String excelPath){
		ArrayList<ArrayList<String>> testData = null;
		try {
			testData = ExcelReader.readExcel(excelPath);
		} catch (IOException e) {
			loggers.error("读取测数据失败 ！");
			e.printStackTrace();
		}
		if(testData.size() == 0){
			loggers.error("测试数据为空!");
		}
		return testData;
	}
	
	public static int getDataExcelRowCounts(String dataPath){
		return ExcelReader.getRowCount(dataPath)+1;
	}
	
	public static ArrayList<String> getRowDataByNum(String data_path, int rowNum){
		return ExcelReader.getRowDataByNum(data_path, rowNum);
	}
}