package com.yedam.app.view.email;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {
	
	@Autowired
	SendEmailService emailService;
	
	// 메일 보내기 폼
	@RequestMapping("mailForm")
	public String mailForm() {
		return "email/mailForm";
	}
	
	// 메일 발송 처리
	@RequestMapping("mailSend")
	public void mailSend(EmailVO vo, HttpServletResponse response) throws IOException {
		emailService.send(vo);
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('mail send success!');");
		out.println("</script>");
	}
}
