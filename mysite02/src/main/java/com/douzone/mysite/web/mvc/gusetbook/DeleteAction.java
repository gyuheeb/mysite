package com.douzone.mysite.web.mvc.gusetbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String num = request.getParameter("no");
		    Long no = Long.parseLong(num);
		    String password = request.getParameter("password");

		    new GuestbookDao().deleteByGuest(no, password);
		    response.sendRedirect(request.getContextPath()+"/guestbook");
	}

}
