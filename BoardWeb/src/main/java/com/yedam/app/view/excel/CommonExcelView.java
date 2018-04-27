package com.yedam.app.view.excel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

@Component
public class CommonExcelView extends AbstractXlsxView {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExcelView.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Sheet sheet = workbook.createSheet("Datatypes in Java");
		Row row;
		Cell cell;
		int rowNum = 0;
		String file_name = (String) model.get("filename") + System.currentTimeMillis() + ".xlsx";
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\"");
		// header 출력
		String[] headers = (String[]) model.get("headers");
		if (headers != null) {
			row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (String header : headers) {
				row.createCell(colNum++).setCellValue(header);
			}
		}
		// body 출력
		List<Map<String, Object>> list = (List<Map<String, Object>>) model.get("datas");
		System.out.println(list);
		if (headers != null) {
			for (Map<String, Object> map : list) {
				row = sheet.createRow(rowNum++);
				int colNum = 0;
				for (String header : headers) {
					cell = row.createCell(colNum++);
					Object field = map.get(header);
					if (field == null) {
						field = "";
						System.out.println(header);
					}
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof BigDecimal) {
						cell.setCellValue(((BigDecimal) field).doubleValue());
					} else if (field instanceof Date) {
						cell.setCellValue((Date) field);
					} else {
						cell.setCellValue(field.toString());
					}
				}
			}
		} else {
			for (Map<String, Object> map : list) {
				row = sheet.createRow(rowNum++);
				int colNum = 0;
				Iterator<String> iter = map.keySet().iterator();
				while (iter.hasNext()) {
					cell = row.createCell(colNum++);
					Object field = map.get(iter.next());
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof BigDecimal) {
						cell.setCellValue(((BigDecimal) field).doubleValue());
					} else if (field instanceof Date) {
						cell.setCellValue((Date) field);
					} else {
						cell.setCellValue(field.toString());
					}
				}
			}
		}
		LOGGER.debug("### buildExcelDocument Map : {} end!");
	}
}

/*@SuppressWarnings("deprecation")
@Component("commonExcelView")
public class CommonExcelView extends AbstractExcelView {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExcelView.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook wb, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		List<Object> categories = (List<Object>) model.get("category");

		HSSFCell cell = null;
		LOGGER.debug("### buildExcelDocument start !!!");
		HSSFSheet sheet = wb.createSheet("User List");

		for (int i = 0; i < categories.size(); i++) {
			LOGGER.debug("### buildExcelDocument Map : {} started!!", i);
			Map<String, Object> category = (Map<String, Object>) categories.get(i);
			System.out.println(category);
			
			 * cell = getCell(sheet, 3 + i, 0); setText(cell,
			 * category.get("SEQ").toString()); cell = getCell(sheet, 3 + i, 1);
			 * setText(cell, (String)category.get("TITLE")); cell = getCell(sheet, 3 + i,
			 * 2); setText(cell, category.get("REGDATE").toString()); cell = getCell(sheet,
			 * 3 + i, 3); setText(cell, category.get("CNT").toString());
			 

			Set<String> keySet = category.keySet();
			Iterator<String> iter = keySet.iterator();
			int j = 0;
			while (iter.hasNext()) {
				cell = getCell(sheet, 3 + i, j++);
				setText(cell, category.get(iter.next()).toString());
			}
			LOGGER.debug("### buildExcelDocument Map : {} end!!", i);
		}
	}
}
*/

