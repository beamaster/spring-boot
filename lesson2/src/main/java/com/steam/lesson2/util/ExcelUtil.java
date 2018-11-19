package com.steam.lesson2.util;

import com.steam.lesson2.model.ClientPowerModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     *
     * @param filePath 文件路径+文件名+后缀
     * @param sheetAt 从0开始
     * @param columnAt 从0开始
     * @param rowAt 从0开始
     * @return
     */
    public static List<ClientPowerModel> readExcelAsModel(String filePath,int sheetAt,int columnAt,int rowAt){
        List<ClientPowerModel> list = new ArrayList<>();

        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
            HSSFSheet sheet = workbook.getSheetAt(sheetAt);
            for (int i = rowAt; i < sheet.getLastRowNum(); i++) {
                ClientPowerModel cpModel = new ClientPowerModel();
                HSSFRow row = sheet.getRow(i);
                row.getCell(columnAt+0).setCellType(HSSFCell.CELL_TYPE_STRING);
                row.getCell(columnAt+1).setCellType(HSSFCell.CELL_TYPE_STRING);
                row.getCell(columnAt+2).setCellType(HSSFCell.CELL_TYPE_STRING);
                row.getCell(columnAt+3).setCellType(HSSFCell.CELL_TYPE_STRING);
                row.getCell(columnAt+4).setCellType(HSSFCell.CELL_TYPE_STRING);
                row.getCell(columnAt+5).setCellType(HSSFCell.CELL_TYPE_STRING);
                cpModel.setHostName(row.getCell(columnAt+0).getStringCellValue());
                cpModel.setClientId(row.getCell(columnAt+1).getStringCellValue());
                cpModel.setClientName(row.getCell(columnAt+2).getStringCellValue());
                cpModel.setDataTime(row.getCell(columnAt+3).getStringCellValue());
                cpModel.setChargePower(row.getCell(columnAt+4).getStringCellValue());
                cpModel.setDiscChargePower(row.getCell(columnAt+5).getStringCellValue());
                list.add(cpModel);
            }
            return list;
        }catch (IOException ioe){
            logger.info("...readExcelAsModel..."+ioe);
        }
        return null;
    }
}
