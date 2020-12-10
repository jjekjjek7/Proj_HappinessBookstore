package com.Command.Product.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;
import com.DatabaseProduct.book.proj.CartDAO;
import com.DatabaseProduct.book.proj.CartDTO;

public class InsertCartCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		String isbn = request.getParameter("isbn");
		
		if(id != null) {
			MemberDAO mao = MemberDAO.getMemberDAO();
			MemberDTO mto = mao.infoDAO(id);
			
			CartDAO dao = CartDAO.getCartDAO();
			CartDTO dto = new CartDTO();
			
			dto.setTitle(request.getParameter("title"));
			dto.setAuthor(request.getParameter("author"));
			dto.setCover(request.getParameter("cover"));
			dto.setPrice(Integer.parseInt(request.getParameter("price")));
			dto.setPoint(Integer.parseInt(request.getParameter("point")));
			dto.setCnt(1);
			dto.setMember_no(mto.getNo());
			dto.setIsbnNo(isbn);
			
			if(dao.chkDAO(dto.getMember_no(), dto.getIsbnNo())) {
				dao.insertDAO(dto);
				request.setAttribute("errMSG", "상품을 장바구니에 담았습니다.");
				request.setAttribute("url", "/Project/view/member/cart.do");
			} else {
				request.setAttribute("errMSG", "이미 담긴 상품입니다.");
				request.setAttribute("url", "/Project/view/product/detail.do?itemNo="+isbn);
			}
			
		} else {
			request.setAttribute("errMSG", "로그인 후 시도해주세요.");
			request.setAttribute("url", "/Project/view/member/login.do");
		}
	}

}
