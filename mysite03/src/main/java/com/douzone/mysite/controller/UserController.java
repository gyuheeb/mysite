package com.douzone.mysite.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	@RequestMapping( value="/join", method=RequestMethod.POST )
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model ) {
			
	   if ( result.hasErrors() ) {
	    //   model.addAttribute("UserVo", vo);
	       model.addAllAttributes(result.getModel());
	       return "/user/join";
	   }
			
	   userService.join(vo);
	   return "redirect:/user/joinsuccess";
	}

	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@Auth
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, UserVo vo, Model model){
		UserVo authUser = userService.getUser(vo);
		
		if(authUser == null) {
			model.addAttribute("email",vo.getEmail());
			return"user/login"; //다시 로그인페이지
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/";   //main으로...
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
//	@Auth
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(@AuthUser UserVo authUser,Model model) {
//		//Access Control
//		 UserVo userVo = userService.getUser(authUser.getNo());
//		model.addAttribute("userVo",userVo);
//		return "user/update";
//		
//	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session ,UserVo vo) {
		//Access Control
		 UserVo authUser = (UserVo)session.getAttribute("authUser");
		 if(authUser ==null) {
			 return "redirect:/";
		 }
		/////////////////////ss제엍ㄴ?///////////////////////////
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		
		authUser.setName(vo.getName());
		return"redirect:/user/update";
	}
	
	
}
