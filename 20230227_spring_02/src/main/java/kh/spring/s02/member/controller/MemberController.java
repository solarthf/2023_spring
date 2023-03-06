package kh.spring.s02.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.s02.member.model.service.MemberService;
import kh.spring.s02.member.model.vo.MemberVo;

@Controller
@RequestMapping("/member") 
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv) {
		// url 주소, jsp 이름
		mv.setViewName("member/signUp");
		return mv;
	}
	@PostMapping("/signUp")
	public ModelAndView insert(ModelAndView mv, MemberVo vo, RedirectAttributes rttr) {
		int result = -1;
		try {
			result = service.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			// 회원가입성공
			// 방법 1 - 사용불가(한글 ???라고 나옴)
//			mv.setViewName("redirect:/?msg=회원가입성공");
			// 방법 2
//			mv.addObject("msg", "회원가입성공");
//			mv.setViewName("error/errorFailure");
			// 방법 3 - Spring에서만(RedirectAttributes) 일회성 사용으로 f5를 눌러도 다시 나오지 않는다.
			rttr.addFlashAttribute("msg", "회원가입성공~"); // controller가 아닌 jsp로 간다. 이 경우 home.jsp로 간다.
			mv.setViewName("redirect:/");			
		} else {
			// 회원가입실패
			rttr.addFlashAttribute("msg", "회원가입실패~"); // controller가 아닌 jsp로 간다. 이 경우 signUp.jsp로 간다.
			mv.setViewName("redirect:/member/signUp");
		}
		return mv;
	}
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv, String id) {
		MemberVo vo = service.selectOne(id);
		mv.addObject("membervo", vo);
		mv.setViewName("/member/update");
		return mv;
	}
//	@GetMapping("/testUpdate")
//	public ModelAndView update(ModelAndView mv, MemberVo vo) {
//		vo.setEmail("user6@test.com");
//		vo.setId("user6");
//		vo.setPasswd("user6");
//		service.update(vo);
//		return mv;
//	}
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv, MemberVo vo) {
		service.update(vo);
		return mv;
	}
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv) {
		String id = "aaa";
		service.delete(id);
		return mv;
	}
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv, String id) {
		if(id == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		MemberVo result = service.selectOne(id);
		return mv;
	}
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv) {
		List<MemberVo> result = service.selectList();
		return mv;
	}
}
