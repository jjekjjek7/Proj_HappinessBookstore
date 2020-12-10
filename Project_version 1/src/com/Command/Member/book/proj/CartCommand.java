package com.Command.Member.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;
import com.DatabaseProduct.book.proj.CartDAO;

public class CartCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO mao = MemberDAO.getMemberDAO();
		MemberDTO mto = mao.infoDAO((String)session.getAttribute("id"));
		
		CartDAO dao = CartDAO.getCartDAO();

		request.setAttribute("cartList", dao.listDAO(mto.getNo()));
		request.setAttribute("sub_title", "장바구니");
	}

}
