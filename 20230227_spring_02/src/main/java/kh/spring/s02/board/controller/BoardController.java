package kh.spring.s02.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;
import kh.spring.s02.common.file.FileUtil;

@Controller
@RequestMapping("/board") 
// 상위폴더를 따로 매핑해주고 클래스 안에서 메소드형태로 하위 폴더값들 매핑시켜준다.
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	@Qualifier("fileUtil")
	private FileUtil fileUtil; // new FileUtil를 안만들고 쓸때 선언해주고 쓰면 된다. 
	
	// BOARD_LIMIT = 화면에 보이는 글 개수
	private final static int BOARD_LIMIT = 5;
	// PAGE_LIMIT = 버튼 숫자 개수 << 1 2 3 >>
	private final static int PAGE_LIMIT = 3;
	
	//private final static String UPLOAD_FOLDER = "\\resources\\uploadfiles";
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard(ModelAndView mv) {
		String searchWord = "";
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
	
	// URL
	// 1. /board/read?boardNum=27&replyPage=3
	    //location.href="board/read?boardNum=${elboardnum}&replyPage=${elreplypage}"
	// 2. /board/read/27/3
        //location.href="board/read/${elboardnum}/${elreplypage}"
	
	
	// 글 상세 읽기 화면
	@GetMapping("/read/{boardNum}")
	public ModelAndView viewReadBoard(ModelAndView mv
			, @PathVariable int boardNum	
//			, @RequestParam("boardNum") int boardNum//, @RequestParam("boardWriter") String writer
			) {
		String writer = "user11";
		
		BoardVo vo = service.selectOne(boardNum, writer);
		mv.addObject("board", vo);
		
		List<BoardVo> replyList = service.selectReplyList(boardNum);
		mv.addObject("replyList", replyList);
		
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
	
	// 첨부파일 등록시
//	@PostMapping("/insert")
//	public ModelAndView doInsertBoard(ModelAndView mv
//			, @RequestParam(name = "report", required = false) MultipartFile multi
//			, BoardVo vo
//			, HttpServletRequest request) {
//		System.out.println("file org name:" + multi.getOriginalFilename());
//		
//		if(multi != null && !multi.equals("")) {
//			String originalFilename = multi.getOriginalFilename();
//			
//			// file을 server의 특정 위치에 저장
//			String webServerRoot = request.getSession().getServletContext().getRealPath("");
//			System.out.println(webServerRoot);
//			
////			String savePath = webServerRoot + "\\resources\\uploadfiles";
//			String savePath = webServerRoot + UPLOAD_FOLDER;
//			// 저장할 폴더(\\resources\\uploadfiles)가 없다면 만들어줘야 함.
//			File folder = new File(savePath);
//			if(!folder.exists()) {
//				folder.mkdirs();
//			}
//			
//			// 시간을 활용한 rename			
//			String renameByTime = System.currentTimeMillis()+"_"+ originalFilename;
//			// UUID
//			String renameByUUID = UUID.randomUUID().toString()+"_"+ originalFilename;
//			
//			// String renameFilePath = savePath + "\\" + multi.getOriginalFilename();
//			String renameFilePath = savePath + "\\" + renameByUUID;
//			
//			System.out.println("rename file:" + renameFilePath);
//			// 파일을 savePath 위치에 저장
//			try {
//				multi.transferTo(new File(savePath + "\\" + renameByUUID));
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			System.out.println("file saved name:" + multi.getName());
//			vo.setBoardOriginalFilename(originalFilename); // a.png
//			vo.setBoardRenameFilename(renameByUUID);       //uuid_a.png
//		}
//				
//		vo.setBoardWriter("user22");
//		int result = service.insert(vo);
//		return mv;
// 
//	}
	
	// 원글작성-첨부파일 등록시(+파일저장 메소드)
	@PostMapping("/insert")
	public ModelAndView doInsertBoard(ModelAndView mv
			, @RequestParam(name = "report", required = false) MultipartFile multi
			, BoardVo vo
			, HttpServletRequest request
			, MultipartHttpServletRequest multiReq) {
		Map<String, String> filePath;
		List<Map<String, String>> fileListPath;
		try {
			fileListPath = fileUtil.saveFileList(multiReq, request, null);
			filePath = fileUtil.saveFile(multi, request, null);
			vo.setBoardOriginalFilename(filePath.get("original"));   // a.png
			vo.setBoardRenameFilename(filePath.get("rename"));       //uuid_a.png
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo.setBoardWriter("user22");
		int result = service.insert(vo);
		return mv; 
	}
		
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

	// 게시글 목록(read.jsp)에서 답글 작성 ajax
	// 파일첨부 report 추가
	@PostMapping("/insertReplyAjax")
	@ResponseBody
	public String insertReplyAjax(BoardVo vo
			, MultipartFile report) {
		if(report != null) {
			System.out.println(report.getOriginalFilename());
		}else {
			System.out.println("파일없음");
		}
		vo.setBoardWriter("user6");
		
		// 답글 작성
		service.insert(vo);
		// 연관 답글을 조회해서 ajax로 return해야함.
		List<BoardVo> replyList = service.selectReplyList(vo.getBoardNum());
		// ajax는 mv에 실어갈 수 없음!
//		mv.addObject("replyList", replyList);
		return new Gson().toJson(replyList);
	}
	
//  GET, POST를 상관없이 한번에 적용할때
  //@RequestMapping(value = "/test")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {
		return mv;
	}
}
