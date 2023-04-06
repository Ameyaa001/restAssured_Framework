package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class getData {

	public static ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException {
		
	
		
		  ArrayList<String> arrayData = new ArrayList<String>();
		
		// step 1 to locate/open excel data file, by crating object of file input stream
		FileInputStream fis = new FileInputStream("E:\\classnotes\\test_data.xlsx");

		// step 2 create object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// step 3 access the desired sheet
		// step 3.1 fetch the count of sheets available in the excel file
		int countOfSheet = workbook.getNumberOfSheets();

		// step 3.2 fetch the name of sheet and compare against desired sheet name
		for (int i = 0; i < countOfSheet; i++) {
			{
				String sheetname = workbook.getSheetName(i);
				if (sheetname.equalsIgnoreCase(testSheetName))
				{
					// step 4 access the sheet and iterate through Rows to fetch the column in which 
					//test case name column in which test case name column is found
					XSSFSheet Sheet = workbook.getSheetAt(i);
					//Step 4.1 create iterator for rows
			        Iterator<Row> Rows = Sheet.iterator();
			        Row firstRow = Rows.next();
			        // Step 4.2 Create iterator for cell
			        Iterator<Cell>Cells = firstRow.cellIterator();
			        int j =0;
			        int tc_column = 0;
			        //step 4.3 read the cell value of row no. 1 to compare against the test case name
			        while(Cells.hasNext())
			        {
			        	Cell cellvalue = Cells.next();
			        	if (cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
			        	{
			        		tc_column = j;
			        		//System.out.println(tc_column);
			        	}
			        	j++;
			        }
			        //step 5 fetch the data for designated test case
			        while (Rows.hasNext())
					{
						Row DataRow = Rows.next();
						if (DataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
						{
							Iterator<Cell> DataCellValue =DataRow.cellIterator();
							while(DataCellValue.hasNext())
							{
								Cell DataofCell = DataCellValue.next();
								//Method 1 to extract Cell value by using try and catch method 
								try
								{
									String TestData =DataofCell.getStringCellValue();
									System.out.println(TestData);
									arrayData.add(TestData);
								}
								catch(Exception e)
								{
									int IntestData = (int) DataofCell.getNumericCellValue();
									String StringTestData=Integer.toString(IntestData);
									System.out.println(StringTestData);
									arrayData.add(StringTestData);
									
								}
//								CellType DataType = DataofCell.getCellType();
//										
//								// Method 2 to extract the cell  value by using if and else 
//								
//								//System.out.println(DataType);
//								
//								if(DataType.toString()== "NUMERIC")
//								{
//									int IntestData = (int) DataofCell.getNumericCellValue();
//									String stringtestdata = Integer.toString(IntestData);
//									arrayData.add(stringtestdata);
//									//System.out.println(IntestData);
//								}
//								else if(DataType.toString()== "STRING")
//								{
//									String TestData = DataofCell.getStringCellValue();
//									System.out.println(TestData);
//								}
//								//method 3 -- extract the data by converting it into String 
//			        		    String TestData = DataofCell.toString().replaceAll("\\.\\d+$", "");
//			        		    arrayData.add(TestData);
//			        		    System.out.println(TestData);
//			        		    
//			        		    //method 4 -- Extract the data by using Dataformatter
//			        		    DataFormatter format = new DataFormatter();
//			        		    String testData = format.formatCellValue(DataofCell);
//			        		    arrayData.add(testData);
//			        		    System.out.println(TestData);
	    					}
						
						}	
					}
				}
			}
		}
			return arrayData;
	}
}
	