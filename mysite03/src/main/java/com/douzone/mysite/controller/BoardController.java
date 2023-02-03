package com.douzone.mysite.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;




@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	//리스트
	@RequestMapping("/page={page}&kwd={keyword}")
	public String list(@PathVariable("page") Long page,@PathVariable("keyword") String keyword,Model model) {
		
		List<BoardVo> boardlist = boardService.list();
		model.addAttribute("list", boardlist);
		
		Map<String, Object> map = boardService.getContentsList(1, "");
		model.addAttribute("map", map);
		model.addAllAttributes(map);
		
		return "board/list";
	}
 

	//자세히 보기
	@RequestMapping(value="/view/{no}", method= {RequestMethod.GET,RequestMethod.POST})
	public String view(@PathVariable("no") Long no,Model model) {
//		BoardVo authUser = (BoardVo)session.getAttribute("authUser");
		BoardVo boardvo = boardService.getContents(no);
		boardService.viewCount(no);
		model.addAttribute("boardvo",boardvo); 
		return "board/view";
	}
	
	//수정
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String update(@PathVariable("no") Long no,Model model) {
		BoardVo boardvo = boardService.getContents(no);
		model.addAttribute("boardvo",boardvo); 
		System.out.println("리스트"+boardvo);
		return "board/modify";
	}

	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String update(@PathVariable("no") Long no, BoardVo vo) {
		boardService.upadateContents(vo);
		 return "redirect:/board/view/"+no;
	}
	
	//삭제
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no, BoardVo vo) {
		  System.out.println(no);
		  boardService.deleteContents(no);
		 return "redirect:/board";
	}
	//글쓰기
	@RequestMapping(value="/write/{no}", method=RequestMethod.GET)
	public String write(@PathVariable("no") Long no,Model model) {
		model.addAttribute("no",no); 
		System.out.println(no);
		return "board/write";
	}
	
	@RequestMapping(value="/write/{no}", method=RequestMethod.POST)
	public String write(HttpSession session,BoardVo vo,@PathVariable("no") Long no) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");	
		System.out.println(no);
		Long Max = boardService.findMax();
		if(no == -1) {
			vo.setG_no(Max+1);
			vo.setDepth(0L);
			vo.setO_no(1L);
		}else {
			BoardVo aa = boardService.getContents(no);
			boardService.updateCount(aa.getG_no(),aa.getO_no());
		
			vo.setG_no(Max);
			vo.setO_no(aa.getO_no()+1);
			vo.setDepth(aa.getDepth()+1);
		} 
		vo.setUser_no(authUser.getNo());
		boardService.addContents(vo);
		return "redirect:/board";
	}
	
}
