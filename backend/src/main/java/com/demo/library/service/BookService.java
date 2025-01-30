package com.demo.library.service;

import com.demo.library.entity.Book;
import com.demo.library.request.BookSaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;


public interface BookService {
    Book addBook(BookSaveRequest request);
    Optional<Book> findById(Long id);
    void updateBook(Book book);
    Page<Book> getAllBooks(Specification<Book> spec, Pageable pageable);
}