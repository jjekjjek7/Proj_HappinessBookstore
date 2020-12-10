package com.DatabaseBoard.book.proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class QNADAO {
	private static QNADAO dao = new QNADAO();
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "book_qna";
	private DataSource dataSource;

	private int sizeOfPage = 5;

	public QNADAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/"+CONNECTION_POOL_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static QNADAO getQNADAO() {
		return dao;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(PreparedStatement pstmt, Connection conn) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<QNADTO> listDAO() {
		ArrayList<QNADTO> list = new ArrayList<QNADTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+TABLE_NAME+" ORDER BY NO DESC";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QNADTO dto = new QNADTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setDate(rs.getString("date"));
				dto.setState(rs.getInt("state"));
				dto.setComment(rs.getString("comment"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public ArrayList<QNADTO> listDAO(int curPage) {
		ArrayList<QNADTO> list = new ArrayList<QNADTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from "+TABLE_NAME+" ORDER BY NO DESC LIMIT "+ (curPage-1) * sizeOfPage + ", " + sizeOfPage;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QNADTO dto = new QNADTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setDate(rs.getString("date"));
				dto.setState(rs.getInt("state"));
				dto.setComment(rs.getString("comment"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public ArrayList<QNADTO> searchDAO(int curPage, String search, String search_value, String state) {
		ArrayList<QNADTO> list = new ArrayList<QNADTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE state LIKE '%"+state+"%' and "+search+" LIKE '%"+search_value+"%' ORDER BY NO DESC LIMIT "+ (curPage-1) * sizeOfPage + ", " + sizeOfPage;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QNADTO dto = new QNADTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setDate(rs.getString("date"));
				dto.setState(rs.getInt("state"));
				dto.setComment(rs.getString("comment"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public int boardCnt(String search, String search_value, String state) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) FROM "+TABLE_NAME+" WHERE state LIKE '%"+state+"%' and "+search+" LIKE '%"+search_value+"%'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result%sizeOfPage == 0 ? result/sizeOfPage : (result/sizeOfPage)+1;
	}
	
	public int insertDAO(QNADTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into "+TABLE_NAME+"(name, id, member_no, type, title, contents) values (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setInt(3, dto.getMember_no());
			pstmt.setString(4, dto.getType());
			pstmt.setString(5, dto.getTitle());
			pstmt.setString(6, dto.getContents());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public QNADTO readDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QNADTO dto = new QNADTO();

		String sql = "select * from "+TABLE_NAME+" where no = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setDate(rs.getString("date"));
				dto.setState(rs.getInt("state"));
				dto.setComment(rs.getString("comment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	public int modifyDAO(QNADTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update "+TABLE_NAME+" set contents=? where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getContents());
			pstmt.setInt(2, dto.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "delete from "+TABLE_NAME+" where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}	
	
	public int commentDAO(QNADTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update "+TABLE_NAME+" set comment=?, state=1 where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
