package com.bookmanage.service;

import java.util.List;
import com.bookmanage.dto.BookDTO;
import com.bookmanage.entity.BookEntity;
import com.bookmanage.entity.UserEntity;

public interface IBookService {
	List<BookDTO> getAllBooks();

	BookEntity createBook(BookDTO bookDTO, UserEntity user);

	BookDTO updateBook(Long id, BookDTO bookDTO);

	void deleteBook(long id);

}
