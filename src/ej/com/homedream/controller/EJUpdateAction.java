package ej.com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import ej.com.homedream.service.EJOrderService;



public class EJUpdateAction implements Action {

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
		
		EJOrderService service=EJOrderService.getService();
		
		service.update(orderno,status);
		/*
		ActionForward f=new ActionForward();
		f.setForward(true);//forward�� �������̵� 
		f.setUrl("/ej_order/ej_update.jsp");//��� �ٷ� .do��
		//f.setUrl("ej_list.do");
		
		//forward�� setAttributem��
	
		return f;*/
		request.setAttribute("orderno", orderno);
		/*���帮���̷�*/
		ActionForward f=new ActionForward();
		f.setForward(false);//forward�� �������̵� �� send redirect�� ��
		f.setUrl("ej_orderlist.do");//��� �ٷ� .do��
		//��� �Ѱ��� 
		return f;
		
		/*ActionForward f=new ActionForward();
		f.setForward(false);
		f.setUrl("ej_update.do");
		request.setAttribute("orderno", orderno);
		return f;
		*/
	}

}
