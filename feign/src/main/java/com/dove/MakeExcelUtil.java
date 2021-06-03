package com.dove;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class MakeExcelUtil {
    /**
     * 创建 excel 表格
     *
     * @param list       一条数据存一个map对象，map对象中存列和值得对应关系
     * @param destFile   当然就是要存的文件信息,即表格保存的路径
     * @param headerList 很重要，它是列的展示，当然和数据的列要对应不然找不到对应的地方存储
     * @param message    表格第一行的表头信息
     */
    public static void createExcelFile(List<Map<String, Object>> list, File destFile, List<String> headerList, String message) throws InterruptedException, IOException {
        int sizeAll = list.size();
        // 设置每页最大条数 65534 ，求出整数页 wholeNumber
        int wholeNumber = sizeAll / 65534;
        int yu = sizeAll % 65534;

        int sheetSize = 1;
        int flagList = 1;

        if (sizeAll <= 65534) {
            sheetSize = 1;
        } else {
            if (yu > 0) {
                sheetSize = wholeNumber + 1;
            } else {
                sheetSize = wholeNumber;
            }
        }
        // 用 HSSF 创建一个可读写的工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        if (list.size() == 0) {
            // list 中没有数据，直接操作空的工作簿
            // 写进文档
            //wb.write(new File(""));
        } else {
            for (int i = 0; i < sheetSize; i++) {
                /*创建工作簿*/
                HSSFSheet sheet = wb.createSheet("Sheet" + (i + 1));

                /*1.设置标题*/
                //设置标题样式
                HSSFCellStyle tabTitleStyle = setTabTitleStyle(wb, sheet);
                //将表格标题设置在第一行（0）
                HSSFRow tableTitleRow = sheet.createRow(0);
                //合并单元格
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerList.size() - 1));
                //选中合并后的单元格作为表格标题信息存储的那个单元格
                HSSFCell cell = tableTitleRow.createCell(0);
                //设置表格标题内容
                cell.setCellValue(message);
                //为单元格设置样式
                cell.setCellStyle(tabTitleStyle);

                /*2.设置表头*/
                //设置头表头样式
                HSSFCellStyle tabHeadStyle = setHeaderStyle(wb);
                HSSFRow headerRow = sheet.createRow(1);

                for (int j = 0; j < headerList.size(); j++) {
                    headerRow.createCell(j).setCellStyle(tabHeadStyle);
                    headerRow.createCell(j).setCellValue(headerList.get(j));
                }
                /*3.数据处理*/
                HSSFCellStyle dataCellStyle = setDataStyle(wb);
                for (int r = 0; r < list.size(); r++) {
                    HSSFRow dataRow = sheet.createRow(2 + r);
                    for (int y = 0; y < headerList.size(); y++) {
                        /*HSSFCell dataCell = dataRow.createCell(y);
                        dataCell.setCellStyle(dataCellStyle);
                        dataCell.setCellValue(String.valueOf(list.get(r).get(headerList.get(y))));*/
                        setCellStyle(wb, dataRow.createCell(y), String.valueOf(list.get(r).get(headerList.get(y))), setDataStyle(wb));
                    }
                }
                OutputStream out = null;
                try {
                    out = new FileOutputStream(destFile);
                    wb.write(out);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                            wb.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    //统一处理cell格式函数
    public static HSSFCell setCellStyle(HSSFWorkbook workbook, HSSFCell cell, String value, HSSFCellStyle cellStyle) {
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
        return cell;
    }

    private static HSSFCellStyle setDataStyle(HSSFWorkbook wb) {
        //3、设置表格数
        //获取表格标题样式对象
        HSSFCellStyle tabDataStyle = wb.createCellStyle();
        //设置内容水平居中
        tabDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置内容垂直居中
        tabDataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置边框
        tabDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        tabDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        tabDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        tabDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return tabDataStyle;
    }

    private static HSSFCellStyle setHeaderStyle(HSSFWorkbook wb) {
        //2、设置表头
        //获取表格标题样式对象
        HSSFCellStyle tabHeadStyle = wb.createCellStyle();
        //设置内容水平居中
        tabHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置内容垂直居中
        tabHeadStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont tabHeadFont = wb.createFont();
        tabHeadFont.setFontName("黑体");
        tabHeadFont.setFontHeightInPoints((short) 14);//设置字体大小

        tabHeadStyle.setFont(tabHeadFont);//tabHeadStyle设置字体
        tabHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        tabHeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        tabHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        tabHeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return tabHeadStyle;
    }

    private static HSSFCellStyle setTabTitleStyle(HSSFWorkbook wb, HSSFSheet sheet) {
        //1、设置表格标题
        //获取表格标题样式对象
        HSSFCellStyle tabTitleStyle = wb.createCellStyle();
        HSSFFont tabTitleFont = wb.createFont();
        //设置内容居中
        tabTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        tabTitleFont.setFontName("楷体");
        tabTitleFont.setFontHeightInPoints((short) 16);//设置字体大小

        tabTitleStyle.setFont(tabTitleFont);//设置字体
        tabTitleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        tabTitleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        tabTitleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        tabTitleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        return tabTitleStyle;
    }

    public static HSSFWorkbook createHSSFWorkbook(List<Map<String, Object>> list, List<String> headerList, String title) {
        int sizeAll = list.size();
        // 设置每页最大条数 65534 ，求出整数页 wholeNumber
        int wholeNumber = sizeAll / 65534;
        int yu = sizeAll % 65534;

        int sheetSize = 1;
        int flagList = 1;

        if (sizeAll <= 65534) {
            sheetSize = 1;
        } else {
            if (yu > 0) {
                sheetSize = wholeNumber + 1;
            } else {
                sheetSize = wholeNumber;
            }
        }
        // 用 HSSF 创建一个可读写的工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        if (list.size() == 0) {
            // list 中没有数据，直接操作空的工作簿
            // 写进文档
            //wb.write(new File(""));
        } else {
            for (int i = 0; i < sheetSize; i++) {
                /*创建工作簿*/
                HSSFSheet sheet = wb.createSheet("Sheet" + (i + 1));

                /*1.设置标题*/
                //设置标题样式
                HSSFCellStyle tabTitleStyle = setTabTitleStyle(wb, sheet);
                //将表格标题设置在第一行（0）
                HSSFRow tableTitleRow = sheet.createRow(0);
                //合并单元格
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerList.size() - 1));
                //选中合并后的单元格作为表格标题信息存储的那个单元格
                HSSFCell cell = tableTitleRow.createCell(0);
                //设置表格标题内容
                cell.setCellValue(title);
                //为单元格设置样式
                cell.setCellStyle(tabTitleStyle);

                /*2.设置表头*/
                //设置头表头样式
                HSSFCellStyle tabHeadStyle = setHeaderStyle(wb);
                HSSFRow headerRow = sheet.createRow(1);

                for (int j = 0; j < headerList.size(); j++) {
                    headerRow.createCell(j).setCellStyle(tabHeadStyle);
                    headerRow.createCell(j).setCellValue(headerList.get(j));
                }
                /*3.数据处理*/
                HSSFCellStyle dataCellStyle = setDataStyle(wb);
                for (int r = 0; r < list.size(); r++) {
                    HSSFRow dataRow = sheet.createRow(2 + r);
                    for (int y = 0; y < headerList.size(); y++) {
                        /*HSSFCell dataCell = dataRow.createCell(y);
                        dataCell.setCellStyle(dataCellStyle);
                        dataCell.setCellValue(String.valueOf(list.get(r).get(headerList.get(y))));*/
                        setCellStyle(wb, dataRow.createCell(y), String.valueOf(list.get(r).get(headerList.get(y))), setDataStyle(wb));
                    }
                }

            }
        }
        return wb;
    }
}
