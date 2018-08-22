package main.java.com.rh.AutoTestFramework.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.*;   
import org.dom4j.io.*;

import junit.framework.Test;

public class XMLReader {
	
	public static String xmlPath = "src/main/java/com/rh/AutoTestFramework/test/page/pageElement.xml";
	public static Logger loggers = Logger.getLogger(XMLReader.class);	

//	public static void main(String[] args){
//		addElement("修改人输入框", "//*[@id='form1']/table/tbody/tr[6]/td[2]/input");
////		deleteElement("测试");
////		//modifyElementPath("测试", "edit test ");
////		//modifyElementName("测试", "编辑测试");	
//	}
	
	public static String getElementPath(String elementName){
		String elementPath = "";
		try{
			File f = new File(xmlPath);
			SAXReader reader = new SAXReader();   
			Document doc = reader.read(f);
			Node element = doc.selectSingleNode("//element[@name='" + elementName + "']/path");
			if(element == null){
				loggers.error("该元素不存在！请检查对象库！");
			}
			else {
				elementPath = element.getText();
			}
		}catch (Exception e) {   
			e.printStackTrace();
		}
		return elementPath;
	}
	
	public static void addElement(String elementName, String elementPath){
		try{
			File f = new File(xmlPath);
			SAXReader reader = new SAXReader();   
			Document doc = reader.read(f);
			Element newElement = doc.getRootElement().addElement("element");
			newElement.addAttribute("name", elementName);
			Element pathElement = newElement.addElement("path");
			pathElement.addText(elementPath);
			saveXmlFile(doc);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteElement(String elementName){
		try{
			File f = new File(xmlPath);
			SAXReader reader = new SAXReader();   
			Document doc = reader.read(f);   
			Element rootElement = doc.getRootElement();
			Node targetElement = doc.selectSingleNode("//element[@name='" + elementName + "']");
			rootElement.remove(targetElement);
			saveXmlFile(doc);
		}catch (Exception e) {   
			e.printStackTrace();
		}
	}
	
	public static void modifyElementPath(String elementName, String newPath){
		try{
			File f = new File(xmlPath);
			SAXReader reader = new SAXReader();   
			Document doc = reader.read(f);   
			Node targetElement = doc.selectSingleNode("//element[@name='" + elementName + "']/path");
			targetElement.setText(newPath);
			saveXmlFile(doc);
		}catch (Exception e) {   
			e.printStackTrace();
		}
	}
	
	public static void modifyElementName(String elementName, String newName){
		try{
			File f = new File(xmlPath);
			SAXReader reader = new SAXReader();   
			Document doc = reader.read(f);   
			Element targetElement = (Element) doc.selectSingleNode("//element[@name='" + elementName + "']");
			Attribute attr = targetElement.attribute("name");
			attr.setValue(newName);
			List<Attribute> list = new ArrayList<Attribute>();
			list.add(attr);
			targetElement.setAttributes(list);
			saveXmlFile(doc);
		}catch (Exception e) {   
			e.printStackTrace();
		}
	}
	
	public static void saveXmlFile(Document doc){
		try{
			OutputStream out =  new FileOutputStream(xmlPath);
			OutputFormat format2 = OutputFormat.createPrettyPrint();
			format2.setEncoding("UTF-8");//文件内容的编码
			XMLWriter writer2 = new XMLWriter(out,format2);
			writer2.write(doc);
			writer2.close();
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
