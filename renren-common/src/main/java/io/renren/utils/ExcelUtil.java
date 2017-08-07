package io.renren.utils;

import org.apache.poi.hssf.usermodel.*;

import java.util.List;

public class ExcelUtil {
                    //db2excel
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
        // 第一步，创建一个webbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = null;
        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                row.createCell(j).setCellValue(values[i][j]);
            }
        }

        return wb;
    }

//    public static HSSFWorkbook excel2Db(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
//        // 第一步，创建一个webbook，对应一个Excel文件
//        if(wb == null){
//            wb = new HSSFWorkbook();
//        }
//        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//        HSSFSheet sheet = wb.createSheet(sheetName);
//        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//        HSSFRow row = sheet.createRow(0);
//        // 第四步，创建单元格，并设置值表头 设置表头居中
//        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//        HSSFCell cell = null;
//        //创建标题
//        for(int i=0;i<title.length;i++){
//            cell = row.createCell(i);
//            cell.setCellValue(title[i]);
//            cell.setCellStyle(style);
//        }
//        //创建内容
//        for(int i=0;i<values.length;i++){
//            row = sheet.createRow(i + 1);
//            for(int j=0;j<values[i].length;j++){
//                row.createCell(j).setCellValue(values[i][j]);
//            }
//        }
//
//        return wb;
//    }
//    public static List<T> loadScoreInfo(String xlsPath) throws IOException{
//        List temp = new ArrayList();
//        FileInputStream fileIn = new FileInputStream(xlsPath);
////根据指定的文件输入流导入Excel从而产生Workbook对象
//        Workbook wb0 = new HSSFWorkbook(fileIn);
////获取Excel文档中的第一个表单
//        Sheet sht0 = wb0.getSheetAt(0);
////对Sheet中的每一行进行迭代
//        for (Row r : sht0) {
//            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
//            If(r.getRowNum()<1){
//                continue;
//            }
////创建实体类
//        T info=new T();
////取出当前行第1个单元格数据，并封装在info实体stuName属性上
//            info.setStuName(r.getCell(0).getStringCellValue());
//            info.setClassName(r.getCell(1).getStringCellValue());
//            info.setRscore(r.getCell(2).getNumericCellValue());
//            info.setLscore(r.getCell(3).getNumericCellValue());
//            temp.add(info);
//        }
//        fileIn.close();
//        return temp;
//    }
}