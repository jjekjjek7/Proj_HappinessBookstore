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

public class EventDAO {
	private static EventDAO dao = new EventDAO();
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "book_event";
	private DataSource dataSource;

	private int sizeOfPage = 2;

	public EventDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/"+CONNECTION_POOL_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static EventDAO getEventDAO() {
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

	public ArrayList<EventDTO> listDAO(int curPage, String state) {
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from "+TABLE_NAME+" WHERE STATE LIKE '%"+state+"%'ORDER BY NO DESC LIMIT "+ (curPage-1) * sizeOfPage + ", " + sizeOfPage;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EventDTO dto = new EventDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setImg_cover(rs.getString("img_cover"));
				dto.setImg_contents(rs.getString("img_contents"));
				dto.setState(rs.getInt("state"));
				dto.setDate(rs.getString("date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}

	public int insertDAO(EventDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into "+TABLE_NAME+" (name, img_cover, img_contents) values (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getImg_cover());
			pstmt.setString(3, dto.getImg_contents());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public EventDTO readDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EventDTO dto = new EventDTO();

		String sql = "select * from "+TABLE_NAME+" where no = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setImg_cover(rs.getString("img_cover"));
				dto.setImg_contents(rs.getString("img_contents"));
				dto.setState(rs.getInt("state"));
				dto.setDate(rs.getString("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
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
	
	public int modifyDAO(int no, int type, String img) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = null;
		
		if(type == 1) sql = "update "+TABLE_NAME+" set img_cover = ? where no = ?";
		else if(type == 2) sql = "update "+TABLE_NAME+" set img_contents = ? where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}	
	
	public int modifyDAO(int no, String img_cover, String img_contents) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update "+TABLE_NAME+" set img_cover = ?, img_contents = ? where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img_cover);
			pstmt.setString(2, img_contents);
			pstmt.setInt(3, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}	
	
	public int stateDAO(int no, int state) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update "+TABLE_NAME+" set state=? where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, state);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int boardCnt(String state) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) FROM "+TABLE_NAME+" WHERE STATE LIKE '%"+state+"%'";
		
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
}
