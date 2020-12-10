package com.Command.Member.book.proj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;
import com.DatabaseProduct.book.proj.CartDTO;
import com.DatabaseProduct.book.proj.OrderDAO;
import com.DatabaseProduct.book.proj.OrderDTO;
import com.DatabaseProduct.book.proj.OrderListDAO;

public class OrderDetailCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO mao = MemberDAO.getMemberDAO();
		MemberDTO mto = mao.infoDAO((String)session.getAttribute("id"));

		int order_no = Integer.parseInt(request.getParameter("no"));
		
		request.setAttribute("memberInfo", mto);
		
		OrderListDAO listdao = OrderListDAO.getOrderListDAO();
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		
		list = listdao.selectListDAO(order_no, mto.getNo());
		request.setAttribute("orderList", list);
		
		OrderDTO dto = new OrderDTO();
		OrderDAO dao = new OrderDAO();
		
		dto = dao.searchlistDAO(order_no, mto.getNo());

		request.setAttribute("orderdto", dto);
		request.setAttribute("sub-title", "주문상세");
	}

}
