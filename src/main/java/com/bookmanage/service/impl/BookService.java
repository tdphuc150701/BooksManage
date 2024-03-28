package com.bookmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.bookmanage.converter.BookConverter;
import com.bookmanage.dto.BookDTO;
import com.bookmanage.entity.BookEntity;
import com.bookmanage.entity.UserEntity;
import com.bookmanage.repository.BookRepository;
import com.bookmanage.repository.UserRepository;
import com.bookmanage.service.IBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookConverter bookConverter;
	@Autowired
	private UserRepository userRepository;

	public List<BookDTO> getAllBooks() {
		List<BookEntity> books = bookRepository.findAll();

		return books.stream().map(bookConverter::toDto).collect(Collectors.toList());
	}

	@Override
	public BookEntity createBook(BookDTO bookDTO, UserEntity user) {
		BookEntity book = new BookEntity();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setContent(bookDTO.getContent());
		book.setImageUrl(bookDTO.getImageUrl());
		book.setPrice(bookDTO.getPrice());
		book.setQuantityInStock(bookDTO.getQuantityIntStock());
		book.setUser(user);
		return bookRepository.save(book);
	}

	@Override
	public BookDTO updateBook(Long id, BookDTO bookDTO) {
		Optional<BookEntity> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			BookEntity bookEntity = optionalBook.get();
			bookEntity.setTitle(bookDTO.getTitle());
			bookEntity.setAuthor(bookDTO.getAuthor());
			bookEntity.setContent(bookDTO.getContent());
			BookEntity updatedBook = bookRepository.save(bookEntity);
			return bookConverter.toDto(updatedBook);
		}
		return null;
	}

	@Override
	public void deleteBook(long id) {
		bookRepository.delete(id);

	}
}
