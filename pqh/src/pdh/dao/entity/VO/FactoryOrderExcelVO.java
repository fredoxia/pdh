package pdh.dao.entity.VO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import pdh.comparator.CustOrderProductComparatorByBrandProductCode;
import pdh.dao.entity.order.CurrentBrands;
import pdh.dao.entity.order.CustOrderProduct;
import pdh.dao.entity.product.Brand;
import pdh.dao.entity.product.Color;
import pdh.dao.entity.product.Product;
import pdh.dao.entity.product.ProductBarcode;
import pdh.utility.DateUtility;
import pdh.utility.ExcelUtility;

public class FactoryOrderExcelVO extends AbstractExcelView {
	private int DATA_ROW = 5;
	private int DATE_ROW = 1;
	private int BRAND_ROW = 2;
	private int FACTORY_ROW = 3;
	private int ORDER_IDENTITY_COLUMN = 3;
	private int PRODUCT_CODE_COLUMN = 0;
	private int COLOR_COLUMN = 1;
	private int QUANTITY_COLUMN =2;
	private int CATEGORY_COLUMN =3;
	private int NUM_PER_HAND_COLUMN =4;
	private int BARCODE_COLUMN =5;
	private String defaulFileName ="ChangJiaBaoBiao.xls";
	
	@Override
	protected void buildExcelDocument(Map<String, Object> dataMap,
			HSSFWorkbook wb, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<HQProdRptVO> orderProducts = (List<HQProdRptVO>)dataMap.get("data");

		CurrentBrands cBrands = (CurrentBrands)dataMap.get("brand");
		
		String fileName = getFileName(cBrands, request);
		
		wb = process(orderProducts,cBrands);
		
        response.setContentType("application/vnd.ms-excel");     
        response.setHeader("Content-disposition", "attachment;filename=" + fileName); 
        OutputStream ouputStream = response.getOutputStream();     
        wb.write(ouputStream);     
        ouputStream.flush();     
        ouputStream.close(); 
	}
	
	private String getFileName(CurrentBrands cBrands, HttpServletRequest request) {
		String fileName = cBrands.getYear().getYear() + "-" + cBrands.getQuarter().getQuarter_Name() + "-" + cBrands.getBrand().getBrand_Name() + ".xls";
		return  ExcelUtility.encodeExcelDownloadName(fileName,defaulFileName);
	}

	private HSSFWorkbook process(List<HQProdRptVO> orderProducts,CurrentBrands cBrands){
		HSSFWorkbook wb = null;
		InputStream is = null;
		try {
			is = FactoryOrderExcelVO.class.getClassLoader()
			 .getResourceAsStream("qdh/templates/FactoryOrderTemplate.xls");
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		try {
			wb = new HSSFWorkbook(is);   
		} catch (FileNotFoundException e) {
			pdh.utility.loggerLocal.error(e);
		} catch (IOException e) {
			pdh.utility.loggerLocal.error(e);
		}   
		
		//1. process data
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow dateRow = sheet.getRow(DATE_ROW);
		dateRow.createCell(1).setCellValue(DateUtility.getToday().toString());
		
		Brand b = cBrands.getBrand();
		HSSFRow brandRow = sheet.getRow(BRAND_ROW);
		brandRow.createCell(1).setCellValue(b.getBrand_Name());
		
		HSSFRow factoryRow = sheet.getRow(FACTORY_ROW);
		factoryRow.createCell(1).setCellValue(b.getSupplier());
		
		int sum = 0;
		String orderIdentity = "";
		
		//2. process elements
		for (int i =0; i < orderProducts.size(); i++){
			HQProdRptVO cop = orderProducts.get(i);
			
			HSSFRow dataRow = sheet.createRow(DATA_ROW + i);
			dataRow.createCell(PRODUCT_CODE_COLUMN).setCellValue(cop.getProductCode());
			
			dataRow.createCell(COLOR_COLUMN).setCellValue(cop.getColor());
			
			dataRow.createCell(QUANTITY_COLUMN).setCellValue(cop.getQuantity());
			
			dataRow.createCell(CATEGORY_COLUMN).setCellValue(cop.getCategory());
			
			dataRow.createCell(NUM_PER_HAND_COLUMN).setCellValue(cop.getNumPerHand());
			
			dataRow.createCell(BARCODE_COLUMN).setCellValue(cop.getBarcode());
			
			sum += cop.getQuantity();
			
//			if (orderIdentity.equals(""))
//				orderIdentity = cop.get;
		}
		
		dateRow.createCell(ORDER_IDENTITY_COLUMN).setCellValue(orderIdentity);
		
		HSSFRow dataRow = sheet.createRow(DATA_ROW + orderProducts.size());
		dataRow.createCell(COLOR_COLUMN).setCellValue("总计:");
		dataRow.createCell(QUANTITY_COLUMN).setCellValue(sum);
		
		return wb;
	}

}
