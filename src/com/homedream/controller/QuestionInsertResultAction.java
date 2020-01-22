package com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;
import com.homedream.dto.QuestionDTO;
import com.homedream.service.QuestionService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QuestionInsertResultAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		
		int filesize=1024*1024*10; // ���Ͽö󰡴� ������
		String uploadpath=request.getServletContext().getRealPath("upload");
		MultipartRequest multi=new MultipartRequest(request, uploadpath, filesize, "utf-8", new DefaultFileRenamePolicy());
		
		String file=multi.getFilesystemName("uploadfile");
		
		request.setAttribute("file", file);
		
		String title=multi.getParameter("title");
		String content=multi.getParameter("content");
		
		//session�� �����Ϸ��� Action���� �����ϸ�ȴ�.

		HttpSession session=request.getSession();
		      String mem_no=(String)session.getAttribute("userId");
		      ActionForward f=new ActionForward();
		      if(mem_no==null)   //������ ������ �α���ȭ������ �Ѿ��
		      {
		         f.setForward(false);
		         f.setUrl("login.do");
		         
		      }
		      else   //id!=null, �� ���̵� ������..
		      {
		    	  QuestionDTO dto=new QuestionDTO();
		  		  dto.setTitle(title);
		  		  dto.setContent(content);
		  		  dto.setFilename(file);
		  		
		  		  QuestionService service=QuestionService.getService();
		  		service.getInsertUpload(dto,mem_no);
		         
		         
		         f.setForward(false);
		         f.setUrl("questionlist.do");
		      }



		//���ǰ��� �����ϰ� ������ 
		//request.setAttribute("session", Integer.parseInt(mem_no));
		//���� session���� request���� ȣ���ϸ� ��. (session���� mem_no��)
		
		
		
		/*forward.setForward(true);
		forward.setUrl("questionlist.do");*/
		return forward;
	}

}
