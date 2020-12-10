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

public class OrderCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO mao = MemberDAO.getMemberDAO();
		MemberDTO mto = mao.infoDAO((String)session.getAttribute("id"));
		
		request.setAttribute("memberInfo", mto);
		
		CartDAO dao = CartDAO.getCartDAO();
		CartDTO dto = new CartDTO();
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		
		String orderValue = request.getParameter("chkArr");
		
		if(orderValue != null) {
			if(orderValue.equals("one")) {
				dto.setTitle(request.getParameter("title"));
				dto.setAuthor(request.getParameter("author"));
				dto.setCover(request.getParameter("cover"));
				dto.setPrice(Integer.parseInt(request.getParameter("price")));
				dto.setPoint(Integer.parseInt(request.getParameter("point")));
				dto.setCnt(1);
				dto.setMember_no(mto.getNo());
				dto.setIsbnNo(request.getParameter("isbn"));
				list.add(dto);
				session.setAttribute("cart", dto);
			} else {
				String[] value = orderValue.split(",");
				for(int i=0; i<value.length; i++) {
					if(!value[i].equals("")) {
						dto = dao.selectListDAO(Integer.parseInt(value[i]), mto.getNo());
						list.add(dto);
					}
				}
			}
			request.setAttribute("cartList", list);
		}
	}

}
