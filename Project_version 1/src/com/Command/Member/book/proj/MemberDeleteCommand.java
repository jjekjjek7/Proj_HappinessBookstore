package com.Command.Member.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;

public class MemberDeleteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO dao = MemberDAO.getMemberDAO();
		
		
		if(session.getAttribute("id").equals("admin")) {
			dao.deleteDAO(request.getParameter("id"));
			request.setAttribute("errMSG", "삭제가 완료되었습니다.");
			request.setAttribute("url", "/Project/view_admin/member/member_List.do");
		} else {
			String id = (String)session.getAttribute("id");
			String pw = request.getParameter("pw");
			
			if(dao.loginDAO(id, pw)) {
				dao.deleteDAO(id);
				request.setAttribute("errMSG", "탈퇴가 완료되었습니다.");
				request.setAttribute("url", "../member/logout.do");
			} else {
				request.setAttribute("errMSG", "비밀번호가 일치하지 않습니다.");
				request.setAttribute("url", "../member/mypage.do");
			}
			
		}
	}

}
