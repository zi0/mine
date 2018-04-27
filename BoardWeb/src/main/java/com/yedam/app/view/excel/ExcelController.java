package com.yedam.app.view.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.board.BoardService;
import com.yedam.app.board.BoardVO;

@Controller
public class ExcelController {

	@Autowired
	BoardService boardService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);
	
	//엑셀출력1
	@RequestMapping("/boardExcelView.do")
	public ModelAndView excelView(BoardVO vo, HttpServletResponse response) throws IOException{
		List<Map<String, Object>> list = boardService.getBoardListMap();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String[] header = {"seq", "title", "writer"};
		map.put("header", header);
		map.put("file_name", "excel_dept");
		map.put("datas", list);
		return new ModelAndView("commonExcelView", map);
	}
	
	// 엑셀출력2
	@RequestMapping("excelDown")
	public void execelDown(HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HSSFWorkbook objWorkBook = new HSSFWorkbook(); // 워크북 생성
		HSSFSheet objSheet = objWorkBook.createSheet(); // 워크시트 생성

		HSSFRow objRow = objSheet.createRow(0); // 로우 생성(괄호안 숫자는 행 번호)
		HSSFCell objCell = objRow.createCell(0); // 셀 생성(괄호안 숫자는 열 번호)
		objCell.setCellValue("연도"); // 괄호안에 저장할 데이터를 넣는다.


		
		// 스타일 적용
		short dot = BorderFormatting.BORDER_DASH_DOT;
		short AQUA = IndexedColors.AQUA.getIndex();
		CellStyle style = objWorkBook.createCellStyle();
		style.setBorderRight(dot);
		style.setRightBorderColor(AQUA);
		style.setBorderBottom(dot);
		style.setBottomBorderColor(AQUA);
		style.setBorderLeft(dot);
		style.setLeftBorderColor(AQUA);
		style.setBorderTop(dot);
		style.setTopBorderColor(AQUA);
		Font headerFont = objWorkBook.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFont(headerFont);

		objCell.setCellStyle(style);
		objCell = objRow.createCell(5);
		objCell.setCellValue("내용");

		objRow = objSheet.createRow(2);
		objCell = objRow.createCell(0);
		objCell.setCellValue("연도55");

		objCell = objRow.createCell(5);
		objCell.setCellValue("내용55");

		FileOutputStream fs = new FileOutputStream("d:/test.xls"); // 괄호안의 위치에 파일 생성한다.
		objWorkBook.write(fs);
		out.println("엑셀 생성 완료");

		fs.close();

		// 다운로드
		File uFile = new File("d:/test.xls");
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			response.setContentType(mimetype);
			setDisposition(uFile.getName(), request, response);
			// response.setContentLength(fSize);

			BufferedInputStream in = null;
			BufferedOutputStream bos = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				bos = new BufferedOutputStream(response.getOutputStream());

				byte[] buf = new byte[256];
				while ((in.read(buf)) != -1) {
					bos.write(buf);
				}
				bos.flush();
			} catch (Exception ex) {
				LOGGER.debug("IGNORED: {}", ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						LOGGER.debug("IGNORED: {}", ignore.getMessage());
					}
				}
				if (bos != null) {
					try {
						bos.close();
					} catch (Exception ignore) {
						LOGGER.debug("IGNORED: {}", ignore.getMessage());
					}
				}
			}
		}
	}

	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
}
