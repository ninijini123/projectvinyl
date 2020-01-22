package yi.com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;



public class HomedreamAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�α��� ȸ������ ������������ �����ϴ� �׼�
	HttpSession session=request.getSession();
	ActionForward f=new ActionForward();
	if(session.getAttribute("userId")==null)	//������ ������ -> �α��ΰ� ȸ������ ���� ������
	{
		
		f.setForward(true);
		f.setUrl("/hs_communitymain.jsp");
	
	}
	else if(session.getAttribute("userId").equals("1"))	//������ admin�϶� -> admin ������
	{
		f.setForward(true);
		f.setUrl("/hs_memberadminmain.jsp");
		System.out.println("a2");
	}
	
	else	//������ ȸ���϶� ->ȸ��������
	{
		f.setForward(true);
		f.setUrl("/hs_communitymain.jsp");
		System.out.println("3");
	}
		return f;
		
	}

}
