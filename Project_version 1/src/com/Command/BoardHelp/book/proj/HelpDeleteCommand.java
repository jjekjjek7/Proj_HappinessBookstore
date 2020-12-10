package com.Command.BoardHelp.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.QNADAO;

public class HelpDeleteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QNADAO dao = QNADAO.getQNADAO();
		
		dao.deleteDAO(Integer.parseInt(request.getParameter("no")));
	}

}
