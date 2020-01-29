package ej.com.homedream.controller;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import ej.com.homedream.service.EJOrderService;



public class EJOrderDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderno1=request.getParameter("orderno");
		int orderno=Integer.parseInt(orderno1);
		System.out.println("orderno:"+orderno);
		
		EJOrderService service=EJOrderService.getService();
		service.delete(orderno);
		
		//forward�� �ѱ��
		ActionForward f=new ActionForward();
		f.setForward(false);//forward�� �������̵� �� send redirect�� ��
		f.setUrl("ej_orderlist.do");//��� �ٷ� .do��
		
		return f;
		
	}

}
