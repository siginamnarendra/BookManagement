package com.bookService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookService.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
