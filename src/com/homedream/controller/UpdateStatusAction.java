package com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;
import com.homedream.service.OrderListService;




public class UpdateStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("updateActon!!!");
		String status1=request.getParameter("status");//
		String orderno1=request.getParameter("what");//
		System.out.println("status"+status1);
		System.out.println("orderno: "+orderno1);
		int status=0; 
		int orderno=0;
		
		if(status1!=null&&!"".equals(status1))
			status=Integer.parseInt(status1);
		if(orderno1!=null&&!"".equals(orderno1))
			orderno=Integer.parseInt(orderno1);
		
		OrderListService service=OrderListService.getService();
		
		service.update(orderno,status);
		/*
		ActionForward f=new ActionForward();
		f.setForward(true);//forward�� �������̵� 
		f.setUrl("/order/update.jsp");//��� �ٷ� .do��
		//f.setUrl("list.do");
		
		//forward�� setAttributem��
	
		return f;*/
		request.setAttribute("orderno", orderno);
		/*���帮���̷�*/
		ActionForward f=new ActionForward();
		f.setForward(false);//forward�� �������̵� �� send redirect�� ��
		f.setUrl("orderlist.do");//��� �ٷ� .do��
		//��� �Ѱ��� 
		return f;
		
		/*ActionForward f=new ActionForward();
		f.setForward(false);
		f.setUrl("update.do");
		request.setAttribute("orderno", orderno);
		return f;
		*/
	}

}
