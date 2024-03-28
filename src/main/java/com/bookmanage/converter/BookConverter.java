package com.bookmanage.converter;

import com.bookmanage.dto.BookDTO;
import com.bookmanage.entity.BookEntity;

import org.springframework.stereotype.Component;

@Component
public class BookConverter {
	public BookDTO toDto(BookEntity BookEntity) {
		BookDTO dto = new BookDTO();
		dto.setId(BookEntity.getId());
		dto.setTitle(BookEntity.getTitle());
		dto.setAuthor(BookEntity.getAuthor());
		dto.setContent(BookEntity.getContent());
		dto.setImageUrl(BookEntity.getImageUrl());
		dto.setPrice(BookEntity.getPrice());
		dto.setQuantityIntStock(BookEntity.getQuantityInStock());

		return dto;
	}

	public BookEntity toEntity(BookDTO bookDTO) {
		BookEntity book = new BookEntity();
		book.setId(bookDTO.getId());
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setContent(bookDTO.getContent());
		book.setImageUrl(bookDTO.getImageUrl());
		book.setPrice(bookDTO.getPrice());
		book.setQuantityInStock(bookDTO.getQuantityIntStock());

		return book;
	}
}
