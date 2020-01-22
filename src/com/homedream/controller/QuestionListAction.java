package com.homedream.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;
import com.homedream.dto.QuestionDTO;
import com.homedream.service.QuestionService;

public class QuestionListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		QuestionService service=QuestionService.getService();
		//�˻�(����� �������θ� �����ϰ� ����)
		String questionsearchtxt=request.getParameter("questionsearchtxt");
		String questionsearch=request.getParameter("questionsearch");
		
		if(questionsearchtxt==null) {
			questionsearchtxt="";
		}
		if(questionsearch==null) {
			questionsearch="";
		}
		//��ü �� ����
		int totalcount=service.totalQcount(questionsearchtxt,questionsearch);
	   //����¡
		int currpage=1;//1page(null�϶� 1page�� ������)
		String curr=request.getParameter("curr"); //���������� parameter
		if(curr!=null) {
			currpage=Integer.parseInt(curr);//���� �´� ��������..
		}
		
		int pagesize=5; //���������� �����ִ� �ڷ��
		int totalpage=(int)Math.ceil((float)totalcount/pagesize); //��������
		
		int startrow=(currpage-1)*pagesize+1;
		int endrow=startrow+pagesize-1;
		    if(endrow>totalcount) { 
		    	endrow=totalcount;
		    }
		//���������    
		int pageblocksize=5;//��������ϼ�
		int startblock=((currpage-1)/pageblocksize)*pageblocksize+1;
		int endblock=startblock+pageblocksize-1;
		   if(endblock>totalpage)
			   endblock=totalpage; 
		//����Ʈ    
		List<QuestionDTO> list=service.getQlist(startrow,endrow, questionsearch, questionsearchtxt);
		
		request.setAttribute("startblock", startblock);
		request.setAttribute("endblock", endblock);
		request.setAttribute("currpage", currpage);
		request.setAttribute("totalpage", totalpage);
	    
		request.setAttribute("questionsearchtxt", questionsearchtxt);
		request.setAttribute("questionsearch", questionsearch);
		
		request.setAttribute("list", list);
		
		forward.setForward(true);
		forward.setUrl("/communitymain.jsp?page=question/question_list.jsp");
	System.out.println("pagesize"+pagesize);
		return forward;
	}
}
