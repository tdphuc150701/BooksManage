package com.bookmanage.controller;

import java.util.List;

import com.bookmanage.converter.BookConverter;
import com.bookmanage.dto.BookDTO;
import com.bookmanage.entity.BookEntity;
import com.bookmanage.entity.UserEntity;
import com.bookmanage.repository.BookRepository;
import com.bookmanage.repository.UserRepository;
import com.bookmanage.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookConverter bookConverter;
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/api/book")
	public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		UserEntity user = userRepository.findByUsername(username);

		if (user != null) {
			BookEntity book = bookService.createBook(bookDTO, user);

			BookDTO responseBookDTO = bookConverter.toDto(book);

			return ResponseEntity.ok(responseBookDTO);
		} else {
			return ResponseEntity.badRequest().body("Error: User not found");
		}
	}

	@GetMapping("/api/book/{id}")
	public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
		BookEntity book = bookRepository.findById(id).orElse(null);

		if (book != null) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/api/books")
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		List<BookDTO> books = bookService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@PutMapping(value = "/api/book/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		BookDTO updatedBook = bookService.updateBook(id, bookDTO);
		if (updatedBook != null) {
			return new ResponseEntity<>(updatedBook, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/api/book/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
