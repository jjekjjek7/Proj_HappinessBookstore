package com.Command.Admin.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;

public class AdminMemberModifyCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		
		String birth = request.getParameter("birthYear")+" "+request.getParameter("birthMonth")+" "+request.getParameter("birthDay");
		dto.setBirth(birth);
		dto.setEmail(request.getParameter("email"));
		
		String phone = request.getParameter("firstPhone")+" "+request.getParameter("midPhone")+" "+request.getParameter("lastPhone");
		dto.setPhone(phone);
		
		dto.setPostcode(request.getParameter("postcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setAddress_detail(request.getParameter("address_detail"));
		
		dao.updateDAO(dto);
	}

}
