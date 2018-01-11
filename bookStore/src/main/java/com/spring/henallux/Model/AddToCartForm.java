package com.spring.henallux.Model;

public class AddToCartForm {

	private Integer numberOfBook;
	private Integer isbn;
	
	public AddToCartForm(){}
	
	public Integer getNumberOfBook() {
		return numberOfBook;
	}
	

	
	public void setNumberOfBook(Integer numberOfBook) {
		this.numberOfBook = numberOfBook;
	}
	public Integer getIsbn() {
		return isbn;
	}
	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}
	
}
