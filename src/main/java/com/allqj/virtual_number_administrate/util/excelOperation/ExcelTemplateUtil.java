package com.allqj.virtual_number_administrate.util.excelOperation;

import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.util.jsonOperation.JsonOperation;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Label;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ExcelTemplateUtil {
    //获取模板
    public static <T extends TemplateEntity> void setTemplate(OutputStream outputStream, Class<T> type) throws IOException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(outputStream);

        WritableSheet sheet = workbook.createSheet("Sheet1", 0);
        WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.NO_BOLD);
        Collection<TemplateEntity.ExcelHead> excelHeadCollection = excelHeadMap(type).values();

        int index = 0;
        for (TemplateEntity.ExcelHead excelHead : excelHeadCollection) {
            Label labelA = new Label(index, 0, excelHead.name(), new WritableCellFormat(font));
            sheet.setColumnView(index, 30);
            sheet.addCell(labelA);
            index++;
        }
        workbook.write();
        workbook.close();
    }

    //获取注解map
    private static <T> Map<String, TemplateEntity.ExcelHead> excelHeadMap(Class<T> type) {
        Map<String, TemplateEntity.ExcelHead> excelHeadMap = new ConcurrentHashMap<>();
        if (type == TemplateEntity.class)
            return excelHeadMap;
        //反射父类
        excelHeadMap.putAll(excelHeadMap(type.getSuperclass()));
        for (Field field : type.getDeclaredFields()) {
            TemplateEntity.ExcelHead excelHead = field.getAnnotation(TemplateEntity.ExcelHead.class);
            if (excelHead != null)
                excelHeadMap.put(excelHead.name(), excelHead);
        }
        return excelHeadMap;
    }

    //读模板
    public static <T extends TemplateEntity> List<T> readTemplate(Sheet sheet, Class<T> resultType) {
        if (!format(tableHeaders(sheet.getRow(0)), classHeaders(resultType)))
            throw new ResultException(StatusCodeEnum.EXCEL_ERROR.getCode(), StatusCodeEnum.EXCEL_ERROR.getMessage());

        List<T> result = new ArrayList<>();
        for (int i = 1; i < sheet.getRows(); i++) {
            Cell[] cells = sheet.getRow(i);
            T t = toClass(tableHeaders(sheet.getRow(0)), classHeaders(resultType), sheet.getRow(0), cells, resultType);
            if (t == null)
                continue;
            result.add(t);
        }
        return result;
    }

    public static Sheet openExcel(InputStream inputStream, Integer sheetIndex) {
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            return workbook.getSheet(sheetIndex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResultException(StatusCodeEnum.EXCEL_ERROR.getCode(), StatusCodeEnum.EXCEL_ERROR.getMessage());
        }

    }

    //检查格式
    private static boolean format(Map<String, String> tableHeaders, Map<String, HeadersFieldName> classHeaders) {
        for (HeadersFieldName value : classHeaders.values()) {
            String v = tableHeaders.get(value.getHeaders());
            if (v == null || v.isEmpty())
                return false;
        }
        return true;
    }

    //获取表头
    private static Map<String, String> tableHeaders(Cell[] cells) {
        Map<String, String> map = new HashMap<>();
        for (Cell cell : cells)
            map.put(cell.getContents(), cell.getContents());
        return map;
    }

    //获取类型头
    private static <T> Map<String, HeadersFieldName> classHeaders(Class<T> type) {
        Map<String, HeadersFieldName> excelHeadMap = new ConcurrentHashMap<>();
        if (type == TemplateEntity.class)
            return excelHeadMap;
        //反射父类
        excelHeadMap.putAll(classHeaders(type.getSuperclass()));
        for (Field field : type.getDeclaredFields()) {
            TemplateEntity.ExcelHead excelHead = field.getAnnotation(TemplateEntity.ExcelHead.class);
            if (excelHead != null)
                excelHeadMap.put(excelHead.name(), new HeadersFieldName(excelHead.name(), field.getName()));
        }
        return excelHeadMap;
    }

    //转换到类型
    private static <T> T toClass(Map<String, String> tableHeaders, Map<String, HeadersFieldName> classHeaders, Cell[] keys, Cell[] values, Class<T> resultType) {
        String json = "";
        for (int i = 0; i < keys.length; i++) {
            String key = tableHeaders.get(keys[i].getContents());
            if (key == null || key.isEmpty())
                continue;
            HeadersFieldName headersFieldName = classHeaders.get(key);
            if (headersFieldName == null)
                continue;
            //拼接json
            String fieldJson = null;
            try {
                fieldJson = "\"" + headersFieldName.fieldName + "\":\"" + values[i].getContents() + "\"";
            } catch (Exception e) {
                throw new ResultException(StatusCodeEnum.EXCEL_LACK.getCode(), StatusCodeEnum.EXCEL_LACK.getMessage());
            }
            json = json == "" ? fieldJson : json + "," + fieldJson;
        }
        return JsonOperation.toClass("{" + json + "}", resultType);
    }

    //字段与描述
    private static class HeadersFieldName {
        private String headers;
        private String fieldName;

        public String getHeaders() {
            return headers;
        }

        public void setHeaders(String headers) {
            this.headers = headers;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public HeadersFieldName(String headers, String fieldName) {
            this.headers = headers;
            this.fieldName = fieldName;
        }
    }
}
