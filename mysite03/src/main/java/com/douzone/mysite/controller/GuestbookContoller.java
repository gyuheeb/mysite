package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookContoller {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<GuestbookVo> guestbooklist = guestbookService.getMessageList();
		
		model.addAttribute("guestbooklist", guestbooklist);
		
		return "guestbook/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String list(GuestbookVo vo) {
		System.out.println(vo);
		guestbookService.addMessage(vo);
		System.out.println(vo);
		
		return "redirect:/guestbook";
	}
	
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no,Model model) {
		
		model.addAttribute("no", no);
		
		return "/guestbook/delete";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam("password") String password) {
	
		guestbookService.deleteMessage(no,password);
		return "redirect:/guestbook";

	}
	

}
