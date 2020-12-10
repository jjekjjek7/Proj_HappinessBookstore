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

public class OrderDAO {
	private static OrderDAO dao = new OrderDAO();
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "book_order";
	private DataSource dataSource;
	
	private int sizeOfPage = 5;

	public OrderDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/"+CONNECTION_POOL_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static OrderDAO getOrderDAO() {
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
	
	public ArrayList<OrderDTO> alllistDAO() {
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+TABLE_NAME+" ORDER BY NO";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setNo(rs.getInt("no"));
				dto.setDate(rs.getString("date"));
				dto.setTitle(rs.getString("title"));
				dto.setPayment_type(rs.getInt("payment_type"));
				dto.setCard(rs.getString("card"));
				dto.setInstallment(rs.getInt("installment"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setState(rs.getInt("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setPoint(rs.getInt("point"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	
	public ArrayList<OrderDTO> alllistDAO(int curPage) {
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+TABLE_NAME+" ORDER BY NO DESC LIMIT "+ (curPage-1) * sizeOfPage + ", " + sizeOfPage;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setNo(rs.getInt("no"));
				dto.setDate(rs.getString("date"));
				dto.setTitle(rs.getString("title"));
				dto.setPayment_type(rs.getInt("payment_type"));
				dto.setCard(rs.getString("card"));
				dto.setInstallment(rs.getInt("installment"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setState(rs.getInt("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setPoint(rs.getInt("point"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	
	public ArrayList<OrderDTO> seartchListDAO(int curPage, String search, String search_value, String state) {
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE state LIKE '%"+state+"%' and "+search+" LIKE '%"+search_value+"%' ORDER BY NO DESC LIMIT "+ (curPage-1) * sizeOfPage + ", " + sizeOfPage;
		if(search.equals("id")) {
			sql = "select b.* from book_member a inner join "+TABLE_NAME+" b on a.no = b.member_no and state LIKE '%"+state+"%' and a.id LIKE '%"+search_value+"%'";
		}
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setNo(rs.getInt("no"));
				dto.setDate(rs.getString("date"));
				dto.setTitle(rs.getString("title"));
				dto.setPayment_type(rs.getInt("payment_type"));
				dto.setCard(rs.getString("card"));
				dto.setInstallment(rs.getInt("installment"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setState(rs.getInt("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setPoint(rs.getInt("point"));
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
		if(search.equals("id")) {
			sql = "select COUNT(*) from book_member a inner join "+TABLE_NAME+" b on a.no = b.member_no and state LIKE '%"+state+"%' and a.id LIKE '%"+search_value+"%'";
		}
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
	
	public ArrayList<OrderDTO> listDAO(int member_no) {
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
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
				OrderDTO dto = new OrderDTO();
				dto.setNo(rs.getInt("no"));
				dto.setDate(rs.getString("date"));
				dto.setTitle(rs.getString("title"));
				dto.setPayment_type(rs.getInt("payment_type"));
				dto.setCard(rs.getString("card"));
				dto.setInstallment(rs.getInt("installment"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setState(rs.getInt("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setPoint(rs.getInt("point"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	
	public OrderDTO searchlistDAO(int no, int member_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderDTO dto = new OrderDTO();
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE NO = ? AND MEMBER_NO = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, member_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setDate(rs.getString("date"));
				dto.setTitle(rs.getString("title"));
				dto.setPayment_type(rs.getInt("payment_type"));
				dto.setCard(rs.getString("card"));
				dto.setInstallment(rs.getInt("installment"));
				dto.setMember_no(rs.getInt("member_no"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setState(rs.getInt("state"));
				dto.setPrice(rs.getInt("price"));
				dto.setPoint(rs.getInt("point"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	public int insertDAO(OrderDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into "+TABLE_NAME+"(title, payment_type, card, member_no, name, phone, email, state, price, point) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setInt(2, dto.getPayment_type());
			pstmt.setString(3, dto.getCard());
			pstmt.setInt(4, dto.getMember_no());
			pstmt.setString(5, dto.getName());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getEmail());
			pstmt.setInt(8, dto.getState());
			pstmt.setInt(9, dto.getPrice());
			pstmt.setInt(10, dto.getPoint());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int maxNO() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT MAX(NO) FROM "+TABLE_NAME;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("MAX(NO)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateState(int state, int order_no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update "+TABLE_NAME+" set state=? where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, state);
			pstmt.setInt(2, order_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
