package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		Long no = request.getParameter("no") == "" ? 0 : Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
	    String contents = request.getParameter("content");
//		String reg_date = request.getParameter("reg_date");
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		
		 
		
		BoardVo vo = new BoardVo();
		
		Long aa = new BoardDao().findMax();
		
		
		if(no == -1) { //게시글
			vo.setG_no(aa+1);
			vo.setO_no(1L);
			vo.setDepth(0L);

		}else { //댓글
			vo= new BoardDao().findNo(no);

			vo.setO_no(vo.getO_no()+1);
			vo.setDepth(vo.getDepth()+1);
			new BoardDao().updateCount(vo.getG_no(),vo.getO_no());
		}
		
		vo.setNo(no);
		vo.setUser_no(userNo);
		vo.setTitle(title);
		vo.setContents(contents);
//		vo.setReg_date(reg_date);
		
	    new BoardDao().insert(vo);
	   	    
	    
	    MvcUtil.redirect(request.getContextPath()+"/board?page=1", request, response);
	}

}
