//package com.shan.yellowpages.base.utils.poi;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.VerticalAlignment;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
///**
// * excel的工具类
// * 参考了： https://www.cnblogs.com/xinyijiu/p/10969690.html
// * @author bruce
// */
//public class ExcelUtil {
//
//    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
//
//    /**
//     * excel导出的数据结构
//     */
//    public static class ExcelExportData implements Serializable {
//        /** sheet的名称 */
//        private String sheetName;
//        /** 首行的title */
//        private String rowTitle;
//        /** key的集合 */
//        private List<String> keyList;
//        /** 数据集合 */
//        private List<List<String>> dataList;
////        private List<Map<String, String>> dataList;
//
//        public String getSheetName() {
//            return sheetName;
//        }
//
//        public void setSheetName(String sheetName) {
//            this.sheetName = sheetName;
//        }
//
//        public String getRowTitle() {
//            return rowTitle;
//        }
//
//        public void setRowTitle(String rowTitle) {
//            this.rowTitle = rowTitle;
//        }
//
////        public List<Map<String, String>> getDataList() {
////            return dataList;
////        }
////
////        public void setDataList(List<Map<String, String>> dataList) {
////            this.dataList = dataList;
////        }
//
//        public List<List<String>> getDataList() {
//            return dataList;
//        }
//
//        public void setDataList(List<List<String>> dataList) {
//            this.dataList = dataList;
//        }
//
//        public List<String> getKeyList() {
//            return keyList;
//        }
//
//        public void setKeyList(List<String> keyList) {
//            this.keyList = keyList;
//        }
//    }
//
//
//    /**
//     *
//     * 目前仅支持单sheet
//     * @param exportData
//     * @return
//     * @throws Exception
//     */
//    public static HSSFWorkbook exportWorkbook(ExcelExportData exportData) throws Exception{
//
//        String sheetName = exportData.getSheetName();
//
//        //1.创建一个工作簿
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        //2.创建一个工作表sheet
//        HSSFSheet sheet = workbook.createSheet(sheetName);
//
//        List<List<String>> dataList = exportData.getDataList();
//
//        dataList.get(0);
//
//        //表头，第一行
//        HSSFRow headerRow = sheet.createRow(0);
//        for(int i=0;i<exportData.getKeyList().size();i++) {
//            String key = exportData.getKeyList().get(i);
//            headerRow.createCell(i).setCellValue(key);
//        }
//
//        //数据， 需要遍历行
//        for(int i = 0;i<dataList.size();i++){
//            HSSFRow row = sheet.createRow(i+1);
//            //对列赋值，遍历列
//
//            List<String> valueList =  dataList.get(i);
//            for(int j=0;j<valueList.size();j++){
//                String value = valueList.get(j);
//                row.createCell(j).setCellValue(value);
//            }
//        }
//        return workbook;
//    }
//
//
//    /**
//     * 创建excel文档
//     *
//     * 目前没使用到，但其中有些api可以作为参考
//     *
//     * @param keys        list中map的key数组集合
//     * @param columnNames excel的列名
//     */
//    @Deprecated
//    public static HSSFWorkbook createWorkBook(List<Map<String, Object>> list, String[] keys, String columnNames[]) {
//        // 创建excel工作簿
//        HSSFWorkbook wb = new HSSFWorkbook();
//        // 创建第一个sheet页，并命名
//        HSSFSheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
//        // 设置列宽
//        for (int i = 0; i < keys.length; i++) {
//            //最后一列为附件URL地址,列宽设置大一些
//            if (i == (keys.length - 1)) {
//                sheet.setColumnWidth((short) i, (short) (200 * 120));
//            } else {
//                sheet.setColumnWidth((short) i, (short) (50 * 60));
//            }
//        }
//
//        // 创建第一行，并设置其单元格格式
//        HSSFRow row = sheet.createRow((short) 0);
//        row.setHeight((short) 500);
//        // 单元格格式(用于列名)
//        HSSFCellStyle cs = wb.createCellStyle();
//        HSSFFont f = wb.createFont();
//        f.setFontName("宋体");
//        f.setFontHeightInPoints((short) 10);
//        f.setBold(true);
//        cs.setFont(f);
//        // 水平居中
//        cs.setAlignment(HorizontalAlignment.CENTER);
//        // 垂直居中
//        cs.setVerticalAlignment(VerticalAlignment.CENTER);
//        cs.setLocked(true);
//        //自动换行
//        cs.setWrapText(true);
//
//        //设置列名
//        for (int i = 0; i < columnNames.length; i++) {
//            HSSFCell cell = row.createCell(i);
//            cell.setCellValue(columnNames[i]);
//            cell.setCellStyle(cs);
//        }
//
//        //设置首行外,每行每列的值(Row和Cell都从0开始)
//        for (short i = 1; i < list.size(); i++) {
//            HSSFRow rowItem = sheet.createRow((short) i);
//            String flag = "";
//            //在Row行创建单元格
//            for (short j = 0; j < keys.length; j++) {
//                HSSFCell cell = rowItem.createCell(j);
//                cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list.get(i).get(keys[j]).toString());
//                if (list.get(i).get(keys[j]) != null) {
//                    if ("优".equals(list.get(i).get(keys[j]).toString())) {
//                        flag = "优";
//                    } else if ("差".equals(list.get(i).get(keys[j]).toString())) {
//                        flag = "差";
//                    }
//                }
//            }
//
//
//            //设置该行样式
//            HSSFFont font = wb.createFont();
//            font.setFontName("宋体");
//            font.setFontHeightInPoints((short) 10);
//            HSSFCellStyle cellStyle = wb.createCellStyle();
//            if ("优".equals(flag)) {
//
//                cellStyle.setFont(font);
//                // 左右居中
//                cellStyle.setAlignment(HorizontalAlignment.CENTER);
//                // 上下居中
//                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//                cellStyle.setLocked(true);
//                //自动换行
//                cellStyle.setWrapText(true);
//                // 设置背景色
//                cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
//                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                //依次为每个单元格设置样式
//                for (int m = 0; m < keys.length; m++) {
//                    HSSFCell hssfCell = rowItem.getCell(m);
//                    hssfCell.setCellStyle(cellStyle);
//                }
//            } else if ("差".equals(flag)) {
//                HSSFCellStyle cellStyle2 = wb.createCellStyle();
//                cellStyle2.setFont(font);
//                cellStyle2.setAlignment(HorizontalAlignment.CENTER);// 左右居中
//                cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
//                cellStyle2.setLocked(true);
//                cellStyle2.setWrapText(true);//自动换行
//                cellStyle2.setFillForegroundColor(HSSFColor.HSSFColorPredefined.RED.getIndex());// 设置背景色
//                cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                for (int m = 0; m < keys.length; m++) {
//                    HSSFCell hssfCell = rowItem.getCell(m);
//                    hssfCell.setCellStyle(cellStyle2);
//                }
//            } else {
//                HSSFCellStyle cs2 = wb.createCellStyle();
//                cs2.setFont(font);
//                cs2.setAlignment(HorizontalAlignment.CENTER);// 左右居中
//                cs2.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
//                cs2.setLocked(true);
//                cs2.setWrapText(true);//自动换行
//                for (int m = 0; m < keys.length; m++) {
//                    HSSFCell hssfCell = rowItem.getCell(m);
//                    hssfCell.setCellStyle(cs2);
//                }
//            }
//        }
//        return wb;
//    }
//
//
////    //生成并下载Excel
////    public static void downloadWorkBook(List<Map<String,Object>> list,
////            String keys[],
////            String columnNames[],
////            String fileName,
////            HttpServletResponse response) throws IOException{
////        ByteArrayOutputStream os = new ByteArrayOutputStream();
////        try {
////            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        byte[] content = os.toByteArray();
////        InputStream is = new ByteArrayInputStream(content);
////        // 设置response参数
////        response.reset();
////        response.setContentType("application/vnd.ms-excel;charset=utf-8");
////        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
////        ServletOutputStream out = response.getOutputStream();
////        BufferedInputStream bis = null;
////        BufferedOutputStream bos = null;
////        try {
////            bis = new BufferedInputStream(is);
////            bos = new BufferedOutputStream(out);
////            byte[] buff = new byte[2048];
////            int bytesRead;
////            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
////                bos.write(buff, 0, bytesRead);
////            }
////        } catch (final IOException e) {
////            throw e;
////        } finally {
////            if (bis != null)
////                bis.close();
////            if (bos != null)
////                bos.close();
////        }
////    }
//
//}