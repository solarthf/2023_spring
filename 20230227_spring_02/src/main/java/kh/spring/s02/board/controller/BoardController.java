package kh.spring.s02.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;

@Controller
@RequestMapping("/board") 
// 상위폴더를 따로 매핑해주고 클래스 안에서 메소드형태로 하위 폴더값들 매핑시켜준다.
public class BoardController {
	
	@Autowired
	private BoardService service;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard(ModelAndView mv) {
		System.out.println("!!!board list Controller!!!");		
		mv.addObject("boardlist", service.selectList());
		mv.setViewName("board/list");
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void viewUpdateBoard() {
		
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void viewDeleteBoard() {
		int boardNum = 19;
		int result = service.delete(boardNum);
	}
	@GetMapping("/read")
	public void viewReadBoard(Model model) {
		int boardNum = 1;
		String writer = "user11";
		BoardVo vo = service.selectOne(boardNum, writer);
	}
	
	
//	@GetMapping("/boardinsert")
//	@RequestMapping(value = "/insert", method = RequestMethod.GET)
//	public void viewInsertBoard(ModelAndView mv, HttpServletRequest req, HttpSession session, BoardVo vo) {
//		// 1
//		mv.addObject("test", "test value");
//		mv.setViewName("boardinsert");
//		return mv;
		
		// 2 public ModelAndView가 아니라 String으로 받아야 한다.
//		return "boardinsert";
//		
//		// 3 void로 받으면 return값이 없어도 된다.
//		return; 
//	}
//	@PostMapping("/boardinsert")
//	@RequestMapping(value = "/insert", method = RequestMethod.POST)
//	public ModelAndView doInsertBoard(ModelAndView mv) {
//		return mv;
//	}


	@GetMapping("/insert")
	public ModelAndView viewInsertBoard(ModelAndView mv, HttpServletRequest req, HttpSession session, BoardVo vo) {
		mv.setViewName("board/insert");
		return mv;
 
	}
	
//	@PostMapping("/insert")
	
	@GetMapping("/insertPostTest")
	public ModelAndView doInsertBoard(ModelAndView mv, BoardVo vo) {
		vo.setBoardContent("15답글2");
		vo.setBoardTitle("15답2");
		vo.setBoardWriter("user7");
		int result = service.insert(vo);
		
		return mv;
	}

//  GET, POST를 상관없이 한번에 적용할때
  //@RequestMapping(value = "/test")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {
		return mv;
	}
}
