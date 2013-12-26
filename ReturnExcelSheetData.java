package khan.tarique;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;

public class ReturnExcelSheetData {
	
	@DataProvider(name="dataProvider")
	public static Object[][] returnExcelSheetData(Method method) throws BiffException, IOException{
		
		Map<String, String> excelSheet = ReadExcelAnnotation.extractAnnotationValue(method);
		String filePath = excelSheet.get("filePath");
        String fileName = excelSheet.get("fileName");
		String sheetName = excelSheet.get("sheetName");
		
		//String excelSheetFolder = "../Training/TestData/";      // this two line was used when there was no file path property in annotation
		//String filePath = excelSheetFolder.concat(fileName); //.concat(".xls");
		
		String absolutePath = filePath.concat("/").concat(fileName);
		
		FileInputStream file = new FileInputStream(new File(absolutePath));
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet worksheet = workbook.getSheet(sheetName);
		
		int ROWS = worksheet.getRows()-1;
		int COLS = worksheet.getColumns();
		
		Object[][] dataset = new Object[ROWS][COLS];
		
		for(int rowCount=0; rowCount<ROWS; rowCount++){
			for(int colCount=0; colCount<COLS; colCount++){
				dataset[rowCount][colCount] = worksheet.getCell(colCount, rowCount+1).getContents();
			}
		}
		workbook.close();
		file.close();
		return dataset;
	}

}