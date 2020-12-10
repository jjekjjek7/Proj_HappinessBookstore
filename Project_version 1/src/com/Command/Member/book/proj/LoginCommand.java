package com.Command.Member.book.proj;

import java.io.IOException
;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;

public class LoginCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO dao = MemberDAO.getMemberDAO();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String chk = request.getParameter("autoidsave");
		
		if(dao.loginDAO(id, pw)) {
			session.setAttribute("id", id);
			session.setAttribute("chk", chk);
			
			if(chk != null) {
				Cookie cookie = new Cookie("id", id);
				cookie.setMaxAge(60*60*24*365);
				response.addCookie(cookie);
			}
		} else {
			request.setAttribute("errMSG", "유효하지 않은 계정입니다.");
			request.setAttribute("url", "login.jsp");
		}
	}
}
