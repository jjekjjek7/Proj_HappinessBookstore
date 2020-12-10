package com.DatabaseProduct.book.proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CartDAO {
	private static CartDAO dao = new CartDAO();
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "book_cart";
	private DataSource dataSource;
	
	public CartDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/"+CONNECTION_POOL_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static CartDAO getCartDAO() {
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
	
	public ArrayList<CartDTO> listDAO(int member_no) {
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE MEMBER_NO = ? ORDER BY NO";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setCover(rs.getString("cover"));
				dto.setPrice(rs.getInt("price"));
				dto.setPoint(rs.getInt("point"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setIsbnNo(rs.getString("isbn"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public CartDTO selectListDAO(int no, int member_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartDTO dto = new CartDTO();
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE NO = ? AND MEMBER_NO = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, member_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setCover(rs.getString("cover"));
				dto.setPrice(rs.getInt("price"));
				dto.setPoint(rs.getInt("point"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setIsbnNo(rs.getString("isbn"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
	}

	public boolean chkDAO(int member_no, String isbn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT NO, CNT FROM "+TABLE_NAME+" WHERE MEMBER_NO = ? AND ISBN = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			pstmt.setString(2, isbn);
			rs = pstmt.executeQuery();
			
			if(rs.next()) return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return true;
	}
	
	public int insertDAO(CartDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into "+TABLE_NAME+"(title, author, cover, price, point, cnt, member_no, isbn) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getCover());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setInt(5, dto.getPoint());
			pstmt.setInt(6, dto.getCnt());
			pstmt.setInt(7, dto.getMember_no());
			pstmt.setString(8, dto.getIsbnNo());
			
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
}
