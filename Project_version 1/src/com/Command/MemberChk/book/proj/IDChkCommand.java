package com.Command.MemberChk.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;

public class IDChkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = dao.infoDAO(id);

		request.setAttribute("id", id);
		
		if(dto.getId() == null) {
			request.setAttribute("flag", true);
		} else {
			request.setAttribute("flag", false);
		}
	}

}
