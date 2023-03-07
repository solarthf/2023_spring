package kh.spring.s02.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;

@Controller
@RequestMapping("/board") 
// 상위폴더를 따로 매핑해주고 클래스 안에서 메소드형태로 하위 폴더값들 매핑시켜준다.
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// BOARD_LIMIT = 화면에 보이는 글 개수
	private final static int BOARD_LIMIT = 5;
	// PAGE_LIMIT = 버튼 숫자 개수 << 1 2 3 >>
	private final static int PAGE_LIMIT = 3;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard(ModelAndView mv) {
		String searchWord = "답";
		int currentPage = 1; // 현재페이지 = 1
		// 검색어를 집어넣었을 때 나오는 총게시물 수
		int totalCnt = service.selectOneCount(searchWord);
		// 총게시물 수
//		int totalCnt = service.selectOneCount(); 
		int totalPage = (totalCnt % BOARD_LIMIT == 0) ? 
				(totalCnt/BOARD_LIMIT) : (totalCnt/BOARD_LIMIT) + 1 ;
		int startPage = (currentPage%PAGE_LIMIT ==0) ?
				(currentPage/PAGE_LIMIT -1)*PAGE_LIMIT + 1 : 
				(currentPage/PAGE_LIMIT)*PAGE_LIMIT + 1 ;
		int endPage = (startPage + PAGE_LIMIT > totalPage) ? 
				totalPage : (startPage + PAGE_LIMIT);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		mv.addObject("pageInfo", map);
		
//		mv.addObject("totalPage", totalPage);
//		mv.addObject("startPage", startPage);
//		mv.addObject("endPage", endPage);
//		mv.addObject("currentPage", currentPage);
		
		// 검색단어는 제목, 내용, 작성자에서 포함되어있으면 찾기
		// null, "" → 검색하지 않음
//		String searchWord = null;
//		String searchWord = "";
//		String searchWord = "답";
		
		
		
		mv.addObject("boardlist", service.selectList(currentPage, BOARD_LIMIT, searchWord));
		mv.setViewName("board/list");
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void viewUpdateBoard() {
		
	}
	
	@GetMapping("/updatePostTest")
	public void updateBoard() {
		int boardNum = 1;
		String boardTitle = "수정제목";
		String boardContent = "수정내용";
		String boardOriginalFilename = "";   // "" = 파일없음
		String boardRenameFilename = "";     // "" = 파일없음
		
		BoardVo vo = new BoardVo();
		vo.setBoardTitle(boardTitle);
		vo.setBoardContent(boardContent);
		vo.setBoardOriginalFilename(boardOriginalFilename);
		vo.setBoardRenameFilename(boardRenameFilename);
		service.update(vo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void viewDeleteBoard() {
		int boardNum = 19;
		int result = service.delete(boardNum);
	}
	@GetMapping("/read")
	public ModelAndView viewReadBoard(ModelAndView mv
			, @RequestParam("boardNum") int boardNum//, @RequestParam("boardWriter") String writer
			) {
		String writer = "user11";
		BoardVo vo = service.selectOne(boardNum, writer);
		mv.addObject("board", vo);
		mv.setViewName("board/read");
		return mv;
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


	// 원글 작성페이지 이동
	@GetMapping("/insert")
	public ModelAndView viewInsertBoard(ModelAndView mv, HttpServletRequest req, HttpSession session, BoardVo vo) {
		mv.setViewName("board/insert");
		return mv;
 
	}
	
//	@PostMapping("/insert")
	
	// 원글 작성
	@GetMapping("/insertPostTest")
	public ModelAndView doInsertBoard(ModelAndView mv, BoardVo vo) {
		vo.setBoardContent("임시내용");
		vo.setBoardTitle("임시제목");
		vo.setBoardWriter("user7");
		int result = service.insert(vo);
		return mv;
	}

	// 답글작성 페이지로 이동
	@GetMapping("/insertReply")
	public ModelAndView viewInsertReply(ModelAndView mv, int boardNum) {
		// 몇번 글에 대한 답글인지
		mv.setViewName("insertReply");
		return mv;
	}
	
	// 답글작성
	@GetMapping("/insertReplyPostTest")
	public ModelAndView viewInsertReply(ModelAndView mv, BoardVo vo) {
		// 몇번 글에 대한 답글인지
		int boardNum = 3;
		vo.setBoardNum(boardNum);
		vo.setBoardContent("임시3답내용");
		vo.setBoardTitle("임시3답제목");
		vo.setBoardWriter("user6");
		service.insert(vo);
		return mv;
	}

	@PostMapping("/insertReplyAjax")
	@ResponseBody
	public String insertReplyAjax(BoardVo vo) {
//		int boardNum = 3;
//		vo.setBoardNum(boardNum);
//		
//		vo.setBoardContent("ccc답내용");
//		vo.setBoardTitle("ccc답제목");
		vo.setBoardWriter("user6");
		
		service.insert(vo);
		
		return "ok";
	}
	
//  GET, POST를 상관없이 한번에 적용할때
  //@RequestMapping(value = "/test")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {
		return mv;
	}
}
