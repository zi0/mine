package com.yedam.app.view.emp;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.common.Paging;
import com.yedam.app.employees.EmpService;
import com.yedam.app.employees.EmpVO;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Controller
@SessionAttributes("emp")
public class EmpController {

	@Autowired
	EmpService empService;

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("성", "last_name");
		conditionMap.put("이름", "first_name");
		return conditionMap;
	}

	// 목록
	@RequestMapping("/getEmpList")
	public String getEmpList(EmpVO vo, Model model, Paging paging) {
		paging.setTotalRecord(empService.getCount(vo));

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		model.addAttribute("empList", empService.getEmpList(vo));
		model.addAttribute("paging", paging);
		return "emp/getEmpList";
	}

	// 등록폼
	@RequestMapping(value = "insertEmp", method = RequestMethod.GET)
	public String insertEmpForm() {
		return "emp/insertEmp";
	}

	// 등록처리
	@RequestMapping(value = "insertEmp", method = RequestMethod.POST)
	public String insertEmp(EmpVO vo) {
		empService.insertEmp(vo);
		return "redirect:/getEmpList";
	}

	// 상세보기
	@RequestMapping("/getEmp")
	public ModelAndView getBoard(@RequestParam Integer employee_id) {
		EmpVO vo = new EmpVO();
		vo.setEmployee_id(employee_id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("emp", empService.getEmp(vo));
		mv.setViewName("emp/getEmp");
		return mv;
	}

	// 수정폼
	@RequestMapping("/updateEmpForm")
	public String updateEmpForm(Model model, EmpVO vo) {
		model.addAttribute("emp", empService.getEmp(vo));
		return "emp/updateEmp";
	}

	// 수정처리
	@RequestMapping("/updateEmp")
	public String updateEmp(@ModelAttribute("emp") EmpVO vo, SessionStatus sessionStatus) {
		System.out.println("session vo : " + vo);
		empService.updateEmp(vo);
		sessionStatus.setComplete(); // session 저장된 모델값(board) 삭제
		return "redirect:/getEmpList";
	}

	// 삭제
	@RequestMapping("/deleteEmp")
	public String deleteEmp(EmpVO vo) {
		empService.deleteEmp(vo);
		return "redirect:/getEmpList";
	}

	@RequestMapping("empReport.do")
	public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			JasperReport report = JasperCompileManager
					.compileReport(request.getSession().getServletContext().getRealPath("reports/empDeptReport.jrxml"));
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(empService.getEmployeeAll());
			JasperPrint print = JasperFillManager.fillReport(report, map, JRdataSource);
			JRExporter exporter = new JRPdfExporter();
			OutputStream out;
			response.reset();
			out = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report3.pdf");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
