package com.Command.MemberChk.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;

public class ChangePWOKCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		if(id == null) {
			id = request.getParameter("id");
		}
		
		MemberDAO dao = MemberDAO.getMemberDAO();
		int result = dao.updatePW(pw, id);
		
		if(result > 0) {
			request.setAttribute("errMSG", "비밀번호 변경이 완료되었습니다.");
			request.setAttribute("url", "changePWOK.jsp");
		}
	}

}
