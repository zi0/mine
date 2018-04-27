package com.yedam.app.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.user.UserService;
import com.yedam.app.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userdao;

	@Override
	public void insertUser(UserVO vo) {
		userdao.insertUser(vo);
	}

	@Override
	public void updateUser(UserVO vo) {
		userdao.updateUser(vo);
	}

	@Override
	public void deleteUser(UserVO vo) {
		userdao.deleteUser(vo);
	}

	@Override
	public UserVO getUser(UserVO vo) {
		return userdao.getUser(vo);
	}

	@Override
	public List<UserVO> getUserList() {
		return userdao.getUserList();
	}

	@Override
	public boolean login(UserVO vo) {
		// id 조회
		UserVO userVO = userdao.getUser(vo);
		
		// 입력 패스워드와 db의 패스워드 비교
		if(userVO != null && vo.getPassword().equals(userVO.getPassword())) {
			return true;
		}
		return false;
	}
	
}
