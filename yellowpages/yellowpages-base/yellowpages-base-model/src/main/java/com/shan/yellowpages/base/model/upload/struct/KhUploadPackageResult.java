package com.shan.yellowpages.base.model.upload.struct;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xuejw
 * @version 1.0
 * @date 2020-03-24 14:25
 */
@Data
public class KhUploadPackageResult implements Serializable {

	/** 显示指定serialVersionUID生成方式 */
	private static final long serialVersionUID = 1L;

	private String resUrl;

	private String fileType;

	private String fileName;
}
