package com.elegps.tscweb.comm;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel{

	private static String EXCEL_XLS ="xls";
	private static String EXCEL_XLSX ="xlsx";
	
   public static List getWorkbook(String xls_or_xlsx,InputStream excel){
	   List list = new ArrayList();
	   try {
		   //根据文件后缀执行
		   if(xls_or_xlsx.equals(EXCEL_XLS)){	//xls
			   HSSFWorkbook hwb = new HSSFWorkbook(excel);
			   list = readXls(hwb);
		   }else if(xls_or_xlsx.equals(EXCEL_XLSX)){//xlsx
			   XSSFWorkbook xwb = new XSSFWorkbook(excel);
			   list = readXlsx(xwb);
		   }else{
			   System.out.println("文件格式错误！");
			   return null;
		   }
	   } catch (IOException e) {
			e.printStackTrace();
		}
	   return list;
   }
   
   /**
    * Xlsx 格式数据
    * @return
    */
   private static List readXlsx(XSSFWorkbook xwb){
	   List<String[]> list = new ArrayList<String[]>();
	   XSSFSheet sheet = xwb.getSheetAt(0);//读取第一张工作表sheet
	   System.out.println("------>>>---正在读取.xlsx格式Excel表数据，当前表："+sheet.getSheetName());
	   for(int rows = 2 ; rows < sheet.getLastRowNum()+1; rows++){//遍历行  (rows =2 从主要内容开始）
		   XSSFRow row = sheet.getRow(rows); //获取某行对象
		   String[] s =new String[row.getLastCellNum()];
		   for(int columns = 0; columns < row.getLastCellNum(); columns++){//遍历列
			   XSSFCell  cell = row.getCell(columns);//获取某列对象
			   if(cell != null){
				   switch ( cell.getCellType()) {    
	                    case XSSFCell.CELL_TYPE_STRING: // 字符串      
	                        s[columns] = cell.getStringCellValue();    
	                        if(s[columns]==null){  
	                            s[columns]="";  
	                        }  
	                        break;   
	                    case XSSFCell.CELL_TYPE_NUMERIC: // 数字                             
	                        double strCell = cell.getNumericCellValue();  
	                        if(String.valueOf(strCell)==null){  
	                            s[columns]="";  
	                        }else{
	                        	s[columns]=new DecimalFormat("#").format(strCell);   

	                        }
	                            
	                        break;  
	                    case XSSFCell.CELL_TYPE_BLANK: // 空值      
	                        s[columns]="";    
	                        break;     
	                    default:     
	                        System.out.print("\n---单元格格式不支持---");     
	                        break;     
	                    } 
//				    System.out.println(s[columns]);
			   }
		   }
		   list.add(s);//存放行数据
	   }
	   
	   return list;
   }
   
   /**
    * Xls 格式数据
    * @param hwb
    * @return
    */
   private static List readXls(HSSFWorkbook hwb){
	   List<String[]> list = new ArrayList<String[]>();
	 //读取第一页,一般一个excel文件会有三个工作表，这里获取第一个工作表来进行操作   
	  HSSFSheet sheet = hwb.getSheetAt(0);
	  System.out.println("------>>>---正在读取.xls格式Excel表数据，当前表："+sheet.getSheetName());
	  for(int rows = 2 ; rows < sheet.getLastRowNum(); rows++){//遍历行  (rows =2 从主要内容开始）
		  HSSFRow row = sheet.getRow(rows); //获取某行对象
		   String[] s =new String[row.getLastCellNum()];
		   for(int columns = 0; columns < row.getLastCellNum(); columns++){//遍历列
			   HSSFCell  cell = row.getCell(columns);//获取某列对象
			   if(cell != null){
				   switch ( cell.getCellType()) {    
                   case HSSFCell.CELL_TYPE_STRING: // 字符串      
                       s[columns] = cell.getStringCellValue();    
                       if(s[columns]==null){  
                           s[columns]="";  
                       }  
                       break;   
                   case HSSFCell.CELL_TYPE_NUMERIC: // 数字                             
                       double strCell = cell.getNumericCellValue();  
                       if(String.valueOf(strCell)==null){  
                           s[columns]="";  
                       }else{
                       	s[columns]=new DecimalFormat("#").format(strCell);   

                       }
                           
                       break;  
                   case HSSFCell.CELL_TYPE_BLANK: // 空值      
                       s[columns]="";    
                       break;     
                   default:     
                       System.out.print("\n---单元格格式不支持---");     
                       break;     
                   } 
//			    System.out.println(s[columns]);
			   }
		   }
		   list.add(s);//存放行数据
	   }
	   
	   return list;
   }
	  
}
