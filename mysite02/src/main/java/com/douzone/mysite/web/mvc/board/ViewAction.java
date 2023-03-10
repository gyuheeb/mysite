package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
	    String contents = request.getParameter("contents");
	    Long no=Long.parseLong(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
	
		BoardVo aa=new BoardDao().findNo(no);
		new BoardDao().viewCount(no);
		request.setAttribute("vo", aa);
		
		MvcUtil.forward("/board/view", request, response);
	   

	}

}
