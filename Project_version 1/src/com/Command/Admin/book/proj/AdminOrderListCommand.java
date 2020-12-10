package com.Command.Admin.book.proj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseProduct.book.proj.OrderDAO;
import com.DatabaseProduct.book.proj.OrderDTO;

public class AdminOrderListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO dao = OrderDAO.getOrderDAO();
		ArrayList<OrderDTO> list = null;
		int curPage = 1;
		
		if(request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		String search = "name";
		String search_value = "";
		String state = "";
		if(request.getParameter("search_value") == null || request.getParameter("search_value").equals("")) {
			if(request.getParameter("state") != null) {
				state = request.getParameter("state");
				if(state.equals("all")) state = "";
				list = dao.seartchListDAO(curPage, search, search_value, state);
			} else list = dao.alllistDAO(curPage);
		} else {
			state = request.getParameter("state");
			if(state.equals("all")) state = "";
			search = request.getParameter("search");
			search_value = request.getParameter("search_value");
			list = dao.seartchListDAO(curPage, search, search_value, state);
		}
		
		request.setAttribute("boardCnt", dao.boardCnt(search, search_value, state));
		request.setAttribute("orderList", list);
		request.setAttribute("sub_title", "주문관리");
	}

}
