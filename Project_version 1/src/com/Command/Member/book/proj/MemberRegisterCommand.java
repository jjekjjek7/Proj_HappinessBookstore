package com.Command.Member.book.proj;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;

public class MemberRegisterCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPw(request.getParameter("pw"));
		
		String birth = request.getParameter("birthYear")+" "+request.getParameter("birthMonth")+" "+request.getParameter("birthDay");
		dto.setBirth(birth);
		dto.setEmail(request.getParameter("email"));
		
		String phone = request.getParameter("firstPhone")+" "+request.getParameter("midPhone")+" "+request.getParameter("lastPhone");
		dto.setPhone(phone);
		
		int result = dao.insertDAO(dto);
		
		if(result > 0) {
			request.setAttribute("errMSG", "가입이 완료되었습니다.");
			request.setAttribute("url", "/Project/index.do");
		} else {
			request.setAttribute("errMSG", "에러가 발생했습니다. 다시 시도해주세요.");
			request.setAttribute("url", "/Project/index.do");
		}
		
	}

}
