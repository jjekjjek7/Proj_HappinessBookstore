package com.Command.Admin.book.proj;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseMember.book.proj.MemberDAO;
import com.DatabaseMember.book.proj.MemberDTO;

public class AdminMemberCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		ArrayList<MemberDTO> list = null;
		int curPage = 1;
		
		if(request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		String search = "id";
		String search_value = "";
		
		if(request.getParameter("search_value") == null || request.getParameter("search_value").equals("")) {
			list = dao.listDAO(curPage);
		} else {
			search = request.getParameter("search");
			search_value = request.getParameter("search_value");
			
			list = dao.searchDAO(curPage, search, search_value);
		}
		request.setAttribute("boardCnt", dao.boardCnt(search, search_value));
		request.setAttribute("memberInfo", list);
		request.setAttribute("sub_title", "회원관리");
	}

}
