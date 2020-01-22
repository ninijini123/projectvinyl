package com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;
import com.homedream.service.OrderListService;

public class AdminOrderDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderno1 = request.getParameter("orderno");
		int orderno = Integer.parseInt(orderno1);
		System.out.println("orderno:" + orderno);

		OrderListService service = OrderListService.getService();
		service.delete(orderno);

		// forward�� �ѱ��
		ActionForward f = new ActionForward();
		f.setForward(false);// forward�� �������̵� �� send redirect�� ��
		f.setUrl("adminorderlist.do");// ��� �ٷ� .do��

		return f;

	}

}
