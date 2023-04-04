package Config.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadFromExcel {


    public static void main(String[] args) {
        String filePath = "../LearnJava_QE_SUMMER2022/DataTest/TestData.xlsx";

        Workbook workbook;
        FileInputStream inputStream;


        try {
            inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream);
            Sheet dataTypeSheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = dataTypeSheet.iterator();
            while (rowIterator.hasNext()){
                Row currentRow = rowIterator.next();

                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellType() == CellType.STRING){
                        System.out.print(currentCell.getStringCellValue()+"       ");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue()+"       ");
                    } else if (currentCell.getCellType() == CellType.BOOLEAN) {
                        System.out.print(currentCell.getBooleanCellValue()+"       ");
                    }
                }
                System.out.println();

            }
            workbook.close();

        } catch (FileNotFoundException e) {
         //   throw new RuntimeException(e);
            System.out.println("File not Found Exception "+e.getMessage());
        } catch (IOException e) {
          //  throw new RuntimeException(e);
            System.out.println("IOException Exception "+e.getMessage());
        } finally {
            System.out.println("Read Excel information is done");
        }


    }



















}
