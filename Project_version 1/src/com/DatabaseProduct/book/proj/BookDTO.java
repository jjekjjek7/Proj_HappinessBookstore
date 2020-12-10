package com.DatabaseProduct.book.proj;

import java.io.Serializable;

public class BookDTO implements Serializable {
	private String title;				// 제목
	private String author;				// 작가
	private String coverLargeUrl;		// 표지 이미지
	private String description;			// 줄거리
	private String publisher;			// 출판사
	private String categoryName;		// 카테고리
	private String isbnNo;				// ISBN
	private String pubDate;				// 출간일
	private long customerReviewRank;	// 평점
	private long priceStandard;			// 정가
	private long priceSales;			// 판매가
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCoverLargeUrl() {
		return coverLargeUrl;
	}
	public void setCoverLargeUrl(String coverLargeUrl) {
		this.coverLargeUrl = coverLargeUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getIsbnNo() {
		return isbnNo;
	}
	public void setIsbnNo(String isbnNo) {
		this.isbnNo = isbnNo;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public long getCustomerReviewRank() {
		return customerReviewRank;
	}
	public void setCustomerReviewRank(long customerReviewRank) {
		this.customerReviewRank = customerReviewRank;
	}
	public long getPriceStandard() {
		return priceStandard;
	}
	public void setPriceStandard(long priceStandard) {
		this.priceStandard = priceStandard;
	}
	public long getPriceSales() {
		return priceSales;
	}
	public void setPriceSales(long priceSales) {
		this.priceSales = priceSales;
	}
}
