package com.Command.Member.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;

public class MemberInfoCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO dao = MemberDAO.getMemberDAO();
		
		if(session.getAttribute("id") != null) {
			MemberDTO dto = dao.infoDAO((String)session.getAttribute("id"));
			String[] birth = dto.getBirth().split(" ");
			String[] phone = dto.getPhone().split(" ");
			
			request.setAttribute("birth", birth);
			request.setAttribute("phone", phone);
			request.setAttribute("info", dto);
		}
		request.setAttribute("sub_title", "회원정보");
	}

}
