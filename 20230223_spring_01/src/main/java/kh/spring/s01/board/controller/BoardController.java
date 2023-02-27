package kh.spring.s01.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.s01.board.model.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public void viewSelectCount(Model model) {
		model.addAttribute("boardCount", service.selectCount());
	}
	
	
}
