package com.yedam.app.view.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.board.BoardService;
import com.yedam.app.board.BoardVO;
import com.yedam.app.common.Paging;

@Controller
@SessionAttributes("board")
public class BoardController {

	@Autowired
	BoardService boardService;

	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "title");
		conditionMap.put("내용", "content");
		return conditionMap;
	}

	// 목록
	/*
	 * @RequestMapping("getBoardList") public ModelAndView getBoardList(
	 * 
	 * @RequestParam(value = "searchCondition", required = false, defaultValue =
	 * "title") String condition,
	 * 
	 * @RequestParam(value = "searchKeyword", required = false) String keyword) {
	 * System.out.println("condition : " + condition); BoardVO vo = new BoardVO();
	 * vo.setSearchCondition(condition); vo.setSearchKeyword(keyword); ModelAndView
	 * mv = new ModelAndView(); mv.addObject("boardList",
	 * boardService.getBoardList(vo)); mv.setViewName("board/getBoardList"); return
	 * mv; }
	 */

	@RequestMapping("getBoardList")
	public String getBoardList(Model model, BoardVO vo, Paging paging, Locale locale) {
		System.out.println(locale.getCountry() + " : " + locale.getLanguage());
		// 전체 레코드 건수
		paging.setPageUnit(3); // 한페이지에 5개씩 보임
		paging.setTotalRecord(boardService.getCount(vo));
		model.addAttribute("paging", paging);

		// vo에 first, last setting
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		model.addAttribute("boardList", boardService.getBoardList(vo));
		model.addAttribute("paging", paging);
		return "board/getBoardList";
	}

	// 등록폼
	@RequestMapping(value = "insertBoard", method = RequestMethod.GET)
	public String insertBoardForm() {
		return "board/insertBoard";
	}

	// 등록처리
	@RequestMapping(value = "insertBoard", method = RequestMethod.POST)
	public String insertBoard(BoardVO vo) throws IOException {
		// 첨부파일이 있는지 확인
		MultipartFile file = vo.getUploadFile();
		if (file != null && file.getSize() > 0) {
			// 업로드 된 파일 이름 읽기
			String filename = file.getOriginalFilename();
			// 중복파일이 있으면 rename

			// 파일을 업로드 위치에 저장
			file.transferTo(new File("D:/upload", filename));
			vo.setUploadFileName(filename);
		}
		boardService.insertBoard(vo);
		System.out.println("seq : " + vo.getSeq());
		return "redirect:/getBoardList";
	}

	// 상세보기
	@RequestMapping("getBoard/{seq}")
	public ModelAndView getBoard(@PathVariable Integer seq) {
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("board", boardService.getBoard(vo));
		mv.setViewName("board/getBoard");
		return mv;
	}

	// 수정폼
	@RequestMapping("updateBoardForm")
	public String updateBoardForm(Model model, BoardVO vo) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "board/updateBoard";
	}

	// 수정처리
	@RequestMapping("updateBoard")
	public String updateBoard(@ModelAttribute("board") BoardVO vo, SessionStatus sessionStatus) {
		System.out.println("session vo : " + vo);
		boardService.updateBoard(vo);
		sessionStatus.setComplete(); // session 저장된 모델값(board) 삭제
		return "redirect:/getBoardList";
	}

	// 삭제
	@RequestMapping("deleteBoard")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:/getBoardList";
	}

	// 단건삭제처리
	@RequestMapping("deleteBoardList")
	public String deleteBoardList(@RequestParam ArrayList<String> seq) {
		boardService.deleteBoardList(seq);
		return "redirect:/getBoardList";
	}
}
