package kh.spring.s02.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s02.member.model.service.MemberService;
import kh.spring.s02.member.model.vo.MemberVo;

@Controller
@RequestMapping("/member") 
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv) {
		// jsp 주소, jsp 이름
		mv.setViewName("member/signUp");
		return mv;
	}
	@PostMapping("/signUp")
	public ModelAndView insert(ModelAndView mv, MemberVo vo) {
		int result = service.insert(vo);
		return mv;
	}
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv) {
		return mv;
	}
	@GetMapping("/testUpdate")
	public ModelAndView update(ModelAndView mv, MemberVo vo) {
		vo.setEmail("user6@test.com");
		vo.setId("user6");
		vo.setPasswd("user6");
		service.update(vo);
		return mv;
	}
//	@PostMapping("/update")
//	public ModelAndView update(ModelAndView mv, MemberVo vo) {
//		return mv;
//	}
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv) {
		String id = "aaa";
		service.delete(id);
		return mv;
	}
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv, String id) {
		return mv;
	}
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv) {
		List<MemberVo> result = service.selectList();
		return mv;
	}
}
