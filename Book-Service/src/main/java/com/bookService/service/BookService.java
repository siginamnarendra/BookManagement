package com.bookService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookService.dao.BookRepository;
import com.bookService.entity.Book;
import com.customerService.exceptions.BookIdNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBookById(int id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookIdNotFoundException("Book not found with id: " + id));

	}

	public Book updateBookQuantity(int id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookIdNotFoundException("TO Update Book not found with id: " + id));
		System.out.println(book);
		int bookCount = book.getBookStockQuantity();
		book.setBookStockQuantity(--bookCount);
		return bookRepository.save(book);
	}

	public Integer checkBookQuantity(int id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookIdNotFoundException("TO update Book Quantity Bookid not found with id: " + id));

		return book.getBookStockQuantity();

	}
}
