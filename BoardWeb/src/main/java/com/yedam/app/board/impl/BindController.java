package com.yedam.app.board.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yedam.app.board.BindVO;
import com.yedam.app.common.CustomDateEditor2;
import com.yedam.app.common.CustomNumberEditor2;

@Controller
public class BindController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor2(formatter, true)); //null 허용여부
		binder.registerCustomEditor(int.class, new CustomNumberEditor2(Integer.class, null, true));
	}
	
	@RequestMapping("bindForm")
	public String bindForm() {
		return "board/bindForm";
	}
	
	@RequestMapping("bindTest")
	public String bindTest(BindVO vo) {
		System.out.println(vo);
		return "board/bindForm";
	}
}
