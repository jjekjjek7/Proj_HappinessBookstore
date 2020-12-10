package com.Command.BoardEvent.book.proj;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.book.proj.Command;
import com.DatabaseBoard.book.proj.EventDAO;
import com.DatabaseBoard.book.proj.EventDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class EventUploadCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		File 업로드
		String path = request.getSession().getServletContext().getRealPath("/EventFile");
		String name = null;
		
		int size = 1024 * 1024 * 10; //10M - 최대 사이즈
		String[] file = new String[2];
		String[] oriFile = new String[2];
		
		try{
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			
			name  = multi.getParameter("name");
			System.out.println("이름 " + name);
			
			Enumeration files = multi.getFileNames();			// 폼 요소 중 input 태그 속성이 file로 된 파라미터의 이름들을 반환
																// upload 된 파일이 없으면 비어있는 Enumeration을 반환
			int i = 0;
			while(files.hasMoreElements()) {
				String str = (String)files.nextElement();
				file[i] = multi.getFilesystemName(str);			// 사용자가 지정해서 서버에 실제로 업로드된 파일명 반환
																// 파일명이 중복되는 경우 변경된 파일명 반환
				oriFile[i] = multi.getOriginalFileName(str);	// 사용자가 업로드한 실제 파일명을 반환.
																// 이때의 파일명은 파일 중복을 고려한 파일명 변경 전의 이름을 말한다.
				
				System.out.println("저장된 경로 " + path + " 저장된 이름 " + file[i] + " 원본이름 " + oriFile[i]);
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		Event DB
		EventDTO dto = new EventDTO();
		EventDAO dao = EventDAO.getEventDAO();
		
		String img_cover = file[0];
		String img_contents = file[1];
		
		dto.setName(name);
		dto.setImg_cover(img_cover);
		dto.setImg_contents(img_contents);
		
		System.out.println();
		
		dao.insertDAO(dto);
	}
}
