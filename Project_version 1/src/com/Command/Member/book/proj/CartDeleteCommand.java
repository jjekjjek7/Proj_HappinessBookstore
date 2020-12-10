package com.Command.Member.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseProduct.book.proj.CartDAO;

public class CartDeleteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartDAO dao = CartDAO.getCartDAO();
		
		String[] value = request.getParameter("chkArr").split(",");
		
		for(int i=0; i<value.length; i++) {
			if(!value[i].equals("")) dao.deleteDAO(Integer.parseInt(value[i]));
		}
		
	}

}
