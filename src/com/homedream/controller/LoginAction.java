package com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ActionForward f=new ActionForward();
		String id=(String)session.getAttribute("userId");
		if(session.getAttribute("userId")==null)	//�α��� ���°� �ƴҶ�
		{			
		f.setForward(true);
		f.setUrl("/communitymain.jsp?page=loginform.jsp");
		}
		/*else if(id.equals("admin"))	//admin���� �α����Ҷ�
		{
			f.setForward(true);
			f.setUrl("/memberadminmain.jsp");
		}
		
		else	//ȸ���α��� �Ҷ�
		{
			f.setForward(false);
			f.setUrl("homedream.do");
		}*/
		return f;
	}

}
