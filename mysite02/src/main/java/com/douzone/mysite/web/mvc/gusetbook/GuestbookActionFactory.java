package com.douzone.mysite.web.mvc.gusetbook;

import com.douzone.mysite.web.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("List".equals(actionName)) {
			action = new ListAction();
		}else if("insert".equals(actionName)){
			action = new AddAction();
		}else if("deleteform".equals(actionName)){
			action = new DeleteformAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else {
			action = new MainAction();	
		}
		return action;
	}

}