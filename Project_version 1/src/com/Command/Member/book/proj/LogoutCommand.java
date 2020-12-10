package com.Command.Member.book.proj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.book.proj.Command;

public class LogoutCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String chk = (String)session.getAttribute("chk");
		
		if(chk == null) {
			Cookie[] delCookie = request.getCookies();
			 for(int i = 0 ; delCookie != null && i < delCookie.length; i++){
			        String str = delCookie[i].getName();
			        if(str.equals("id")){
			        	delCookie[i].setMaxAge(0);
			            response.addCookie(delCookie[i]);
			            break;
			        }
			 }
		}
		session.invalidate();
	}

}
