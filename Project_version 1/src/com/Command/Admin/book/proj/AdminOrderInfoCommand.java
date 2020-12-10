package com.Command.Admin.book.proj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;
import com.DatabaseProduct.book.proj.CartDTO;
import com.DatabaseProduct.book.proj.OrderDAO;
import com.DatabaseProduct.book.proj.OrderDTO;
import com.DatabaseProduct.book.proj.OrderListDAO;

public class AdminOrderInfoCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int order_no = Integer.parseInt(request.getParameter("no"));
		int member_no = Integer.parseInt(request.getParameter("member_no"));

		MemberDAO mao = MemberDAO.getMemberDAO();
		MemberDTO mto = mao.infoDAO(member_no);
		
		request.setAttribute("memberInfo", mto);
		
		OrderListDAO listdao = OrderListDAO.getOrderListDAO();
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		
		list = listdao.selectListDAO(order_no, member_no);
		request.setAttribute("orderList", list);
		
		OrderDTO dto = new OrderDTO();
		OrderDAO dao = new OrderDAO();
		
		dto = dao.searchlistDAO(order_no, mto.getNo());

		request.setAttribute("orderdto", dto);
		request.setAttribute("sub_title", "주문관리");
	}

}
