package com.cartService.client;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
import com.cartService.dto.Book;

@Service
@FeignClient(url = "http://localhost:8081/books", name = "Book-Client")
@ComponentScan
public interface BookClient {

	@GetMapping("/listBooks")
	public List<Book> getAllBooks();

	@GetMapping("/getBook/{bookId}")
	public Book getBookById(@PathVariable("bookId") int bookId);

	@GetMapping("/checkBookQuantity/{bookId}")
	public Integer checkBookQuantity(@PathVariable("bookId") int bookId);

}
