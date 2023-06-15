package com.orderService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081/books", name = "Book-Client")
public interface BookClient {
	@GetMapping("/updateBook/{bookId}")
	public String updateBookQuantity(@PathVariable("bookId") Integer bookid);
}