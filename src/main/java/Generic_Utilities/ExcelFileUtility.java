package Generic_Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 */
public class ExcelFileUtility {

	public Workbook wb;

	/**
	 * @Note : this method is used to fetch from the excel file.
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @return string
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 */
	public String fetchDataFromExcelFile(String sheetname, int rowindex, int cellindex) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row ro = sh.getRow(rowindex);
		Cell cel = ro.getCell(cellindex);
		String data = cel.toString();
		return data;
	}

	/**
	 * @Note : this method is used to fetch multiple data from the excel file.
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @return String
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 */
	public String fetchMultipleDataFromExcelFile(String sheetname, int rowindex, int cellindex)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger.xlsx");
		String data = null;
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		
		for (int i = 0; i < sh.getLastRowNum(); i++) {

			for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) {
				 data = sh.getRow(i).getCell(j).toString();
			}
		}	
		return data;
	}
	
	

	/**
	 * @Note : this method is used to writeback on the excel file.
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param data
	 * @return void
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 */

	public void writeBackDataToExcelFile(String sheetname, int rowindex, int cellindex, String data)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row ro = sh.getRow(rowindex);
		Cell cel = ro.createCell(cellindex);
		cel.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VTiger.xlsx");
		wb.write(fos);
	}

}
