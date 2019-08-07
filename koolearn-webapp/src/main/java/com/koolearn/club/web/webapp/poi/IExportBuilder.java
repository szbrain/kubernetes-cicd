package com.koolearn.club.web.webapp.poi;

import org.apache.poi.ss.usermodel.CellStyle;

import java.util.Collection;

/**
 * 自定义构建Sheet接口
 * @author thend
 */
public interface IExportBuilder {

    CellStyle buildTitleStyle();

    CellStyle buildDataStyle();

    CellStyle buildFootStyle();

    IExportBuilder buildTitle(Collection<String> titles);

    <T> IExportBuilder buildData(Collection<T> datas);

    <T> IExportBuilder buildFoot(Collection<T> datas);

    ExportInfo getExportInfo();
}
