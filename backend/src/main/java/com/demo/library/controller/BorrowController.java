package com.demo.library.controller;

import com.demo.library.entity.BorrowRecord;
import com.demo.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<?> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowService.borrowBook(userId, bookId));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowService.returnBook(userId, bookId));
    }

    @GetMapping("/borrowed-books")
    public ResponseEntity<List<BorrowRecord>> getBorrowedBooks(@RequestParam String username) {
        List<BorrowRecord> books = borrowService.getBorrowedBooks(username);
        return ResponseEntity.ok(books);
    }
}