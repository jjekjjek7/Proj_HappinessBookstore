package com.Command.Product.book.proj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.Command.book.proj.Command;
import com.DatabaseProduct.book.proj.BookDTO;

public class CategoryCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = null;
		String[] categoryIdArr = {"38396", "38398", "38400", "38404", "56388", "38412", "38401", "38422"};
		String[] categoryName = {"소설/시/희곡", "경제/경영", "자기계발", "사회과학", "건강/취미/레저", "수험서/자격증", "컴퓨터/인터넷", "전공/대학교재"};
		try{
			String key = "ttbjjekjejjek1216001";
			String queryType = "Bestseller";
			String categoryId = request.getParameter("category");
			String urlstr = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey="+key
					+"&QueryType="+queryType
					+"&MaxResults=50&Cover=Big&CategoryId="+categoryId
					+"&start=1&SearchTarget=eBook&output=js&Version=20131101";

			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
			String result = "";
			String line;
			while((line = br.readLine()) != null) {
				result = result + line + "\n";
			}

			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(result);
			JSONArray parse_listArr = (JSONArray)obj.get("item");

			ArrayList<BookDTO> list = new ArrayList<BookDTO>();
			for (int i=0;i< parse_listArr.size();i++) {
				JSONObject weather = (JSONObject) parse_listArr.get(i);
				BookDTO dto = new BookDTO();
				dto.setTitle((String) weather.get("title"));	           			// 제목
				dto.setAuthor((String) weather.get("author"));       				// 작가
				dto.setCoverLargeUrl((String) weather.get("cover"));    			// 표지 이미지
				dto.setDescription((String) weather.get("description"));         	// 줄거리
				dto.setPublisher((String) weather.get("publisher"));     	  		// 출판사
				dto.setCategoryName((String) weather.get("categoryName"));       	// 카테고리
				dto.setIsbnNo((String) weather.get("isbn13"));						// ISBN
				dto.setPubDate((String) weather.get("pubDate"));					// 출간일
				dto.setCustomerReviewRank((long) weather.get("customerReviewRank"));	// 평점
				dto.setPriceStandard((long) weather.get("priceStandard"));      	// 정가
				dto.setPriceSales((long) weather.get("priceSales"));      			// 판매가

				list.add(dto);
			}

			br.close();
			
			for(int i=0; i<categoryIdArr.length; i++) {
				if(categoryId.equals(categoryIdArr[i])) {
					request.setAttribute("categoryName", categoryName[i]);
					break;
				}
			}
			
			request.setAttribute("categoryList", list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
