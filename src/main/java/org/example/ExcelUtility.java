package org.example;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtility {
    public static Map<String,String> getMapData(String file, String date_file, int column) throws FileNotFoundException {

//        Map <String,String> system_imbalance_map = new HashMap<String,String>();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = new HSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNumber = sheet.getLastRowNum();

            Row data_row = sheet.getRow(5);
            Cell data_cell = data_row.getCell(column);
            String data_name = data_cell.getStringCellValue().trim();
//            DataFormatter formatter = new DataFormatter();
//            String data_name = formatter.formatCellValue(data_cell);
            String data_name_target = data_name.substring(data_name.indexOf(""), data_name.lastIndexOf("(")-1);


            for (int i=6;i<=29;i++){
                Row row = sheet.getRow(i);
                Cell keyCell = row.getCell(0);
//                String time = String.valueOf((int) keyCell.getNumericCellValue());
                int time = (int)keyCell.getNumericCellValue()-1;


                Cell system_imbalance = row.getCell(column);
//                String system_imbalance_string = String.valueOf((int) system_imbalance.getNumericCellValue());
                double system_imbalance_double = system_imbalance.getNumericCellValue();
                String system_imbalance_format = String.format("%.3f",system_imbalance_double);


                System.out.println(data_name_target+"; "+date_file+" "+time+":00; "+system_imbalance_format);

//                system_imbalance_map.put(time,system_imbalance_string);

            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }
}
