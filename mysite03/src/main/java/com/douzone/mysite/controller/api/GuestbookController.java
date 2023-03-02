package com.douzone.mysite.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;



@RestController("guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	@GetMapping("")
	public JsonResult list() {
		List<GuestbookVo> guestbooklist = guestbookService.getMessageList();
		
		return JsonResult.success(guestbooklist);
		
	}
	@PostMapping("")
	public JsonResult list(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		
		return JsonResult.success(vo);
		
	}
	@DeleteMapping("/{no}")
	public JsonResult delete(
			@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
		boolean result = guestbookService.deleteMessage(no, password);
		return JsonResult.success(result ? no : -1);
	}	
	
	

}
