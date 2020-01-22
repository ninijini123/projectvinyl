package com.homedream.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.homedream.comm.DBConnection;
import com.homedream.dao.AnswerDAO;
import com.homedream.dao.QuestionDAO;
import com.homedream.dto.AnswerDTO;
import com.homedream.dto.QuestionDTO;

public class QuestionService {
	private static QuestionService service=new QuestionService();
	
	public static QuestionService getService() {
		return service;
	}
	private QuestionService() {
		
	}
	public int totalQcount(String questionsearchtxt, String questionsearch) {
		// TODO Auto-generated method stub
		int totalcount=0;
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			QuestionDAO dao=QuestionDAO.getDAO();
			totalcount=dao.totoalQcount(conn, questionsearchtxt, questionsearch);
			conn.commit();
		}catch(NamingException|SQLException e ) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return totalcount;
	}
	public List<QuestionDTO> getQlist(int startrow, int endrow, String questionsearch, String questionsearchtxt) {
		// TODO Auto-generated method stub
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		List<QuestionDTO> list=new ArrayList<>();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			QuestionDAO dao=QuestionDAO.getDAO();
			list=dao.getQlist(conn,startrow,endrow,questionsearch, questionsearchtxt);
			conn.commit();
		}catch(NamingException|SQLException e){
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return list;
	}
	public void getInsertUpload(QuestionDTO dto, String mem_no) {
		// TODO Auto-generated method stub
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			QuestionDAO dao=QuestionDAO.getDAO();
			dao.insertUpload(conn,dto,mem_no);
			conn.commit();
		}catch(NamingException | SQLException e) {
			System.out.println(e);
			try {conn.rollback();}catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
	public QuestionDTO detailSelect(int q_no) {
		// TODO Auto-generated method stub
		QuestionDTO dto=new QuestionDTO();
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			QuestionDAO dao=QuestionDAO.getDAO();
			dto=dao.detailSelect(conn,q_no);
			dao.readNoUpdate(conn,q_no);
			conn.commit();
		}catch(NamingException | SQLException e) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return dto;
	}
	public void questionUpdate(QuestionDTO dto) {
		// TODO Auto-generated method stub	
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			QuestionDAO dao=QuestionDAO.getDAO();
			dao.questionUpdate(conn,dto);
			conn.commit();
		}catch(NamingException | SQLException e) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
	public void questionDelete(int q_no) {
		// TODO Auto-generated method stub
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			QuestionDAO dao=QuestionDAO.getDAO();
			dao.questionDelete(conn,q_no);
			System.out.println("dao"+q_no);
			conn.commit();
		}catch(NamingException | SQLException e) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
	public String getAnswerAdd(AnswerDTO dto) {
		// TODO Auto-generated method stub
		int mem_no=dto.getMem_no();
		String answerid=null;
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			AnswerDAO dao=AnswerDAO.getAnswerDAO();
			dao.getAnswerAdd(conn,dto);
			answerid=dao.answerID(conn,mem_no);
			System.out.println("ȭ�� �亯���̵�" + answerid);
			conn.commit();
		}catch(NamingException | SQLException e) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return answerid;
	}
	public List<AnswerDTO> getDetailList(int no) {
		// TODO Auto-generated method stub
		List<AnswerDTO> list=new ArrayList<>();
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			AnswerDAO dao=AnswerDAO.getAnswerDAO();
			list=dao.getDetailList(conn,no);
			conn.commit();
		}catch(NamingException | SQLException e) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return list;
	}
	public void getAnswerDelete(int a_no) {
		// TODO Auto-generated method stub
		Connection conn=null;
		DBConnection db=DBConnection.getInstance();
		try {
			conn=db.getConnection();
			conn.setAutoCommit(false);
			AnswerDAO dao=AnswerDAO.getAnswerDAO();
			dao.getAnswerDelete(conn,a_no);
			conn.commit();
		}catch(NamingException | SQLException e) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
}
