package ej.com.homedream.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.homedream.comm.DBConnection;


import ej.com.homedream.dao.EJOrderDAO;
import ej.com.homedream.dto.EJOrderDTO;




public class EJOrderService {

	private static EJOrderService service=new EJOrderService();
	public static EJOrderService getService() {
		
		return service;
	}//�̱���
	
	private EJOrderService() {}
	//////////////////////////////////////////////////////////////
	public int getCount(String search, String txtsearch, int txtsearch1, int txtsearch2)
	{//connection���� dao�� �Ѱ�
		
		DBConnection db=DBConnection.getInstance();//dbconnection�� �޾�
		Connection conn=null;
		int count=0;
		try{
			conn=db.getConnection();
			conn.setAutoCommit(false);
			EJOrderDAO dao=EJOrderDAO.getDAO();//�̱��������ϱ� �̷���
			count=dao.orderCount(conn, search, txtsearch, txtsearch1, txtsearch2);
			//System.out.println("count: "+count);
			conn.commit();
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return count;
	}//��ü �ڷ��
	
	public List<EJOrderDTO> getList(int startrow, int endrow, String search, String txtsearch
			, int stxtsearch1, int stxtsearch2) {//���
		Connection conn=null;
		List<EJOrderDTO> list=null;
				
		try {
			DBConnection db=DBConnection.getInstance();
			
			conn=db.getConnection();
			conn.setAutoCommit(false);
			
			EJOrderDAO dao=EJOrderDAO.getDAO();
			list=dao.getlist(conn,startrow, endrow, search, txtsearch, stxtsearch1, stxtsearch2);
			//list�� ������ �޾ƢZ����
			
			conn.commit();
			
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}return list;
		
	}
	

	public void delete(int orderno) {
		Connection conn=null;
	
				
		try {
			DBConnection db=DBConnection.getInstance();
			
			conn=db.getConnection();
			conn.setAutoCommit(false);
			
			EJOrderDAO dao=EJOrderDAO.getDAO();
			
			dao.delete(conn,orderno);
			//list�� ������ �޾ƢZ����
			
			conn.commit();
			
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
	}

		public void update(int orderno, int status) {
		Connection conn=null;
		
		
		try {
			DBConnection db=DBConnection.getInstance();
			
			conn=db.getConnection();
			conn.setAutoCommit(false);
			
			EJOrderDAO dao=EJOrderDAO.getDAO();
			dao.update(conn,orderno, status);
			//list�� ������ �޾ƢZ����
			
			conn.commit();
			
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		
	}
	
		
	}
	


