package com.training.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Naveen
 * @see this class will take the records from excel sheet, and return it as list
 *      of list of object, and can be generic, can given any records until it
 *      exists. Test it with main method provided, and the path is hard coded,
 *      participatns are asked to refractor this path in the property file and
 *      access.
 */
public class ApachePOIExcelRead {
	public static List<List<Object>> getExcelContent(String fileName) {

		List<List<Object>> list = new ArrayList<List<Object>>();

		try {
			System.out.println("File Name Got " + fileName);
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				// Skipping first record
				List<Object> tempList = new ArrayList<Object>();

				// skipping first row/header- added by Preethi 18-May-2020
				if (row.getRowNum() == 0) {
					continue; // just skip the rows if row number is 0
				}

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// Check the cell type and format accordingly
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_NUMERIC:
						tempList.add(((Double) cell.getNumericCellValue()).toString());
						break;
					case Cell.CELL_TYPE_STRING:
						tempList.add(cell.getStringCellValue());
						break;
					}

				}

				list.add(tempList);

			}

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {
		String fileName1 = "C:\\Users\\PREETHIVijayKumar\\git\\repository\\git\\Selenium-Automation-Elearning-Framework-TestNG\\DataProviders\\excel-inputs.xlsx";
		String fileName2 = "C:\\Users\\PREETHIVijayKumar\\git\\repository\\git\\Selenium-Automation-Elearning-Framework-TestNG\\DataProviders\\excel-ForEnquiries.xlsx";
		String fileName3 = "C:\\Users\\PREETHIVijayKumar\\git\\repository\\git\\Selenium-Automation-Elearning-Framework-TestNG\\DataProviders\\excel-ChangeRole.xlsx";

		for (List<Object> temp : getExcelContent(fileName1)) {
			System.out.println(temp.get(0) + "," + temp.get(1));
		}

		for (List<Object> temp : getExcelContent(fileName2)) {
			System.out.println(temp.get(0) + "," + temp.get(1));
		}

		for (List<Object> temp : getExcelContent(fileName3)) {
			System.out.println(temp.get(0) + "," + temp.get(1));
		}

	}

}
