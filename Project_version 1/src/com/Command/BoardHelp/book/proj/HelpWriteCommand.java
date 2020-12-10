package com.Command.BoardHelp.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;

public class HelpWriteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = dao.infoDAO((String)session.getAttribute("id"));
		
		request.setAttribute("info", dto);
		request.setAttribute("sub_title", "1:1 문의하기");
	}

}
