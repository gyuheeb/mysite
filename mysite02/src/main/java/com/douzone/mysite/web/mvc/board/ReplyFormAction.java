package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
	    String contents = request.getParameter("content");
		String reg_date = request.getParameter("reg_date");
		Long depth= Long.parseLong(request.getParameter("depth"));
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		
		
		BoardVo vo = new BoardVo();
		vo.setUser_no(userNo);
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setReg_date(reg_date);
		vo.setDepth(depth);

		
//	    new BoardDao().depthinsert(vo);
	    MvcUtil.redirect(request.getContextPath()+"/board?a=list", request, response);
		

	}

}
