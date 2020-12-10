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
import com.DatabaseProduct.book.proj.CartDAO;
import com.DatabaseProduct.book.proj.CartDTO;
import com.DatabaseProduct.book.proj.OrderDAO;
import com.DatabaseProduct.book.proj.OrderDTO;
import com.DatabaseProduct.book.proj.OrderListDAO;

public class OrderOKCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDTO dto = new OrderDTO();
		OrderDAO dao = new OrderDAO();
		OrderListDAO dao_list = new OrderListDAO();
		
//		member: 구매자 정보
		HttpSession session = request.getSession();
		MemberDAO mao = MemberDAO.getMemberDAO();
		MemberDTO mto = mao.infoDAO((String)session.getAttribute("id"));
		
		request.setAttribute("memberInfo", mto);
		
//		cart: 구매한 책 정보
		CartDAO cao = CartDAO.getCartDAO();
		CartDTO cto = new CartDTO();
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		
		String orderValue = request.getParameter("chkArr");
		String[] value = null;
		
		if(orderValue != null) {
			if(orderValue.equals("one")) {
				cto = (CartDTO)session.getAttribute("cart");
				list.add(cto);
			} else {
				value = orderValue.split(",");
				for(int i=0; i<value.length; i++) {
					if(!value[i].equals("")) {
						cto = cao.selectListDAO(Integer.parseInt(value[i]), mto.getNo());
						list.add(cto);
					}
				}
			}
			request.setAttribute("cartList", list);
		}
		
//		order: 주문 정보
		String title = list.get(0).getTitle();
		if(list.size()>1) {
			title += " 외 "+(list.size()-1)+"권";
		}
		dto.setTitle(title);
		dto.setPayment_type(Integer.parseInt(request.getParameter("payment_type")));
		switch (dto.getPayment_type()) {
		case 1:
			dto.setCard(null);
			break;
		case 2:
			dto.setCard("check");
			break;
		case 3:
			dto.setCard("credit");
			break;
		}
		
		dto.setMember_no(mto.getNo());
		dto.setName(mto.getName());
		dto.setPhone(mto.getPhone());
		dto.setEmail(mto.getEmail());
		
		if(dto.getPayment_type() == 1) dto.setState(0);
		else dto.setState(1);
		
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dto.setPoint(Integer.parseInt(request.getParameter("point")));
		
		dao.insertDAO(dto);
		
//		orderList: 책 정보들
		int order_no = dao.maxNO();
		
		for(int i=0; i<list.size(); i++) {
			cto = list.get(i);
			cto.setOrder_no(order_no);
			dao_list.insertDAO(cto);
		}
		
//		cart: 장바구니 삭제
		if(value != null) {
			for(int i=0; i<value.length; i++) {
				cao.deleteDAO(Integer.parseInt(value[i]));
			}
		}
		
//		member point
		int input_point = Integer.parseInt(request.getParameter("point_input"));
		int get_point = dto.getPoint();
		int point = get_point-input_point;
		
		mao.updatePoint(mto.getNo(), point);
		
		request.setAttribute("errMSG", "상품 구매가 완료되었습니다.");
		request.setAttribute("url", "/Project/index.do");
	}

}
