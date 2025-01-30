package com.demo.library.controller;

import com.demo.library.entity.Book;
import com.demo.library.exception.UserFriendlyException;
import com.demo.library.request.BookSaveRequest;
import com.demo.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Specification<Book> spec = (root, query, cb) -> {
            if (!search.isEmpty()) {
                String likeSearch = "%" + search.toLowerCase() + "%";
                return cb.or(
                        cb.like(cb.lower(root.get("title")), likeSearch),
                        cb.like(cb.lower(root.get("author")), likeSearch)
                );
            }
            return null;
        };
        return bookService.getAllBooks(spec, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .orElseThrow(() -> new UserFriendlyException("Book not found"));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookSaveRequest request) {
        Book savedBook = bookService.addBook(request);
        return ResponseEntity.ok(savedBook);
    }
}

