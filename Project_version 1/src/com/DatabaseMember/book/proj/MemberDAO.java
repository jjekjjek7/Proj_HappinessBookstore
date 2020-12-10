package com.DatabaseMember.book.proj;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemberDAO {
	private static MemberDAO dao = new MemberDAO();
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "book_member";
	private DataSource datasource;
	
	private int sizeOfPage = 5;

	
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource)context.lookup("java:comp/env/"+CONNECTION_POOL_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDAO getMemberDAO() {
		return dao;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
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
	
	public ArrayList<MemberDTO> listDAO(int curPage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "select * from "+TABLE_NAME+" ORDER BY NO LIMIT "+ (curPage-1) * sizeOfPage + ", " + sizeOfPage;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setPoint(rs.getInt("point"));
				if(rs.getString("postcode") != null) {
					dto.setPostcode(rs.getString("postcode"));
					dto.setAddress(rs.getString("address"));
					dto.setAddress_detail(rs.getString("address_detail"));
				}
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public ArrayList<MemberDTO> searchDAO(int curPage, String search, String search_value) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+search+" LIKE '%"+search_value+"%' ORDER BY NO LIMIT "+ (curPage-1) * sizeOfPage + ", " + sizeOfPage;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setPoint(rs.getInt("point"));
				if(rs.getString("postcode") != null) {
					dto.setPostcode(rs.getString("postcode"));
					dto.setAddress(rs.getString("address"));
					dto.setAddress_detail(rs.getString("address_detail"));
				}
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public int boardCnt(String search, String search_value) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) FROM "+TABLE_NAME+" WHERE "+search+" LIKE '%"+search_value+"%'";
		
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
	
	public boolean loginDAO(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from "+TABLE_NAME+" where id=? and pw=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return false;
	}
	
	public int insertDAO(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into "+TABLE_NAME+"(id, name, pw, birth, email, phone) values (?, ?, ?, ?, ?, ?)";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return result;
	}
	
	public MemberDTO infoDAO(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO dto = new MemberDTO();
		String sql = "select * from "+TABLE_NAME+" where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setPoint(rs.getInt("point"));
				if(rs.getString("postcode") != null) {
					dto.setPostcode(rs.getString("postcode"));
					dto.setAddress(rs.getString("address"));
					dto.setAddress_detail(rs.getString("address_detail"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	public MemberDTO infoDAO(int member_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO dto = new MemberDTO();
		String sql = "select * from "+TABLE_NAME+" where no=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setPoint(rs.getInt("point"));
				if(rs.getString("postcode") != null) {
					dto.setPostcode(rs.getString("postcode"));
					dto.setAddress(rs.getString("address"));
					dto.setAddress_detail(rs.getString("address_detail"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	public int updateDAO(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update "+TABLE_NAME+" set birth=?, email=?, phone=?, postcode=?, address=?, address_detail=? where id = ?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getBirth());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getPostcode());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getAddress_detail());
			pstmt.setString(7, dto.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return result;
	}
	
	public int updatePW(String pw, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update "+TABLE_NAME+" set pw=? where id = ?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return result;
	}
	
	public void deleteDAO(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from "+TABLE_NAME+" where id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	
	public String searchID(String name, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		String sql = "select id from "+TABLE_NAME+" where name = ? and email = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return id;
	}
	
	public MemberDTO searchChange(String id, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO dto = new MemberDTO();
		String sql = "select * from "+TABLE_NAME+" where id = ? and email = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setPoint(rs.getInt("point"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return dto;
	}

	public int updatePoint(int member_no, int point) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "update "+TABLE_NAME+" set point = point + ? where no = ?";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, point);
			pstmt.setInt(2, member_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return result;
	}
	

}
