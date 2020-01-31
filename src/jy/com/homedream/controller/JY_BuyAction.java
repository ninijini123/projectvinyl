package jy.com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;
import jy.com.homedream.service.JY_BuyService;
import jy.com.homedream.service.JY_JN_Service;

public class JY_BuyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		
		
		String memno = request.getParameter("mem_no");
		System.out.println(memno);
		String itemno = request.getParameter("item_no");
		System.out.println(itemno);
		int mem_no = 1;
		int item_no = 1;
	
		// 
		
		if(memno!=null &&!("".equals(memno))) {
			mem_no = Integer.parseInt(memno);
		}
		
		if(itemno!=null &&!("".equals(itemno))) {
			item_no = Integer.parseInt(itemno);
		}
		
		System.out.println(mem_no);
		System.out.println(item_no);
		request.setAttribute("mem_no", mem_no);
		request.setAttribute("item_no", item_no);
		
		forward.setForward(true);
		forward.setUrl("jy_buyresult.do");
		
		return forward;
	}

}
