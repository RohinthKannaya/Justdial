package Excelutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils{
	
	public String[] readExcel(int index) throws IOException {
	File src = new File(".\\src\\main\\resources\\POMResources\\Testdata.xlsx");
	FileInputStream fis = new FileInputStream(src);
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheetAt(index);
	XSSFRow row =sheet.getRow(1);
	int size = row.getLastCellNum();
	String[] input = new String[size+1];
	for(int i =0;i<= size;i++) {
		input[i]=String.valueOf(row.getCell(i));
		}
	workbook.close();
	return input;
	}
	public void writeExcel(LinkedList<String> list) throws IOException {
		String[] input=readExcel(0);
		File file = new File(".\\Outputs\\Output1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Testcase1_OUTPUT");
		sheet.createRow(0).createCell(0).setCellValue("CarWashCenters & Phonenumbers");
		for(int i=0;i<Integer.parseInt(input[2].replaceAll(".0",""));i++) {
		sheet.createRow(i+1).createCell(0).setCellValue((String) list.get(i));
		}
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
	}
	public void writeExcel(String errormessage) throws IOException {
		
		File file = new File(".\\Outputs\\Output2.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Testcase2_OUTPUT");
		sheet.createRow(0).createCell(0).setCellValue("ErrorMessage");
		sheet.createRow(1).createCell(0).setCellValue(errormessage);
		
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
	}
	public void gymExcel(LinkedList<String> list) throws IOException {
		File file = new File(".\\Outputs\\Output3.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Testcase3_OUTPUT");
		sheet.createRow(0).createCell(0).setCellValue("Gym Sub-menu");
		for(int i=0;i<list.size();i++) {
		sheet.createRow(i+1).createCell(0).setCellValue((String) list.get(i));
		}
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
	}
	
}
