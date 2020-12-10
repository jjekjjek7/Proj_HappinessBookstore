package com.Command.BoardHelp.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.QNADAO;
import com.DatabaseBoard.book.proj.QNADTO;
import com.DatabaseMember.book.proj.MemberDAO;

public class HelpReadCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QNADAO qao = QNADAO.getQNADAO();
		QNADTO dto = qao.readDAO(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("readInfo", dto);

		MemberDAO dao = MemberDAO.getMemberDAO();
		request.setAttribute("memberInfo", dao.infoDAO(dto.getMember_no()));
		
		request.setAttribute("sub_title", "1:1 문의내역");
	}

}
