package com.yedam.app.view.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.user.UserService;
import com.yedam.app.user.UserVO;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// 목록
	@RequestMapping("getUserList")
	public String getUserList(Model model) {
		model.addAttribute("userList", userService.getUserList());
		return "user/getUserList";
	}

	// 등록폼
	@RequestMapping("insertUserForm")
	public String insertUserForm() {
		return "user/insertUser";
	}

	// 등록처리
	@RequestMapping("insertUser")
	public String insertUser(UserVO uservo) {
		userService.insertUser(uservo);
		return "redirect:/getUserList";
	}

	// 상세보기
	@RequestMapping("getUser")
	public String getUser(Model model, UserVO uservo) {
		model.addAttribute("user", userService.getUser(uservo));
		return "user/getUser";
	}

	// 수정폼
	@RequestMapping("updateUserForm")
	public String updateUserForm(Model model, UserVO uservo) {
		model.addAttribute("user", userService.getUser(uservo));
		return "user/updateUser";
	}

	// 수정처리
	@RequestMapping("updateForm")
	public String updateForm(Model model, UserVO uservo) {
		userService.updateUser(uservo);
		return "redirect:/getUserList";
	}

	// 삭제
	@RequestMapping("deleteForm")
	public String deleteForm(Model model, UserVO uservo) {
		userService.deleteUser(uservo);
		return "redirect:/getUserList";
	}
	
	// 로그인
	@RequestMapping("login")
	public String login(@ModelAttribute("user") UserVO uservo, HttpSession session) {
		if(userService.login(uservo)) {
			session.setAttribute("user", userService.getUser(uservo));
			return "redirect:/getUserList";
		} else {
			return "user/login";
		}
	}
	
	// 로그인폼
	@RequestMapping("loginForm")
	public String loginForm() {
		return "user/login";
	}

	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/getUserList";
	}
	
	// 중복체크
	@RequestMapping("dupCheck")
	@ResponseBody
	public Map<String, Boolean> dupCheck(UserVO uservo){
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		// 서비스(한건조회)
		UserVO vo = userService.getUser(uservo);
		
		// id가 있으면 false
		if(vo != null) {
			map.put("result", false);
		}
		// id가 없으면 true
		else {
			map.put("result", true);
		}
		return map;
		
	}

}
