package com.Command.BoardHelp.book.proj;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.QNADAO;
import com.DatabaseBoard.book.proj.QNADTO;

public class HelpCommentCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QNADAO dao = QNADAO.getQNADAO();
		QNADTO dto = new QNADTO();
		
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setComment(request.getParameter("comment"));
		
		dao.commentDAO(dto);

//		답변 완료 메일 전송
		String email = request.getParameter("email");
		sendMail(email);
	}
	
	public void sendMail(String email) {
		// 메일 인코딩
		final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩

		String subject = "[행복서점] 답변이 등록되었습니다. ";
		String fromEmail = "happinessbookstore77@gmail.com2";
		String fromUsername = "행복서점";
		String toEmail = email; // 콤마(,)로 여러개 나열

		final String username = "happinessbookstore77@gmail.com";         
		final String password = "godqhrtjwja77";

		// 메일에 출력할 텍스트
		StringBuffer sb = new StringBuffer();
		sb.append("<p>안녕하세요 행복서점입니다.</p><br/>\n");
		sb.append("<h4>남겨주신 문의글에 답변이 등록되었습니다.</h4>\n");
		String html = sb.toString();

		// 메일 옵션 설정
		Properties props = new Properties();    
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			// 메일 서버  인증 계정 설정
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			// 메일 세션 생성
			Session session = Session.getInstance(props, auth);

			// 메일 송/수신 옵션 설정
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromUsername));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
			message.setSubject(subject);
			message.setSentDate(new Date());

			// 메일 콘텐츠 설정
			Multipart mParts = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();
			MimeBodyPart mFilePart = null;

			// 메일 콘텐츠 - 내용
			mTextPart.setText(html, bodyEncoding, "html");
			mParts.addBodyPart(mTextPart);

			// 메일 콘텐츠 설정
			message.setContent(mParts);

			// MIME 타입 설정
			MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(MailcapCmdMap);

			// 메일 발송
			Transport.send( message );
			System.out.println("send Log...");

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}

}
