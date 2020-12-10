package com.DatabaseBoard.book.proj;

import java.io.Serializable;

public class EventDTO implements Serializable {
	private int no;
	private String name;
	private String img_cover;
	private String img_contents;
	private int state;
	private String date;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg_cover() {
		return img_cover;
	}
	public void setImg_cover(String img_cover) {
		this.img_cover = img_cover;
	}
	public String getImg_contents() {
		return img_contents;
	}
	public void setImg_contents(String img_contents) {
		this.img_contents = img_contents;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
