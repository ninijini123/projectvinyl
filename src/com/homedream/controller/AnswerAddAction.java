package com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;
import com.homedream.dto.AnswerDTO;
import com.homedream.service.QuestionService;

public class AnswerAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		
		int no = Integer.parseInt(request.getParameter("no"));

		System.out.println("ȭ�� ��� ��ȣ"+no);
		// ��ȣ �����°� Ȯ�ε�
		
		String answer=request.getParameter("answer");
		System.out.println("ȭ�� ��� ����"+answer);
		
		AnswerDTO dto=new AnswerDTO();
		
		dto.setQ_no(no);
		dto.setMem_no(2);
		dto.setContent(answer);
		
		QuestionService service=QuestionService.getService();
		
		String answerid=service.getAnswerAdd(dto);
		
		request.setAttribute("answer", answerid);
		
		forward.setForward(true);
		forward.setUrl("questiondetail.do?no="+no);
		
		return forward;
	}

}