package com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;
import com.homedream.dto.ExpertDTO;
import com.homedream.service.ExpertService;

public class ExpertManagerDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num = request.getParameter("no");

		int no = 1;
		if(num!=null&&!("".equals(num))) {
			no = Integer.parseInt(num);
		}

		ExpertService service = ExpertService.getService();
		ExpertDTO dto = service.getSelect(no);

		request.setAttribute("dto", dto);
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setUrl("/communityadminmain.jsp?page=expert/managerdetail.jsp");


		return forward;
	}

}