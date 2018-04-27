package com.yedam.app.view.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.BoardService;
import com.yedam.app.board.BoardVO;
import com.yedam.app.employees.EmpService;

@Controller
public class AjaxController {
	@Autowired
	BoardService boardService;
	
	@Autowired
	EmpService empService;

	@RequestMapping("/ajaxTest.do")
	@ResponseBody // 리턴값을 자동으로 HttpMessageConvertor가 json으로 변환시켜줌
	public List<String> test() {
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("이순신");
		list.add("을지문덕");
		return list;
	}

	@RequestMapping("/getBoardListAjax")
	@ResponseBody
	public Map getBoardList(Model model, BoardVO vo) {

		vo.setFirst(1);
		vo.setLast(10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", boardService.getBoardList(vo));
		map.put("page", 1);
		map.put("total", boardService.getCount(vo));
		map.put("records", 10);
		return map;
	}

	@RequestMapping("/deptChart")
	public String deptChart() {
		return "emp/deptChart";
	}
	
	@RequestMapping("/getChartData")
	@ResponseBody
	public List<Map<String, Object>> getChartData() {
		/*List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "인사");
		map.put("cnt", "5");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("name", "총무");
		map.put("cnt", "10");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("name", "기획");
		map.put("cnt", "20");
		list.add(map);*/
		return empService.getDeptEmpStatistic(null);
	}

}
