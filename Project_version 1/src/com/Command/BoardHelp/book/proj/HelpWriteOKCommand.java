package com.Command.BoardHelp.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.QNADAO;
import com.DatabaseBoard.book.proj.QNADTO;

public class HelpWriteOKCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QNADAO dao = QNADAO.getQNADAO();
		QNADTO dto = new QNADTO();
		
		dto.setName(request.getParameter("name"));
		dto.setId(request.getParameter("id"));
		dto.setMember_no(Integer.parseInt(request.getParameter("member_no")));
		dto.setType(request.getParameter("type"));
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		dto.setDate(request.getParameter("date"));
		
		dao.insertDAO(dto);
	}

}
