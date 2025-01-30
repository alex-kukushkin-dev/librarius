package com.demo.library.service.impl;

import com.demo.library.entity.Book;
import com.demo.library.repository.BookRepository;
import com.demo.library.request.BookSaveRequest;
import com.demo.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public Page<Book> getAllBooks(Specification<Book> spec, Pageable pageable) {
        return bookRepository.findAll(spec, pageable);
    }

    public Book addBook(BookSaveRequest request) {
        Book newBook = new Book();
        newBook.setAuthor(request.getAuthor());
        newBook.setTitle(request.getTitle());
        newBook.setAvailable(true);
        return bookRepository.save(newBook);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

}
