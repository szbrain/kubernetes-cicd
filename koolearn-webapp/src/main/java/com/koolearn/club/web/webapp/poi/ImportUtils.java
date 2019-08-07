package com.koolearn.club.web.webapp.poi;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by lilong01 on 2017/12/8.
 */
public class ImportUtils {


    /**
     * 获取excel中所有sheet内容
     * @param in
     * @return
     */
    public static ExcelInfo readExcel(InputStream in){
        ExcelInfo excelInfo = new ExcelInfo();
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(new BufferedInputStream(in));
            int sheetNum = wb.getNumberOfSheets();
            for(int i = 0; i< sheetNum; i++){
                SheetInfo sheetInfo = new SheetInfo();
                Sheet sheet = wb.getSheetAt(sheetNum);
                sheetInfo.setName(getSheetName(wb, sheetNum));
                sheetInfo.setTitle(getSheetTitle(sheet));
                sheetInfo.setData(getSheetData(sheet));
                excelInfo.add(sheetInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelInfo;


    }

    /**
     * 获取sheet内容
     * @param in
     * @param sheetNum
     * @return
     */
    public static SheetInfo readSheet(InputStream in, int sheetNum) {
        SheetInfo sheetInfo = new SheetInfo();
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(new BufferedInputStream(in));
            Sheet sheet = wb.getSheetAt(sheetNum);
            sheetInfo.setName(getSheetName(wb, sheetNum));
            sheetInfo.setTitle(getSheetTitle(sheet));
            sheetInfo.setData(getSheetData(sheet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheetInfo;
    }

    /***
     * 获取sheet名称
     * @param wb
     * @param sheetNum
     * @return
     */
    private static String getSheetName(Workbook wb, int sheetNum) {
        return wb.getSheetName(sheetNum);
    }

    /**
     * 获取sheet的表头
     *
     * @param sheet
     * @return
     */
    private static RowInfo getSheetTitle(Sheet sheet) {
        RowInfo titleRow = getRow(sheet, 0);
        return titleRow;
    }

    /**
     * 获取列
     * @param sheet
     * @param rowNum
     * @return
     */
    private static RowInfo getRow(Sheet sheet, int rowNum) {
        Row row = sheet.getRow(rowNum);
        // 标题总列数
        int colNum = row.getLastCellNum();
        RowInfo rowInfo = new RowInfo(rowNum);
        for (int i = 0; i < colNum; i++) {
            rowInfo.add(parseCellFormatValue(row.getCell((short) i)));
        }
        return rowInfo;
    }


    /**
     * 获取sheet数据
     * @param sheet
     * @return
     */
    private static List<RowInfo> getSheetData(Sheet sheet) {
        List<RowInfo> rowInfoList = Lists.newArrayList();
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        for (int i = 1; i <= rowNum; i++) {
            RowInfo rowInfo = getRow(sheet, i);
            rowInfoList.add(rowInfo);
        }
        return rowInfoList;
    }


    /**
     * 解析单元格
     *
     * @param cell
     * @return
     */
    private static String parseCellFormatValue(Cell cell) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getPercentInstance();
        String result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING: // 字符串
                    result = cell.getStringCellValue();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC: // 数字
                    double strCell = cell.getNumericCellValue();
                    if (String.valueOf(strCell) == null) {
                        result = " ";
                    }
                    df.applyPattern("0");
                    result = df.format(strCell);
                    break;
                case XSSFCell.CELL_TYPE_BLANK: // 空值
                    result = " ";
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}
