package com.Command.MemberChk.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;

public class ChangePWCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		if(id == null) {
			id = request.getParameter("id");
		}
		
		if(request.getParameter("mode") == null) {
			MemberDAO dao = MemberDAO.getMemberDAO();
			if(!dao.loginDAO(id, pw)) {
				request.setAttribute("flag", true);
			}
		} else request.setAttribute("flag", false);
		
		request.setAttribute("id", id);
	}

}
