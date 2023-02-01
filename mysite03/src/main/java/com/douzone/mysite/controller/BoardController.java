package com.douzone.mysite.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;



@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
//	
//	@RequestMapping("/")
//	public String list(Model model) {
//		Map<String, Object> map = boardService.list();
//		
//		model.addAttribute("map", map);
//		// model.addAllAttributes(map);
//		
//		return "board/list";
//	}
	
	@RequestMapping("")
	public String list(Model model) {
		
		List<BoardVo> boardlist = boardService.list();
		model.addAttribute("list", boardlist);
		return "board/list";
	}

	@RequestMapping(value="/view/{no}", method=RequestMethod.POST)
	public String view(@PathVariable("no") Long no) {
		
		boardService.getContents(no);
		
		return "redirect:/board";
	}
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no") Long no,Model model) {
		BoardVo boardvo = boardService.getContents(no);
		
		System.out.println(boardvo);
		model.addAttribute("boardvo",boardvo);
		return "board/view";
	}
	
//	@RequestMapping(value="/write", method=RequestMethod.GET)
//	public String list(BoardVo vo) {
//		boardService.addContents();
//		
//		return "board/write";
//	}
}
