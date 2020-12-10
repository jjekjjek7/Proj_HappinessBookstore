package com.Controller.book.proj;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Admin.book.proj.*;
import com.Command.BoardEvent.book.proj.*;
import com.Command.BoardHelp.book.proj.*;
import com.Command.Member.book.proj.*;
import com.Command.MemberChk.book.proj.*;
import com.Command.Product.book.proj.*;
import com.Command.book.proj.Command;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String commandName = request.getServletPath();
		String context = request.getContextPath();
		
		String viewPage = null;
		boolean flag = false;
		
		System.out.println(commandName);
		
		
		// main
		if(commandName.contains("/index.do")) {
			Command command = new BestCommand();
			command.excute(request, response);
			command = new newCommand();
			command.excute(request, response);
			viewPage = "index.jsp";
		}
		

		// view_admin
		else if(commandName.contains("/view_admin")) {
			if(commandName.contains("/member_List.do")) {
				Command command = new AdminMemberCommand();
				command.excute(request, response);
				viewPage = "member_List.jsp";
			} else if(commandName.contains("/member_Info.do")) {
				Command command = new AdminMemberInfoCommand();
				command.excute(request, response);
				viewPage = "member_Info.jsp";
			} else if(commandName.contains("/member_Add.do")) {
				Command command = new AdminMemberAddCommand();
				command.excute(request, response);
				viewPage = "member_Add.jsp";
			} else if(commandName.contains("/member_AddOK.do")) {
				Command command = new AdminMemberAddCommand();
				command.excute(request, response);
				viewPage = "member_List.do";
			} else if(commandName.contains("/member_Modify.do")) {
				Command command = new AdminMemberModifyCommand();
				command.excute(request, response);
				viewPage = "member_List.do";
			} else if(commandName.contains("/admin_Order_List.do")) {
				Command command = new AdminOrderListCommand();
				command.excute(request, response);
				viewPage = "admin_Order_List.jsp";
			} else if(commandName.contains("/admin_Order_Info.do")) {
				Command command = new AdminOrderInfoCommand();
				command.excute(request, response);
				viewPage = "admin_Order_Info.jsp";
			} else if(commandName.contains("/admin_Order_Modify.do")) {
				Command command = new AdminOrderModifyCommand();
				command.excute(request, response);
				viewPage = "admin_Order_List.do";
				flag = true;
			}
		}

		
		// member_chk
		else if(commandName.contains("/member_chk")) {
			if(commandName.contains("/idChkOK.do")) {
				Command command = new IDChkCommand();
				command.excute(request, response);
				viewPage = "idChkOK.jsp";
			} else if(commandName.contains("/searchIDOK.do")) {
				Command command = new SearchIdCommand();
				command.excute(request, response);
				viewPage = "searchIDOK.jsp";
			} else if(commandName.contains("/searchPWOK.do")) {
				Command command = new SearchPWCommand();
				command.excute(request, response);
				viewPage = "searchPWOK.jsp";
			} else if(commandName.contains("/changePW.do")) {
				Command command = new ChangePWCommand();
				command.excute(request, response);
				viewPage = "changePW.jsp";
			} else if(commandName.contains("/changePWOK.do")) {
				Command command = new ChangePWOKCommand();
				command.excute(request, response);
				viewPage = "../error.jsp";
			} 
		}
		
		
		// member
		else if(commandName.contains("/member")) {
			if(commandName.contains("/login.do")) {
				viewPage = "login.jsp";
			} else if(commandName.contains("/loginOK.do")) {
				Command command = new LoginCommand();
				command.excute(request, response);
				if(request.getAttribute("errMSG") == null) {
					viewPage = context+"/index.do";
					flag = true;
				} else {
					viewPage = "../error.jsp";
				}
			} else if(commandName.contains("/logout.do")) {
				Command command = new LogoutCommand();
				command.excute(request, response);
				viewPage = context+"/index.do";
				flag = true;
			} else if(commandName.contains("/join.do")) {
				viewPage = "join.jsp";
			} else if(commandName.contains("/memberResister.do")) {
				Command command = new MemberRegisterCommand();
				command.excute(request, response);
				viewPage = "../error.jsp";
			} else if(commandName.contains("/inputPW.do")) {
				viewPage = "inputPW.jsp";
			} else if(commandName.contains("/memberDelete.do")) {
				Command command = new MemberDeleteCommand();
				command.excute(request, response);
				viewPage = "../error.jsp";
			} else if(commandName.contains("/mypage.do")) {
				Command command = new MemberInfoCommand();
				command.excute(request, response);
				viewPage = "mypage.jsp";
			} else if(commandName.contains("/memberModify.do")) {
				Command command = new MemberModifyCommand();
				command.excute(request, response);
				viewPage = context+"/index.do";
				flag = true;
			} else if(commandName.contains("/cart.do")) {
				Command command = new CartCommand();
				command.excute(request, response);
				viewPage = "cart.jsp";
			} else if(commandName.contains("/cartDelete.do")) {
				Command command = new CartDeleteCommand();
				command.excute(request, response);
				viewPage = "cart.do";
				flag = true;
			} else if(commandName.contains("/order.do")) {
				Command command = new OrderCommand();
				command.excute(request, response);
				viewPage = "order.jsp";
			} else if(commandName.contains("/orderOK.do")) {
				Command command = new OrderOKCommand();
				command.excute(request, response);
				viewPage = "../error.jsp";
			} else if(commandName.contains("/order_List.do")) {
				Command command = new OrderListCommand();
				command.excute(request, response);
				viewPage = "order_List.jsp";
			} else if(commandName.contains("/order_Detail.do")) {
				Command command = new OrderDetailCommand();
				command.excute(request, response);
				viewPage = "order_Detail.jsp";
			}
		}

		
		// product
		else if(commandName.contains("/product")) {
			if(commandName.contains("/best.do")) {
				Command command = new BestCommand();
				command.excute(request, response);
				viewPage = "best.jsp";
			} else if(commandName.contains("/detail.do")) {
				Command command = new DetailCommand();
				command.excute(request, response);
				viewPage = "detail.jsp";
			} else if(commandName.contains("/insertCart.do")) {
				Command command = new InsertCartCommand();
				command.excute(request, response);
				viewPage = "../error.jsp";
			} else if(commandName.contains("/new.do")) {
				Command command = new newCommand();
				command.excute(request, response);
				viewPage = "new.jsp";
			} else if(commandName.contains("/search.do")) {
				Command command = new SearchCommand();
				command.excute(request, response);
				viewPage = "search.jsp";
			} else if(commandName.contains("/recommand.do")) {
				Command command = new RecommandCommand();
				command.excute(request, response);
				viewPage = "recommand.jsp";
			}
		}
		
		
		// event
		else if(commandName.contains("/event")) {
			if(commandName.contains("/event.do")) {
				Command command = new EventListCommand();
				command.excute(request, response);
				viewPage = "event.jsp";
			} else if(commandName.contains("/event_content.do")) {
				Command command = new EventContentCommand();
				command.excute(request, response);
				viewPage = "event_content.jsp";
			} else if(commandName.contains("/event_upload.do")) {
				viewPage = "event_upload.jsp";
			} else if(commandName.contains("/event_uploadOK.do")) {
				Command command = new EventUploadCommand();
				command.excute(request, response);
				viewPage = "event.do";
				flag = true;
			} else if(commandName.contains("/event_update.do")) {
				Command command = new EventUpdateCommand();
				command.excute(request, response);
				viewPage = "event.do";
				flag = true;
			} else if(commandName.contains("/event_modify.do")) {
				Command command = new EventModifyCommand();
				command.excute(request, response);
				viewPage = "event.do";
				flag = true;
			} else if(commandName.contains("/event_delete.do")) {
				Command command = new EventDeleteCommand();
				command.excute(request, response);
				viewPage = "event.do";
				flag = true;
			}
		}
		
		
		// help
		else if(commandName.contains("/help")) {
			if(commandName.contains("/main.do")) {
				Command command = new HelpMainCommand();
				command.excute(request, response);
				viewPage = "main.jsp";
			} else if(commandName.contains("/help_write.do")) {
				Command command = new HelpWriteCommand();
				command.excute(request, response);
				viewPage = "help_write.jsp";
			} else if(commandName.contains("/help_writeOK.do")) {
				Command command = new HelpWriteOKCommand();
				command.excute(request, response);
				viewPage = "main.do";
				flag = true;
			} else if(commandName.contains("/help_read.do")) {
				Command command = new HelpReadCommand();
				command.excute(request, response);
				viewPage = "help_read.jsp";
			} else if(commandName.contains("/help_modify.do")) {
				Command command = new HelpModifyCommand();
				command.excute(request, response);
				viewPage = "main.do";
			} else if(commandName.contains("/help_delete.do")) {
				Command command = new HelpDeleteCommand();
				command.excute(request, response);
				viewPage = "main.do";
			} else if(commandName.contains("/comment.do")) {
				Command command = new HelpCommentCommand();
				command.excute(request, response);
				viewPage = "main.do";
				flag = true;
			}
		}
		
		
		// category
		else if(commandName.contains("/category")) {
			if(commandName.contains("/main_category.do")) {
				Command command = new MainCategoryCommand();
				command.excute(request, response);
				viewPage = "main_category.jsp";
			} else if(commandName.contains("/category.do")) {
				Command command = new CategoryCommand();
				command.excute(request, response);
				viewPage = "category.jsp";
			}
		}
		
		
		if(flag) {
			response.sendRedirect(viewPage);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}

}
