package com.shan.yellowpages.util;

import com.shan.yellowpages.base.enumeration.StatusEnum;
import com.shan.yellowpages.controller.ExcelExportData;
import com.shan.yellowpages.security.model.KhCompanyEntity;
import com.shan.yellowpages.security.model.KhContactEntity;
import com.shan.yellowpages.security.service.IKhCompanyEntityService;
import com.shan.yellowpages.security.service.IKhContactEntityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 *
 * @author bruce
 */
@Component
public class ExcelUtil implements InitializingBean {

	private static final String FILE_PATH = "/Users/bruce/Documents/技术工作/shan/Shan_Data_2020.xlsx";
//	private static final String FILE_PATH = "/Users/bruce/Documents/技术工作/shan/Shan_Data_2020_test.xlsx";

	@Autowired
	private IKhContactEntityService khContactEntityService;
	@Autowired
	private IKhCompanyEntityService khCompanyEntityService;

	/**
	 * 导出excel
	 * @param exportData
	 * @return
	 * @throws Exception
	 */
	public static XSSFWorkbook exportWorkbook(ExcelExportData exportData) throws Exception {
		String sheetName = exportData.getSheetName();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);
		List<List<String>> dataList = exportData.getDataList();
		dataList.get(0);
		XSSFRow headerRow = sheet.createRow(0);

		int i;
		for(i = 0; i < exportData.getKeyList().size(); ++i) {
			String key = exportData.getKeyList().get(i);
			headerRow.createCell(i).setCellValue(key);
		}

		for(i = 0; i < dataList.size(); ++i) {
			XSSFRow row = sheet.createRow(i + 1);
			List<String> valueList = (List)dataList.get(i);

			for(int j = 0; j < valueList.size(); ++j) {
				String value = (String)valueList.get(j);
				row.createCell(j).setCellValue(value);
			}
		}
		return workbook;
	}



	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khContactEntityService, "khContactEntityService can't be null");
	}

	public static void main(String[] args) throws Exception {
		ExcelUtil instance = new ExcelUtil();
		instance.importExcel();
	}

	public List<KhContactEntity> importExcel() throws Exception{

		int amountSucceed = 0;
		int amountFailed = 0;

		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(FILE_PATH)));
//		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(FILE_PATH)));
		XSSFSheet sheet = null;
//		HSSFSheet sheet = null;

		Map<Integer, String> errorLineMap = new LinkedHashMap<>();

		Date currentTime = new Date();
		for(int i = 0;i < workbook.getNumberOfSheets();i++){
			//获取每个sheet
			sheet = workbook.getSheetAt(i);
			List<KhContactEntity> list = new ArrayList<>();
			//getPhysicalNumberOfRows获取有记录的行数
			System.err.println("===条数"+sheet.getPhysicalNumberOfRows());
			for(int j = 1;j < sheet.getPhysicalNumberOfRows();j++){
				Row row = sheet.getRow(j);
				if(null!=row){
					//getLastCellNum获取最后一列
					KhContactEntity contact = new KhContactEntity();
					KhCompanyEntity khCompanyEntity = null;

					String companyName = null;
					String companyEnName = null;
					String companyWebsite = null;

					//String companyAddress = null;

					for(int k = 0;k < row.getLastCellNum();k++){



						if(null!=row.getCell(k)){
//							if(k==0){
//								Cell cell = row.getCell(0);
//								//cell->double
//								Double d = cell.getNumericCellValue();
//								//double->int
//								int id = new Double(d).intValue();
//								contact.setId(id);
//							}

							Cell cell = row.getCell(k);
							//检索/备注/数据来源、联系记录
							if(k==1){
								//cell->string
								contact.setRemark(StringUtils.trim(cell.getStringCellValue()));
							}

							//姓名
							if(k==2){
								contact.setName(StringUtils.trim(cell.getStringCellValue()));
							}

							//公司名称
							if(k==3){
								companyName = StringUtils.trim(cell.getStringCellValue());
								contact.setCompany(companyName);

							}

							//职位
							if(k==4){
								contact.setTitle(StringUtils.trim(cell.getStringCellValue()));
							}

							//部门
							if(k==5){
								contact.setDepartment(StringUtils.trim(cell.getStringCellValue()));
							}

							//手机
							if(k==6){
								contact.setMobile(StringUtils.trim(cell.getStringCellValue()));
							}

							//电话
							if(k==7){
								contact.setTelphone(StringUtils.trim(cell.getStringCellValue()));
							}

							//邮箱
							if(k==8){
								contact.setEmail(StringUtils.trim(cell.getStringCellValue()));
							}

							//传真
							if(k==9){
								contact.setFax(StringUtils.trim(cell.getStringCellValue()));
							}

							//地址/邮编
							if(k==10){
								contact.setAddress(StringUtils.trim(cell.getStringCellValue()));
							}

							//公司网址
							if(k==11){
								companyWebsite = StringUtils.trim(cell.getStringCellValue());
								contact.setCompanyWebsite(companyWebsite);


//								khCompanyEntity.setWebsite(website);
							}

							//所属行业/研究方向
							if(k==12){
								contact.setIndustry(StringUtils.trim(cell.getStringCellValue()));
							}

							//公司英文名称
							if(k==13){
								companyEnName  = StringUtils.trim(cell.getStringCellValue());
								contact.setCompanyEn(companyEnName);
//								khCompanyEntity.setEnName(companyEnName);
							}
						}
					}

					contact.setCreateTime(currentTime);
					contact.setStatus(StatusEnum.ENABLE.getStatus());
					contact.setUpdateTime(currentTime);

					Integer companyId = null;
					try{

//						if(StringUtils.isNotBlank(companyName)){
//							khCompanyEntity = khCompanyEntityService.loadByName(companyName);
//							if(!KhCompanyEntity.isValid(khCompanyEntity)){
//								khCompanyEntity = new KhCompanyEntity();
//								khCompanyEntity.setName(companyName);
//								khCompanyEntity.setCreateTime(currentTime);
//								khCompanyEntity.setStatus(StatusEnum.ENABLE.getStatus());
//								khCompanyEntity.setUpdateTime(currentTime);
//								khCompanyEntityService.save(khCompanyEntity);
//							}
//							companyId = khCompanyEntity.getId();
//						}
						contact.setCompanyId(companyId);
						khContactEntityService.save(contact);
						amountSucceed ++;
					}catch(Exception e){
						System.err.println("row ："+j+  "数据异常");
						errorLineMap.put(j, e.getMessage());
						e.printStackTrace();
						amountFailed ++;
					}finally {
						contact = null;
					}

//					list.add(contact);
				}
			}
			System.err.println("读取sheet表："+ workbook.getSheetName(i) + "完成");
//			return list;
		}
		System.err.println("读取成功："+ amountSucceed +"，读取失败："+ amountFailed +", map: "+ errorLineMap);
		return null;
	}

}
