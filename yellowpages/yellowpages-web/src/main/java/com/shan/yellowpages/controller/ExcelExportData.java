package com.shan.yellowpages.controller;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bruce
 */
@Data
public  class ExcelExportData implements Serializable {

    /** sheet名称 */
    private String sheetName;
    /** 行标题 */
    private String rowTitle;
    /** 表头 */
    private List<String> keyList;
    /** 行内容 */
    private List<List<String>> dataList;

    public ExcelExportData() {
    }


}