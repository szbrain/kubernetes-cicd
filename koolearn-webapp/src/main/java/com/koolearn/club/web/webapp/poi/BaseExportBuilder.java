package com.koolearn.club.web.webapp.poi;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;

import java.util.Collection;
import java.util.Iterator;

/**
 * 自定义构建POI Sheet 基类
 * @author thend
 */
public class BaseExportBuilder implements IExportBuilder {

    private ExportInfo info;

    public BaseExportBuilder(String sheetName){
        info = new ExportInfo(sheetName);
        Sheet localSheet = ExportUtils.getLocalWorkbook().createSheet();
        info.setLocalSheet(localSheet);
        int index = ExportUtils.getLocalWorkbook().getSheetIndex(localSheet);
        ExportUtils.getLocalWorkbook().setSheetName(index, sheetName);
    }
    public <T> BaseExportBuilder(String sheetName, Collection<String> titles, Collection<T> datas){
        info = new ExportInfo(sheetName, titles, datas);
    }
    public <T, K> BaseExportBuilder(String sheetName, Collection<String> titles, Collection<T> datas, Collection<K> footDatas){
        info = new ExportInfo(sheetName, titles, datas, footDatas);
    }

    @Override
    public ExportInfo getExportInfo(){
        return info;
    }

    @Override
    public IExportBuilder buildTitle(Collection<String> titles){
        if(titles == null || titles.size() == 0){
            return this;
        }
        Row headRow = info.getLocalSheet().createRow(info.getCurRowIndex());
        int colIndex = 0;
        for(Iterator list = titles.iterator(); list.hasNext();){
            Cell cellHead = headRow.createCell(colIndex);
            cellHead.setCellValue(list.next().toString());
            cellHead.setCellStyle(buildTitleStyle());
            colIndex++;
        }
        info.setCurRowIndex(info.getCurRowIndex() + 1);
        return this;
    }

    @Override
    public <T> IExportBuilder buildData(Collection<T> datas) {
        if(datas == null || datas.size() == 0){
            return this;
        }
        Iterable curData = null;
        if(datas instanceof Collection){
            curData = (Collection) datas;
            Iterator rowList = curData.iterator();
            while(rowList.hasNext()){
                Iterable colList = (Iterable) rowList.next();
                if(colList == null){
                    return this;
                }
//            Row row = localSheet.createRow(dataIndex - ExcelExportUtil_bak.EXCEL_MAX_ROWNUM * curSheetIndex + 1);
                Row row = info.getLocalSheet().createRow(info.getCurRowIndex());
                int colIndex = 0;
                Iterator colIterator = colList.iterator();
                while(colIterator.hasNext()){
                    Object rowData = colIterator.next();
                    rowData = rowData == null ? "" : rowData;
                    Cell cell = row.createCell(colIndex);
                    cell.setCellStyle(buildDataStyle());
                    cell.setCellValue(rowData.toString());
                    colIndex++;
                }
                info.setCurRowIndex(info.getCurRowIndex() + 1);
            }
        }
        return this;
    }

    @Override
    public <T> IExportBuilder buildFoot(Collection<T> datas) {
        if(datas == null || datas.size() == 0){
            return this;
        }
        Row footRow = info.getLocalSheet().createRow(info.getCurRowIndex());
        int index = 0;
        for(Iterator list = datas.iterator(); list.hasNext();){
            Cell cellHead = footRow.createCell(index);
            cellHead.setCellValue(list.next().toString());
            cellHead.setCellStyle(buildTitleStyle());
            index++;
        }
        info.setCurRowIndex(1 + info.getCurRowIndex());
        return this;
    }

    @Override
    public CellStyle buildTitleStyle() {
        return buildSpecialStyle(ExportUtils.getLocalWorkbook());
    }

    @Override
    public CellStyle buildDataStyle() {
        CellStyle titleStyle = ExportUtils.getLocalWorkbook().createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font font = ExportUtils.getLocalWorkbook().createFont();
        titleStyle.setFont(font);
        return titleStyle;
    }

    @Override
    public CellStyle buildFootStyle() {
        return buildSpecialStyle(ExportUtils.getLocalWorkbook());
    }


    private CellStyle buildSpecialStyle(Workbook workbook){
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleStyle.setFont(font);
        return titleStyle;
    }
}
