package Config.utilities;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteToExcel {
    // Apache POI library:


    String filePath = "DataTest";


    public static void main(String[] args) {

        // Create an Excel File using Java
        // Path: where the file will be created
        // Absolute path : Full path


        String filePath1 = "C:\\Users\\mhsha\\IdeaProjects\\LearnJava_QE_SUMMER2022\\DataTest\\TestData-Auto.xlsx";

        // Relative path : partial path
        String filePath = "../LearnJava_QE_SUMMER2022/DataTest/TestData-Automation.xlsx";
//        String filePathUsingRandomName = "../LearnJava_QE_SUMMER2022/DataTest/TestData-Automation-" + LearnRandomNumber.randomNumberGenerate() + ".xlsx";
        String filePathUsingStatic = "../LearnJava_QE_SUMMER2022/DataTest/TestData-Automation-1.xlsx";


        String filePathForDoc = "../LearnJava_QE_SUMMER2022/DataTest/TestData-Automation.docx";

        Object[][] studentInfo = {
                {"SL","firstName","lastName","address"},
                {"101","Jesmin","Sultana","Jamaica,NYC"},
                {"102","Md","Alam","Queens,NYC"},
                {"103",222.45,999,true}

        };

        //writeToExcelFile(filePathUsingRandomName,studentInfo,"studentsDetails");
        writeToExcelFile(filePathUsingStatic,studentInfo,"studentsDetails");
       // writeToExcelFileUsingForLoop(filePathUsingRandomName,studentInfo,"studentsDetails");
     //   writeToExcelFile(filePath1,studentInfo,"studentsDetails");
       // writeToExcelFile(filePath,studentInfo,"studentsDetails");
     //   writeToExcelFile(filePathForDoc,studentInfo,"studentsDetails");


    }

    public static void writeToExcelFile(String filePath, Object[][] input, String sheetName){
        // Write something inside the Excel file

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);

        int rowNumber = 0;
        for (Object[] data : input) {

            Row row = sheet.createRow(rowNumber++);
            int columnNumber = 0;
            for (Object field : data) {
                Cell cell = row.createCell(columnNumber++);

                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                } else if (field instanceof Boolean) {
                    cell.setCellValue((boolean) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            System.out.println("Excel file is created");
            workbook.close();
        } catch (FileNotFoundException e) {
            // throw new RuntimeException(e);
            System.out.println("File not found exception "+e.getMessage());

        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("File not closed and done "+e.getMessage());

        }

    }

    public static void writeToExcelFileUsingForLoop(String filePath, Object[][] input, String sheetName){
        // Write something inside the Excel file

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);

        int rowNumber = 0;

        for (int i = 0; i < input.length; i++) {
            Row row = sheet.createRow(rowNumber++);
            int columnNumber = 0;
            for (int j = 0; j < input.length; j++) {
                Cell cell = row.createCell(columnNumber++);


                if (input[i][j] instanceof String) {
                    cell.setCellValue((String) input[i][j]);
                } else if (input[i][j] instanceof Integer) {
                    cell.setCellValue((Integer) input[i][j]);
                } else if (input[i][j] instanceof Double) {
                    cell.setCellValue((Double) input[i][j]);
                } else if (input[i][j] instanceof Boolean) {
                    cell.setCellValue((boolean) input[i][j]);
                }

            }

        }

//        for (Object[] data : input) {
//
//            Row row = sheet.createRow(rowNumber++);
//            int columnNumber = 0;
//
//            for (Object field : data) {
//                Cell cell = row.createCell(columnNumber++);
//
//                if (field instanceof String) {
//                    cell.setCellValue((String) field);
//                } else if (field instanceof Integer) {
//                    cell.setCellValue((Integer) field);
//                } else if (field instanceof Double) {
//                    cell.setCellValue((Double) field);
//                } else if (field instanceof Boolean) {
//                    cell.setCellValue((boolean) field);
//                }
//            }
//        }

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            System.out.println("Excel file is created");
            workbook.close();
        } catch (FileNotFoundException e) {
            // throw new RuntimeException(e);
            System.out.println("File not found exception "+e.getMessage());

        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("File not closed and done "+e.getMessage());

        }

    }

}
