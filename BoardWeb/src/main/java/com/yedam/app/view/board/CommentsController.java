package com.yedam.app.view.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.CommentsService;
import com.yedam.app.board.CommentsVO;

@Controller
public class CommentsController {

	// 서비스 객체 injection
	@Autowired
	CommentsService commentsService;
	
	// 댓글 등록
	@RequestMapping("insertComments")
	@ResponseBody
		public CommentsVO insertComments(CommentsVO vo) {
				commentsService.insertComments(vo);
				return commentsService.getComments(vo);
	}
	// 댓글 수정
	@RequestMapping("updateComments")
	@ResponseBody
		public CommentsVO updateComments(CommentsVO vo) {
				commentsService.updateComments(vo);
				return vo;
	}
	// 댓글 삭제
	@RequestMapping("deleteComments")
	@ResponseBody
		public CommentsVO deleteComments(CommentsVO vo) {
				commentsService.deleteComments(vo);
				return vo;
	}
	// 댓글 목록
	@RequestMapping("getCommentsList")
	@ResponseBody
		public List<CommentsVO> getCommentsList(CommentsVO vo) {
			vo.setPageUnit(10);
			return commentsService.getCommentsList(vo);
			
		}
	}

