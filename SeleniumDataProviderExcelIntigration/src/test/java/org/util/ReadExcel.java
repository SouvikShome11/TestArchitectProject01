package org.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ReadExcel {

   public Object[][] readAndReturn2DArray(String filePath) throws FileNotFoundException, IOException {

        FileInputStream file = new FileInputStream("./InputFiles/InputFIle.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet("Sheet1");

        int rowCount = sheet.getPhysicalNumberOfRows();

        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();

        Object data[][] = new Object[rowCount - 1][colCount];

        for (int i = 0; i < rowCount - 1; i++) {

            row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {
                //System.out.println(String.valueOf(row.getCell(j)));
                data[i][j] = String.valueOf(row.getCell(j));
            }
        }
        return data;
    }

}
