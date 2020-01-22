package jy.com.homedream.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import jy.com.homedream.DTO.JY_JN_BoardDTO;
import jy.com.homedream.service.JY_JN_Service;

public class JY_JN_ListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String search = request.getParameter("search");
		String searchtxt = request.getParameter("searchtxt");
		
		if (search==null) {
			search="";
		} 
		
		if(searchtxt==null) {
			searchtxt="";
		}
		
		String sorting = request.getParameter("sorting");	
		
		if(sorting==null) {
			sorting="";
		}
		
		
		JY_JN_Service service = JY_JN_Service.getService();
		
		int totalconut = service.jn_getTotalCount(search,searchtxt);
		System.out.println(totalconut);
		
		int totalpage = (int)Math.ceil((double)totalconut/15);
		System.out.println(totalpage);
		
		int pagepersize = 15;
		
		int currpage = 1;
		// ù �������� null �϶� �̰� ������
		// null�� �ƴϸ� ������ ������ ���� ����
		
		// int currpage = Integer.parseInt(request.getParameter("curr"));
		// �̰� �ȵǴ� ���� : �������� null�϶�(ù�� �������� null�� > list.do�� ����)�� null�� ���ͼ� ���� �߻�
		
		String curr = request.getParameter("curr");
		// ���� �������� �޾ƿ�
		if(curr!=null) {
			currpage = Integer.parseInt(curr);
			// ��, �ΰ��϶��� 1�������� ������ �ΰ��� �ƴҶ� ������ ������ ���� ����
		}
		
		System.out.println("currpage = " + currpage);
	
		
		
		int startrow = (currpage-1)*pagepersize +1; // �� �������� startrow > 1�������� 1���� 2 �������� 4�� ��
		int endrow = startrow+pagepersize-1; // �� �������� endrow > 1�������� 3�� ��, 2 �������� 6����
		
		if(endrow > totalconut) {
			endrow = totalconut;
		}
		
		// ������ ��
		
		// �������� �ѹ��� ������ ������ ��ȣ ����
		int pageblocksize = 5;
		// ù��° �� ��ȣ > 1, 6, 11
		int startblock = ((currpage-1)/pageblocksize)*pageblocksize+1;
		// ������ �� ��ȣ ? 6, 10, 15
		int endblock = startblock+pageblocksize-1;
		if(endblock>totalpage) {
			endblock = totalpage;
		}
		
		request.setAttribute("startblock", startblock);
		request.setAttribute("endblock", endblock);
		request.setAttribute("currpage", currpage);
		request.setAttribute("totalpage", totalpage);
		
		request.setAttribute("search", search);
		request.setAttribute("searchtxt", searchtxt);
		request.setAttribute("sorting", sorting);


		
        List<JY_JN_BoardDTO> jn_list = service.jn_getList(search, searchtxt,startrow, endrow,sorting);
		
		request.setAttribute("jn_list", jn_list);
		
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setUrl("/hs_communitymain.jsp?page=jy_jn/jy_jn_list.jsp");
		
		return forward;
	}

}
