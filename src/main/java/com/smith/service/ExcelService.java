package com.smith.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class ExcelService {
    private static Logger log = LoggerFactory.getLogger(ExcelService.class);
    public boolean compareExcelFiles(String filePath1, String filePath2) throws IOException {
        try (FileInputStream file1 = new FileInputStream(filePath1);
             FileInputStream file2 = new FileInputStream(filePath2);
             Workbook workbook1 = new XSSFWorkbook(file1);
             Workbook workbook2 = new XSSFWorkbook(file2)) {

            Sheet sheet1 = workbook1.getSheetAt(0);
            Sheet sheet2 = workbook2.getSheetAt(0);

            if (sheet1.getLastRowNum() != sheet2.getLastRowNum()) {
                return false;
            }

            for (int i = 0; i <= sheet1.getLastRowNum(); i++) {
                Row row1 = sheet1.getRow(i);
                Row row2 = sheet2.getRow(i);

                if (Objects.isNull(row1) || Objects.isNull(row2)) {
                    return false;
                }

                if (row1.getLastCellNum() != row2.getLastCellNum()) {
                    return false;
                }

                for (int j = 0; j < row1.getLastCellNum(); j++) {
                    Cell cell1 = row1.getCell(j);
                    Cell cell2 = row2.getCell(j);

                    if (!Objects.equals(cell1.toString(), cell2.toString())) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
