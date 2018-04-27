package com.yedam.app.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yedam.app.common.JDBCUtil;
import com.yedam.app.user.UserVO;

@Repository
public class UserDAO{
    // JDBC 관련 변수
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    // SQL 명령어들
    private final String USER_INSERT = "insert into users(id, password, name, role) values(?,?,?,?)";
    private final String USER_UPDATE = "update users set name=?, password=?, role =? where id=?";
    private final String USER_DELETE = "delete users where id=?";
    private final String USER_GET    = "select * from users where id=?";
    private final String USER_LIST = "select * from users  order by id desc";

    // CRUD 기능의 메소드 구현
    // 글 등록
    public void insertUser(UserVO vo){
        System.out.println("===> JDBC로 insertUser() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_INSERT);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            stmt.setString(3, vo.getName());
            stmt.setString(4, vo.getRole());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    
    // 글 수정
    public void updateUser(UserVO vo){
        System.out.println("===> JDBC로 updateUser() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_UPDATE);
            stmt.setString(1, vo.getName());
            stmt.setString(2, vo.getPassword());
            stmt.setString(3, vo.getRole());
            stmt.setString(4, vo.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }    
    
    // 글 삭제
    public void deleteUser(UserVO vo){
        System.out.println("===> JDBC로 deleteUser() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_DELETE);
            stmt.setString(1, vo.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    
    // 글 상세 조회
    public UserVO getUser(UserVO vo){
        System.out.println("===> JDBC로 getUser() 기능 처리");
        UserVO user = null;
        try {
            conn = JDBCUtil.getConnection();        
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            rs = stmt.executeQuery();
            if(rs.next()){
                user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
            }    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }
    
    // 글 목록 조회
    public List<UserVO> getUserList(){
        System.out.println("===> JDBC로 getUserList() 기능 처리");
        List<UserVO> userList = new ArrayList<UserVO>();
        try {
            conn = JDBCUtil.getConnection(); 
           	stmt = conn.prepareStatement(USER_LIST);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                UserVO user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return userList;
    }    

}
