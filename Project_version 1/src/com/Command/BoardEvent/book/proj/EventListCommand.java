package com.Command.BoardEvent.book.proj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.EventDAO;
import com.DatabaseBoard.book.proj.EventDTO;

public class EventListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int curPage = 1;
		if(request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		String state = "";
		if(session.getAttribute("id") == null || !session.getAttribute("id").equals("admin")) {
			state = "0";
		} else if(request.getParameter("state") != null && !request.getParameter("state").equals("all")) {
			state = request.getParameter("state");
		}
		
		EventDAO dao = EventDAO.getEventDAO();
		ArrayList<EventDTO> list = null;
		
		list = dao.listDAO(curPage, state);
		
		request.setAttribute("eventList", list);
		request.setAttribute("boardCnt", dao.boardCnt(state));
	}

}
