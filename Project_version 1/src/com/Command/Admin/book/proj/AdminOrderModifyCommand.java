package com.Command.Admin.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseProduct.book.proj.OrderDAO;

public class AdminOrderModifyCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO dao = OrderDAO.getOrderDAO();
		
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		int state = Integer.parseInt(request.getParameter("state"));
		
		dao.updateState(state, order_no);
	}

}
