package com.bookmanage.dto;

public class BookDTO {
	private Long id;
	private String title;
	private String author;
	private String content;
	private String imageUrl;
	private String price;
	private Integer quantityIntStock;

	public String getPrice() {
		return price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getQuantityIntStock() {
		return quantityIntStock;
	}

	public void setQuantityIntStock(Integer quantityIntStock) {
		this.quantityIntStock = quantityIntStock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Author
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
