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
		   //�����ļ���׺ִ��
		   if(xls_or_xlsx.equals(EXCEL_XLS)){	//xls
			   HSSFWorkbook hwb = new HSSFWorkbook(excel);
			   list = readXls(hwb);
		   }else if(xls_or_xlsx.equals(EXCEL_XLSX)){//xlsx
			   XSSFWorkbook xwb = new XSSFWorkbook(excel);
			   list = readXlsx(xwb);
		   }else{
			   System.out.println("�ļ���ʽ����");
			   return null;
		   }
	   } catch (IOException e) {
			e.printStackTrace();
		}
	   return list;
   }
   
   /**
    * Xlsx ��ʽ����
    * @return
    */
   private static List readXlsx(XSSFWorkbook xwb){
	   List<String[]> list = new ArrayList<String[]>();
	   XSSFSheet sheet = xwb.getSheetAt(0);//��ȡ��һ�Ź�����sheet
	   System.out.println("------>>>---���ڶ�ȡ.xlsx��ʽExcel�����ݣ���ǰ��"+sheet.getSheetName());
	   for(int rows = 2 ; rows < sheet.getLastRowNum()+1; rows++){//������  (rows =2 ����Ҫ���ݿ�ʼ��
		   XSSFRow row = sheet.getRow(rows); //��ȡĳ�ж���
		   String[] s =new String[row.getLastCellNum()];
		   for(int columns = 0; columns < row.getLastCellNum(); columns++){//������
			   XSSFCell  cell = row.getCell(columns);//��ȡĳ�ж���
			   if(cell != null){
				   switch ( cell.getCellType()) {    
	                    case XSSFCell.CELL_TYPE_STRING: // �ַ���      
	                        s[columns] = cell.getStringCellValue();    
	                        if(s[columns]==null){  
	                            s[columns]="";  
	                        }  
	                        break;   
	                    case XSSFCell.CELL_TYPE_NUMERIC: // ����                             
	                        double strCell = cell.getNumericCellValue();  
	                        if(String.valueOf(strCell)==null){  
	                            s[columns]="";  
	                        }else{
	                        	s[columns]=new DecimalFormat("#").format(strCell);   

	                        }
	                            
	                        break;  
	                    case XSSFCell.CELL_TYPE_BLANK: // ��ֵ      
	                        s[columns]="";    
	                        break;     
	                    default:     
	                        System.out.print("\n---��Ԫ���ʽ��֧��---");     
	                        break;     
	                    } 
//				    System.out.println(s[columns]);
			   }
		   }
		   list.add(s);//���������
	   }
	   
	   return list;
   }
   
   /**
    * Xls ��ʽ����
    * @param hwb
    * @return
    */
   private static List readXls(HSSFWorkbook hwb){
	   List<String[]> list = new ArrayList<String[]>();
	 //��ȡ��һҳ,һ��һ��excel�ļ��������������������ȡ��һ�������������в���   
	  HSSFSheet sheet = hwb.getSheetAt(0);
	  System.out.println("------>>>---���ڶ�ȡ.xls��ʽExcel�����ݣ���ǰ��"+sheet.getSheetName());
	  for(int rows = 2 ; rows < sheet.getLastRowNum(); rows++){//������  (rows =2 ����Ҫ���ݿ�ʼ��
		  HSSFRow row = sheet.getRow(rows); //��ȡĳ�ж���
		   String[] s =new String[row.getLastCellNum()];
		   for(int columns = 0; columns < row.getLastCellNum(); columns++){//������
			   HSSFCell  cell = row.getCell(columns);//��ȡĳ�ж���
			   if(cell != null){
				   switch ( cell.getCellType()) {    
                   case HSSFCell.CELL_TYPE_STRING: // �ַ���      
                       s[columns] = cell.getStringCellValue();    
                       if(s[columns]==null){  
                           s[columns]="";  
                       }  
                       break;   
                   case HSSFCell.CELL_TYPE_NUMERIC: // ����                             
                       double strCell = cell.getNumericCellValue();  
                       if(String.valueOf(strCell)==null){  
                           s[columns]="";  
                       }else{
                       	s[columns]=new DecimalFormat("#").format(strCell);   

                       }
                           
                       break;  
                   case HSSFCell.CELL_TYPE_BLANK: // ��ֵ      
                       s[columns]="";    
                       break;     
                   default:     
                       System.out.print("\n---��Ԫ���ʽ��֧��---");     
                       break;     
                   } 
//			    System.out.println(s[columns]);
			   }
		   }
		   list.add(s);//���������
	   }
	   
	   return list;
   }
	  
}
