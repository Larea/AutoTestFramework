package main.java.com.rh.AutoTestFramework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	public static String data_path = "src/main/java/com/rh/AutoTestFramework/test/data/testdata.xlsx";
	public static String test_path =  "src/main/java/com/rh/AutoTestFramework/test/cases/testcase.xlsx";
	
	
	public static void main(String[] args) throws IOException{
		ArrayList<ArrayList<String>> testStep = readExcel(test_path);
		for (int i=0;i<testStep.size();i++){
			System.out.println(testStep.get(i));
		}
	}
	
	//读取excel文件，并将内容以Array<ArrayList<String>>的形式返回
	public static ArrayList<ArrayList<String>> readExcel(String excelPath) throws IOException{
		Workbook book = null;
		book = getExcelWorkbook(excelPath);
        Sheet sheet = getSheetByNum(book,0);
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        ArrayList<String> rowList = new ArrayList<String>();
        int lastRowNum = sheet.getLastRowNum();
        
        for(int i = 0 ; i <= lastRowNum ; i++){
            Row row = null;
            row = sheet.getRow(i);
            rowList = new ArrayList<String>();
            if( row != null ){
                //System.out.println("reading line is " + i);
                int lastCellNum = row.getLastCellNum();
                //System.out.println("lastCellNum is " + lastCellNum );
                Cell cell = null;
                
                for( int j = 0 ; j <= lastCellNum ; j++ ){
                    cell = row.getCell(j);
                    if( cell != null ){
                    	cell.setCellType(Cell.CELL_TYPE_STRING); //将单元格数据转换为STRING格式，避免int数据出现.0后缀
                    	//System.out.println(cell + "" + cell.getCellTypeEnum());
                        rowList.add(j, cell.toString());
                    }
                }
                dataList.add(rowList);
            }
        }
        return dataList;
    }
	
	public static int getRowCount(String dataPath){
		Workbook book = null;
		try {
			book = getExcelWorkbook(dataPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = getSheetByNum(book,0);
		return sheet.getLastRowNum();
	}
	
	public static ArrayList<String> getRowDataByNum(String dataPath, int rowNum){
		Workbook book = null;
		try {
			book = getExcelWorkbook(dataPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = getSheetByNum(book,0);
		Row row = sheet.getRow(rowNum);
		int lastCellNum = row.getLastCellNum();
        //System.out.println("lastCellNum is " + lastCellNum );
        Cell cell = null;
        
        ArrayList<String> rowList = new ArrayList<String>();
		for( int j = 0 ; j <= lastCellNum ; j++ ){
            cell = row.getCell(j);
            if( cell != null ){
            	cell.setCellType(Cell.CELL_TYPE_STRING); //将单元格数据转换为STRING格式，避免int数据出现.0后缀
            	//System.out.println(cell + "" + cell.getCellTypeEnum());
                rowList.add(j, cell.toString());
            }
        }
		return rowList;
	}

    public static Sheet getSheetByNum(Workbook book,int number){  
        Sheet sheet = null;  
        try {  
            sheet = book.getSheetAt(number);  
//          if(sheet == null){  
//              sheet = book.createSheet("Sheet"+number);  
//          }  
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage());  
        }  
        return sheet;  
    }

	public static Workbook getExcelWorkbook(String filePath) throws IOException{  
		Workbook book = null;  
		File file  = null;  
		FileInputStream fis = null;   
	          
		try {  
			file = new File(filePath);  
			if(!file.exists()){  
					throw new RuntimeException("文件不存在");  
			}else{  
					fis = new FileInputStream(file);  
					book = WorkbookFactory.create(fis); 
			}  
		} catch (Exception e) {  
			throw new RuntimeException(e.getMessage());  
		} finally {  
			if(fis != null){  
				fis.close();  
			}  
		}
		return book;
	} 
}