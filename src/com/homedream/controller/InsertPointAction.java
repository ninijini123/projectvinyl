package com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import  com.homedream.service. MemberService;

public class InsertPointAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String addpoint1=request.getParameter("addpoint");//��� insert.jsp��������
		String memno1=request.getParameter("what");//list jsp���� ������
		int addpoint=0; 
		int memno=0;
		
		if(addpoint1!=null&&!"".equals(addpoint1))
			addpoint=Integer.parseInt(addpoint1);
		if(memno1!=null&&!"".equals(memno1))
			memno=Integer.parseInt(memno1);
		System.out.println("�μ�Ʈ �׼ǿ��� point"+addpoint);
		System.out.println(" �μ�Ʈ �׼ǿ��� memno: "+memno);
		MemberService service=MemberService.getService();
		//service.update(memno);
		service.insert(memno,addpoint);
		
		/*ActionForward f=new ActionForward();
		f.setForward(true);*///forward�� �������̵� 
		/*f.setUrl("/member/insert.jsp");*///��� �ٷ� .do��
		/*f.setUrl("list.do");*/
		/*f.setUrl("/member/list.jsp");*/
		//��� �Ѱ��� 
		//forward�� setAttributem��
	
		/*return f;*/
		ActionForward f=new ActionForward();
		f.setForward(false);//forward�� �������̵� �� send redirect�� ��
		f.setUrl("memberlist.do");//��� �ٷ� .do��
		request.setAttribute("memNo", memno1);
		return f;
		
	}

}
