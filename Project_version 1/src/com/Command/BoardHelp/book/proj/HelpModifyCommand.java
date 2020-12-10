package com.Command.BoardHelp.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.QNADAO;
import com.DatabaseBoard.book.proj.QNADTO;

public class HelpModifyCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QNADAO dao = QNADAO.getQNADAO();
		QNADTO dto = new QNADTO();
		
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setContents(request.getParameter("contents"));
		
		dao.modifyDAO(dto);
	}

}
