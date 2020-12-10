package com.Command.Admin.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;

public class AdminMemberInfoCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		
		MemberDTO dto = dao.infoDAO(request.getParameter("id"));
		String[] birth = dto.getBirth().split(" ");
		String[] phone = dto.getPhone().split(" ");
	
		request.setAttribute("birth", birth);
		request.setAttribute("phone", phone);
		request.setAttribute("info", dto);
		request.setAttribute("sub_title", "회원관리");
	}

}
