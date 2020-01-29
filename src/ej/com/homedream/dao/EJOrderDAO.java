package ej.com.homedream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import ej.com.homedream.dto.EJMemberDTO;
import ej.com.homedream.dto.EJOrderDTO;

public class EJOrderDAO {
	
	private static EJOrderDAO dao=new EJOrderDAO();
	public static EJOrderDAO getDAO() {
		
		return dao;
	}//�̱���
	private EJOrderDAO() {}
///////////////////////////////////////////////////////////////////////////////////////////////////

	public int orderCount(Connection conn, String search, String txtsearch
			, int stxtsearch1, int stxtsearch2) throws SQLException {//�ڷ�� ����
		StringBuilder sql=new StringBuilder();
		int count=0;
		ResultSet rs=null;
		
		sql.append(" select count(*) ");
		sql.append(" from orderlist     ");
		//�˻�
		if(!search.equals("")&&!txtsearch.equals(""))
		{
			if(search.equals("orderno"))
				sql.append(" where order_no like ? ");
			else if(search.equals("memno"))
				sql.append(" where mem_no like ? ");
		}
		//�ݾװ˻� 
		if(stxtsearch1!=0&&stxtsearch2!=0)
		{
			sql.append(" and cost between ? and ? ");
		}
		System.out.println("sql:"+sql);
		
	
		try(PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
			if(!search.equals("")&&!txtsearch.equals(""))
			{
			pstmt.setString(1,"%"+txtsearch+"%");//txtsearch�� �����ϴ�
				
			}//else//�˻����Ҷ�
			{
				if(stxtsearch1!=0&&stxtsearch2!=0)//�ݾװ˻��ϸ�
				{
					pstmt.setInt(1, stxtsearch1);
					pstmt.setInt(2, stxtsearch2);
				}
			}
			
			
			 rs=pstmt.executeQuery();
			 if(rs.next()) {
					count=rs.getInt(1);//ù��°��������
			 }
		
		}
		finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
		}
		return count;
	}
//////////////////////////////////////////////////////////////////////////////
	public List<EJOrderDTO> getlist(Connection conn, int startrow, int endrow, String search, String txtsearch
			, int stxtsearch1, int stxtsearch2) throws SQLException {//����Ʈ���
		
		List<EJOrderDTO> list=new ArrayList<>();
		StringBuilder sql=new StringBuilder();
		ResultSet rs=null;
		
		sql.append(" select * from orderlist ");
		
		
		
		if(!search.equals("")&&!txtsearch.equals(""))//�˻��� �ϸ�
		{	
			if(search.equals("orderno"))
				sql.append(" where order_no like ? ");
			else if(search.equals("memno"))
				sql.append(" where mem_no like ? ");
		}
		if(stxtsearch1!=0&&stxtsearch2!=0)//�ݾװ˻��ϸ�
		{
			sql.append(" where cost between ? and ? ");
		}
		
		
			sql.append(" limit ?,15 ");
			
			System.out.println("sql2:"+sql);
			
		try(PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
		
		if(!search.equals("")&&!txtsearch.equals(""))//�˻��ϸ�
		{
			pstmt.setString(1, "%"+txtsearch+"%");
			pstmt.setInt(2, startrow);
			
		}
		else if(stxtsearch1!=0&&stxtsearch2!=0)//�ݾװ˻��ϸ�
		{
			pstmt.setInt(1, stxtsearch1);
			pstmt.setInt(2, stxtsearch2);
			pstmt.setInt(3, startrow);
		}
		else{//�������������� �Ǵ� �˻����ϸ�
			pstmt.setInt(1, startrow);
		}
		
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				EJOrderDTO dto=new EJOrderDTO();
				dto.setOrderno(rs.getInt("order_no"));
				dto.setMemno(rs.getInt("mem_no"));
				dto.setItemno(rs.getInt("item_no"));
				dto.setOrderdate(rs.getString("orderdate"));
				dto.setStatus(rs.getInt("status"));
				dto.setCost(rs.getInt("cost"));
				
				
				list.add(dto);
			}
	}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		return list;
	}
	public void delete(Connection conn, int orderno) throws SQLException {
		StringBuilder sql=new StringBuilder();
		
		System.out.println("orderno:"+orderno);
		sql.append(" delete from orderlist ");
		sql.append(" where order_no=? ");
		try(PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
			pstmt.setInt(1, orderno);
			pstmt.executeUpdate();
			//System.out.println("2222");
		}
	}
	public void update(Connection conn, int orderno, int status) throws SQLException {
		StringBuilder sql=new StringBuilder();
		
    	sql.append(" update orderlist ");
		sql.append(" set status=? ");
		sql.append(" where order_no=? ");
		try(PreparedStatement pstmt=conn.prepareStatement(sql.toString())){
			pstmt.setInt(1, status);
			pstmt.setInt(2, orderno);
			pstmt.executeUpdate();
			
		}
		
	}
	
		
	

}
