package com.Command.BoardEvent.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.EventDAO;

public class EventContentCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventDAO dao = EventDAO.getEventDAO();

		int no = Integer.parseInt(request.getParameter("no"));
		request.setAttribute("dto", dao.readDAO(no));
	}

}
