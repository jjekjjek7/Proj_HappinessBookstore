package com.Command.BoardHelp.book.proj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.QNADAO;
import com.DatabaseBoard.book.proj.QNADTO;

public class HelpMainCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		QNADAO dao = QNADAO.getQNADAO();
		ArrayList<QNADTO> list = null;
		int curPage = 1;
		
		if(session.getAttribute("id").equals("admin")) {
			if(request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}

			String search = "id";
			String search_value = "";
			String state = "";
			
			if(request.getParameter("search_value") == null || request.getParameter("search_value").equals("")) {
				list = dao.listDAO(curPage);
				if(request.getParameter("state") != null) {
					state = request.getParameter("state");
					if(state.equals("all")) state = "";
					list = dao.searchDAO(curPage, search, search_value, state);
				} else list = dao.listDAO(curPage);
			} else {
				state = request.getParameter("state");
				if(state == null || state.equals("all")) state = "";
				search = request.getParameter("search");
				search_value = request.getParameter("search_value");
				
				list = dao.searchDAO(curPage, search, search_value, state);
			}
			
			request.setAttribute("boardCnt", dao.boardCnt(search, search_value, state));
		} else {
			list = dao.listDAO();
		}
		request.setAttribute("sub_title", "1:1 문의내역");
		request.setAttribute("helpList", list);
	}

}
