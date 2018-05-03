package cn.pdmi.modianSpider.utils;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen_ on 2018/4/28.
 */
public class KeyWordUtils {
//    public static void main(String[] args) {
//        List<String> keyWords = getKeyWords();
//        for (String k:keyWords
//             ) {
//            System.out.println(k);
//        }
//    }
    public static List<String> getKeyWords(String excelName) {
        try {
            ArrayList<String> keyWords = new ArrayList<>();
            FileInputStream excelFileInputStream = new FileInputStream("D:/phantomjs/"+excelName+".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);

            excelFileInputStream.close();
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                // XSSFRow 代表一行数据
                XSSFRow row = sheet.getRow(rowIndex);
                XSSFCell cell = row.getCell(row.getFirstCellNum());
                if (cell!=null){
                    if (cell.getStringCellValue()!=null&&!"".equals(cell.getStringCellValue().trim())){
                        keyWords.add(cell.getStringCellValue());
                    }
                }
            }

            workbook.close();
            return keyWords;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

