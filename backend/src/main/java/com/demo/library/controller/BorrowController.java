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
    public ResponseEntity<BorrowRecord> borrowBook(@RequestParam String username, @RequestParam Long bookId) {
        BorrowRecord record = borrowService.borrowBook(username, bookId);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/return")
    public ResponseEntity<BorrowRecord> returnBook(@RequestParam Long recordId) {
        BorrowRecord record = borrowService.returnBook(recordId);
        return ResponseEntity.ok(record);
    }

    @GetMapping("/borrowed-books")
    public ResponseEntity<List<BorrowRecord>> getBorrowedBooks(@RequestParam String username) {
        List<BorrowRecord> books = borrowService.getBorrowedBooks(username);
        return ResponseEntity.ok(books);
    }
}