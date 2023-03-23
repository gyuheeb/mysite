package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public ResponseEntity<JsonResult> joinpage(UserVo userVo) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(userVo));
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<JsonResult> joinsend(@RequestBody UserVo userVo) {
	
		userService.join(userVo);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(userVo));
	}
	
	@GetMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	

	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	
	@Auth
	@GetMapping("/update")
	public ResponseEntity<JsonResult> update(@AuthUser UserVo authUser) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(userService.getUser(authUser.getNo())));
	} 
	@Auth
	@PostMapping("/update")
	public ResponseEntity<JsonResult> update(@AuthUser UserVo authUser, UserVo uservo) {
		
		uservo.setNo(authUser.getNo());
		userService.updateUser(uservo);
		authUser.setName(uservo.getName());
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(userService.getUser(authUser.getNo())));
	}

	@GetMapping("/auth")
	public void auth() {
	}
	
	@GetMapping("/logout")
	public void logout() {
	}
	
}
