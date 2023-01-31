package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;



public class ListAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("page");
		String kwd = request.getParameter("kwd");
		request.setAttribute("keyword", kwd);
		List<BoardVo> list;
		
		
		if(kwd == null || kwd =="") {
		
			 list = new BoardDao().findAll();
			 
		}else {
			 list = new BoardDao().Search(kwd);
			 
		}

	      request.setAttribute("list", list);
	      request.setAttribute("page", page);
	
	 
		MvcUtil.forward("board/list",request,response);
		
}
}
